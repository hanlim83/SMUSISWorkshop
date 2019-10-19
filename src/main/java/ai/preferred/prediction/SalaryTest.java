package ai.preferred.prediction;

import ai.preferred.regression.Shell;
import ai.preferred.regression.pe.custom.SalaryConversion;

public class SalaryTest {
    public static void main(String[] args) {
        Shell.reset();
        Shell.run(SalaryConversion.class, "-i data.csv -o data_salary.csv -c 3");
    }
}
