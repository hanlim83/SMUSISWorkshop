package ai.preferred.UI;

import ai.preferred.regression.EvaluateRegression;
import ai.preferred.regression.PrintRegression;
import ai.preferred.regression.Shell;
import ai.preferred.regression.TrainLinearRegression;
import ai.preferred.regression.custom.PlotLinearRegression;
import ai.preferred.regression.pe.Partition;
import ai.preferred.regression.pe.ProjectColumns;
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

public class jobSkillsController implements Initializable {

    private static AnchorPane rootP;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXButton javaBtn;
    @FXML
    private JFXButton javaScriptBtn;
    @FXML
    private JFXButton htmlBtn;
    @FXML
    private JFXButton pythonBtn;
    @FXML
    private JFXButton cssBtn;
    @FXML
    private JFXButton phpBtn;
    @FXML
    private JFXButton mutlipleBtn;
    @FXML
    private JFXSpinner runningSpinner;
    @FXML
    private JFXButton salesBtn;
    private Thread thread;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hamburger.setDisable(true);
        javaBtn.setDisable(true);
        javaScriptBtn.setDisable(true);
        htmlBtn.setDisable(true);
        pythonBtn.setDisable(true);
        cssBtn.setDisable(true);
        phpBtn.setDisable(true);
        mutlipleBtn.setDisable(true);
        salesBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(CustomEncodeTextAsFrequency.class, "-i data/data_salary.csv -o data/data_skills.csv -c 5");
            Shell.run(CustomEncodeTextAsFrequency.class, "-i data/data_skills.csv -o data/data_skills_jobtype.csv -c 4");
            Shell.run(ProjectColumns.class, "-i data/data_skills.csv -o data/data.java.csv -c meanSalary WORD:java");
            Shell.run(ProjectColumns.class, "-i data/data_skills.csv -o data/data.javascript.csv -c meanSalary WORD:javascript");
            Shell.run(ProjectColumns.class, "-i data/data_skills.csv -o data/data.python.csv -c meanSalary WORD:python");
            Shell.run(ProjectColumns.class, "-i data/data_skills.csv -o data/data.html.csv -c meanSalary WORD:html");
            Shell.run(ProjectColumns.class, "-i data/data_skills.csv -o data/data.css.csv -c meanSalary WORD:css");
            Shell.run(ProjectColumns.class, "-i data/data_skills.csv -o data/data.php.csv -c meanSalary WORD:php");
            Shell.run(ProjectColumns.class, "-i data/data_skills_jobtype.csv -o data/data.manyskills_fulltime.csv -c meanSalary WORD:sales WORD:php WORD:css WORD:html WORD:python WORD:javascript WORD:java WORD:full-time");
            Shell.run(ProjectColumns.class, "-i data/data_skills.csv -o data/data.sales.csv -c meanSalary WORD:sales");
            Platform.runLater(() -> {
                hamburgerBar();
                hamburger.setDisable(false);
                javaBtn.setDisable(false);
                javaScriptBtn.setDisable(false);
                htmlBtn.setDisable(false);
                pythonBtn.setDisable(false);
                cssBtn.setDisable(false);
                phpBtn.setDisable(false);
                mutlipleBtn.setDisable(false);
                salesBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generateCSSRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        javaBtn.setDisable(true);
        javaScriptBtn.setDisable(true);
        htmlBtn.setDisable(true);
        pythonBtn.setDisable(true);
        cssBtn.setDisable(true);
        phpBtn.setDisable(true);
        mutlipleBtn.setDisable(true);
        salesBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(TrainLinearRegression.class, "-i data/data.css.csv -m model/salary.css_model.model");
            Shell.run(PrintRegression.class, "-i data/data.css.csv -m model/salary.css_model.model");
            Shell.run(PlotLinearRegression.class, "-i data/data.css.csv -m model/salary.css_model.model -n CSS");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                javaBtn.setDisable(false);
                javaScriptBtn.setDisable(false);
                htmlBtn.setDisable(false);
                pythonBtn.setDisable(false);
                cssBtn.setDisable(false);
                phpBtn.setDisable(false);
                mutlipleBtn.setDisable(false);
                salesBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generateHTMLRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        javaBtn.setDisable(true);
        javaScriptBtn.setDisable(true);
        htmlBtn.setDisable(true);
        pythonBtn.setDisable(true);
        cssBtn.setDisable(true);
        phpBtn.setDisable(true);
        mutlipleBtn.setDisable(true);
        salesBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(TrainLinearRegression.class, "-i data/data.html.csv -m model/salary.html_model.model");
            Shell.run(PrintRegression.class, "-i data/data.html.csv -m model/salary.html_model.model");
            Shell.run(PlotLinearRegression.class, "-i data/data.html.csv -m model/salary.html_model.model -n HTML");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                javaBtn.setDisable(false);
                javaScriptBtn.setDisable(false);
                htmlBtn.setDisable(false);
                pythonBtn.setDisable(false);
                cssBtn.setDisable(false);
                phpBtn.setDisable(false);
                mutlipleBtn.setDisable(false);
                salesBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generateJavaRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        javaBtn.setDisable(true);
        javaScriptBtn.setDisable(true);
        htmlBtn.setDisable(true);
        pythonBtn.setDisable(true);
        cssBtn.setDisable(true);
        phpBtn.setDisable(true);
        mutlipleBtn.setDisable(true);
        salesBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(TrainLinearRegression.class, "-i data/data.java.csv -m model/salary.java_model.model");
            Shell.run(PrintRegression.class, "-i data/data.java.csv -m model/salary.java_model.model");
            Shell.run(PlotLinearRegression.class, "-i data/data.java.csv -m model/salary.java_model.model -n Java");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                javaBtn.setDisable(false);
                javaScriptBtn.setDisable(false);
                htmlBtn.setDisable(false);
                pythonBtn.setDisable(false);
                cssBtn.setDisable(false);
                phpBtn.setDisable(false);
                mutlipleBtn.setDisable(false);
                salesBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generateJavaScriptRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        javaBtn.setDisable(true);
        javaScriptBtn.setDisable(true);
        htmlBtn.setDisable(true);
        pythonBtn.setDisable(true);
        cssBtn.setDisable(true);
        phpBtn.setDisable(true);
        mutlipleBtn.setDisable(true);
        salesBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(TrainLinearRegression.class, "-i data/data.javascript.csv -m model/salary.javascript_model.model");
            Shell.run(PrintRegression.class, "-i data/data.javascript.csv -m model/salary.javascript_model.model");
            Shell.run(PlotLinearRegression.class, "-i data/data.javascript.csv -m model/salary.javascript_model.model -n JavaScript");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                javaBtn.setDisable(false);
                javaScriptBtn.setDisable(false);
                htmlBtn.setDisable(false);
                pythonBtn.setDisable(false);
                cssBtn.setDisable(false);
                phpBtn.setDisable(false);
                mutlipleBtn.setDisable(false);
                salesBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generateMutlipleSkillsRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        javaBtn.setDisable(true);
        javaScriptBtn.setDisable(true);
        htmlBtn.setDisable(true);
        pythonBtn.setDisable(true);
        cssBtn.setDisable(true);
        phpBtn.setDisable(true);
        mutlipleBtn.setDisable(true);
        salesBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(TrainLinearRegression.class, "-i data/data.manyskills_fulltime.csv -m model/salary.manyskills_fulltime_model.model");
            Shell.run(PrintRegression.class, "-i data/data.manyskills_fulltime.csv -m model/salary.manyskills_fulltime_model.model");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                javaBtn.setDisable(false);
                javaScriptBtn.setDisable(false);
                htmlBtn.setDisable(false);
                pythonBtn.setDisable(false);
                cssBtn.setDisable(false);
                phpBtn.setDisable(false);
                mutlipleBtn.setDisable(false);
                salesBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generatePHPRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        javaBtn.setDisable(true);
        javaScriptBtn.setDisable(true);
        htmlBtn.setDisable(true);
        pythonBtn.setDisable(true);
        cssBtn.setDisable(true);
        phpBtn.setDisable(true);
        mutlipleBtn.setDisable(true);
        salesBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(TrainLinearRegression.class, "-i data/data.php.csv -m model/salary.php_model.model");
            Shell.run(PrintRegression.class, "-i data/data.php.csv -m model/salary.php_model.model");
            Shell.run(PlotLinearRegression.class, "-i data/data.php.csv -m model/salary.php_model.model -n PHP");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                javaBtn.setDisable(false);
                javaScriptBtn.setDisable(false);
                htmlBtn.setDisable(false);
                pythonBtn.setDisable(false);
                cssBtn.setDisable(false);
                phpBtn.setDisable(false);
                mutlipleBtn.setDisable(false);
                salesBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generatePythonRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        javaBtn.setDisable(true);
        javaScriptBtn.setDisable(true);
        htmlBtn.setDisable(true);
        pythonBtn.setDisable(true);
        cssBtn.setDisable(true);
        phpBtn.setDisable(true);
        mutlipleBtn.setDisable(true);
        salesBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(TrainLinearRegression.class, "-i data/data.python.csv -m model/salary.python_model.model");
            Shell.run(PrintRegression.class, "-i data/data.python.csv -m model/salary.python_model.model");
            Shell.run(PlotLinearRegression.class, "-i data/data.python.csv -m model/salary.python_model.model -n Python");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                javaBtn.setDisable(false);
                javaScriptBtn.setDisable(false);
                htmlBtn.setDisable(false);
                pythonBtn.setDisable(false);
                cssBtn.setDisable(false);
                phpBtn.setDisable(false);
                mutlipleBtn.setDisable(false);
                salesBtn.setDisable(false);
                runningSpinner.setVisible(false);
            });
        });
        thread.start();
    }

    @FXML
    void generateSalesRegression(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        javaBtn.setDisable(true);
        javaScriptBtn.setDisable(true);
        htmlBtn.setDisable(true);
        pythonBtn.setDisable(true);
        cssBtn.setDisable(true);
        phpBtn.setDisable(true);
        mutlipleBtn.setDisable(true);
        salesBtn.setDisable(true);
        thread = new Thread(() -> {
            Shell.run(Partition.class, "-i data/data.sales.csv -o data/data.sales_training.csv");
            Shell.run(Partition.class, "-i data/data.sales.csv -o data/data.sales_testing.csv -e");
            Shell.run(TrainLinearRegression.class, "-i data/data.sales_training.csv -m model/salary.sales.model");
            Shell.run(EvaluateRegression.class, "-s data/data.sales_training.csv -m model/salary.sales.model -i data/data.sales_testing.csv");
            Shell.run(TrainLinearRegression.class, "-i data/data.sales.csv -m model/salary.sales_model.model");
            Shell.run(PrintRegression.class, "-i data/data.sales.csv -m model/salary.sales_model.model");
            Shell.run(PlotLinearRegression.class, "-i data/data.sales.csv -m model/salary.sales_model.model -n Sales");
            Platform.runLater(() -> {
                hamburger.setDisable(false);
                javaBtn.setDisable(false);
                javaScriptBtn.setDisable(false);
                htmlBtn.setDisable(false);
                pythonBtn.setDisable(false);
                cssBtn.setDisable(false);
                phpBtn.setDisable(false);
                mutlipleBtn.setDisable(false);
                salesBtn.setDisable(false);
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
