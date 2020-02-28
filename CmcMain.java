import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class CmcMain extends Application {
	
	@Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./CmcView.fxml"));
        
        Scene scene = new Scene(root);
        
        //prepare window/set title
        stage.setScene(scene);
        stage.setTitle("CoinMarketCap - Ryan McGuire");
        stage.show();
        
	}
	
	public static void main(String[] args) {
        
		
		launch(args);

    } //end main (launch)
	
} //end CmcMain