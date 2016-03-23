package dessert.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="bankcard")
public class BankCard implements Serializable{
	
	private String bankCardId;
	private double residual;
	
	@Id
	public String getBankCardId() {
		return bankCardId;
	}
	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}
	
	@Column
	public double getResidual() {
		return residual;
	}
	public void setResidual(double residual) {
		this.residual = residual;
	}
	
}
