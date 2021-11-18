package com.example.petdating;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;


import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class registerController{
    @FXML
    private Button cancelButton;

    @FXML
    private Label confirmpasswordmessage;

    @FXML
    private PasswordField setPasswordtextField;

    @FXML
    private PasswordField confirmpasswordtextField;

    @FXML
    private Label registermessage;


    @FXML
    private TextField petnamefield;

    @FXML
    private TextField petkindfield;

    @FXML
    private TextField usernametextField;

    @FXML
    private TextField petagefield;

    @FXML
    private TextField cityfield;


    /**
     * click the cancel button, the page will be closed
     *
     * @param  event the action event, it is a click action.
     *
     *
     * */
    public void cancelbutton(ActionEvent event){
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

    /**
     *
     * ask the users to fill in all the blank. And confirm the password, if passwords entered twice are not matched, throws an error.
     * @param  event the action event, it is a click action.
     *
     *
     *
     * */

    public void registerbutton(ActionEvent event){

        if (setPasswordtextField.getText().equals(confirmpasswordtextField.getText())){
            confirmpasswordmessage.setText("");

            if (petagefield.getText().isBlank()==false && petnamefield.getText().isBlank()==false && petkindfield.getText().isBlank()==false && cityfield.getText().isBlank()==false && usernametextField.getText().isBlank()==false){
                registerUser();

            }
            else{
                confirmpasswordmessage.setText("All blank is required");
            }



        }
        else {
            registermessage.setText("");
            confirmpasswordmessage.setText("Password does not match");

        }


    }




//        confirmpasswordmessage.setText("User has registered successfully");


    /**
     *
     * when new user register, his information will be updated to database.
     *
     *
     * */
    public void registerUser(){
        DatabaseConnection connection=new DatabaseConnection();
        Connection connectdb=connection.getConnection();

        String petname=petnamefield.getText();
        String petkind=petkindfield.getText();
        String petage=petagefield.getText();
        String petcity=cityfield.getText();
        String username=usernametextField.getText();
        String password=setPasswordtextField.getText();

        String insertFields="INSERT INTO petinfo(petname,petkind,username,age,city,password,petfood,petplace,pettoy,petgender,petcategory,petbirthday,friend,picurl) values ('";
        String insertValues=petname+"','"+petkind+"','"+username+"','"+petage+"','"+petcity+"','"+password+"',"+"'','','','','','','','')";
        String inserttoRegister=insertFields+insertValues;
        System.out.println(inserttoRegister);





        try{

            Statement statement=connectdb.createStatement();

            try{
                statement.executeUpdate(inserttoRegister);
                registermessage.setText("User has registered successfully");
            }catch (Exception e){
                confirmpasswordmessage.setText("User exists!");
            }



        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }




}
