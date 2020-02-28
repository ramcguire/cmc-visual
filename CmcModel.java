import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.scene.image.Image;
import com.google.gson.JsonObject;
import java.util.Date;

public class CmcModel {
	private JsonElement jse;
	private final String baseURL = "https://api.coinmarketcap.com/v2/ticker/";
	private String currentCoin;
	boolean validInput;
	
	
	//gets results from passed in zip code
	public boolean getResults(String inputCoin) {

		//construct api url
		String stringURL = baseURL + inputCoin + "/";
		currentCoin = inputCoin;
									
		try {
			//final query URL
			URL newURL = new URL(stringURL);
			HttpsURLConnection cn = (HttpsURLConnection) newURL.openConnection();
			
			//check response code (check for 404 error)
			//try to get ErrorStream as Json data
			cn.getResponseCode();
			InputStream is = cn.getErrorStream();
			//if no Error Stream, get data as usual
			if (is == null) {
				is = cn.getInputStream();
			}
			//pass to JsonParser
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			jse = new JsonParser().parse(br);
		
			//close connections
			is.close();
			br.close();		
			cn.disconnect();
		
						
		} catch (Exception e) {
			System.out.println("exception caught, returning false");
			return false;
		}
		validInput = isValid(inputCoin);
		return validInput;
		
	} //end getResults 
	
	public boolean isValid(String inputStr) {
		//check if input is integer value
		if (!isInteger(inputStr)) {
			return false;
		}
		//check for error in json
		if (jse != null) {
			String errorString = getError();			
			if ((errorString.equals("null"))) {
				return true;
			}
		}
		//if jse is null, return false
		return false;				
	} //end isValid
	
	public String getError() {
		if (!isInteger(currentCoin)) {
			return "invalid input";
		}
		
		Object error = jse.getAsJsonObject().get("metadata").getAsJsonObject().get("error");
		if (error.toString().equals("null")) {
			return "null";
		}
		return jse.getAsJsonObject().get("metadata").getAsJsonObject().get("error").getAsString();
	}
	
	public String getName() {
		return jse.getAsJsonObject().get("data").getAsJsonObject().get("name").getAsString();
	}
	
	public String getSymbol() {
		return jse.getAsJsonObject().get("data").getAsJsonObject().get("symbol").getAsString();
	}
	
	public String getRank() {
		return Integer.toString(jse.getAsJsonObject().get("data").getAsJsonObject().get("rank").getAsInt());
	}
	
	public String getCircSupply() {
		return (Long.toString(jse.getAsJsonObject().get("data").getAsJsonObject().get("circulating_supply").getAsLong()))
				+ " " + getSymbol();
	}
	
	public String getTotalSupply() {
		return (Long.toString(jse.getAsJsonObject().get("data").getAsJsonObject().get("total_supply").getAsLong()) +
				" " + getSymbol());
	}
	
	public String getMaxSupply() {
		//check if max supply is defined
		Object maxSupply = jse.getAsJsonObject().get("data").getAsJsonObject().get("max_supply");
		if(maxSupply.toString().equals("null")) {
			return "N/A - No defined max supply";
		}
		//max supply is defined, return value
		return (Long.toString(jse.getAsJsonObject().get("data").getAsJsonObject().get("max_supply").getAsLong()) +
				" " + getSymbol());
	}
	
	public String getPrice() {
		return Double.toString(jse.getAsJsonObject().get("data").getAsJsonObject().get("quotes").getAsJsonObject().get("USD")
				.getAsJsonObject().get("price").getAsDouble());
	}
	
	public String getTimestamp() {
		//if input invalid, return blank
		if (!validInput) {
			return "";
		}
		long numTime = jse.getAsJsonObject().get("metadata").getAsJsonObject().get("timestamp").getAsLong();
		Date time = new Date((long)numTime*1000);
		return time.toString();
	}
	
	public String getVolume() {
		return Long.toString(jse.getAsJsonObject().get("data").getAsJsonObject().get("quotes").getAsJsonObject().get("USD")
				.getAsJsonObject().get("volume_24h").getAsLong());
	}
	
	public String getMarketCap() {
		return Long.toString(jse.getAsJsonObject().get("data").getAsJsonObject().get("quotes").getAsJsonObject().get("USD")
				.getAsJsonObject().get("market_cap").getAsLong());
	}
	
	public String getPercentChange1h() {
		return Double.toString(jse.getAsJsonObject().get("data").getAsJsonObject().get("quotes").getAsJsonObject().get("USD")
				.getAsJsonObject().get("percent_change_1h").getAsDouble());
	}
	
	public String getPercentChange24h() {
		return Double.toString(jse.getAsJsonObject().get("data").getAsJsonObject().get("quotes").getAsJsonObject().get("USD")
				.getAsJsonObject().get("percent_change_24h").getAsDouble());
	}
	
	public String getPercentChange7d() {
		return Double.toString(jse.getAsJsonObject().get("data").getAsJsonObject().get("quotes").getAsJsonObject().get("USD")
				.getAsJsonObject().get("percent_change_7d").getAsDouble());
	}
	
	public Image getGraph7d() {
		return new Image("https://s2.coinmarketcap.com/generated/sparklines/web/7d/usd/" + currentCoin + ".png");
	}
	
	public Image getGraph1d() {
		return new Image("https://s2.coinmarketcap.com/generated/sparklines/web/1d/usd/" + currentCoin + ".png");
	}
	
	public Image getImage() {
		String imageURL = "https://s2.coinmarketcap.com/static/img/coins/128x128/" + currentCoin + ".png";
		return new Image(imageURL);
	}
	
	//local method to check if input can be converted to integer value
	public static boolean isInteger(String s) {
		boolean isValidInteger = false;
		try
		{
			Integer.parseInt(s);	 
			// s is a valid integer	 
			isValidInteger = true;
		}
		catch (Exception ex)
		{
			// s is not an integer
			return false;
		}
	 
	      return isValidInteger;
	} //end isInteger
		    
} //end WxModel    



