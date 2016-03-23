package dessert.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProductType {

	private static ProductType productType = new ProductType();
	private String ProductTypeFilePath =  "E:\\zhangyi\\1zy13\\Git\\DessertHouse\\dessertHouse\\dessert\\WebContent"
			+"\\ProductTypeConfig.txt";
	
	private ArrayList<String> productTypeList;
	
	private ProductType(){
		productTypeList = new ArrayList<String>();
		try {
			FileInputStream stream = new FileInputStream(new File(ProductTypeFilePath));
    		BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"UTF-8"));
    		String temp = null;
    		while((temp = reader.readLine())!=null){
    			productTypeList.add(temp);
    		}
    		reader.close();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	public static ProductType getInstance(){
		return productType;
	}
	
	public ArrayList<String> getTypeList(){
		return productTypeList;
	}
	
}
