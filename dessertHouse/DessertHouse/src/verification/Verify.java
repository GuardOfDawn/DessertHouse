package verification;

public class Verify implements VerifyService{
	
	private static Verify verify = new Verify();
	
	private Verify(){
		
	}
	
	public static Verify getInstance(){
		return verify;
	}

	public String sendVerifyCode(String tel) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checkBankCard(String bankCard) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkBankResidual(String bankCard, double money) {
		// TODO Auto-generated method stub
		return false;
	}

}
