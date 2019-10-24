package ai.preferred.UI;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

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
    private JFXButton ExitButton;

    @FXML
    void goToJobIndustry(ActionEvent event) {

    }

    @FXML
    void goToJobLocation(ActionEvent event) {

    }

    @FXML
    void goToJobSkills(ActionEvent event) {

    }

    @FXML
    void goToJobType(ActionEvent event) {

    }

    @FXML
    void goToWebCrawler(ActionEvent event) {

    }

    @FXML
    void onClickExitButton(ActionEvent event) {

    }

    @FXML
    void onClickJobIndustry(MouseEvent event) {

    }

    @FXML
    void onClickJobLocation(MouseEvent event) {

    }

    @FXML
    void onClickJobSkills(MouseEvent event) {

    }

    @FXML
    void onClickJobType(MouseEvent event) {

    }

    @FXML
    void onClickWebCrawler(MouseEvent event) {

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

}
