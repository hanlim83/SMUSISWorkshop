package ai.preferred.prediction;

import ai.preferred.regression.EvaluateRegression;
import ai.preferred.regression.PrintRegression;
import ai.preferred.regression.Shell;
import ai.preferred.regression.TrainLinearRegression;
import ai.preferred.regression.pe.*;

public class OneHotJobCountry {
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
        //Evaluating Regression For Overall
        Shell.run(TrainLinearRegression.class, "-i data_countries_oneHot_training.csv -m jobCountries.model");
        System.out.println(" ==== For Overall Countries ==== ");
        Shell.run(EvaluateRegression.class, "-s data_countries_oneHot_training.csv -m jobCountries.model -i data_countries_oneHot_testing.csv");
        //Spilt into individual Countries
        Shell.run(SelectEquals.class, "-i data_countries_oneHot_Shuffled.csv -o data_Singapore.csv -c 4 -e 1");
        Shell.run(SelectEquals.class, "-i data_countries_oneHot_Shuffled.csv -o data_Philippines.csv -c 3 -e 1");
        Shell.run(SelectEquals.class, "-i data_countries_oneHot_Shuffled.csv -o data_Malaysia.csv -c 2 -e 1");
        Shell.run(SelectEquals.class, "-i data_countries_oneHot_Shuffled.csv -o data_Indonesia.csv -c 1 -e 1");
        //Splitting into Training and testing data sets
        Shell.run(Partition.class, "-i data_Singapore.csv -o data_Singapore_training.csv");
        Shell.run(Partition.class, "-i data_Singapore.csv -o data_Singapore_testing.csv -e");
        Shell.run(Partition.class, "-i data_Philippines.csv -o data_Philippines_training.csv");
        Shell.run(Partition.class, "-i data_Philippines.csv -o data_Philippines_testing.csv -e");
        Shell.run(Partition.class, "-i data_Malaysia.csv -o data_Malaysia_training.csv");
        Shell.run(Partition.class, "-i data_Malaysia.csv -o data_Malaysia_testing.csv -e");
        Shell.run(Partition.class, "-i data_Indonesia.csv -o data_Indonesia_training.csv");
        Shell.run(Partition.class, "-i data_Indonesia.csv -o data_Indonesia_testing.csv -e");
        //Printing Regression and Plotting Regression per Country
        System.out.println(" ==== For Singapore ==== ");
        Shell.run(TrainLinearRegression.class, "-i data_Singapore_training.csv -m jobCountriesSingapore.model");
        Shell.run(EvaluateRegression.class, "-s data_Singapore_training.csv -m jobCountriesSingapore.model -i data_Singapore_testing.csv");
        System.out.println("[FULL-SET]");
        Shell.run(TrainLinearRegression.class, "-i data_Singapore.csv -m jobCountriesSingapore.model");
        Shell.run(PrintRegression.class, "-i data_Singapore.csv -m jobCountriesSingapore.model");
//        Shell.run(PlotLinearRegression.class,"-i data_Singapore.csv -m jobCountriesSingapore.model -n Singapore");
        System.out.println(" ==== For Philippines ==== ");
        Shell.run(TrainLinearRegression.class, "-i data_Philippines_training.csv -m jobCountriesPhilippines.model");
        Shell.run(EvaluateRegression.class, "-s data_Philippines_training.csv -m jobCountriesPhilippines.model -i data_Philippines_testing.csv");
        System.out.println("[FULL-SET]");
        Shell.run(TrainLinearRegression.class, "-i data_Philippines.csv -m jobCountriesPhilippines.model");
        Shell.run(PrintRegression.class, "-i data_Philippines.csv -m jobCountriesPhilippines.model");
//        Shell.run(PlotLinearRegression.class,"-i data_Philippines.csv -m jobCountriesPhilippines.model -n Philippines");
        System.out.println(" ==== For Malaysia ==== ");
        Shell.run(TrainLinearRegression.class, "-i data_Malaysia_training.csv -m jobCountriesMalaysia.model");
        Shell.run(EvaluateRegression.class, "-s data_Malaysia_training.csv -m jobCountriesMalaysia.model -i data_Malaysia_testing.csv");
        System.out.println("[FULL-SET]");
        Shell.run(TrainLinearRegression.class, "-i data_Malaysia.csv -m jobCountriesMalaysia.model");
        Shell.run(PrintRegression.class, "-i data_Malaysia.csv -m jobCountriesMalaysia.model");
//        Shell.run(PlotLinearRegression.class,"-i data_Malaysia.csv -m jobCountriesMalaysia.model -n Malaysia");
        System.out.println(" ==== For Indonesia ==== ");
        Shell.run(TrainLinearRegression.class, "-i data_Indonesia_training.csv -m jobCountriesIndonesia.model");
        Shell.run(EvaluateRegression.class, "-s data_Indonesia_training.csv -m jobCountriesIndonesia.model -i data_Indonesia_testing.csv");
        System.out.println("[FULL-SET]");
        Shell.run(TrainLinearRegression.class, "-i data_Indonesia.csv -m jobCountriesIndonesia.model");
        Shell.run(PrintRegression.class, "-i data_Indonesia.csv -m jobCountriesIndonesia.model");
//        Shell.run(PlotLinearRegression.class,"-i data_Indonesia.csv -m jobCountriesIndonesia.model -n Indonesia");
    }
}
