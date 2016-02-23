package daoImpl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MemberDaoImpl {
	
	@PersistenceContext
	protected EntityManager em;
	
	
}
