package com.example.petdating;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.example.petdating.loginController.username;

public class matchfriendcontroller {
    @FXML
    private Label username1;

    @FXML
    private Label petname1;

    @FXML
    private Label username2;

    @FXML
    private Label petname2;

    @FXML
    private Label username3;

    @FXML
    private Label petname3;

    @FXML
    private Label username4;

    @FXML
    private Label petname4;

    @FXML
    private Label username5;

    @FXML
    private Label petname5;

    @FXML
    private Label username6;

    @FXML
    private Label petname6;

    @FXML
    private TextField petnamefield;

    @FXML
    private TextField genderfield;

    @FXML
    private TextField foodfield;

    @FXML
    private TextField categoryfield;

    @FXML
    private TextField placefield;

    @FXML
    private TextField birthdayfield;

    @FXML
    private TextField toyfield;

    @FXML
    private TextField friendfield;

    @FXML
    private Label likelabel;

    @FXML
    private int count;

    @FXML
    private int dislikecount;

    @FXML
    static public String currentusername;

    @FXML
    private ImageView profileimginefield;


    @FXML
    private void initialize() {


    }
    /**
     *
     * get the user from which current user clicks
     * @param someusername, this parameter is passed from matchfriendmainpage.
     *
     *
     *
     * */

    public void setparameter(String someusername) {
        currentusername=someusername;
    }



    /**
     *
     * get users information from which current user clicks
     * @param id, username which is clicked by current users
     *
     *
     *
     * */
    public void otherpetprofile(String id) {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectdb = connection.getConnection();

        String sql = "select * from petinfo where username='" + id + "'" ;
        System.out.println(sql);
        try {

            Statement statement = connectdb.createStatement();

            try {
                ResultSet queryResult = statement.executeQuery(sql);
                while (queryResult.next()) {
                    petnamefield.setText(queryResult.getString("petname"));
                    genderfield.setText(queryResult.getString("petgender"));
                    foodfield.setText(queryResult.getString("petfood"));
                    categoryfield.setText(queryResult.getString("petcategory"));
                    placefield.setText(queryResult.getString("petplace"));
                    birthdayfield.setText(queryResult.getString("petbirthday"));
                    birthdayfield.setText(queryResult.getString("petbirthday"));
                    toyfield.setText(queryResult.getString("pettoy"));
                    friendfield.setText(queryResult.getString("friend"));


                    String filepath=queryResult.getString("picurl").split(":")[1].trim();
                    File file = new File(filepath);
                    Image image = new Image(file.toURI().toString());
                    System.out.println(image.getHeight());
                    profileimginefield.setImage(image);

                }

            } catch (Exception e) {

                e.printStackTrace();
                e.getCause();
            }


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }

    /**
     *
     * change scene
     * @param  event the action event, it is a click action.
     *
     *
     *
     * */

    public void changesceneafterclosebutton(ActionEvent event) throws IOException {
        Parent fmxlloader = FXMLLoader.load(getClass().getResource("/com/example/petdating/MainTable.fxml"));

        Scene scene = new Scene(fmxlloader);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Petdating");

        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * change scene after click profile
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
     * update the database when user click the like button/dislike
     * @param  event the action event, it is a click action.
     *
     *
     *
     * */



    public void likebutton(ActionEvent event) throws Exception {

        DatabaseConnection connection = new DatabaseConnection();
        Connection connectdb = connection.getConnection();

        String sql = "update petinfo set likes=ifnull(likes, 0) + 1 where username='" + currentusername + "'";
        System.out.println(sql);
        if (count < 1) {
            try {

                Statement statement = connectdb.createStatement();

                try {
                    statement.executeUpdate(sql);
                    likelabel.setText("I like your profile!!");
                    count++;

                } catch (Exception e) {

                    e.printStackTrace();
                    e.getCause();
                }


            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        } else {
            likelabel.setText("You have already like profile !");
        }


    }
    /**
     *
     * update the database when user click the like button/dislike
     * @param  event the action event, it is a click action.
     *
     *
     *
     * */

    public void dislikebutton(ActionEvent event) throws Exception {

        DatabaseConnection connection = new DatabaseConnection();
        Connection connectdb = connection.getConnection();

        String sql = "update petinfo set dislikes=ifnull(dislikes, 0) + 1 where username='" + username + "'";
        System.out.println(sql);

        if (dislikecount < 1) {

            try {

                Statement statement = connectdb.createStatement();

                try {
                    statement.executeUpdate(sql);
                    likelabel.setText("I dislike your profile!!");

                    dislikecount++;
                } catch (Exception e) {

                    e.printStackTrace();
                    e.getCause();
                }


            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }


        } else {
            likelabel.setText("You have already disliked the profile !");
        }
    }


    public void set(String user1,String user2,String user3,String user4,String user5,String user6,String pet1,String pet2,String pet3,String pet4,String pet5,String pet6){
        System.out.println("TEST SET FUNCTION");
        System.out.println(user1);
        username1.setText(user1);
        username2.setText(user2);
        username3.setText(user3);
        username4.setText(user4);
        username5.setText(user5);
        username6.setText(user6);

        petname1.setText(pet1);
        petname2.setText(pet2);
        petname3.setText(pet3);
        petname4.setText(pet4);
        petname5.setText(pet5);
        petname6.setText(pet6);
    }

}











