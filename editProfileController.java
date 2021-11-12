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
import java.util.Random;
import java.util.ResourceBundle;

import static com.example.petdating.loginController.username;


public class editProfileController {
//    loginController lc=new loginController();
    @FXML
    private Button Select;

    @FXML
    private Button Upload;

    @FXML
    private Label uploadsuccesslabel;

    @FXML
    private Stage stage;

    @FXML
    private Label Profile;

    @FXML
    private ImageView profileimginefield;

    @FXML
    private TextField petnamefield;


    @FXML
    private TextField foodfield;

    @FXML
    private TextField placefield;

    @FXML
    private TextField toyfield;

    @FXML
    private TextField genderfield;

    @FXML
    private TextField categoryfield;

    @FXML
    private TextField birthdayfield;

    @FXML
    private TextField friendfield;

    @FXML
    private Label savelabel;


    @FXML
    private String profilepicture;

    public void imageChooser(ActionEvent event){


        FileChooser fc=new FileChooser();

        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPEG file","*.jpg","*.png","*.JPG","*.PNG"));
        File f=fc.showOpenDialog(stage);

        if (f!=null){

            profileimginefield.setImage(new Image(f.toURI().toString()));
            profilepicture=f.toURI().toString();
        }
        else {
            uploadsuccesslabel.setText("You must upload Image file");
        }

    }

    public void uploadbutton(ActionEvent event){
        uploadsuccesslabel.setText("success");
    }



    public void saveProfile(ActionEvent event){
        DatabaseConnection connection=new DatabaseConnection();
        Connection connectdb=connection.getConnection();

        String petname=petnamefield.getText();
        String petfood=foodfield.getText();
        String petplace=placefield.getText();
        String pettoy=toyfield.getText();
        String petgender=genderfield.getText();
        String petcategory=categoryfield.getText();
        String petbirthday=birthdayfield.getText();
        String friend=friendfield.getText();


        String sql="update petinfo set petfood='"+petfood+"' ,petplace='"+petplace+"' ,pettoy='"+pettoy+"' , petgender='"+petgender+"' , petcategory='"+petcategory+"' , petbirthday='"+petbirthday+"' , friend='"+friend+"' , picurl='"+profilepicture+"' where username='"+username+"'";
//"INSERT INTO petinfo(petname,petkind,username,age,city,password,petfood,petplace,pettoy,petgender,petcategory,petbirthday,friend) values ('";
//        String inserttoRegister=insertFields+insertValues;
        System.out.println(sql);



        try{

            Statement statement=connectdb.createStatement();

            try{
                statement.executeUpdate(sql);
                savelabel.setText("Success!");

            }catch (Exception e){
                savelabel.setText("Failed!");
                e.printStackTrace();
                e.getCause();
            }



        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void backtomain(ActionEvent event){
        try{
            Parent fmxlloader=FXMLLoader.load(getClass().getResource("/com/example/petdating/emptymaintable.fxml"));

            Scene scene = new Scene(fmxlloader);

            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setTitle("Petdating");

            stage.setScene(scene);
            stage.show();


        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }




    }



}
