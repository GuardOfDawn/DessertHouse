package dao;

import javax.ejb.Local;

@Local
public interface BankCardDao {

	public void modifyResidualForCard(String bankCard,String payment);
	
}
