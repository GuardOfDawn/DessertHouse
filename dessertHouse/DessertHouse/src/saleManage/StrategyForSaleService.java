package saleManage;

import models.Strategy;

public interface StrategyForSaleService {

	public Strategy getStrategy(String memberId, double cost);
	
}
