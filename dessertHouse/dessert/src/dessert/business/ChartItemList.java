package dessert.business;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ChartItemList implements Serializable{
	
	private String[][] pairs;
	private int index = -1;
	
	public String[][] getPairs() {
		return pairs;
	}

	public void setPairs(String[][] pairs) {
		this.pairs = pairs;
	}
	
	public String[] getNextPair(){
		index = (index+1)%pairs.length;
		return pairs[index];
	}
}
