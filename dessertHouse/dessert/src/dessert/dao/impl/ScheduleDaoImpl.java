package dessert.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dessert.dao.BaseDao;
import dessert.dao.ScheduleDao;
import dessert.models.ScheduleDetail;
import dessert.models.WeekSchedule;

@Repository
public class ScheduleDaoImpl implements ScheduleDao{

	@Autowired
	private BaseDao baseDao;
	private Session session;

	public boolean saveSchedule(WeekSchedule schedule) {
		try {
			baseDao.save(schedule);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("rawtypes")
	public WeekSchedule findSchedule(String scheduleId) {
		session = baseDao.getNewSession();
		Query query = session.createQuery("select ws from WeekSchedule ws "
							+ "where ws.scheduleId=?");
		query.setString(0, scheduleId);
		List list = query.list();
		session.close();
		if(list!=null&&list.size()==1){
			return (WeekSchedule) list.get(0);
		}
		else{
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<WeekSchedule> retrieveAllSchedule(){
		session = baseDao.getNewSession();
		Query query = session.createQuery("select ws from WeekSchedule ws");
		List list = query.list();
		session.close();
		return (ArrayList<WeekSchedule>) list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<WeekSchedule> retrieveScheduleForStore(String storeId,int state) {
		session = baseDao.getNewSession();
		Query query = session.createQuery("select ws from WeekSchedule ws "
				+ "where ws.store.storeId=? and ws.scheduleState=?");
		query.setString(0, storeId);
		query.setInteger(1, state);
		List list = query.list();
		session.close();
		return (ArrayList<WeekSchedule>) list;
	}

	@SuppressWarnings("rawtypes")
	public ArrayList<WeekSchedule> retrieveScheduleForStore(String storeId, Date[] period,int state) {
		ArrayList<WeekSchedule> scheduleList = new ArrayList<WeekSchedule>();
		session = baseDao.getNewSession();
		Query query = session.createQuery("select ws from WeekSchedule ws "
				+ "where ws.store.storeId=? and ws.scheduleState=?");
		query.setString(0, storeId);
		query.setInteger(1, state);
		List list = query.list();
		session.close();
		for(Object o:list){
			WeekSchedule schedule = (WeekSchedule) o;
			if((!schedule.getStartTime().after(period[0]))&&(!period[1].after(schedule.getEndTime()))){
				scheduleList.add(schedule);
			}
		}
		return scheduleList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<WeekSchedule> retrieveScheduleByState(int scheduleState) {
		session = baseDao.getNewSession();
		Query query = session.createQuery("select ws from WeekSchedule ws "
				+ "where ws.scheduleState=?");
		query.setInteger(0, scheduleState);
		List list = query.list();
		session.close();
		return (ArrayList<WeekSchedule>) list;
	}

	public boolean updateSchedule(WeekSchedule schedule) {
		try{
			baseDao.update(schedule);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteSchedule(WeekSchedule schedule){
		try {
			baseDao.delete(schedule);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean removeSchedule(String scheduleId){
		if(scheduleId!=null){
			StringBuffer ql = new StringBuffer();
			ql.append("delete from WeekSchedule ws where ws.scheduleId=?");
			session = baseDao.getNewSession();
			Query query = session.createQuery(ql.toString());
			query.setString(0, scheduleId);
			query.executeUpdate();
			session.close();
			return true;
		}
		else{
			return false;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<ScheduleDetail> findScheduleDetail(String scheduleId){
		if(scheduleId!=null){
			session = baseDao.getNewSession();
			Query query = session.createQuery("select ws.scheduleDetailList from WeekSchedule ws where ws.scheduleId=?");
			query.setString(0, scheduleId);
			List list = query.list();
			session.close();
			return (ArrayList<ScheduleDetail>) list;
		}
		else{
			return null;
		}
	}

	public boolean removeScheduleDetail(String scheduleId){
		if(scheduleId!=null){
			StringBuffer ql = new StringBuffer();
			ql.append("delete from ScheduleDetail sd where sd.weekSchedule.scheduleId=?");
			session = baseDao.getNewSession();
			Query query = session.createQuery(ql.toString());
			query.setString(0, scheduleId);
			query.executeUpdate();
			session.close();
			return true;
		}
		else{
			return false;
		}
	}

	public boolean saveScheduleDetail(ScheduleDetail scheduleDetail){
		try {
			baseDao.save(scheduleDetail);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
