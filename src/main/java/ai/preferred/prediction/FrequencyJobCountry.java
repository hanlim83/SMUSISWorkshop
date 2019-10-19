package ai.preferred.prediction;

import ai.preferred.regression.EvaluateRegression;
import ai.preferred.regression.Shell;
import ai.preferred.regression.TrainLinearRegression;
import ai.preferred.regression.pe.*;

public class FrequencyJobCountry {
    public static void main(String[] args) {
        Shell.reset();

        //Select Requried Columns
        Shell.run(ProjectColumns.class, "-i data_salary.csv -o data_countries.csv -c location meanSalary");
        //Swap Columns
        Shell.run(SwapColumns.class, "-i data_countries.csv -o data_countries_swapped.csv -x 0 -y 1");
        //One Hot on Countries
        Shell.run(EncodeValueAsOneHot.class, "-i data_countries_swapped.csv -o data_countries_oneHot.csv -c 1 -p COUNTRY:");
        //Shuffle and Split into training and testing data sets
        Shell.run(Shuffle.class, "-i data_countries_oneHot.csv -o data_countries_oneHot_Shuffled.csv");
        Shell.run(Partition.class, "-i data_countries_oneHot_Shuffled.csv -o data_countries_oneHot_training.csv");
        Shell.run(Partition.class, "-i data_countries_oneHot_Shuffled.csv -o data_countries_oneHot_testing.csv -e");
        //Build Regression
        Shell.run(TrainLinearRegression.class, "-i data_countries_oneHot_training.csv -m jobCountries.model");
        Shell.run(EvaluateRegression.class, "-s data_countries_oneHot_training.csv -m jobCountries.model -i data_countries_oneHot_testing.csv");
    }
}
