package dessert.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="memberpassword")
public class MemberPasswd implements Serializable{

	private String Id;
	private String loginPassword;
	private String payPassword;
	private Member member;
	

	@Id
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	
	@OneToOne
	@JoinColumn(name="memberId",insertable=true,unique=true)
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	@Column
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	@Column
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	
}
