package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label notifiTextField;

    @FXML
    void onLogin() {
        notifiTextField.setText(String.format("user name: %s start login",userNameTextField.getText()));




        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@199.212.26.208:1521:sqld", "comp214_f19_ers_63", "password")) {

            if (connection != null) {
                System.out.println("Connected to the database!");
                Statement statement = connection.createStatement();
                statement.executeQuery("select IDSHOPPER, FIRSTNAME, LASTNAME from bb_shopper where IDSHOPPER = fun_authen_user('kids2','steel')");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
