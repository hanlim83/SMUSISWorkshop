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

package ai.preferred.regression;

import ai.preferred.regression.io.ARFFDataReader;
import org.kohsuke.args4j.Option;
import weka.classifiers.Classifier;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.Logistic;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.RemoveUseless;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.BitSet;

public class PrintRegression extends Command {

    @Option(name = "-i", aliases = {"--input"}, usage = "the path to the input CSV file", required = true)
    private File input;

    @Option(name = "-m", aliases = {"--model"}, usage = "the path to the model file", required = true)
    private File model;

    @Option(name = "-h", aliases = {"--header"}, usage = "specifies if the input CSV files have headers")
    private boolean header = true;

    public static void main(String[] args) {
        parseArgsAndRun(PrintRegression.class, args);
    }

    private void printSignature(ArrayList<Attribute> signature) {
        for (int i = 1; i < signature.size(); i++) {
            System.out.print(signature.get(i).name() + "\t");
        }
        System.out.println("Bias");
    }

    @Override
    protected void exec() throws Exception {
        System.out.println();
        try (final FileInputStream stream = new FileInputStream(model)) {
            final Classifier classifier = (Classifier) SerializationHelper.read(stream);

            if (classifier instanceof LinearRegression) {
                final ARFFDataReader reader = new ARFFDataReader(input, false, header);
                final Instances instances = preprocess(reader.read(input));

                final BitSet ignore = new BitSet(instances.numAttributes());
                for (int i = 0; i < instances.numAttributes(); i++) {
                    if (i != instances.classIndex()) {
                        if (Math.sqrt(instances.variance(i)) == 0) {
                            ignore.set(i);
                        }
                    }
                }

                final double[] w = ((LinearRegression) classifier).coefficients();
                System.out.printf("%-20s W", "FEATURE");
                System.out.println();
                for (int i = 1; i < instances.numAttributes(); i++) {
                    if (ignore.get(i)) {
                        continue;
                    }
                    System.out.printf("%-20s %.6f", instances.attribute(i).name(), w[i]);
                    System.out.println();
                }
                System.out.printf("%-20s %.6f", "Bias", w[instances.numAttributes()]);
                System.out.println();
            } else if (classifier instanceof Logistic) {
                final ARFFDataReader reader = new ARFFDataReader(input, true, header);
                final Instances instances = preprocess(reader.read(input));

                final double[][] w = ((Logistic) classifier).coefficients();
                for (int i = 0; i < instances.classAttribute().numValues(); i++) {
                    System.out.printf("%s %s", "CLASS[" + i + "] =", instances.classAttribute().value(i));
                    System.out.println();
                }
                System.out.println();

                System.out.printf("%-20s W", "FEATURE");
                System.out.println();
                for (int i = 1; i < instances.numAttributes(); i++) {
                    System.out.printf("%-20s %.6f", instances.attribute(i).name(), w[i][0]);
                    System.out.println();
                }
                System.out.printf("%-20s %.6f", "Bias", w[0][0]);
                System.out.println();
            } else {
                throw new RuntimeException("We can process only regression models!");
            }
        }
    }

    private Instances preprocess(Instances instances) throws Exception {
        final ReplaceMissingValues replaceMissingValues = new ReplaceMissingValues();
        replaceMissingValues.setInputFormat(instances);
        instances = Filter.useFilter(instances, replaceMissingValues);

        final RemoveUseless removeUseless = new RemoveUseless();
        removeUseless.setInputFormat(instances);
        instances = Filter.useFilter(instances, removeUseless);

        return instances;
    }

}
