package daoImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.ScheduleDao;
import models.WeekSchedule;

@Stateless
public class ScheduleDaoImpl implements ScheduleDao{
	
	@PersistenceContext
	protected EntityManager em;

	public boolean saveSchedule(WeekSchedule schedule) {
		try {
			em.persist(schedule);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("rawtypes")
	public WeekSchedule findSchedule(String scheduleId) {
		Query query = em.createQuery("select ws from WeekSchedule ws "
							+ "where ws.scheduleId=?1");
		query.setParameter(1, scheduleId);
		List list = query.getResultList();
		if(list!=null&&list.size()==1){
			return (WeekSchedule) list.get(0);
		}
		else{
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<WeekSchedule> retrieveScheduleForStore(String storeId) {
		Query query = em.createQuery("select ws from WeekSchedule ws "
				+ "where ws.store.storeId=?1");
		query.setParameter(1, storeId);
		List list = query.getResultList();
		return (ArrayList<WeekSchedule>) list;
	}

	@SuppressWarnings("rawtypes")
	public ArrayList<WeekSchedule> retrieveScheduleForStore(String storeId, Date[] period) {
		ArrayList<WeekSchedule> scheduleList = new ArrayList<WeekSchedule>();
		Query query = em.createQuery("select ws from WeekSchedule ws "
				+ "where ws.store.storeId=?1");
		query.setParameter(1, storeId);
		List list = query.getResultList();
		for(Object o:list){
			WeekSchedule schedule = (WeekSchedule) o;
			if((!schedule.getStartTime().before(period[0]))&&schedule.getEndTime().before(period[1])){
				scheduleList.add(schedule);
			}
		}
		return scheduleList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<WeekSchedule> retrieveScheduleByState(int scheduleState) {
		Query query = em.createQuery("select ws from WeekSchedule ws "
				+ "where ws.scheduleState=?1");
		query.setParameter(1, scheduleState);
		List list = query.getResultList();
		return (ArrayList<WeekSchedule>) list;
	}

	public boolean updateSchedule(WeekSchedule schedule) {
		try{
			em.merge(schedule);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
