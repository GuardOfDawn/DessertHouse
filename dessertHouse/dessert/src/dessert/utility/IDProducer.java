package dessert.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class IDProducer {

	private static IDProducer idProducer = new IDProducer();
	
	private IDProducer(){
		try {
    		BufferedReader reader = new BufferedReader(new FileReader(IdConfigFilePath));
    		memberIdMax = reader.readLine();
    		memberPasswdIdMax = reader.readLine();
    		orderIdMax = reader.readLine();
    		billIdMax = reader.readLine();
    		rechargeIdMax = reader.readLine();
    		storeIdMax = reader.readLine();
    		salespersonIdMax = reader.readLine();
    		productIdMax = reader.readLine();
    		scheduleIdMax = reader.readLine();
    		scheduleDetailIdMax = reader.readLine();
    		reader.close();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	public static IDProducer getInstance(){
		return idProducer;
	}
	
	private String IdConfigFilePath = "E:\\zhangyi\\1zy13\\Git\\DessertHouse\\dessertHouse\\dessert\\WebContent"
					+"\\IdConfig.txt";

	private String memberIdMax;
	private String memberPasswdIdMax;
	private String orderIdMax;
	private String billIdMax;
	private String rechargeIdMax;
	private String storeIdMax;
	private String salespersonIdMax;
	private String productIdMax;
	private String scheduleIdMax;
	private String scheduleDetailIdMax;
	
	public String produceMemberId(){
		memberIdMax = String.valueOf(Integer.parseInt(memberIdMax)+1);
		StringBuffer bf = new StringBuffer();
		for(int i=0;i<7-memberIdMax.length();i++){
			bf.append(0);
		}
		bf.append(memberIdMax);
		updateFile();
		return bf.toString();
	}
	
	public String produceMemberPasswdId(){
		memberPasswdIdMax = String.valueOf(Integer.parseInt(memberPasswdIdMax)+1);
		StringBuffer bf = new StringBuffer("PW");
		for(int i=0;i<7-memberPasswdIdMax.length();i++){
			bf.append(0);
		}
		bf.append(memberPasswdIdMax);
		updateFile();
		return bf.toString();
	}
	
	public String produceOrderId(){
		orderIdMax = String.valueOf(Integer.parseInt(orderIdMax)+1);
		StringBuffer bf = new StringBuffer("O");
		for(int i=0;i<9-orderIdMax.length();i++){
			bf.append(0);
		}
		bf.append(orderIdMax);
		updateFile();
		return bf.toString();
	}
	
	public String produceBillId(){
		billIdMax = String.valueOf(Integer.parseInt(billIdMax)+1);
		StringBuffer bf = new StringBuffer("B");
		for(int i=0;i<9-billIdMax.length();i++){
			bf.append(0);
		}
		bf.append(billIdMax);
		updateFile();
		return bf.toString();
	}
	
	public String produceRechargeId(){
		rechargeIdMax = String.valueOf(Integer.parseInt(rechargeIdMax)+1);
		StringBuffer bf = new StringBuffer("R");
		for(int i=0;i<9-rechargeIdMax.length();i++){
			bf.append(0);
		}
		bf.append(rechargeIdMax);
		updateFile();
		return bf.toString();
	}
	
	public String produceStoreId(){
		storeIdMax = String.valueOf(Integer.parseInt(storeIdMax)+1);
		StringBuffer bf = new StringBuffer("S");
		for(int i=0;i<5-storeIdMax.length();i++){
			bf.append(0);
		}
		bf.append(storeIdMax);
		updateFile();
		return bf.toString();
	}

	public String produceSalespersonId(){
		salespersonIdMax = String.valueOf(Integer.parseInt(salespersonIdMax)+1);
		StringBuffer bf = new StringBuffer("A");
		for(int i=0;i<5-salespersonIdMax.length();i++){
			bf.append(0);
		}
		bf.append(salespersonIdMax);
		updateFile();
		return bf.toString();
	}

	public String produceProductId(){
		productIdMax = String.valueOf(Integer.parseInt(productIdMax)+1);
		StringBuffer bf = new StringBuffer("P");
		for(int i=0;i<5-productIdMax.length();i++){
			bf.append(0);
		}
		bf.append(productIdMax);
		updateFile();
		return bf.toString();
	}
	
	public String produceScheduleId(){
		scheduleIdMax = String.valueOf(Integer.parseInt(scheduleIdMax)+1);
		StringBuffer bf = new StringBuffer("SC");
		for(int i=0;i<8-scheduleIdMax.length();i++){
			bf.append(0);
		}
		bf.append(scheduleIdMax);
		updateFile();
		return bf.toString();
	}
	
	public String produceScheduleDetailId(){
		scheduleDetailIdMax = String.valueOf(Integer.parseInt(scheduleDetailIdMax)+1);
		StringBuffer bf = new StringBuffer("SD");
		for(int i=0;i<10-scheduleDetailIdMax.length();i++){
			bf.append(0);
		}
		bf.append(scheduleDetailIdMax);
		updateFile();
		return bf.toString();
	}
	
	private void updateFile(){
		try{
			File file_out = new File(IdConfigFilePath);
			FileWriter filewriter = new FileWriter(file_out);
			filewriter.write(memberIdMax);
			filewriter.write("\n");
			filewriter.write(memberPasswdIdMax);
			filewriter.write("\n");
			filewriter.write(orderIdMax);
			filewriter.write("\n");
			filewriter.write(billIdMax);
			filewriter.write("\n");
			filewriter.write(rechargeIdMax);
			filewriter.write("\n");
			filewriter.write(storeIdMax);;
			filewriter.write("\n");
			filewriter.write(salespersonIdMax);
			filewriter.write("\n");
			filewriter.write(productIdMax);
			filewriter.write("\n");
			filewriter.write(scheduleIdMax);
			filewriter.write("\n");
			filewriter.write(scheduleDetailIdMax);
			filewriter.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
