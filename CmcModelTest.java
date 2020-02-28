import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CmcModelTest {

	
	//initialize model class
	CmcModel cmc = new CmcModel();
	
	@Test
	public void testgetBTC() {
		cmc.getResults("1"); //get results for BTC
		assertEquals("BTC", cmc.getSymbol());
	}
	
	@Test
	public void testInvalidCoin() {
		assertEquals(false, cmc.getResults("65465")); //getResults should return error for 65465 (invalid coin id)
		
	}
	
	@Test
	public void testTotalSupply() {
		cmc.getResults("1765"); //get results for EOS coin (with static supply)
		assertEquals("900000000 EOS", cmc.getTotalSupply());
	}
	
	
	
} //end class