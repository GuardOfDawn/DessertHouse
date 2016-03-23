package dessert.dao;

import dessert.models.BankCard;

public interface BankCardDao {
	
	public BankCard findBankCard(String bankCard);

	public void updateBankCard(BankCard bankCard);
	
	
}
