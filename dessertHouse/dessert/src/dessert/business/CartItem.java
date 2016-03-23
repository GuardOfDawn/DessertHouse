package dessert.business;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CartItem implements Serializable{

	private String productId;
	private String productName;
	private String productType;
	private String imagePath;
	private double sellingPrice;
	private int buyingCount;
	
	
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
	public int getBuyingCount() {
		return buyingCount;
	}
	public void setBuyingCount(int buyingCount) {
		this.buyingCount = buyingCount;
	}
	
}
