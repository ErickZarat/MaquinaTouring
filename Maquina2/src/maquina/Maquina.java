package maquina;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Maquina extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {        
        stage.setScene(new Scene(FXMLLoader.load(
                getClass().getResource("Principal.fxml"))));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
