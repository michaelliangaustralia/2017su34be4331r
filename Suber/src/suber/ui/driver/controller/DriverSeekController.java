/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.ui.driver.controller;

import assignment1carseek.DatabaseManager;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import suber.Suber;
import suber.backend.SuberDB;
import suber.backend.member.session.LoginSession;

/**
 * FXML Controller class
 *
 * @author Andrew
 */
public class DriverSeekController implements Initializable {
    
    SuberDB db;
    LoginSession session;
    DatabaseManager insertDB;
    
    @FXML
    private Text loginLabel;
    
    @FXML
    private TextField dateSeekingText;
    
    @FXML
    private TextField numberOfPeopleText;
    
    @FXML
    private TextField startTimeText;
    
    @FXML
    private TextField endTimeText;
    
    @FXML
    private TextField startPostcodeText;
    
    @FXML
    private TextField endPostcodeText;
    
    @FXML
    private Button submitButton;
    
    @FXML
    private Button offerButton;
    
    @FXML
    private Button profileButton;

    @FXML
    private Button paymentButton;

    @FXML
    private void handlePaymentButton(ActionEvent event) {
        try {
            Stage stage = (Stage) paymentButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/DriverPayments.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            final Node source = (Node) event.getSource();
            final Stage stage2 = (Stage) source.getScene().getWindow();
            stage2.close();
        } catch (IOException ioex) {
            System.out.println(ioex);
        }
    }
    
    @FXML
    private void profileButtonAction(ActionEvent event) {
        try {
            Stage stage = (Stage) profileButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/DriverLandingPage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            final Node source = (Node) event.getSource();
            final Stage stage2 = (Stage) source.getScene().getWindow();
            stage2.close();
        } catch (IOException ioex) {
            System.out.println(ioex);
        }
    }

    
    @FXML
    private void handleOfferButton(ActionEvent event) {
        try {
            Stage stage = (Stage) offerButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/DriverOffer.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            final Node source = (Node) event.getSource();
            final Stage stage2 = (Stage) source.getScene().getWindow();
            stage2.close();
        } catch (IOException ioex) {
            System.out.println(ioex);
        }
    }
    
    @FXML
    private void submitButtonAction(ActionEvent event) {
        
        // if u have time validate the ints :)))
        //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //Date dateSeeking = df.parse(dateSeekingText.getText(),)
        //insertCarSeek(int carSeekID, int riderId, Date sqlDateSeeking, Timestamp startTime, Timestamp endTime, int numberOfPeople, int startPostCode, int endPostCode) {
        //insertDB.insertCarSeek(0, session.getUserId(), );
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        db = new SuberDB();
        session = Suber.session;
        try {
            insertDB = new DatabaseManager();
        } catch (Exception ex) {
            Logger.getLogger(DriverSeekController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loginLabel.setText("Logged in as:\n" + session.getEmail());
    }    
    
}