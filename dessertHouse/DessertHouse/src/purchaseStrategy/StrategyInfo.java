package purchaseStrategy;

import models.Strategy;
import saleManage.StrategyForSaleService;

public class StrategyInfo implements StrategyForSaleService{

	private static StrategyInfo strategyInfo = new StrategyInfo();
	
	private StrategyInfo(){

	}
	
	public static StrategyInfo getInstance(){
		return strategyInfo;
	}
	
	public Strategy getStrategy(String memberId, double cost) {
		// TODO Auto-generated method stub
		return null;
	}

}
