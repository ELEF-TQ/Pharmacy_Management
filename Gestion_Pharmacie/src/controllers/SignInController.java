package controllers;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import com.mysql.jdbc.Statement;
import database.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;


public class SignInController implements Initializable {
	
	//___________ Database Connection :
	public Connection con ;
	public Statement statement;
	public ResultSet resault;

	//___________ Interface Controllers :
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;

    //___________ Sign In :
    @FXML void On_SignIn() {
		String username = usernameField.getText();
		String password = passwordField.getText();
		if (username.isEmpty() || password.isEmpty()) {
			showAlert(AlertType.ERROR, "input vide", "Veuillez entrer le nom d’utilisateur et le mot de passe.");
			return;
		}
		try {
			String query = "SELECT * FROM users WHERE username = ? AND password = ?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			resault = statement.executeQuery();

			if (resault.next()) {
				Stage stage = (Stage) usernameField.getScene().getWindow();
				Parent root = null;
				try {
					root = FXMLLoader.load(getClass().getResource("/interfaces/Home.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				stage.setScene(new Scene(root));
				stage.centerOnScreen();	
			} else {
				showAlert(AlertType.ERROR, "informations non valides", "Veuillez entrer un nom d’utilisateur et un mot de passe valides.");
			}

			resault.close();
			statement.close();
		} catch (SQLException e) {
			 Alert alert = new Alert(AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("erreur de connexion");
             alert.setContentText("Échec de la connexion à la base de données. Veuillez vérifier vos paramètres de connexion.");
             alert.showAndWait();
		}
	}

	//___________ Forgot Password :
	@FXML void On_forgotPass() {
	    TextInputDialog dialog = new TextInputDialog();
	    dialog.setTitle("Mot de passe oubliée");
	    dialog.setHeaderText("Enter votre adresse email ");
	    dialog.setContentText("Email:");

	    Optional<String> result = dialog.showAndWait();
	    if (result.isPresent()) {
	        String emailAddress = result.get();

	        try {
	            String query = "SELECT * FROM users WHERE email = ?";
	            PreparedStatement statement = con.prepareStatement(query);
	            statement.setString(1, emailAddress);

	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                showAlert(AlertType.INFORMATION, "Email envoyé", "Nom d'utilisateur et mot de passe ont été envoyés à votre adresse email.");
	            } else {
	                showAlert(AlertType.ERROR, "Email incorrect", "L'adresse email que vous avez saisie n'est pas enregistrée.");
	            }
	            resultSet.close();
	            statement.close();
	        } catch (SQLException e) {
	        	 Alert alert = new Alert(AlertType.ERROR);
	                alert.setTitle("Erreur");
	                alert.setHeaderText("erreur de connexion");
	                alert.setContentText("Échec de la connexion à la base de données. Veuillez vérifier vos paramètres de connexion.");
	                alert.showAndWait();
	        }
	    }
	}
	
	//___________ Alert Showing :
	private void showAlert(AlertType alertType, String title, String content) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		con = DBConnection.connect();
	}
}




