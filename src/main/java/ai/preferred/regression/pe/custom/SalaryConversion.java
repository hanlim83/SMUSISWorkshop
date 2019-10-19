package ai.preferred.regression.pe.custom;

import ai.preferred.regression.io.CSVInputData;
import ai.preferred.regression.pe.ProcessingElement;
import org.apache.commons.csv.CSVPrinter;
import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class SalaryConversion extends ProcessingElement {

    private static final Logger LOGGER = LoggerFactory.getLogger(SalaryConversion.class);

    private static final String IDR = "IDR";

    private static final String USD = "USD";

    private static final double IDR_Rate = 0.000097;

    private static final double USD_Rate = 1.36;

    @Option(name = "-c", aliases = {"--column"}, usage = "the index of the salary column", required = true)
    private int column;

    public static void main(String[] args) {
        parseArgsAndRun(SalaryConversion.class, args);
    }

    @Override
    protected void process(CSVInputData data, CSVPrinter printer) throws Exception {
        if (data.hasHeader()) {
            printer.printRecord(data.getHeader());
        }

        for (final ArrayList<String> record : data) {
            if (record.get(column).contains(IDR) || record.get(column).contains(USD)) {
                String currentData = record.get(column);
                String[] dataArray = currentData.split("-");
                dataArray[0] = dataArray[0].trim();
                dataArray[1] = dataArray[1].substring(0, dataArray[1].length() - 3);
                dataArray[1] = dataArray[1].trim();
                double lowerSalaryDouble = Double.parseDouble(dataArray[0]);
                double higherSalaryDouble = Double.parseDouble(dataArray[1]);
                int lowerSalaryInteger = 0, higherSalaryInteger = 0;
                if (record.get(column).contains(IDR)) {
                    lowerSalaryInteger = (int) Math.round(lowerSalaryDouble * IDR_Rate);
                    higherSalaryInteger = (int) Math.round(higherSalaryDouble * IDR_Rate);
                } else if (record.get(column).contains(USD)) {
                    lowerSalaryInteger = (int) Math.round(lowerSalaryDouble * USD_Rate);
                    higherSalaryInteger = (int) Math.round(higherSalaryDouble * USD_Rate);
                }
                record.set(column, lowerSalaryInteger + " - " + higherSalaryInteger);
                printer.printRecord(record);
            } else if (!record.get(column).contains("Negotiated")) {
                record.set(column, record.get(column).substring(0, record.get(column).length() - 3));
                printer.printRecord(record);
            }
        }
    }
}
