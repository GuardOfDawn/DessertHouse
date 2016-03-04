package daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.BillDao;
import models.Bill;

@Stateless
public class BillDaoImpl implements BillDao{

	@PersistenceContext
	protected EntityManager em;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Bill> findBillForMember(String memberId) {
		Query query = em.createQuery("select b from Bill b "
							+ "where b.billMember.memberId=?1");
		query.setParameter(1, memberId);
		List list = query.getResultList();
		return (ArrayList<Bill>) list;
	}

	public void saveBill(Bill bill) {
		try {
			em.persist(bill);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
