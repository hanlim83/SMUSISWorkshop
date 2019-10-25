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

public class jobIndustryController implements Initializable {

    private static AnchorPane rootP;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXButton allIndustriesBtn;
    @FXML
    private JFXButton uiUXIndustryBtn;
    @FXML
    private JFXSpinner runningSpinner;
    private Thread thread;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hamburger.setDisable(true);
        allIndustriesBtn.setDisable(true);
        uiUXIndustryBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(SwapColumns.class, "-i data/data_salary_industry.csv -o data/industryDataV1.csv -x 0 -y 8");
            Shell.run(SwapColumns.class, "-i data/industryDataV1.csv -o data/industryDataV2.csv -x 1 -y 3");
            Shell.run(RemoveColumn.class, "-i data/industryDataV2.csv -o data/industryDataV3.csv -c 2");
            Shell.run(RemoveColumn.class, "-i data/industryDataV3.csv -o data/industryDataV4.csv -c 2");
            Shell.run(RemoveColumn.class, "-i data/industryDataV4.csv -o data/industryDataV5.csv -c 2");
            Shell.run(RemoveColumn.class, "-i data/industryDataV5.csv -o data/industryDataV6.csv -c 2");
            Shell.run(RemoveColumn.class, "-i data/industryDataV6.csv -o data/industryDataV7.csv -c 2");
            Shell.run(RemoveColumn.class, "-i data/industryDataV7.csv -o data/industryDataV8.csv -c 2");
            Shell.run(RemoveColumn.class, "-i data/industryDataV8.csv -o data/industryDataV9.csv -c 2");
            Shell.run(CustomEncodeTextAsFrequency.class, "-i data/industryDataV9.csv -o data/industryDataV10.csv -c 1");
            Shell.run(Partition.class, "-i data/industryDataV10.csv -o data/trainingIndustry.csv -p 0.8");
            Shell.run(Partition.class, "-i data/industryDataV10.csv -o data/testingIndustry.csv -p 0.8 -e");
            Shell.run(ProjectColumns.class, "-i data/industryDataV10.csv -o data/salary_uxui.csv -c meanSalary WORD:ui/ux");
            Shell.run(Partition.class, "-i data/salary_uxui.csv -o data/trainingUXUI.csv -p 0.8");
            Shell.run(Partition.class, "-i data/salary_uxui.csv -o data/testingUXUI.csv -p 0.8 -e");
            Platform.runLater(() -> {
                hamburgerBar();
                hamburger.setDisable(false);
                allIndustriesBtn.setDisable(false);
                uiUXIndustryBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generateAllIndustriesRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        allIndustriesBtn.setDisable(true);
        uiUXIndustryBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(TrainLinearRegression.class, "-i data/trainingIndustry.csv -m model/trainingIndustry_model.model -r 10");
            Shell.run(PrintRegression.class, "-i data/trainingIndustry.csv -m model/trainingIndustry_model.model");
            Shell.run(EvaluateRegression.class, "-i data/testingIndustry.csv -s data/trainingIndustry.csv -m model/trainingIndustry_model.model");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                allIndustriesBtn.setDisable(false);
                uiUXIndustryBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generateUIUXIndustryRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        allIndustriesBtn.setDisable(true);
        uiUXIndustryBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(TrainLinearRegression.class, "-i data/trainingUXUI.csv -m model/salary_uxui_model.model");
            Shell.run(EvaluateRegression.class, "-i data/testingUXUI.csv -s data/trainingUXUI.csv -m model/salary_uxui_model.model");
            Shell.run(PrintRegression.class, "-i data/salary_uxui.csv -m model/salary_uxui_model.model");
            Shell.run(PlotLinearRegression.class, "-i data/salary_uxui.csv -m model/salary_uxui_model.model -n UI/UX");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                allIndustriesBtn.setDisable(false);
                uiUXIndustryBtn.setDisable(false);
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
