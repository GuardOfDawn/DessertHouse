package daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.ProductDao;
import models.Product;

@Stateless
public class ProductDaoImpl implements ProductDao {

	@PersistenceContext
	protected EntityManager em;
	
	public boolean saveProduct(Product product) {
		try {
			em.persist(product);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeProduct(String[] columns, String[] values) {
		if(columns!=null&&columns.length!=0){
			int index = 1;
			StringBuffer ql = new StringBuffer();
			ql.append("delete from Product p where ");
			for(index=1;index<=columns.length;index++){
				ql.append("p.").append(columns[index-1]).append("=").append("?").append(index)
						.append(" and ");
			}
			ql.delete(ql.length()-5, ql.length());
			Query query = em.createQuery(ql.toString());
			for(int i=1;i<=index;i++){
				query.setParameter(i, values[i-1]);
			}
			query.executeUpdate();
			return true;
		}
		else{
			return false;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Product> findProduct(String[] columns, String[] values) {
		if(columns!=null&&columns.length!=0){
			int index = 1;
			StringBuffer ql = new StringBuffer();
			ql.append("select p from Product p where ");
			for(index=1;index<=columns.length;index++){
				ql.append("p.").append(columns[index-1]).append("=").append("?").append(index)
						.append(" and ");
			}
			ql.delete(ql.length()-5, ql.length());
			Query query = em.createQuery(ql.toString());
			for(int i=1;i<=index;i++){
				query.setParameter(i, values[i-1]);
			}
			List list = query.getResultList();
			return (ArrayList<Product>) list;
		}
		else{
			return findAllProduct();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<Product> findAllProduct() {
		Query query = em.createQuery("select p from Product p");
		List list = query.getResultList();
		return (ArrayList<Product>) list;
	}

	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

}
