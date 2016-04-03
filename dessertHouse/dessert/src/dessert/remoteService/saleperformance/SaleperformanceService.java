package dessert.remoteService.saleperformance;

public interface SaleperformanceService {
	
	public String[][] getMemberAgeDistribution(String monthAndyear);
	
	public String[][] getMemberGenderDistribution(String monthAndyear);
	
	public String[][] getMemberCardDistribution(String monthAndyear);

	public String[][] getMemberConsumeDis(String monthAndyear);

	public String[][] getMemberResidualDis(String monthAndyear);
	
	/**
	 * 折后销售额
	 * @param monthAndyear
	 * @return
	 */
	public String[][] getStoreSellingDis(String monthAndyear);
	
	/**
	 * 折后预订额
	 * @param monthAndyear
	 * @return
	 */
	public String[][] getStoreOrderingDis(String monthAndyear);

	public String[][] getTotalPopProductDis(String monthAndyear);

	public String[][] getPopProductDis(String storeId,String monthAndyear);

}
