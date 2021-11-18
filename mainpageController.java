package com.example.petdating;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;


import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import static com.example.petdating.loginController.username;


/**
 * mainpageController has four methods. This controller controls the main page of APP.
 * Main page has three tabs. Edit profile, chat messages and match friend.
 *
 *
 * **/

public class mainpageController implements Initializable {
    @FXML
    private matchfrinedmainpage controller1;

    @FXML
    private Label username1;

    @FXML
    private ImageView mainbackgroundimage;

    @FXML
    private Button matchfriend;

    @FXML
    private ImageView messageicon;


    @Override
    /**
     *
     * If there is message sent to current user, the notification pic will be showed. Otherwise, another pic will be showed
     * @param url
     *
     *
     * */
    public void initialize(URL url, ResourceBundle resourceBundle) {


        File message = new File("0027ca30586ae71fd4f5a4323d5c990a.png");
        Image messagebutton = new Image(message.toURI().toString());
        mainbackgroundimage.setImage(messagebutton);

        if (notification()){
            File file2 = new File("/Users/kezhou/Desktop/JAVA/PetdatingAPP/19-新消息.png");
            Image image2 = new Image(file2.toURI().toString());
            System.out.println(image2.getHeight());
            messageicon.setImage(image2);

        }else{
            File file2 = new File("/Users/kezhou/Desktop/JAVA/PetdatingAPP/src/main/resources/com/example/petdating/提醒.png");
            Image image2 = new Image(file2.toURI().toString());
            messageicon.setImage(image2);
        }


    }
    /**
     *
     * change the scene when click the profile tab
     * @param  event the action event, it is a click action.
     *
     *
     *
     * */

    public void changesceneclickprofile(ActionEvent event) throws IOException {


        Parent fmxlloader = FXMLLoader.load(getClass().getResource("/com/example/petdating/editprofile.fxml"));

        Scene scene = new Scene(fmxlloader);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Petdating");

        stage.setScene(scene);
        stage.show();

    }

    /**
     *
     * change the scene after click the match friend button
     * @param  event the action event, it is a click action.
     *
     *
     *
     * */

    public void changesceneaftermatchfriend(ActionEvent event) throws Exception {

        try {
            Parent fmxlloader = FXMLLoader.load(getClass().getResource("/com/example/petdating/MainTable.fxml"));


            Scene scene = new Scene(fmxlloader);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Petdating");

            stage.setScene(scene);
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


//        System.out.println();


    }

    /**
     *
     * change scene after click the message button
     * @param  event the action event, it is a click action.
     *
     *
     *
     * */

    public void changesceneaftermessage(ActionEvent event) throws Exception {
        try {
            Parent fmxlloader = FXMLLoader.load(getClass().getResource("/com/example/petdating/messagenotification.fxml"));


            Scene scene = new Scene(fmxlloader);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Petdating");

            stage.setScene(scene);
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    /**
     * check if there are messages sent to current users from database. If yes, return true.
     *
     * @return boolean
     *
     *
     * */
    public boolean notification() {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectdb = connection.getConnection();

        String sql = "select count(*) from messageinfo where sentuser='" + username + "' and messageinfo.check=0 and messageinfo.ignore=0";
        System.out.println(sql);
        try {

            Statement statement = connectdb.createStatement();
            try {
                ResultSet queryResult = statement.executeQuery(sql);
                while (queryResult.next()) {
                    if (queryResult.getInt(1) >= 1) {
                        return true;
                    } else {
                        return false;
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return false;
    }







}
