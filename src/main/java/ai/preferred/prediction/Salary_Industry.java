package ai.preferred.prediction;

import ai.preferred.regression.*;
import ai.preferred.regression.pe.Partition;
import ai.preferred.regression.pe.ProjectColumns;
import ai.preferred.regression.pe.RemoveColumn;
import ai.preferred.regression.pe.SwapColumns;
import ai.preferred.regression.pe.custom.CustomEncodeTextAsFrequency;

public class Salary_Industry {
    public static void main(String[] args) {
        Shell.reset();

        //Moving salary to first col
        Shell.run(SwapColumns.class,"-i data_salary.csv -o industryDataV1.csv -x 0 -y 8");
        //Moving Type to 2nd col
        Shell.run(SwapColumns.class,"-i industryDataV1.csv -o industryDataV2.csv -x 1 -y 3");
        Shell.run(RemoveColumn.class,"-i industryDataV2.csv -o industryDataV3.csv -c 2");
        Shell.run(RemoveColumn.class,"-i industryDataV3.csv -o industryDataV4.csv -c 2");
        Shell.run(RemoveColumn.class,"-i industryDataV4.csv -o industryDataV5.csv -c 2");
        Shell.run(RemoveColumn.class,"-i industryDataV5.csv -o industryDataV6.csv -c 2");
        Shell.run(RemoveColumn.class,"-i industryDataV6.csv -o industryDataV7.csv -c 2");
        Shell.run(RemoveColumn.class,"-i industryDataV7.csv -o industryDataV8.csv -c 2");
        Shell.run(RemoveColumn.class,"-i industryDataV8.csv -o industryDataV9.csv -c 2");

        //Splitting | as well as " ";
        Shell.run(CustomEncodeTextAsFrequency.class, "-i industryDataV9.csv -o industryDataV10.csv -c 1");

        //ALL INDUSTRIES
        Shell.run(Partition.class,"-i industryDataV10.csv -o trainingIndustry.csv -p 0.8");
        Shell.run(Partition.class,"-i industryDataV10.csv -o testingIndustry.csv -p 0.8 -e");

        System.out.println(" ==== ALL INDUSTRIES ===");
        Shell.run(TrainLinearRegression.class,"-i trainingIndustry.csv -m temp/trainingIndustry_model.model -r 10");
        Shell.run(PrintRegression.class,"-i trainingIndustry.csv -m temp/trainingIndustry_model.model");
        Shell.run(EvaluateRegression.class,"-i testingIndustry.csv -s trainingIndustry.csv -m temp/trainingIndustry_model.model");
        System.out.println(" ==== ALL INDUSTRIES ===");

        //UI/UX JOBS ONLY
        Shell.run(ProjectColumns.class,"-i industryDataV10.csv -o salary_uxui.csv -c meanSalary WORD:ui/ux");
        Shell.run(Partition.class,"-i salary_uxui.csv -o trainingUXUI.csv -p 0.8");
        Shell.run(Partition.class,"-i salary_uxui.csv -o testingUXUI.csv -p 0.8 -e");

        System.out.println(" ==== FULL TIME JOB ONLY ===");
        Shell.run(TrainLinearRegression.class, "-i trainingUXUI.csv -m temp/salary_uxui_model.model");
        Shell.run(EvaluateRegression.class,"-i testingUXUI.csv -s trainingUXUI.csv -m temp/salary_uxui_model.model");
        System.out.println(" ==== FULL TIME JOB ONLY ===");



        //USE THIS FOR SCREENSHOT - WITH SKILL = HIGHER MEAN SALARY
//        Shell.run(TrainLinearRegression.class, "-i salary_uxui.csv -m temp/salary_uxui_model.model");
        Shell.run(PrintRegression.class,"-i salary_uxui.csv -m temp/salary_uxui_model.model");
        Shell.run(PlotLinearRegression.class,"-i salary_uxui.csv -m temp/salary_uxui_model.model");


//        //Salary against various industries
//        Shell.run(ProjectColumns.class,"-i trainingIndustry.csv -o salary_analytics.csv -c meanSalary WORD:analytics ");
//        Shell.run(ProjectColumns.class,"-i trainingIndustry.csv -o salary_architect.csv -c meanSalary WORD:architect ");
//        Shell.run(ProjectColumns.class,"-i trainingIndustry.csv -o salary_marketing.csv -c meanSalary WORD:marketing");
//        Shell.run(ProjectColumns.class,"-i trainingIndustry.csv -o salary_design.csv -c meanSalary WORD:design ");
//        Shell.run(ProjectColumns.class,"-i trainingIndustry.csv -o salary_editorial.csv -c meanSalary WORD:editorial");
//        Shell.run(ProjectColumns.class,"-i trainingIndustry.csv -o salary_investment.csv -c meanSalary WORD:investment");
//

        //Training model for individual industries
//        Shell.run(TrainLinearRegression.class, "-i salary_analytics.csv -m temp/salary_analytics_model.model");
//        Shell.run(PrintRegression.class,"-i salary_analytics.csv -m temp/salary_analytics_model.model");
//        Shell.run(PlotLinearRegression.class,"-i salary_analytics.csv -m temp/salary_analytics_model.model");

//        Shell.run(TrainLinearRegression.class, "-i salary_architect.csv -m temp/salary_architect_model.model");
//        Shell.run(PrintRegression.class,"-i salary_architect.csv -m temp/salary_architect_model.model");
//        Shell.run(PlotLinearRegression.class,"-i salary_architect.csv -m temp/salary_architect_model.model");

//        Shell.run(TrainLinearRegression.class, "-i salary_marketing.csv -m temp/salary_marketing_model.model");
//        Shell.run(PrintRegression.class,"-i salary_marketing.csv -m temp/salary_marketing_model.model");
//        Shell.run(PlotLinearRegression.class,"-i salary_marketing.csv -m temp/salary_marketing_model.model");

//        Shell.run(TrainLinearRegression.class, "-i salary_design.csv -m temp/salary_design_model.model");
//        Shell.run(PrintRegression.class,"-i salary_design.csv -m temp/salary_design_model.model");
//        Shell.run(PlotLinearRegression.class,"-i salary_design.csv -m temp/salary_design_model.model");

//        Shell.run(TrainLinearRegression.class, "-i salary_editorial.csv -m temp/salary_editorial_model.model");
//        Shell.run(PrintRegression.class,"-i salary_editorial.csv -m temp/salary_editorial_model.model");
//        Shell.run(PlotLinearRegression.class,"-i salary_editorial.csv -m temp/salary_editorial_model.model");

//        Shell.run(TrainLinearRegression.class, "-i salary_investment.csv -m temp/salary_investment_model.model");
//        Shell.run(PrintRegression.class,"-i salary_investment.csv -m temp/salary_investment_model.model");
//        Shell.run(PlotLinearRegression.class,"-i salary_investment.csv -m temp/salary_investment_model.model");


    }
}
