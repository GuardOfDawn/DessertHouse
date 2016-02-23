package utility;

public class IDProducer {

	private static IDProducer idProducer = new IDProducer();
	
	private IDProducer(){
		
	}
	
	public static IDProducer getInstance(){
		return idProducer;
	}

	private String memberIdMax;
	private String orderIdMax;
	private String billIdMax;
	private String rechargeIdMax;
	
	public String produceMemberId(){
		memberIdMax = String.valueOf(Integer.parseInt(memberIdMax)+1);
		StringBuffer bf = new StringBuffer();
		for(int i=0;i<7-memberIdMax.length();i++){
			bf.append(0);
		}
		bf.append(memberIdMax);
		return bf.toString();
	}
	
	public String produceOrderId(){
		orderIdMax = String.valueOf(Integer.parseInt(orderIdMax)+1);
		StringBuffer bf = new StringBuffer("O");
		for(int i=0;i<9-orderIdMax.length();i++){
			bf.append(0);
		}
		bf.append(orderIdMax);
		return bf.toString();
	}
	
	public String produceBillId(){
		billIdMax = String.valueOf(Integer.parseInt(billIdMax)+1);
		StringBuffer bf = new StringBuffer("B");
		for(int i=0;i<9-billIdMax.length();i++){
			bf.append(0);
		}
		bf.append(billIdMax);
		return bf.toString();
	}
	
	public String produceRechargeId(){
		rechargeIdMax = String.valueOf(Integer.parseInt(rechargeIdMax)+1);
		StringBuffer bf = new StringBuffer("R");
		for(int i=0;i<9-rechargeIdMax.length();i++){
			bf.append(0);
		}
		bf.append(rechargeIdMax);
		return bf.toString();
	}
	
}
