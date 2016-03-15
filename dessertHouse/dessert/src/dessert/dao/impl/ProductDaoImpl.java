package dessert.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dessert.dao.BaseDao;
import dessert.dao.ProductDao;
import dessert.models.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private BaseDao baseDao;
	private Session session;
	
	public boolean saveProduct(Product product) {
		try {
			baseDao.save(product);
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
				ql.append("p.").append(columns[index-1]).append("=").append("?")
						.append(" and ");
			}
			ql.delete(ql.length()-5, ql.length());
			session = baseDao.getNewSession();
			Query query = session.createQuery(ql.toString());
			for(int i=0;i<values.length;i++){
				query.setString(i, values[i]);
			}
			query.executeUpdate();
			session.close();
			return true;
		}
		else{
			return false;
		}
	}

	public Product findProduct(String productId){
		return (Product) baseDao.load(Product.class, productId);
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
			session = baseDao.getNewSession();
			Query query = session.createQuery(ql.toString());
			for(int i=0;i<values.length;i++){
				query.setString(i, values[i]);
			}
			List list = query.list();
			session.close();
			return (ArrayList<Product>) list;
		}
		else{
			return findAllProduct();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<Product> findAllProduct() {
		session = baseDao.getNewSession();
		Query query = session.createQuery("select p from Product p");
		List list = query.list();
		session.close();
		return (ArrayList<Product>) list;
	}

	public boolean updateProduct(Product product) {
		try{
			baseDao.update(product);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
