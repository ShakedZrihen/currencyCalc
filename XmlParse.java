package ac.shenkar.Calc;
import java.net.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;


public class XmlParse implements Runnable{
	
	private NodeList name;
	private NodeList unit;
	private NodeList code;
	private NodeList country;
	private NodeList rate;
	private NodeList change;
	private DocumentBuilderFactory factory; 
	private DocumentBuilder builder;
	URL url;
	private CurrencyTable currTable;
	
	
	public XmlParse() throws IOException, ParserConfigurationException, SAXException
	{
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();		
		url = new URL("http://www.boi.org.il/currency.xml");
		if(url == null)
		{
			System.out.println("error!");
			return;
		}
		init();
		currTable=new CurrencyTable(name.getLength()); 
	}
	
	@Override
	public void run() {
		try {
			init();
			System.out.println("\n"+"name" + "	unit" + "	code" + "	country" + "		rate		change");
			int length = name.getLength();
			for (int i = 0; i < length; i++) {
				currTable.addElement(i, new Currency(
						name.item(i).getFirstChild().getNodeValue()
						,Integer.parseInt(unit.item(i).getFirstChild().getNodeValue())
						,code.item(i).getFirstChild().getNodeValue()
						,country.item(i).getFirstChild().getNodeValue()
						,Double.parseDouble(rate.item(i).getFirstChild().getNodeValue())
						,Double.parseDouble(change.item(i).getFirstChild().getNodeValue())));
			}
		} catch (IOException | ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void init() throws IOException, ParserConfigurationException, SAXException {
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.connect();
		InputStream in = con.getInputStream();
		Document doc = builder.parse(in);
		name = doc.getElementsByTagName("NAME");
		unit = doc.getElementsByTagName("UNIT");
		code = doc.getElementsByTagName("CURRENCYCODE");
		country = doc.getElementsByTagName("COUNTRY");
		rate = doc.getElementsByTagName("RATE");
		change = doc.getElementsByTagName("CHANGE");
	
		con.disconnect();
		
	}	
	public CurrencyTable getCurrencyTable(){
		return currTable;
	}
}
