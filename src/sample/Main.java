/*
   * Filename       Main.java
   * Date           11/11/20
   * Author         Shubh Singh Garg
   * Email          shubhsinghgarg@gmail.com
   * Version        1.1
   * Copyright 2020, All rights reserved
   *
   * Description

            Implement a solution that performs CRUD operations (Create, Read, Update, and Delete) for a Vehicle
                (automobile) entity.
            A Vehicle is a simple object defined as follows:
                You must implement the following methods:
                    GET vehicles
                    GET vehicles/{id}
                    CREATE vehicles
                    UPDATE vehicles
                    DELETE vehicles/{id}
            Additionally any solution must employ the following:
                    1) Usage of either C# or Java.
                    2) Some form of automated testing.
                    3) Some form of in-memory persistence of created vehicle objects

*/



package sample;

import java.util.*;
import java.sql.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.runner.*;
import org.junit.runner.notification.Failure;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Mitchell Coding Challenge");
        primaryStage.setScene(new Scene(root, 728, 845));
        primaryStage.show();
    }

    public static class Vehicle {
        public int Id;
        public int Year;
        public String Make;
        public String Model;

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public int getYear() {
            return Year;
        }

        public void setYear(int year) {
            Year = year;
        }

        public String getMake() {
            return Make;
        }

        public void setMake(String make) {
            Make = make;
        }

        public String getModel() {
            return Model;
        }

        public void setModel(String model) {
            Model = model;
        }
    }

    public static void main(String[] args) {
        launch(args);

        Result result = JUnitCore.runClasses(ControllerTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
            System.out.println("Result = " + result.wasSuccessful());
        }

    }
}
