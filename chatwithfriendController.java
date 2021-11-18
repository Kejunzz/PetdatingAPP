package com.example.petdating;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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




public class chatwithfriendController {

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

    String currentusername;

    @FXML
    private Label sent;



    public void setparameter(String someusername) {
        this.currentusername=someusername;

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


    /**
     *
     * when check button was clicked, the messageinfo will update by changing check column from 0 to 1.
     * If check is 1, then it will not display in the notification section anymore.
     * @param  id, the username of clicked users.
     *
     *
     * */

    public void updatecheck(String id){

        DatabaseConnection connection = new DatabaseConnection();
        Connection connectdb = connection.getConnection();

        String sql = "update messageinfo set messageinfo.check=1 where messageinfo.username='"+id+"' and messageinfo.sentuser='"+username+"'";
        System.out.println(sql);
        try {

            Statement statement = connectdb.createStatement();

            try {
                statement.executeUpdate(sql);


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
        Parent fmxlloader = FXMLLoader.load(getClass().getResource("/com/example/petdating/messagenotification.fxml"));

        Scene scene = new Scene(fmxlloader);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Petdating");

        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * display the message sent to current user by clicked user.
     * @param  id, the username of clicked users.
     *
     *
     * */

    public void displaymessage(String id){

        DatabaseConnection connection = new DatabaseConnection();
        Connection connectdb = connection.getConnection();

        String sql="select message from messageinfo where sentuser='"+username+"' and username='"+id+"' and messageinfo.check=0 and messageinfo.ignore=0";
        System.out.println(sql);
        try {
            Statement statement = connectdb.createStatement();
            try {

                ResultSet resultSet=statement.executeQuery(sql);

                String total_message="";
                while (resultSet.next()){

                    System.out.println(resultSet.getString("message"));
                    total_message=total_message+resultSet.getString("message")+"\n";


                }

                frinedtext.setText(total_message);
                updatecheck(id);


            }catch (Exception e){
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
     * If current user sends a message to the clicked user, update the database by the message content, receiver and senter.
     * @param  event
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

    public void changesceneaftermatchfriend(ActionEvent event) throws IOException {


            Parent fmxlloader = FXMLLoader.load(getClass().getResource("/com/example/petdating/MainTable.fxml"));


            Scene scene = new Scene(fmxlloader);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Petdating");

            stage.setScene(scene);
            stage.show();





    }

    public void changesceneclickprofile(ActionEvent event) throws IOException {


        Parent fmxlloader = FXMLLoader.load(getClass().getResource("/com/example/petdating/editprofile.fxml"));

        Scene scene = new Scene(fmxlloader);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Petdating");

        stage.setScene(scene);
        stage.show();

    }


}
