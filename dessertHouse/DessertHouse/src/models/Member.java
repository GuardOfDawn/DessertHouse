package models;

import java.sql.Date;

public class Member{
	
	private String memberId;
	private String memberName;
	private String memberTel;
	private int memberAge;
	private String memberLoc;
	private int cardState;
	private int memberLevel;
	private double residual;
	private int bonusPoint;
	private Date registerTime;
	private Date latestUsage;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberTel() {
		return memberTel;
	}
	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}
	public int getMemberAge() {
		return memberAge;
	}
	public void setMemberAge(int memberAge) {
		this.memberAge = memberAge;
	}
	public String getMemberLoc() {
		return memberLoc;
	}
	public void setMemberLoc(String memberLoc) {
		this.memberLoc = memberLoc;
	}
	public int getCardState() {
		return cardState;
	}
	public void setCardState(int cardState) {
		this.cardState = cardState;
	}
	public int getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}
	public double getResidual() {
		return residual;
	}
	public void setResidual(double residual) {
		this.residual = residual;
	}
	public int getBonusPoint() {
		return bonusPoint;
	}
	public void setBonusPoint(int bonusPoint) {
		this.bonusPoint = bonusPoint;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public Date getLatestUsage() {
		return latestUsage;
	}
	public void setLatestUsage(Date latestUsage) {
		this.latestUsage = latestUsage;
	}
}
