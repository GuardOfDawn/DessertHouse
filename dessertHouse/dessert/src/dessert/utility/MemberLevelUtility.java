package dessert.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MemberLevelUtility {
	
	private static MemberLevelUtility memberLevelUtil = new MemberLevelUtility();
	private String memberLevelFilePath =  "E:\\zhangyi\\1zy13\\Git\\DessertHouse\\dessertHouse\\dessert\\WebContent"
			+"\\MemberLevelConfig.txt";
	
	private int[] memberLevel;
	private double[] rechargeLevel;
	private double[] favorRate;
	
	private MemberLevelUtility(){
		try {
			FileInputStream stream = new FileInputStream(new File(memberLevelFilePath));
    		BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"UTF-8"));
    		ArrayList<String> content = new ArrayList<String>();
    		String temp = null;
    		while((temp = reader.readLine())!=null){
    			content.add(temp);
    		}
    		memberLevel = new int[content.size()];
    		rechargeLevel = new double[content.size()];
    		favorRate = new double[content.size()];
    		for(int i=0;i<content.size();i++){
    			String[] parts = content.get(i).split(";");
    			memberLevel[i] = Integer.parseInt(parts[0]);
    			rechargeLevel[i] = Double.parseDouble(parts[1]);
    			favorRate[i] = Double.parseDouble(parts[2]);
    		}
    		reader.close();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	public static MemberLevelUtility getInstance(){
		return memberLevelUtil;
	}
	
	public int judgeMemberLevel(double totalRecharge){
		int index = 0;
		for(index=0;index<memberLevel.length;index++){
			if(totalRecharge<rechargeLevel[index]){
				break;
			}
		}
		index--;
		return memberLevel[index];
	}
	
	public double getFavorRate(int memberLevel){
		return favorRate[memberLevel];
	}

}
