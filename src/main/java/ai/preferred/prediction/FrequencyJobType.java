package ai.preferred.prediction;

import ai.preferred.regression.Shell;
import ai.preferred.regression.pe.EncodeTextAsFrequency;
import ai.preferred.regression.pe.ProjectColumns;
import ai.preferred.regression.pe.RemoveColumn;
import ai.preferred.regression.pe.SwapColumns;
import ai.preferred.regression.pe.custom.EncodeTextAsFrequencyNoWhitespace;

public class FrequencyJobType {
    public static void main(String[] args) {
        Shell.reset();
        Shell.help(EncodeTextAsFrequencyNoWhitespace.class);

        //Moving salary to first col
        Shell.run(SwapColumns.class,"-i data_salary.csv -o updatedDataV1.csv -x 0 -y 3");
        //Moving Type to 2nd col
        Shell.run(SwapColumns.class,"-i updatedDataV1.csv -o updatedDataV2.csv -x 1 -y 5");
        //Removing remaining columns
        Shell.run(RemoveColumn.class,"-i updatedDataV2.csv -o updatedDataV3.csv -c 2");
        Shell.run(RemoveColumn.class,"-i updatedDataV3.csv -o updatedDataV4.csv -c 2");
        Shell.run(RemoveColumn.class,"-i updatedDataV4.csv -o updatedDataV5.csv -c 2");
        Shell.run(RemoveColumn.class,"-i updatedDataV5.csv -o updatedDataV6.csv -c 2");
        Shell.run(RemoveColumn.class,"-i updatedDataV6.csv -o updatedDataV7.csv -c 2");

        //Splitting | as well as " ";
        Shell.run(EncodeTextAsFrequencyNoWhitespace.class,"-i updatedDataV7.csv -o updatedDataV8.csv -c 1");

        //Salary -> Co-Founder
        Shell.run(ProjectColumns.class,"-i updatedDataV8.csv -o salary_cofounder.csv -c salary WORD:co-founder ");

        Shell.run(ProjectColumns.class,"-i updatedDataV8.csv -o salary_contract.csv -c salary WORD:contract ");

        Shell.run(ProjectColumns.class,"-i updatedDataV8.csv -o salary_freelance.csv -c salary WORD:freelance ");

        Shell.run(ProjectColumns.class,"-i updatedDataV8.csv -o salary_fulltime.csv -c salary WORD:full-time ");

        Shell.run(ProjectColumns.class,"-i updatedDataV8.csv -o salary_internship.csv -c salary WORD:internship ");

        Shell.run(ProjectColumns.class,"-i updatedDataV8.csv -o salary_parttime.csv -c salary WORD:part-time ");

        Shell.run(ProjectColumns.class,"-i updatedDataV8.csv -o salary_temporary.csv -c salary WORD:temporary ");

//        Shell.run(SwapColumns.class,"-i data.csv -o salaryFirstCol.csv ")
//        Shell.run(ProjectColumns.class,"-i data.csv -o JobType.csv ")
    }
}
