package ac.shenkar.Calc;

public class CurrencyTable {
	private int ROWS;
	private Currency[] row;
	
	public CurrencyTable(int size) {
		ROWS=size;
		this.row = new Currency[ROWS];
	
	}
	public void addElement(int index,Currency curr){
		row[index]=curr;
	}
	public Object[][] getTable(){
		Object[][] table=new Object[ROWS+1][3];
		table[0][0]="CODE";
		table[0][1]="RATE";
		table[0][2]="CHANGE";
		
		for(int i=0;i<ROWS;i++){
			table[i+1][0]=row[i].getCode();
			table[i+1][1]=row[i].getRate();
			table[i+1][2]=row[i].getChange();
		}
		return table;
	}
	
}
