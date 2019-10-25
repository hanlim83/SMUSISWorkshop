package ai.preferred.UI;

import ai.preferred.regression.EvaluateRegression;
import ai.preferred.regression.PrintRegression;
import ai.preferred.regression.Shell;
import ai.preferred.regression.TrainLinearRegression;
import ai.preferred.regression.custom.PlotLinearRegression;
import ai.preferred.regression.pe.*;
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

public class jobLocationController implements Initializable {

    private static AnchorPane rootP;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXButton singaporeBtn;
    @FXML
    private JFXButton malaysiaBtn;
    @FXML
    private JFXButton philippinesBtn;
    @FXML
    private JFXButton indonesiaBtn;
    @FXML
    private JFXSpinner runningSpinner;
    private Thread thread;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hamburger.setDisable(true);
        singaporeBtn.setDisable(true);
        malaysiaBtn.setDisable(true);
        philippinesBtn.setDisable(true);
        indonesiaBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(ProjectColumns.class, "-i data/data_salary.csv -o data/data_countries.csv -c location meanSalary");
            Shell.run(SwapColumns.class, "-i data/data_countries.csv -o data/data_countries_swapped.csv -x 0 -y 1");
            Shell.run(EncodeValueAsOneHot.class, "-i data/data_countries_swapped.csv -o data/data_countries_oneHot.csv -c 1 -p COUNTRY:");
            Shell.run(Shuffle.class, "-i data/data_countries_oneHot.csv -o data/data_countries_oneHot_Shuffled.csv");
            Shell.run(ProjectColumns.class, "-i data/data_countries_oneHot_Shuffled.csv -o data/data_Singapore.csv -c meanSalary COUNTRY:Singapore");
            Shell.run(ProjectColumns.class, "-i data/data_countries_oneHot_Shuffled.csv -o data/data_Philippines.csv -c meanSalary COUNTRY:Philippines");
            Shell.run(ProjectColumns.class, "-i data/data_countries_oneHot_Shuffled.csv -o data/data_Malaysia.csv -c  meanSalary COUNTRY:Malaysia");
            Shell.run(ProjectColumns.class, "-i data/data_countries_oneHot_Shuffled.csv -o data/data_Indonesia.csv -c meanSalary COUNTRY:Indonesia");
            Shell.run(Partition.class, "-i data/data_Singapore.csv -o data/data_Singapore_training.csv");
            Shell.run(Partition.class, "-i data/data_Singapore.csv -o data/data_Singapore_testing.csv -e");
            Shell.run(Partition.class, "-i data/data_Philippines.csv -o data/data_Philippines_training.csv");
            Shell.run(Partition.class, "-i data/data_Philippines.csv -o data/data_Philippines_testing.csv -e");
            Shell.run(Partition.class, "-i data/data_Malaysia.csv -o data/data_Malaysia_training.csv");
            Shell.run(Partition.class, "-i data/data_Malaysia.csv -o data/data_Malaysia_testing.csv -e");
            Shell.run(Partition.class, "-i data/data_Indonesia.csv -o data/data_Indonesia_training.csv");
            Shell.run(Partition.class, "-i data/data_Indonesia.csv -o data/data_Indonesia_testing.csv -e");
            Platform.runLater(() -> {
                hamburgerBar();
                hamburger.setDisable(false);
                singaporeBtn.setDisable(false);
                malaysiaBtn.setDisable(false);
                philippinesBtn.setDisable(false);
                indonesiaBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generateIndonesiaRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        singaporeBtn.setDisable(true);
        malaysiaBtn.setDisable(true);
        philippinesBtn.setDisable(true);
        indonesiaBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(TrainLinearRegression.class, "-i data/data_Indonesia_training.csv -m model/jobCountriesIndonesia.model");
            Shell.run(EvaluateRegression.class, "-s data/data_Indonesia_training.csv -m model/jobCountriesIndonesia.model -i data/data_Indonesia_testing.csv");
            System.out.println("[FULL-SET]");
            Shell.run(PrintRegression.class, "-i data/data_Indonesia.csv -m model/jobCountriesIndonesia.model");
            Shell.run(PlotLinearRegression.class, "-i data/data_Indonesia.csv -m model/jobCountriesIndonesia.model -n Indonesia");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                singaporeBtn.setDisable(false);
                malaysiaBtn.setDisable(false);
                philippinesBtn.setDisable(false);
                indonesiaBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generateMalaysiaRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        singaporeBtn.setDisable(true);
        malaysiaBtn.setDisable(true);
        philippinesBtn.setDisable(true);
        indonesiaBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(TrainLinearRegression.class, "-i data/data_Malaysia_training.csv -m model/jobCountriesMalaysia.model");
            Shell.run(EvaluateRegression.class, "-s data/data_Malaysia_training.csv -m model/jobCountriesMalaysia.model -i data/data_Malaysia_testing.csv");
            System.out.println("[FULL-SET]");
            Shell.run(PrintRegression.class, "-i data/data_Malaysia.csv -m model/jobCountriesMalaysia.model");
            Shell.run(PlotLinearRegression.class, "-i data/data_Malaysia.csv -m model/jobCountriesMalaysia.model -n Malaysia");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                singaporeBtn.setDisable(false);
                malaysiaBtn.setDisable(false);
                philippinesBtn.setDisable(false);
                indonesiaBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generatePhilippinesRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        singaporeBtn.setDisable(true);
        malaysiaBtn.setDisable(true);
        philippinesBtn.setDisable(true);
        indonesiaBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(TrainLinearRegression.class, "-i data/data_Philippines_training.csv -m model/jobCountriesPhilippines.model");
            Shell.run(EvaluateRegression.class, "-s data/data_Philippines_training.csv -m model/jobCountriesPhilippines.model -i data/data_Philippines_testing.csv");
            System.out.println("[FULL-SET]");
            Shell.run(PrintRegression.class, "-i data/data_Philippines.csv -m model/jobCountriesPhilippines.model");
            Shell.run(PlotLinearRegression.class, "-i data/data_Philippines.csv -m model/jobCountriesPhilippines.model -n Philippines");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                singaporeBtn.setDisable(false);
                malaysiaBtn.setDisable(false);
                philippinesBtn.setDisable(false);
                indonesiaBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generateSingaporeRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        singaporeBtn.setDisable(true);
        malaysiaBtn.setDisable(true);
        philippinesBtn.setDisable(true);
        indonesiaBtn.setDisable(true);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Shell.run(TrainLinearRegression.class, "-i data/data_Singapore_training.csv -m model/jobCountriesSingapore.model");
                Shell.run(EvaluateRegression.class, "-s data/data_Singapore_training.csv -m model/jobCountriesSingapore.model -i data/data_Singapore_testing.csv");
                System.out.println("[FULL-SET]");
                Shell.run(PrintRegression.class, "-i data/data_Singapore.csv -m model/jobCountriesSingapore.model");
                Shell.run(PlotLinearRegression.class, "-i data/data_Singapore.csv -m model/jobCountriesSingapore.model -n Singapore");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        hamburger.setDisable(false);
                        singaporeBtn.setDisable(false);
                        malaysiaBtn.setDisable(false);
                        philippinesBtn.setDisable(false);
                        indonesiaBtn.setDisable(false);
                        runningSpinner.setVisible(false);
                    }
                });
            }
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
