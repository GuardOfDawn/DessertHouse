package dessert.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BonusUtility {
	
	private static BonusUtility bonusUtil = new BonusUtility();
	private String bonusFilePath =  "E:\\zhangyi\\1zy13\\Git\\DessertHouse\\dessertHouse\\dessert\\WebContent"
			+"\\BonusConfig.txt";
	
	private double[] paymentLevel;
	private int[] bonusStage;
	private double[] bonusRate;
	
	private BonusUtility(){
		try {
			FileInputStream stream = new FileInputStream(new File(bonusFilePath));
    		BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"UTF-8"));
    		ArrayList<String> content = new ArrayList<String>();
    		String temp = null;
    		while((temp = reader.readLine())!=null){
    			content.add(temp);
    		}
    		paymentLevel = new double[content.size()];
    		bonusStage = new int[content.size()];
    		bonusRate = new double[content.size()];
    		for(int i=0;i<content.size();i++){
    			String[] parts = content.get(i).split(";");
    			paymentLevel[i] = Double.parseDouble(parts[0]);
    			bonusStage[i] = Integer.parseInt(parts[1]);
    			bonusRate[i] = Double.parseDouble(parts[2]);
    		}
    		reader.close();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	public static BonusUtility getInstance(){
		return bonusUtil;
	}
	
	public int getBonus(double payment){
		int index = 0;
		for(index=0;index<paymentLevel.length;index++){
			if(payment<paymentLevel[index]){
				break;
			}
		}
		index--;
		int bonus = (int) (bonusStage[index]+(payment-paymentLevel[index])*bonusRate[index]);
		return bonus;
	}

}
