package models;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="weekschedule")
public class WeekSchedule implements Serializable{
	
	private String scheduleId;
	private Store store;
	private Date startTime;
	private Date endTime;
	private int scheduleState;
	private Set<ScheduleDetail> scheduleDetailList;
	
	
	@Id
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="storeId")
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	
	@Column
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column
	public int getScheduleState() {
		return scheduleState;
	}
	public void setScheduleState(int scheduleState) {
		this.scheduleState = scheduleState;
	}
	
	@OneToMany(mappedBy="weekSchedule", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@OrderBy(value="scheduleDate ASC")
	public Set<ScheduleDetail> getScheduleDetailList() {
		return scheduleDetailList;
	}
	public void setScheduleDetailList(Set<ScheduleDetail> scheduleDetailList) {
		this.scheduleDetailList = scheduleDetailList;
	}
	
	
}
