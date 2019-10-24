package ai.preferred.UI;

import ai.preferred.crawler.EntityCSVStorage;
import ai.preferred.crawler.entity.jobListing;
import ai.preferred.crawler.handlers.findJobListingsHandler;
import ai.preferred.venom.Crawler;
import ai.preferred.venom.Session;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.request.VRequest;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
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

public class webCrawlerController implements Initializable {

    public static final Session.Key<EntityCSVStorage<jobListing>> STORAGE_KEY = new Session.Key<>();

    public static AnchorPane rootP;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXButton callButton;

    @FXML
    private JFXSpinner runningSpinner;

    @FXML
    void startCrawler(ActionEvent event) {
        runningSpinner.setVisible(true);
        hamburger.setDisable(true);
        callButton.setDisable(true);
        try (EntityCSVStorage<jobListing> storage = new EntityCSVStorage<>("data_JavaFX.csv")) {
            try (Crawler crawler = Crawler.builder().setSession(Session.builder().put(STORAGE_KEY, storage).build()).build().start()) {
                Request request = new VRequest("https://startupjobs.asia/job/all/anywhere");
                crawler.getScheduler().add(request, new findJobListingsHandler());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            runningSpinner.setVisible(false);
            hamburger.setDisable(false);
            callButton.setDisable(false);
        }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hamburgerBar();
        runningSpinner.setVisible(false);
    }

}
