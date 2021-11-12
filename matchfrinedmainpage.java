package com.example.petdating;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;

import info.debatty.java.stringsimilarity.*;


import static com.example.petdating.loginController.username;

public class matchfrinedmainpage implements Initializable {

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
    private String petname;

    private String user1;

    private String user2;
    private String user3;
    private String user4;
    private String user5;
    private String user6;

    private String pet1;
    private String pet2;
    private String pet3;
    private String pet4;
    private String pet5;
    private String pet6;



    @FXML
    public String usernametemp;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        matchfriendbutton();




    }
//


    public void changesceneafterclickmore(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/petdating/profiledetail.fxml"));
        Parent root = (Parent) loader.load();

        matchfriendcontroller matchfriend = loader.getController();

        final Node source = (Node) event.getSource();
        String id = source.getId();
        System.out.println(id);

        if (id.equals("morebutton1")){
            usernametemp=username1.getText();
        }
        else if(id.equals("morebutton2")){
            usernametemp=username2.getText();
        }
        else if(id.equals("morebutton3")){
            usernametemp=username3.getText();
        }
        else if(id.equals("morebutton4")){
            usernametemp=username4.getText();
        }
        else if(id.equals("morebutton5")){
            usernametemp=username5.getText();
        }
        else if(id.equals("morebutton6")){
            usernametemp=username6.getText();
        }

        otherpetprofile(usernametemp);
        matchfriend.otherpetprofile(usernametemp);
        matchfriend.setparameter(usernametemp);
        matchfriend.set(user1,user2,user3,user4,user5,user6,pet1,pet2,pet3,pet4,pet5,pet6);

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Petdating");

        stage.setScene(scene);
        stage.show();


    }


    public void changesceneafterclickchat(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/petdating/chat.fxml"));
        Parent root = (Parent) loader.load();

        chatcontroller chatwithfriend = loader.getController();

        final Node source = (Node) event.getSource();
        String id = source.getId();
        System.out.println(id);

        if (id.equals("chatbutton1")){
            usernametemp=username1.getText();
        }
        else if(id.equals("chatbutton2")){
            usernametemp=username2.getText();
        }
        else if(id.equals("chatbutton3")){
            usernametemp=username3.getText();
        }
        else if(id.equals("chatbutton4")){
            usernametemp=username4.getText();
        }
        else if(id.equals("chatbutton5")){
            usernametemp=username5.getText();
        }
        else if(id.equals("chatbutton6")){
            usernametemp=username6.getText();
        }

//        otherpetprofile(usernametemp);
        chatwithfriend.chatwithfriend(usernametemp);
        chatwithfriend.setparameter(usernametemp);
        chatwithfriend.setfriendtext(usernametemp);
        chatwithfriend.set(user1,user2,user3,user4,user5,user6,pet1,pet2,pet3,pet4,pet5,pet6);





        Scene scene = new Scene(root);

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


    public void matchfriendbutton() {

        DatabaseConnection connection = new DatabaseConnection();
        Connection connectdb = connection.getConnection();

        String sql = "select distinct username,petname,similarity from petinfo where username !='"+username+"' order by similarity desc ";
        System.out.println(sql);
        similaritycal();
//        System.out.println();

        try {

            Statement statement = connectdb.createStatement();

            try {
                ResultSet queryResult = statement.executeQuery(sql);

                int row = 0;
                Label[] usernamelist = new Label[]{username1, username2, username3, username4, username5, username6};
                Label[] petnamelist = new Label[]{petname1, petname2, petname3, petname4, petname5, petname6};
                int count = 0;
                while (queryResult.next() && count < 6) {

//                    System.out.println(queryResult.getString("username"));
//                    System.out.println(usernamelist[0].getText());
                    usernamelist[row].setText(queryResult.getString("username"));
                    petnamelist[row].setText(queryResult.getString("petname"));
                    row++;
                    count++;
                }

                getallmatcheduser(usernamelist,petnamelist);
//                c.set();




            } catch (Exception e) {

                e.printStackTrace();
                e.getCause();
            }


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void getallmatcheduser(Label[] user,Label[] pet){
        System.out.println("TEST:USERNAME");
        System.out.println(user[0].getText());
//        System.out.println(username1.getText());

        user1=user[0].getText();
        user2=user[1].getText();
        user3=user[2].getText();
        user4=user[3].getText();
        user5=user[4].getText();
        user6=user[5].getText();

        pet1=pet[0].getText();

        System.out.println(pet1);

        pet2=pet[1].getText();
        pet3=pet[2].getText();
        pet4=pet[3].getText();
        pet5=pet[4].getText();
        pet6=pet[5].getText();
    }


    public void otherpetprofile(String id) {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectdb = connection.getConnection();

        String sql = "select * from petinfo where username='" + id + "'";
        System.out.println(sql);
        try {

            Statement statement = connectdb.createStatement();

            try {
                ResultSet queryResult = statement.executeQuery(sql);
                while (queryResult.next()) {
                    petname=queryResult.getString("petname");
                    System.out.println(petname);
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

    public void similaritycal(){
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectdb = connection.getConnection();
        String sql = "select * from petinfo where username='"+username+"'";
        String sql2 = "select * from petinfo where username!='"+username+"'";
        System.out.println(sql);

        String petfood1;

        try {

            Statement statement = connectdb.createStatement();
            Statement statement2=connectdb.createStatement();
            try{
                ResultSet queryResult = statement.executeQuery(sql);
                ResultSet queryResult2 = statement2.executeQuery(sql2);

                while (queryResult.next()){
                    String petkind1=queryResult.getString("petkind");
                    String age1=queryResult.getString("age");
                    String city1=queryResult.getString("city");
                    petfood1=queryResult.getString("petfood");
                    String petplace1=queryResult.getString("petplace");
                    String pettoy1=queryResult.getString("pettoy");
                    String petgender1=queryResult.getString("petgender");
                    String petcategory1=queryResult.getString("petcategory");
                    String petbirthday1=queryResult.getString("petbirthday");
                    String petfriend1=queryResult.getString("friend");

//                    System.out.println(petfood1);
                    while (queryResult2.next()){
                        String user=queryResult2.getString("username");
                        String petkind2=queryResult2.getString("petkind");
                        String age2=queryResult2.getString("age");
                        String city2=queryResult2.getString("city");
                        String petfood2=queryResult2.getString("petfood");
                        String petplace2=queryResult2.getString("petplace");
                        String pettoy2=queryResult2.getString("pettoy");
                        String petgender2=queryResult2.getString("petgender");
                        String petcategory2=queryResult2.getString("petcategory");
                        String petbirthday2=queryResult2.getString("petbirthday");
                        String petfriend2=queryResult2.getString("friend");


                        Cosine cos=new Cosine();

                        double agesim;
                        double kindsim;
                        double citysim;
                        double foodsim;
                        double placesim;
                        double toysim;
                        double categorysim;
                        double birthdaysim;
                        double friendsim;
                        double gendersim;

                        if (age1.equals(null)||age2.equals(null)){
                            agesim=0;
                        }
                        else{
                            agesim=cos.similarity(age1.toLowerCase(),age2.toLowerCase());
                        }

                        if (petkind1.equals(null)||petkind2.equals(null)){
                            kindsim=0;
                        }else {
                            kindsim=cos.similarity(petkind1.toLowerCase(),petkind2.toLowerCase());
                        }



                        if (city1.isBlank()||city2.isBlank()){
                            citysim=0;
                        }else {
                            citysim=cos.similarity(city1.toLowerCase(),city2.toLowerCase());
                        }


                        if (petfood1==null||petfood2==null){
                            foodsim=0;
                        }else {
                            foodsim=cos.similarity(petfood1.toLowerCase(),petfood2.toLowerCase());
                        }


                        if (petplace1.isBlank()||petplace2.isBlank()){
                            placesim=0;
                        }else {
                            placesim=cos.similarity(petplace1.toLowerCase(),petplace2.toLowerCase());
                        }


                        if (pettoy1.isBlank()||pettoy2.isBlank()){
                            toysim=0;
                        }else {
                            toysim=cos.similarity(pettoy1.toLowerCase(),pettoy2.toLowerCase());
                        }


                        if (petcategory1.isBlank()||petcategory1.isBlank()){
                            categorysim=0;
                        }else {
                            categorysim=cos.similarity(petcategory1.toLowerCase(),petcategory2.toLowerCase());
                        }



                        if (petbirthday1.isBlank()||petbirthday2.isBlank()){
                            birthdaysim=0;
                        }
                        else {
                            birthdaysim=cos.similarity(petbirthday1.toLowerCase(),petbirthday2.toLowerCase());
                        }


                        if (petfriend1.isBlank()||petfriend2.isBlank()){
                            friendsim=0;
                        }
                        else{
                            friendsim=cos.similarity(petfriend1.toLowerCase(),petfriend2.toLowerCase());
                        }

//

                        try {
                            if (petgender1.equals(petgender2)){
                                gendersim=1;
                            }
                            else gendersim=0;
                        }catch (Exception e){
                            gendersim=0;
                        }



                        double total_similarity=2*gendersim+6*kindsim+2*agesim+foodsim+3*citysim+1*placesim+toysim+2*categorysim+1*birthdaysim+2*friendsim;
                        System.out.println(user+":"+gendersim+" "+kindsim+" "+agesim+" "+foodsim+" "+citysim+" "+placesim+" "+toysim+" "+categorysim+" "+birthdaysim+" "+friendsim);
                        String update_sim="update petinfo set similarity="+total_similarity+" where username='" + user + "'";
                        System.out.println(update_sim);
                        System.out.println();
                        Statement statement3 = connectdb.createStatement();
                        statement3.executeUpdate(update_sim);



                    }
                }




//                Levenshtein l = new Levenshtein();
//                System.out.println(l.distance(petfood1,petfood2));




            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }



        }catch (Exception e){
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