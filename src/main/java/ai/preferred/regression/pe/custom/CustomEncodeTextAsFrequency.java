/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package ai.preferred.regression.pe.custom;

import ai.preferred.regression.io.CSVInputData;
import ai.preferred.regression.io.CSVUtils;
import ai.preferred.regression.pe.ProcessingElement;
import ai.preferred.regression.pe.data.Vocabulary;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import org.apache.commons.csv.CSVPrinter;
import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Pattern;

public class CustomEncodeTextAsFrequency extends ProcessingElement {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomEncodeTextAsFrequency.class);

    @Option(name = "-c", aliases = {"--column"}, usage = "the index of the input column", required = true)
    private int column;

    @Option(name = "-s", aliases = {"--separator"}, usage = "specifies regular expression for splitting text into words")
    private String separator = " \\| ";

    @Option(name = "-n", aliases = {"--number-of-words"}, usage = "the maximum number of words to keep")
    private int numberOfWords = 1000;

    @Option(name = "-p", aliases = {"--prefix"}, usage = "the prefix of the new columns")
    private String prefix = "WORD:";

    private static <T> Comparator<Multiset.Entry<T>> getDecreasingCountComparator() {
        return (entry1, entry2) -> Integer.compare(entry2.getCount(), entry1.getCount());
    }

    private static String[] toLowerCase(String[] words) {
        final String[] result = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            result[i] = words[i].toLowerCase();
        }
        return result;
    }

    private static Multiset<String> toBagOfWords(String text, String separator) {
        final Pattern tokenizer = Pattern.compile(separator);
        String[] words;
        words = tokenizer.split(text);
        words = toLowerCase(words);
        return ImmutableMultiset.copyOf(toLowerCase(words));
    }

    public static void main(String[] args) {
        parseArgsAndRun(CustomEncodeTextAsFrequency.class, args);
    }

    private Vocabulary buildVocabulary(CSVInputData reader, int numberOfWords) {
        final Multiset<String> vocabulary = HashMultiset.create();

        for (final ArrayList<String> values : reader) {
            final String text = values.get(column);
            vocabulary.addAll(toBagOfWords(text, separator));
        }

        final ArrayList<Multiset.Entry<String>> highestCountFirst = new ArrayList<>(vocabulary.entrySet());
        highestCountFirst.sort(getDecreasingCountComparator());

        final ArrayList<String> wordsToRetain = new ArrayList<>(numberOfWords);
        for (final Multiset.Entry<String> e : highestCountFirst.subList(0, Math.min(highestCountFirst.size(), numberOfWords))) {
            wordsToRetain.add(e.getElement());
        }

        return new Vocabulary(wordsToRetain);
    }

    @Override
    protected void process(CSVInputData data, CSVPrinter printer) throws IOException {
        final Vocabulary vocabulary = buildVocabulary(data, numberOfWords);

        if (data.hasHeader()) {
            final ArrayList<String> header = data.getHeader();
            header.remove(column);
            for (final String h : vocabulary.getVocabularyList()) {
                header.add(prefix + h);
            }
            printer.printRecord(header);
        }

        for (final ArrayList<String> record : data) {
            final Multiset<String> bagOfWords = toBagOfWords(record.get(column), separator);
            final Integer[] vDocument = new Integer[vocabulary.size()];
            Arrays.fill(vDocument, 0);
            for (final Multiset.Entry<String> entry : bagOfWords.entrySet()) {
                final int index = vocabulary.getIndex(entry.getElement());
                if (index == -1) {
                    continue;
                }
                vDocument[index] = entry.getCount();
            }
            record.remove(column);
            Collections.addAll(record, CSVUtils.toStringArray(vDocument));
            printer.printRecord(record);
        }
    }

}
