package com.example.petdating;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static com.example.petdating.loginController.username;
import static com.example.petdating.matchfriendcontroller.currentusername;

public class chatcontroller {
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
    private ImageView myimage;

    @FXML
    private ImageView friendimage;

    @FXML
    private TextArea mytext;

    @FXML
    private TextArea frinedtext;

    @FXML
    private TextField entertest;

    @FXML
    private Label friendname;


    public String currentusername;

    @FXML
    private Label sent;

    /**
     *
     * get messages sent to current user.
     * @param  id, this is the user name which current user clicks.
     *
     *
     *
     * */


    public void setfriendtext(String id){
        String insertFields="select message from messageinfo where username='"+id+"' and sentuser='"+username+"' order by id desc limit 1";
        System.out.println(insertFields);
        String sql=insertFields;
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectdb = connection.getConnection();

        try {

            Statement statement = connectdb.createStatement();
            ResultSet queryResult = statement.executeQuery(sql);
            while (queryResult.next() ) {
                System.out.println(queryResult.getString("message"));
                frinedtext.setText(queryResult.getString("message"));

            }


        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    /**
     *
     *
     * @param  user1,user2...， passed the username and petname information from last controller
     *
     *
     *
     * */



    public void set(String user1,String user2,String user3,String user4,String user5,String user6,String pet1,String pet2,String pet3,String pet4,String pet5,String pet6){

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



    public void setparameter(String someusername) {

        currentusername=someusername;


    }


    /**
     *
     * get username, picurl of two users from database.
     * Display such information
     * @param  id, the username of clicked users.
     *
     *
     * */

    public void chatwithfriend(String id) {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectdb = connection.getConnection();

        String sql = "select username, picurl from petinfo where username='" + id + "'" ;
        System.out.println(sql);

        String sql2 = "select username, picurl from petinfo where username='" + username + "'" ;
        System.out.println(sql2);
        try {

            Statement statement = connectdb.createStatement();
            Statement statement1= connectdb.createStatement();

            try {
                ResultSet queryResult = statement.executeQuery(sql);
                ResultSet queryResult2 = statement1.executeQuery(sql2);

                while (queryResult.next() ) {
                    friendname.setText(queryResult.getString("username"));


                    System.out.println(queryResult.getString("picurl"));
                    String filepath=queryResult.getString("picurl").split(":")[1].trim();



                    File file = new File(filepath);
                    Image image = new Image(file.toURI().toString());

                    friendimage.setImage(image);


                }
                while(queryResult2.next()){
                    String filepath2=null;
                    try {
                        filepath2=queryResult2.getString("picurl").split(":")[1].trim();
                    }catch (Exception e){
                        filepath2="/Users/kezhou/Desktop/JAVA/PetdatingAPP/download.png";
                    }

                    File file2 = new File(filepath2);
                    Image image2 = new Image(file2.toURI().toString());
                    myimage.setImage(image2);
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
     * when user sends message, it will insert into database.
     * @param  event the action event, it is a click action.
     *
     *
     *
     * */


    public void sendmessage(ActionEvent event){
        String message=entertest.getText();
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectdb = connection.getConnection();


        String insertFields="INSERT INTO messageinfo(username,message,sentuser) values ('";
        String insertValues= username+"','"+message+"','"+currentusername+"')";
        String sql=insertFields+insertValues;

        try {
            Statement statement = connectdb.createStatement();
            try {
                statement.executeUpdate(sql);
                sent.setText("sent!");
                entertest.setText("");
                mytext.setText(message);
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
                sent.setText("error");
            }



        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

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



}
