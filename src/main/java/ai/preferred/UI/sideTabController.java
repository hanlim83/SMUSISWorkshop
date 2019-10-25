package ai.preferred.UI;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class sideTabController {

    @FXML
    private JFXButton webCrawlerButton;

    @FXML
    private JFXButton locationButton;

    @FXML
    private JFXButton typeButton;

    @FXML
    private JFXButton industryButton;

    @FXML
    private JFXButton skillsButton;

    @FXML
    private JFXButton locationFullTimeButton;


    @FXML
    private JFXButton ExitButton;

    private Scene myScene;

    @FXML
    void goToJobIndustry(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/jobIndustry.fxml"));
        myScene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) (myScene).getWindow();
        Parent nextView = loader.load();
        stage.setScene(new Scene(nextView));
        stage.show();
    }

    @FXML
    void goToJobLocation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/jobLocation.fxml"));
        myScene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) (myScene).getWindow();
        Parent nextView = loader.load();
        stage.setScene(new Scene(nextView));
        stage.show();
    }

    @FXML
    void goToJobSkills(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/jobSkills.fxml"));
        myScene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) (myScene).getWindow();
        Parent nextView = loader.load();
        stage.setScene(new Scene(nextView));
        stage.show();
    }

    @FXML
    void goToJobType(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/jobType.fxml"));
        myScene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) (myScene).getWindow();
        Parent nextView = loader.load();
        stage.setScene(new Scene(nextView));
        stage.show();
    }

    @FXML
    void goToWebCrawler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/webCrawler.fxml"));
        myScene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) (myScene).getWindow();
        Parent nextView = loader.load();
        stage.setScene(new Scene(nextView));
        stage.show();
    }

    @FXML
    void onClickExitButton(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void onClickJobIndustry(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/jobIndustry.fxml"));
        myScene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) (myScene).getWindow();
        Parent nextView = loader.load();
        stage.setScene(new Scene(nextView));
        stage.show();
    }

    @FXML
    void onClickJobLocation(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/jobLocation.fxml"));
        myScene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) (myScene).getWindow();
        Parent nextView = loader.load();
        stage.setScene(new Scene(nextView));
        stage.show();
    }

    @FXML
    void onClickJobSkills(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/jobSkills.fxml"));
        myScene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) (myScene).getWindow();
        Parent nextView = loader.load();
        stage.setScene(new Scene(nextView));
        stage.show();
    }

    @FXML
    void onClickJobType(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/jobType.fxml"));
        myScene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) (myScene).getWindow();
        Parent nextView = loader.load();
        stage.setScene(new Scene(nextView));
        stage.show();
    }

    @FXML
    void onClickWebCrawler(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/webCrawler.fxml"));
        myScene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) (myScene).getWindow();
        Parent nextView = loader.load();
        stage.setScene(new Scene(nextView));
        stage.show();
    }

    @FXML
    void onClickLocationFullTimeButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/jobLocationFullTime.fxml"));
        myScene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) (myScene).getWindow();
        Parent nextView = loader.load();
        stage.setScene(new Scene(nextView));
        stage.show();
    }

    @FXML
    void onHover(MouseEvent event) {
        webCrawlerButton.setButtonType(JFXButton.ButtonType.RAISED);
    }

    @FXML
    void onHover1(MouseEvent event) {
        locationButton.setButtonType(JFXButton.ButtonType.RAISED);
    }

    @FXML
    void onHover2(MouseEvent event) {
        typeButton.setButtonType(JFXButton.ButtonType.RAISED);
    }

    @FXML
    void onHover3(MouseEvent event) {
        industryButton.setButtonType(JFXButton.ButtonType.RAISED);
    }

    @FXML
    void onHover4(MouseEvent event) {
        skillsButton.setButtonType(JFXButton.ButtonType.RAISED);
    }

    @FXML
    void onHover5(MouseEvent event) {
        ExitButton.setButtonType(JFXButton.ButtonType.RAISED);
    }

    @FXML
    void onHover6(MouseEvent event) {
        locationFullTimeButton.setButtonType(JFXButton.ButtonType.RAISED);
    }

    @FXML
    void onHoverExit(MouseEvent event) {
        webCrawlerButton.setButtonType(JFXButton.ButtonType.FLAT);
    }

    @FXML
    void onHoverExit1(MouseEvent event) {
        locationButton.setButtonType(JFXButton.ButtonType.FLAT);
    }

    @FXML
    void onHoverExit2(MouseEvent event) {
        typeButton.setButtonType(JFXButton.ButtonType.FLAT);
    }

    @FXML
    void onHoverExit3(MouseEvent event) {
        industryButton.setButtonType(JFXButton.ButtonType.FLAT);
    }

    @FXML
    void onHoverExit4(MouseEvent event) {
        skillsButton.setButtonType(JFXButton.ButtonType.FLAT);
    }

    @FXML
    void onHoverExit5(MouseEvent event) {
        ExitButton.setButtonType(JFXButton.ButtonType.FLAT);
    }

    @FXML
    void onHoverExit6(MouseEvent event) {
        locationFullTimeButton.setButtonType(JFXButton.ButtonType.FLAT);
    }

}
