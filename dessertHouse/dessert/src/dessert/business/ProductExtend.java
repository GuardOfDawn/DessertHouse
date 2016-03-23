package dessert.business;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProductExtend implements Serializable{

	private String productId;
	private String productName;
	private String productType;
	private String imagePath;
	private double sellingPrice;
	private int sellingCount;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public int getSellingCount() {
		return sellingCount;
	}
	public void setSellingCount(int sellingCount) {
		this.sellingCount = sellingCount;
	}
	
}
