package dessert.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="scheduledetail")
public class ScheduleDetail implements Serializable{
	
	private WeekSchedule weekSchedule;
	private Date scheduleDate;
	private String productId;
	private double sellingPrice;
	private int sellingCount;
	
	@Id
	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="scheduleId")
	public WeekSchedule getWeekSchedule() {
		return weekSchedule;
	}
	public void setWeekSchedule(WeekSchedule weekSchedule) {
		this.weekSchedule = weekSchedule;
	}
	
	@Column
	public Date getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	@Column
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Column
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	@Column
	public int getSellingCount() {
		return sellingCount;
	}
	public void setSellingCount(int sellingCount) {
		this.sellingCount = sellingCount;
	}
	

}
