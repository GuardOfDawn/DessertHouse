package dessert.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="member")
public class Member implements Serializable{
	
	private String memberId;
	private String memberName;
	private int memberGender;
	private int memberAge;
	private String memberTel;
	private String memberLoc;
	private int cardState;
	private String bankCardId;
	private int memberLevel;
	private double residual;
	private int bonusPoint;
	private Date registerTime;
	private double totalRecharge;
	private Date latestUsage;
	private Date lastRecharge;
	private Date suspendTime;
	private MemberPasswd passwd;
	private Set<Recharge> rechargeList;
	private Set<Order> orderList;
	private Set<Bill> billList;

	
	@Id
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Column
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Column
	public int getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(int memberGender) {
		this.memberGender = memberGender;
	}

	@Column
	public int getMemberAge() {
		return memberAge;
	}
	public void setMemberAge(int memberAge) {
		this.memberAge = memberAge;
	}

	@Column
	public String getMemberTel() {
		return memberTel;
	}
	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}

	@Column
	public String getMemberLoc() {
		return memberLoc;
	}
	public void setMemberLoc(String memberLoc) {
		this.memberLoc = memberLoc;
	}

	@Column
	public int getCardState() {
		return cardState;
	}
	public void setCardState(int cardState) {
		this.cardState = cardState;
	}
	
	@Column
	public String getBankCardId() {
		return bankCardId;
	}
	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}

	@Column
	public int getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}

	@Column
	public double getResidual() {
		return residual;
	}
	public void setResidual(double residual) {
		this.residual = residual;
	}

	@Column
	public int getBonusPoint() {
		return bonusPoint;
	}
	public void setBonusPoint(int bonusPoint) {
		this.bonusPoint = bonusPoint;
	}

	@Column
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Column
	public Date getLatestUsage() {
		return latestUsage;
	}
	public void setLatestUsage(Date latestUsage) {
		this.latestUsage = latestUsage;
	}

	@Column
	public double getTotalRecharge() {
		return totalRecharge;
	}
	public void setTotalRecharge(double totalRecharge) {
		this.totalRecharge = totalRecharge;
	}
	
	@Column
	public Date getLastRecharge() {
		return lastRecharge;
	}
	public void setLastRecharge(Date lastRecharge) {
		this.lastRecharge = lastRecharge;
	}
	
	@Column
	public Date getSuspendTime() {
		return suspendTime;
	}
	public void setSuspendTime(Date suspendTime) {
		this.suspendTime = suspendTime;
	}

	@OneToOne(mappedBy="member")
	public MemberPasswd getPasswd() {
		return passwd;
	}
	public void setPasswd(MemberPasswd passwd) {
		this.passwd = passwd;
	}
	
	@OneToMany(mappedBy="member", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@OrderBy(value="rechargeTime DESC")
	public Set<Recharge> getRechargeList() {
		return rechargeList;
	}
	public void setRechargeList(Set<Recharge> rechargeList) {
		this.rechargeList = rechargeList;
	}
	
	@OneToMany(mappedBy="orderMember", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@OrderBy(value="orderTime DESC")
	public Set<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(Set<Order> orderList) {
		this.orderList = orderList;
	}
	
	@OneToMany(mappedBy="billMember", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@OrderBy(value="billTime DESC")
	public Set<Bill> getBillList() {
		return billList;
	}
	public void setBillList(Set<Bill> billList) {
		this.billList = billList;
	}
}
