package dessert.remoteService.verification;

public interface VerifyService {

	public String sendVerifyCode(String tel);
	
	public boolean checkBankCard(String bankCard);
	
	public boolean checkBankResidual(String bankCard,double money);
	
}
