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


import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;


/**
 * loginController controls the login page. The login page has 5 methods.
 * There are loginbuttonOnaction, validatelogin,registerbuttonAction,createAccount and changeSceneafterlogin.
 * It asks the user to enter their username and password. Check if there are matched. If not, throws a warning
 * If users have no username, ask them to register. When click the register button, another scene will come out.
 * When click the log in button, main page will show up.
 *
 * **/

public class loginController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private Label loginmessagelabel;

    @FXML
    private ImageView brandingimageviewer;

    @FXML
    private ImageView loginimageviewer;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private Stage stage;

    @FXML
    private Scene scene;

    @FXML
    private Parent root;

    @FXML
    static String username;


    @Override
    /**
     *
     * show the two customized image of login page.
     * @param  url  an absolute URL giving the base location of the image
     * @see   image
     *
     *
     * */
    public void initialize(URL url, ResourceBundle resourceBundle){
        File branding=new File("Petbarn-National-Pet-Dating-Day.png");
        Image brandingimage=new Image(branding.toURI().toString());
        brandingimageviewer.setImage(brandingimage);

        File login=new File("download.png");
        Image loginimage=new Image(login.toURI().toString());
        loginimageviewer.setImage(loginimage);


    }

    /**
     *
     * validate the users' username and password. If not match, throws an error.
     * @param  event the action event, it is a click action.
     *
     *
     *
     * */
    public void loginbuttonOnaction(ActionEvent event) throws IOException {


        if (usernameTextField.getText().isBlank()==false && passwordfield.getText().isBlank()==false){
            this.username=validatelogin(event);

        }
        else{
            loginmessagelabel.setText("Please enter the username and password");
        }
    }



    public void registerbuttonAction(ActionEvent event){
        createAccount();

    }


    /**
     *
     * validate the users' username and password extracted from the database. If not match, throws an error. If match,
     * the current username will be updated.
     * @param  event the action event, it is a click action.
     * @return username the current user name.
     *
     *
     * */
    public String validatelogin(ActionEvent event){
        DatabaseConnection connection=new DatabaseConnection();
        Connection connectDB=connection.getConnection();


        String veriftlogin="select count(1) from petinfo where username='"+usernameTextField.getText()+"' and password='"+ passwordfield.getText()+"'";

        try{
            Statement statement=connectDB.createStatement();
            ResultSet queryResult=statement.executeQuery(veriftlogin);

            while (queryResult.next()){
                if (queryResult.getInt(1)==1) {
                    username=usernameTextField.getText();
                    System.out.println(username);
                    loginmessagelabel.setText("Congratulation");
                    changeSceneafterlogin(event);


                }
                else{
                    loginmessagelabel.setText("Invalid Login, try again. \n If you don't have an account, register one!");
                }

            }



        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return username;
    }

    /**
     *
     * Click the register button, change the scene to register page.
     *
     *
     *
     * */

    public void createAccount(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("/com/example/petdating/register.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 712, 680);

            Stage registerstage=new Stage();

            registerstage.setTitle("registerform");

            registerstage.setScene(scene);
            registerstage.show();

        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }




    }
    /**
     * click the log in page, the scene will change to register page.
     *
     * @param  event the action event, it is a click action.
     *
     *
     *
     * */

    public void changeSceneafterlogin(ActionEvent event) throws IOException {

        Parent fmxlloader=FXMLLoader.load(getClass().getResource("/com/example/petdating/emptymaintable.fxml"));

        Scene scene = new Scene(fmxlloader);
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setTitle("Petdating");

        stage.setScene(scene);
        stage.show();





    }



}