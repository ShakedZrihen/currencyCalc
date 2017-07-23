package ac.shenkar.Calc;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class ClientApp {
	private static TableGUI tb;
	private static XmlParse xmlparse;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			xmlparse=new XmlParse();
			Thread t1=new Thread(xmlparse);
			t1.start();
		} catch (IOException | ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		tb=new TableGUI(xmlparse.getCurrencyTable());
	}

}
