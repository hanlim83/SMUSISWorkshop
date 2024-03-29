package ai.preferred.prediction;

import ai.preferred.regression.*;
import ai.preferred.regression.pe.EncodeTextAsFrequency;
import ai.preferred.regression.pe.Partition;
import ai.preferred.regression.pe.ProjectColumns;
import ai.preferred.regression.pe.custom.CustomEncodeTextAsFrequency;


public class Skills {

    public static void main(final String[] args) {
        Shell.reset();

        Shell.run(CustomEncodeTextAsFrequency.class, "-i data_salary.csv -o data_skills.csv -c 5");
        Shell.run(CustomEncodeTextAsFrequency.class, "-i data_skills.csv -o data_skills_jobtype.csv -c 4");
        Shell.run(ProjectColumns.class, "-i data_skills.csv -o data.java.csv -c meanSalary WORD:java");
        Shell.run(ProjectColumns.class, "-i data_skills.csv -o data.javascript.csv -c meanSalary WORD:javascript");
        Shell.run(ProjectColumns.class, "-i data_skills.csv -o data.python.csv -c meanSalary WORD:python");
        Shell.run(ProjectColumns.class, "-i data_skills.csv -o data.html.csv -c meanSalary WORD:html");
        Shell.run(ProjectColumns.class, "-i data_skills.csv -o data.css.csv -c meanSalary WORD:css");
        Shell.run(ProjectColumns.class, "-i data_skills.csv -o data.php.csv -c meanSalary WORD:php");
        Shell.run(ProjectColumns.class, "-i data_skills.csv -o data.excel.csv -c meanSalary WORD:excel");
        Shell.run(ProjectColumns.class, "-i data_skills.csv -o data.sales.csv -c meanSalary WORD:sales");

        Shell.run(ProjectColumns.class, "-i data_skills_jobtype.csv -o data.manyskills_fulltime.csv -c meanSalary WORD:excel WORD:php WORD:css WORD:html WORD:python WORD:javascript WORD:java WORD:full-time");

        //Training model for individual job type
        Shell.run(TrainLinearRegression.class, "-i data.java.csv -m temp/salary.java_model.model");
        Shell.run(PrintRegression.class,"-i data.java.csv -m temp/salary.java_model.model");
        Shell.run(PlotLinearRegression.class,"-i data.java.csv -m temp/salary.java_model.model");

        Shell.run(TrainLinearRegression.class, "-i data.javascript.csv -m temp/salary.javascript_model.model");
        Shell.run(PrintRegression.class,"-i data.javascript.csv -m temp/salary.javascript_model.model");
        Shell.run(PlotLinearRegression.class,"-i data.javascript.csv -m temp/salary.javascript_model.model");

        Shell.run(TrainLinearRegression.class, "-i data.python.csv -m temp/salary.python_model.model");
        Shell.run(PrintRegression.class,"-i data.python.csv -m temp/salary.python_model.model");
        Shell.run(PlotLinearRegression.class,"-i data.python.csv -m temp/salary.python_model.model");

        Shell.run(TrainLinearRegression.class, "-i data.html.csv -m temp/salary.html_model.model");
        Shell.run(PrintRegression.class,"-i data.html.csv -m temp/salary.html_model.model");
        Shell.run(PlotLinearRegression.class,"-i data.html.csv -m temp/salary.html_model.model");

        Shell.run(TrainLinearRegression.class, "-i data.css.csv -m temp/salary.css_model.model");
        Shell.run(PrintRegression.class,"-i data.css.csv -m temp/salary.css_model.model");
        Shell.run(PlotLinearRegression.class,"-i data.css.csv -m temp/salary.css_model.model");

        Shell.run(TrainLinearRegression.class, "-i data.manyskills_fulltime.csv -m temp/salary.manyskills_fulltime_model.model");
        Shell.run(PrintRegression.class,"-i data.manyskills_fulltime.csv -m temp/salary.manyskills_fulltime_model.model");
        //Shell.run(PlotLinearRegression.class,"-i data.manyskills_fulltime.csv -m temp/salary.manyskills_fulltime_model.model");



        Shell.run(Partition.class, "-i data.sales.csv -o data.sales_training.csv");
        Shell.run(Partition.class, "-i data.sales.csv -o data.sales_testing.csv -e");

        System.out.println(" ==== For sales ==== ");
        Shell.run(TrainLinearRegression.class, "-i data.sales_training.csv -m salary.sales.model");
        Shell.run(EvaluateRegression.class, "-s data.sales_training.csv -m salary.sales.model -i data.sales_testing.csv");
        System.out.println("[FULL-SET]");
        Shell.run(TrainLinearRegression.class, "-i data.sales.csv -m salary.sales.model");
        Shell.run(PrintRegression.class, "-i data.sales.csv -m salary.sales.model");
        Shell.run(PlotLinearRegression.class, "-i data.sales.csv -m salary.sales.model -n sales");

        Shell.run(TrainLinearRegression.class, "-i data.sales.csv -m temp/salary.sales_model.model");
        Shell.run(PrintRegression.class,"-i data.sales.csv -m temp/salary.sales_model.model");
        Shell.run(PlotLinearRegression.class,"-i data.sales.csv -m temp/salary.sales_model.model");
    }
}
