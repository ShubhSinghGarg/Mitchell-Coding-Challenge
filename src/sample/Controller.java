/*
   * Filename       Controller.java
   * Date           11/11/20
   * Author         Shubh Singh Garg
   * Email          shubhsinghgarg@gmail.com
   * Version        3.2
   * Copyright 2020, All rights reserved
   *
   * Description

            This is the java class to control the logic behind the GUI created using JAVAFx.

*/

package sample;

import java.util.*;
import java.sql.*;
import javafx.scene.control.*;

public class Controller {

    // All the GUI elements for Create Tab
    public TextArea TA_create_ID;
    public TextArea TA_create_year;
    public TextArea TA_create_model;
    public TextArea TA_create_make;

    public Button btn_create_submit;
    public Button btn_create_clear;

    public Label lab_result_create;

    // All the GUI elements for Get Tab
    public TextArea TA_get_ID;

    public Button btn_get_submit;

    public Label lab_get_result;
    public Label lab_get_result_tag;

    // All the GUI elements for Update Tab
    public TextField txf_update_col;
    public TextField txf_update_val;

    public Button btn_update_submit;

    public Label lab_update_result;


    // All the GUI elements for Delete Tab
    public TextArea TA_delete_ID;

    public Button btn_delete_submit;

    public Label lab_delete_result;


    //The function called when you hit submit on the Create tab
    public void SubmitCreate() {

        int id = Integer.parseInt(TA_create_ID.getText());
        int year = Integer.parseInt(TA_create_year.getText());
        String model = TA_create_model.getText();
        String make = TA_create_make.getText();

        // Validation check
        if (model.isEmpty() || make.isEmpty()) {
            lab_result_create.setText("!!! Model and Make of the vehicle can not be empty !!!");
        }

        // Validation check
        else if (year < 1950 || year > 2050) {
            lab_result_create.setText("!!! Year of the vehicle must be between 1950 and 2050 !!!");
        }

        // If everything is okay then create the entry in the database.
        else {
            try {

                //establishing a connection to the database
                Connection myConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vehicle_schema", "Shubh", "ShubhIsWorking");

                //Create a statement
                Statement myStatement = myConnection.createStatement();

                String query = "insert into vehicles values ('" + id + "', '" + year + "', '" + model + "', '" + make + "')";
                myStatement.executeUpdate(query);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            TA_create_ID.setText("");
            TA_create_year.setText("");
            TA_create_model.setText("");
            TA_create_make.setText("");

            lab_result_create.setText("Entry Created:- " +
                    "\n\t ID: " + id +
                    "\n\t Year: " + year +
                    "\n\t Model: " + model +
                    "\n\t Make: " + make);

        }
    }

    // If the clear button is clicked
    public void ClearCreate() {
        TA_create_ID.setText("");
        TA_create_year.setText("");
        TA_create_model.setText("");
        TA_create_make.setText("");

        lab_result_create.setText("Entries Cleared");

    }


    // The function that executes if the search button is pressed on get tab
    public void SearchGet() {

       String input = TA_get_ID.getText();

       // if the user wants to get all the entries
       if (input.equals("*")){
           try {

               //establishing a connection to the database
               Connection myConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vehicle_schema", "Shubh", "ShubhIsWorking");

               //Create a statement
               Statement myStatement = myConnection.createStatement();

               ResultSet myResultSet = myStatement.executeQuery("select * from vehicles");

               // to set the formatting of the result
               lab_get_result_tag.setVisible(true);
               lab_get_result.setText("S.No \t\t ID \t\t Year \t\t Model \t\t Make \n");

               int i = 1;

               // because there can be more than one result
               while (myResultSet.next()){
                   String entry = i + " \t \t \t " + myResultSet.getString("ID") + " \t \t" + myResultSet.getString("year")+ " \t \t" + myResultSet.getString("model")+ " \t \t" + myResultSet.getString("make");
                   lab_get_result.setText(lab_get_result.getText() + "\n" + entry);

                   i++;

               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }

       // If the user is searching for a particular ID.
       else {
           try {

               //establishing a connection to the database
               Connection myConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vehicle_schema", "Shubh", "ShubhIsWorking");

               //Create a statement
               Statement myStatement = myConnection.createStatement();

               int id = Integer.parseInt(input);
               ResultSet myResultSet = myStatement.executeQuery("select * from vehicles where ID='" + id + "'");

               // Formatting the result
               lab_get_result_tag.setVisible(true);
               lab_get_result.setText("S.No \t\t ID \t\t Year \t\t Model \t\t Make \n");

               int i = 1;

               // there could be more than one vehicle with the given ID.
               while (myResultSet.next()) {

                   String entry = i + " \t\t\t" + myResultSet.getString("ID") + " \t \t" + myResultSet.getString("year") + " \t \t" + myResultSet.getString("model") + " \t \t" + myResultSet.getString("make");

                   lab_get_result.setText(lab_get_result.getText() + "\n" + entry);

                   i++;

               }

           } catch (SQLException e) {
               e.printStackTrace();
           }
       }

    }


    public void UpdateSubmit() {
        String field = txf_update_col.getText();

        // the Field statement can be typed in multiple ways
        switch (field) {
            case "id":
            case "ID":
            case "Id":
                field = "ID";
                break;
            case "year":
            case "YEAR":
            case "Year":
                field = "year";
                break;
            case "model":
            case "MODEL":
            case "Model":
                field = "model";
                break;
            case "make":
            case "MAKE":
            case "Make":
                field = "make";
                break;
        }


        if (field.equals("ID") || field.equals("year")){
            int value = Integer.parseInt(txf_update_val.getText());

            try {

                //establishing a connection to the database
                Connection myConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vehicle_schema", "Shubh", "ShubhIsWorking");

                //Create a statement
                Statement myStatement = myConnection.createStatement();

                String query = "update vehicles set " + field + "='" + value + "'";
                myStatement.executeUpdate(query);

                lab_update_result.setText(field + "updated to " + value);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        else {
            String value = txf_update_val.getText();

            try {

                //establishing a connection to the database
                Connection myConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vehicle_schema", "Shubh", "ShubhIsWorking");

                //Create a statement
                Statement myStatement = myConnection.createStatement();

                String query = "update vehicles set " + field + "='" + value + "'";
                myStatement.executeUpdate(query);

                lab_update_result.setText(field + "updated to " + value);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }


    public void DeleteSubmit() {
        int id = Integer.parseInt(TA_delete_ID.getText());

        String query = "delete from vehicles where ID='" + id + "'";
        try {

            //establishing a connection to the database
            Connection myConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vehicle_schema", "Shubh", "ShubhIsWorking");

            //Create a statement
            Statement myStatement = myConnection.createStatement();

            int rowsAffected = myStatement.executeUpdate(query);

            lab_delete_result.setText("Rows affected: " + rowsAffected + "\n Delete Complete");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
