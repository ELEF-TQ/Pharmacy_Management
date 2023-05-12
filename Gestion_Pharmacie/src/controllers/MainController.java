package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Duration;

public class MainController {

	//___________ Interface Controllers :
    @FXML
    private javafx.scene.layout.VBox VBox;
    private Parent Page ;
    
    //___________ Sign In :
    @FXML void On_SignIn() {
    	TranslateTransition T = new TranslateTransition(Duration.seconds(1), VBox);
    	T.setToX(VBox.getLayoutX()*7.2);
    	T.play();
    	T.setOnFinished(e -> {
    		try {
				Page = FXMLLoader.load(getClass().getResource("/interfaces/SignIn.fxml"));
			    VBox.getChildren().removeAll();
			    VBox.getChildren().setAll(Page);
    		} catch (IOException e1) {
    			System.out.print("Signin");
				e1.printStackTrace();
			}
    	});
    }

    //___________ Sign Up :
    @FXML void On_SignUp() {
    	TranslateTransition T = new TranslateTransition(Duration.seconds(1), VBox);
    	T.setToX(0);
    	T.play();
    	T.setOnFinished(e -> {
    		try {
    			Page = FXMLLoader.load(getClass().getResource("/interfaces/SignUp.fxml"));
			    VBox.getChildren().removeAll();
			    VBox.getChildren().setAll(Page);
    		} catch (IOException e1) {
				e1.printStackTrace();
			}
    	});
   
    }
    
    public void initialize(URL location , ResourceBundle resourses) {
    	TranslateTransition T = new TranslateTransition(Duration.seconds(1), VBox);
    	T.setToX(VBox.getLayoutX()*5.5);
    	T.play();

			try {
				Page = FXMLLoader.load(getClass().getResource("/interfaces/SignUp.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		    VBox.getChildren().removeAll();
		    VBox.getChildren().setAll(Page);
		
    }

}
