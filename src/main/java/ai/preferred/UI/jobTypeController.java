package ai.preferred.UI;

import ai.preferred.regression.EvaluateRegression;
import ai.preferred.regression.PrintRegression;
import ai.preferred.regression.Shell;
import ai.preferred.regression.TrainLinearRegression;
import ai.preferred.regression.custom.PlotLinearRegression;
import ai.preferred.regression.pe.Partition;
import ai.preferred.regression.pe.ProjectColumns;
import ai.preferred.regression.pe.RemoveColumn;
import ai.preferred.regression.pe.SwapColumns;
import ai.preferred.regression.pe.custom.CustomEncodeTextAsFrequency;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class jobTypeController implements Initializable {

    private static AnchorPane rootP;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXButton contractBtn;
    @FXML
    private JFXButton freelanceBtn;
    @FXML
    private JFXButton fullTimeBtn;
    @FXML
    private JFXButton internshipBtn;
    @FXML
    private JFXButton partTimeBtn;
    @FXML
    private JFXButton temporaryBtn;
    @FXML
    private JFXSpinner runningSpinner;
    private Thread thread;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hamburger.setDisable(true);
        contractBtn.setDisable(true);
        freelanceBtn.setDisable(true);
        fullTimeBtn.setDisable(true);
        internshipBtn.setDisable(true);
        partTimeBtn.setDisable(true);
        temporaryBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(SwapColumns.class, "-i data/data_salary.csv -o data/updatedDataV1.csv -x 0 -y 8");
            Shell.run(SwapColumns.class, "-i data/updatedDataV1.csv -o data/updatedDataV2.csv -x 1 -y 4");
            Shell.run(RemoveColumn.class, "-i data/updatedDataV2.csv -o data/updatedDataV3.csv -c 2");
            Shell.run(RemoveColumn.class, "-i data/updatedDataV3.csv -o data/updatedDataV4.csv -c 2");
            Shell.run(RemoveColumn.class, "-i data/updatedDataV4.csv -o data/updatedDataV5.csv -c 2");
            Shell.run(RemoveColumn.class, "-i data/updatedDataV5.csv -o data/updatedDataV6.csv -c 2");
            Shell.run(RemoveColumn.class, "-i data/updatedDataV6.csv -o data/updatedDataV7.csv -c 2");
            Shell.run(RemoveColumn.class, "-i data/updatedDataV7.csv -o data/updatedDataV8.csv -c 2");
            Shell.run(RemoveColumn.class, "-i data/updatedDataV8.csv -o data/updatedDataV9.csv -c 2");
            Shell.run(CustomEncodeTextAsFrequency.class, "-i data/updatedDataV9.csv -o data/updatedDataV10.csv -c 1");
            Shell.run(ProjectColumns.class, "-i data/updatedDataV10.csv -o data/salary_contract.csv -c meanSalary WORD:contract ");
            Shell.run(Partition.class, "-i data/salary_contract.csv -o data/trainingContract.csv -p 0.8");
            Shell.run(Partition.class, "-i data/salary_contract.csv -o data/testingContract.csv -p 0.8 -e");
            Shell.run(ProjectColumns.class, "-i data/updatedDataV10.csv -o data/salary_freelance.csv -c meanSalary WORD:freelance ");
            Shell.run(Partition.class, "-i data/salary_freelance.csv -o data/trainingFreelance.csv -p 0.8");
            Shell.run(Partition.class, "-i data/salary_freelance.csv -o data/testingFreelance.csv -p 0.8 -e");
            Shell.run(ProjectColumns.class, "-i data/updatedDataV10.csv -o data/salary_fulltime.csv -c meanSalary WORD:full-time ");
            Shell.run(Partition.class, "-i data/salary_fulltime.csv -o data/trainingFullTime.csv -p 0.8");
            Shell.run(Partition.class, "-i data/salary_fulltime.csv -o data/testingFullTime.csv -p 0.8 -e");
            Shell.run(ProjectColumns.class, "-i data/updatedDataV10.csv -o data/salary_internship.csv -c meanSalary WORD:internship ");
            Shell.run(Partition.class, "-i data/salary_internship.csv -o data/trainingInternship.csv -p 0.8");
            Shell.run(Partition.class, "-i data/salary_internship.csv -o data/testingInternship.csv -p 0.8 -e");
            Shell.run(ProjectColumns.class, "-i data/updatedDataV10.csv -o data/salary_parttime.csv -c meanSalary WORD:part-time ");
            Shell.run(Partition.class, "-i data/salary_parttime.csv -o data/trainingPartTime.csv -p 0.8");
            Shell.run(Partition.class, "-i data/salary_parttime.csv -o data/testingPartTime.csv -p 0.8 -e");
            Shell.run(ProjectColumns.class, "-i data/updatedDataV10.csv -o data/salary_temporary.csv -c meanSalary WORD:temporary ");
            Shell.run(Partition.class, "-i data/salary_temporary.csv -o data/trainingTemporary.csv -p 0.8");
            Shell.run(Partition.class, "-i data/salary_temporary.csv -o data/testingTemporary.csv -p 0.8 -e");
            Platform.runLater(() -> {
                hamburgerBar();
                hamburger.setDisable(false);
                contractBtn.setDisable(false);
                freelanceBtn.setDisable(false);
                fullTimeBtn.setDisable(false);
                internshipBtn.setDisable(false);
                partTimeBtn.setDisable(false);
                temporaryBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generateContractRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        contractBtn.setDisable(true);
        freelanceBtn.setDisable(true);
        fullTimeBtn.setDisable(true);
        internshipBtn.setDisable(true);
        partTimeBtn.setDisable(true);
        temporaryBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(TrainLinearRegression.class, "-i data/trainingContract.csv -m model/salary_contract_model.model -r 10");
            Shell.run(EvaluateRegression.class, "-i data/testingContract.csv -s data/trainingContract.csv -m model/salary_contract_model.model");
            System.out.println("[FULL-SET]");
            Shell.run(PrintRegression.class, "-i data/salary_contract.csv -m model/salary_contract_model.model");
            Shell.run(PlotLinearRegression.class, "-i data/salary_contract.csv -m model/salary_contract_model.model -n Contract");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                contractBtn.setDisable(false);
                freelanceBtn.setDisable(false);
                fullTimeBtn.setDisable(false);
                internshipBtn.setDisable(false);
                partTimeBtn.setDisable(false);
                temporaryBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generateFreelanceRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        contractBtn.setDisable(true);
        freelanceBtn.setDisable(true);
        fullTimeBtn.setDisable(true);
        internshipBtn.setDisable(true);
        partTimeBtn.setDisable(true);
        temporaryBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(TrainLinearRegression.class, "-i data/trainingFreelance.csv -m model/salary_freelance_model.model -r 10");
            Shell.run(EvaluateRegression.class, "-i data/testingFreelance.csv -s data/trainingFreelance.csv -m model/salary_freelance_model.model");
            System.out.println("[FULL-SET]");
            Shell.run(PrintRegression.class, "-i data/salary_freelance.csv -m model/salary_freelance_model.model");
            Shell.run(PlotLinearRegression.class, "-i data/salary_freelance.csv -m model/salary_freelance_model.model -n Freelance");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                contractBtn.setDisable(false);
                freelanceBtn.setDisable(false);
                fullTimeBtn.setDisable(false);
                internshipBtn.setDisable(false);
                partTimeBtn.setDisable(false);
                temporaryBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generateFullTimeRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        contractBtn.setDisable(true);
        freelanceBtn.setDisable(true);
        fullTimeBtn.setDisable(true);
        internshipBtn.setDisable(true);
        partTimeBtn.setDisable(true);
        temporaryBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(TrainLinearRegression.class, "-i data/trainingFullTime.csv -m model/salary_fulltime_model.model -r 10");
            Shell.run(EvaluateRegression.class, "-i data/testingFullTime.csv -s data/trainingFullTime.csv -m model/salary_fulltime_model.model");
            System.out.println("[FULL-SET]");
            Shell.run(PrintRegression.class, "-i data/salary_fulltime.csv -m model/salary_fulltime_model.model");
            Shell.run(PlotLinearRegression.class, "-i data/salary_fulltime.csv -m model/salary_fulltime_model.model -n Full-Time");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                contractBtn.setDisable(false);
                freelanceBtn.setDisable(false);
                fullTimeBtn.setDisable(false);
                internshipBtn.setDisable(false);
                partTimeBtn.setDisable(false);
                temporaryBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generateInternshipRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        contractBtn.setDisable(true);
        freelanceBtn.setDisable(true);
        fullTimeBtn.setDisable(true);
        internshipBtn.setDisable(true);
        partTimeBtn.setDisable(true);
        temporaryBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(TrainLinearRegression.class, "-i data/trainingInternship.csv -m model/salary_internship_model.model -r 10");
            Shell.run(EvaluateRegression.class, "-i data/testingInternship.csv -s data/trainingInternship.csv -m model/salary_internship_model.model");
            System.out.println("[FULL-SET]");
            Shell.run(PrintRegression.class, "-i data/salary_internship.csv -m model/salary_internship_model.model");
            Shell.run(PlotLinearRegression.class, "-i data/salary_internship.csv -m model/salary_internship_model.model -n Internship");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                contractBtn.setDisable(false);
                freelanceBtn.setDisable(false);
                fullTimeBtn.setDisable(false);
                internshipBtn.setDisable(false);
                partTimeBtn.setDisable(false);
                temporaryBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generatePartTimeRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        contractBtn.setDisable(true);
        freelanceBtn.setDisable(true);
        fullTimeBtn.setDisable(true);
        internshipBtn.setDisable(true);
        partTimeBtn.setDisable(true);
        temporaryBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(TrainLinearRegression.class, "-i data/trainingPartTime.csv -m model/salary_parttime_model.model -r 10");
            Shell.run(EvaluateRegression.class, "-i data/testingPartTime.csv -s data/trainingPartTime.csv -m model/salary_parttime_model.model");
            System.out.println("[FULL-SET]");
            Shell.run(PrintRegression.class, "-i data/salary_parttime.csv -m model/salary_parttime_model.model");
            Shell.run(PlotLinearRegression.class, "-i data/salary_parttime.csv -m model/salary_parttime_model.model -n Part-Time");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                contractBtn.setDisable(false);
                freelanceBtn.setDisable(false);
                fullTimeBtn.setDisable(false);
                internshipBtn.setDisable(false);
                partTimeBtn.setDisable(false);
                temporaryBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generateTemporaryRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        contractBtn.setDisable(true);
        freelanceBtn.setDisable(true);
        fullTimeBtn.setDisable(true);
        internshipBtn.setDisable(true);
        partTimeBtn.setDisable(true);
        temporaryBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(TrainLinearRegression.class, "-i data/trainingTemporary.csv -m model/salary_temporary_model.model -r 10");
            Shell.run(EvaluateRegression.class, "-i data/testingTemporary.csv -s data/trainingTemporary.csv -m model/salary_temporary_model.model");
            System.out.println("[FULL-SET]");
            Shell.run(PrintRegression.class, "-i data/salary_temporary.csv -m model/salary_temporary_model.model");
            Shell.run(PlotLinearRegression.class, "-i data/salary_temporary.csv -m model/salary_temporary_model.model -n Temporary");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                contractBtn.setDisable(false);
                freelanceBtn.setDisable(false);
                fullTimeBtn.setDisable(false);
                internshipBtn.setDisable(false);
                partTimeBtn.setDisable(false);
                temporaryBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    public void hamburgerBar() {
        rootP = anchorPane;

        try {
            VBox box = FXMLLoader.load(getClass().getResource("/sideTab.fxml"));
            drawer.setSidePane(box);
            drawer.setVisible(false);
            drawer.setDefaultDrawerSize(219);
        } catch (IOException ex) {

        }

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isOpened()) {
                drawer.close();
                drawer.setDisable(true);
            } else {
                drawer.open();
                drawer.setVisible(true);
                drawer.setDisable(false);
            }
        });
    }
}
