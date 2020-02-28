import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ProgressIndicator;

public class CmcController {
	
	@FXML
	private Button btnsubmit;
	
	@FXML
	private Label name;
	
	@FXML
	private Label symbol;
	
	@FXML
	private Label rank;
	
	@FXML
	private Label price;
	
	@FXML
	private Label volume;
	
	@FXML
	private Label marketcap;
	
	@FXML
	private Label circulating;
	
	@FXML
	private Label total;
	
	@FXML
	private Label max;
	
	@FXML
	private Label change1h;
	
	@FXML
	private Label change24h;
	
	@FXML
	private Label change7d;
	
	@FXML
	private Label timestamp;
	
	@FXML
	private Label lblupdated;
	
	@FXML
	private ImageView icon;
	
	@FXML
	private ImageView graph1d;
	
	@FXML
	private ImageView graph7d;
	
	@FXML
	private TextField txtinput;
	
	@FXML
	private ProgressIndicator pi; 
	
	@FXML
	private void handleButtonPress(ActionEvent e) {
		final String eStr = "*";
		final String updateStr ="(updated every 5 min)";
		
		//initialize model class
		CmcModel cmc = new CmcModel();
		
		//check if button has been pressed
		if (e.getSource() == btnsubmit) {
			String inputCoin = txtinput.getText();
			
			//if weather returns valid results
			if (cmc.getResults(inputCoin)) {
				clearInterface();
				name.setText(cmc.getName());
				symbol.setText(cmc.getSymbol());
				rank.setText(cmc.getRank());
				price.setText("$" + cmc.getPrice());
				volume.setText("$" + cmc.getVolume());
				marketcap.setText("$" + cmc.getMarketCap());
				circulating.setText(cmc.getCircSupply());
				total.setText(cmc.getTotalSupply());
				max.setText(cmc.getMaxSupply());
				change1h.setText(cmc.getPercentChange1h());
				change24h.setText(cmc.getPercentChange24h());
				change7d.setText(cmc.getPercentChange7d());				
				icon.setImage(cmc.getImage());
				graph1d.setImage(cmc.getGraph1d());
				graph7d.setImage(cmc.getGraph7d());
				icon.setVisible(true);
				graph1d.setVisible(true);
				graph7d.setVisible(true);
				lblupdated.setText(updateStr);
				timestamp.setText(cmc.getTimestamp());				
			}
			
			//if cmc returns false fails
			else {
				//show error icon and reset all values
				clearInterface();
				name.setText(eStr);
				symbol.setText(cmc.getError());
				rank.setText(eStr);
				price.setText(eStr);
				volume.setText(eStr);
				marketcap.setText(eStr);
				circulating.setText(eStr);
				total.setText(eStr);
				max.setText(eStr);
				change1h.setText(eStr);
				change24h.setText(eStr);
				change7d.setText(eStr);				
				icon.setImage(new Image("error.png"));
				graph1d.setImage(new Image("error.png"));
				graph7d.setImage(new Image("error.png"));
				icon.setVisible(true);
				graph1d.setVisible(true);
				graph7d.setVisible(true);
				lblupdated.setText(updateStr);
				timestamp.setText(eStr);
			}
												
		}
		
	} //end handleButtonPress
	
	public void clearInterface() {
		
		name.setText("");
		symbol.setText("Enter Valid Coin ID");
		rank.setText("");
		price.setText("");
		volume.setText("");
		marketcap.setText("");
		circulating.setText("");
		total.setText("");
		max.setText("");
		change1h.setText("");
		change24h.setText("");
		change7d.setText("");				
		icon.setVisible(false);
		graph1d.setVisible(false);
		graph7d.setVisible(false);
		lblupdated.setText("");
		timestamp.setText("");
		
	} //end clearInterface
		
} //end CmcController