package dessert.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dessert.dao.BankCardDao;
import dessert.dao.BaseDao;
import dessert.models.BankCard;

@Repository
public class BankCardDaoImpl implements BankCardDao{
	
	@Autowired
	private BaseDao baseDao;
	private Session session;

	@SuppressWarnings("rawtypes")
	public BankCard findBankCard(String bankCard) {
		session = baseDao.getNewSession();
		Query query = session.createQuery("select bc from BankCard bc where bc.bankCardId=?");
		query.setString(0, bankCard);
		List list = query.list();
		session.close();
		if(list!=null&&list.size()==1){
			return (BankCard) list.get(0);
		}
		return null;
	}

	public void updateBankCard(BankCard bankCard) {
		try{
			baseDao.update(bankCard);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
