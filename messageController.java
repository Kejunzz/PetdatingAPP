package com.example.petdating;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import static com.example.petdating.loginController.username;

/**
 *
 * This controller will control the total messages received by current users.
 * It will be displayed by who sent xxx messages to you.
 *
 *
 *
 * */


public class messageController {


    @FXML
    AnchorPane button_pane;

    Button[] check_button=new Button[1000];

    Label[] messageLabel=new Label[1000];


    public void initialize() throws IOException {
        fromwho();


    }

    /**
     *
     * fromwho method helps to update the UI based on how many messages you received.
     * If user click one user, scene will change.
     * After scene changed, you can chat with him/her. The function is from chatwithfriendcontroller.
     * Call it by using chatwithfriendController controller = loader.getController()
     *
     *
     *
     *
     * */


    public void fromwho() throws IOException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectdb = connection.getConnection();

        String sql = "select username, count(*) as cnt from messageinfo where sentuser='" + username + "' and messageinfo.check=0 and messageinfo.ignore=0 group by username";
        System.out.println(sql);
        try {

            Statement statement = connectdb.createStatement();
            try {
                int index=1;
                ResultSet queryResult = statement.executeQuery(sql);

                int i=0;
                while (queryResult.next()) {

                    String from=queryResult.getString("username");
                    String number=queryResult.getString("cnt");

                    System.out.println("You received " +number+" messages from "+ from);

//                    sentuser[i]=from;

                    messageLabel[i]=new Label("You received " +number+" messages from "+ from);
                    messageLabel[i].setFont(new Font("Chalkboard SE Light", 16));


                    AnchorPane.setTopAnchor(messageLabel[i], 20.0*(index+1.5));
                    AnchorPane.setLeftAnchor(messageLabel[i], 30.0);

                    check_button[i] = new Button("check");
//                    ignore_button[i]=new Button("ignore");

                    check_button[i].setOnAction(new EventHandler<ActionEvent>(){
                        public void handle(ActionEvent event){
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/petdating/chatwithfriend.fxml"));
                                Parent root = (Parent) loader.load();

                                chatwithfriendController controller = loader.getController();

                                controller.chatwithfriend(from);
                                controller.setparameter(from);
                                controller.displaymessage(from);

                                Scene scene = new Scene(root);

                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                                stage.setTitle("Petdating");

                                stage.setScene(scene);
                                stage.show();





                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }

                    });
//
                    AnchorPane.setTopAnchor(check_button[i], 20.0*(index+1.5));
                    AnchorPane.setRightAnchor(check_button[i], 90.0);

//

                    Line line= new Line(-121.99999237060547, 20*(index+5), 720, 20*(index+5));
                    line.setOpacity(0.22);

                    button_pane.getChildren().addAll(messageLabel[i],check_button[i],line);
                    i+=1;
                    index+=6;




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
