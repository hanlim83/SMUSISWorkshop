package ai.preferred.prediction;

import ai.preferred.regression.*;
import ai.preferred.regression.pe.Partition;
import ai.preferred.regression.pe.ProjectColumns;
import ai.preferred.regression.pe.RemoveColumn;
import ai.preferred.regression.pe.SwapColumns;
import ai.preferred.regression.pe.custom.CustomEncodeTextAsFrequency;

public class Salary_JobType {
    public static void main(String[] args) {
        Shell.reset();

        //Moving salary to first col
        Shell.run(SwapColumns.class,"-i data_salary.csv -o updatedDataV1.csv -x 0 -y 8");
        //Moving Type to 2nd col
        Shell.run(SwapColumns.class,"-i updatedDataV1.csv -o updatedDataV2.csv -x 1 -y 4");
        //Removing remaining columns
        Shell.run(RemoveColumn.class,"-i updatedDataV2.csv -o updatedDataV3.csv -c 2");
        Shell.run(RemoveColumn.class,"-i updatedDataV3.csv -o updatedDataV4.csv -c 2");
        Shell.run(RemoveColumn.class,"-i updatedDataV4.csv -o updatedDataV5.csv -c 2");
        Shell.run(RemoveColumn.class,"-i updatedDataV5.csv -o updatedDataV6.csv -c 2");
        Shell.run(RemoveColumn.class,"-i updatedDataV6.csv -o updatedDataV7.csv -c 2");
        Shell.run(RemoveColumn.class,"-i updatedDataV7.csv -o updatedDataV8.csv -c 2");
        Shell.run(RemoveColumn.class,"-i updatedDataV8.csv -o updatedDataV9.csv -c 2");

        //Splitting | as well as " ";
        Shell.run(CustomEncodeTextAsFrequency.class, "-i updatedDataV9.csv -o updatedDataV10.csv -c 1");

        //All Job Types
        Shell.run(Partition.class,"-i updatedDataV10.csv -o trainingJobType.csv -p 0.8");
        Shell.run(Partition.class,"-i updatedDataV10.csv -o testingJobType.csv -p 0.8 -e");


        System.out.println(" ==== ALL JOB TYPES ====");
        Shell.run(TrainLinearRegression.class,"-i trainingJobType.csv -m temp/trainingJobType_model.model -r 10");
        Shell.run(PrintRegression.class,"-i trainingJobType.csv -m temp/trainingJobType_model.model");
        Shell.run(EvaluateRegression.class,"-i testingJobType.csv -s trainingJobType.csv -m temp/trainingJobType_model.model");
        System.out.println(" ==== ALL JOB TYPES ====\n");

        //Full time job only
        Shell.run(ProjectColumns.class,"-i updatedDataV10.csv -o salary_fulltime.csv -c meanSalary WORD:full-time ");
        Shell.run(Partition.class,"-i salary_fulltime.csv -o trainingFullTime.csv -p 0.8");
        Shell.run(Partition.class,"-i salary_fulltime.csv -o testingFullTime.csv -p 0.8 -e");

        System.out.println(" ==== FULL TIME JOB ONLY ===");
        Shell.run(TrainLinearRegression.class,"-i trainingFullTime.csv -m temp/salary_fulltime_model.model -r 10");
        Shell.run(EvaluateRegression.class,"-i testingFullTime.csv -s trainingFullTime.csv -m temp/salary_fulltime_model.model");
        System.out.println(" ==== FULL TIME JOB ONLY ===\n");

        //USE THIS FOR SCREENSHOT - FULLTIME = HIGHER MEAN SALARY
//        Shell.run(TrainLinearRegression.class, "-i trainingFullTime.csv -m temp/salary_fulltime_model.model -r 10");
        Shell.run(PrintRegression.class,"-i trainingFullTime.csv -m temp/salary_fulltime_model.model");
        Shell.run(PlotLinearRegression.class,"-i trainingFullTime.csv -m temp/salary_fulltime_model.model");
        
        //Salary against various job type
//        Shell.run(ProjectColumns.class,"-i trainingJobType.csv -o salary_contract.csv -c meanSalary WORD:contract ");
//        Shell.run(ProjectColumns.class,"-i trainingJobType.csv -o salary_freelance.csv -c meanSalary WORD:freelance ");

//        Shell.run(ProjectColumns.class,"-i trainingJobType.csv -o salary_fulltime.csv -c meanSalary WORD:full-time ");

//        Shell.run(ProjectColumns.class,"-i trainingJobType.csv -o salary_internship.csv -c meanSalary WORD:internship ");
//        Shell.run(ProjectColumns.class,"-i trainingJobType.csv -o salary_parttime.csv -c meanSalary WORD:part-time ");
//        Shell.run(ProjectColumns.class,"-i trainingJobType.csv -o salary_temporary.csv -c meanSalary WORD:temporary ");

        //Training model for individual job type
//        Shell.run(TrainLinearRegression.class, "-i salary_contract.csv -m temp/salary_contract_model.model");
//        Shell.run(PrintRegression.class,"-i salary_contract.csv -m temp/salary_contract_model.model");
//        Shell.run(PlotLinearRegression.class,"-i salary_contract.csv -m temp/salary_contract_model.model");

//        Shell.run(TrainLinearRegression.class, "-i salary_freelance.csv -m temp/salary_freelance_model.model");
//        Shell.run(PrintRegression.class,"-i salary_freelance.csv -m temp/salary_freelance_model.model");
//        Shell.run(PlotLinearRegression.class,"-i salary_freelance.csv -m temp/salary_freelance_model.model");


//        Shell.run(TrainLinearRegression.class, "-i salary_internship.csv -m temp/salary_internship_model.model");
//        Shell.run(PrintRegression.class,"-i salary_internship.csv -m temp/salary_internship_model.model");
//        Shell.run(PlotLinearRegression.class,"-i salary_internship.csv -m temp/salary_internship_model.model");

//        Shell.run(TrainLinearRegression.class, "-i salary_parttime.csv -m temp/salary_parttime_model.model");
//        Shell.run(PrintRegression.class,"-i salary_parttime.csv -m temp/salary_parttime_model.model");
//        Shell.run(PlotLinearRegression.class,"-i salary_parttime.csv -m temp/salary_parttime_model.model");

//        Shell.run(TrainLinearRegression.class, "-i salary_temporary.csv -m temp/salary_temporary_model.model");
//        Shell.run(PrintRegression.class,"-i salary_temporary.csv -m temp/salary_temporary_model.model");
//        Shell.run(PlotLinearRegression.class,"-i salary_temporary.csv -m temp/salary_temporary_model.model");
    }
}
