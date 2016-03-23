package dessert.remoteService.memberManage;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.dao.BankCardDao;
import dessert.dao.BillDao;
import dessert.dao.MemberDao;
import dessert.models.BankCard;
import dessert.models.Bill;
import dessert.models.Member;
import dessert.models.MemberPasswd;
import dessert.models.Recharge;
import dessert.remoteService.verification.VerifyService;
import dessert.utility.DayTransformer;
import dessert.utility.FormulationNumber;
import dessert.utility.IDProducer;
import dessert.utility.MemberLevelUtility;

@Service
public class MemberManageController implements MemberManageService{
	
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private BankCardDao bankcardDao;
	@Autowired
	private BillDao billDao;
	
	private VerifyService verify;

	public boolean findMemberName(String memberName) {
		String[] columns = {"memberName"};
		String[] values = new String[1];
		values[0] = memberName;
		ArrayList<Member> res = memberDao.find(columns, values);
		return (res!=null)&&(res.size()!=0);
	}
	
	public boolean findMemberName(String memberId,String memberName){
		String[] columns = {"memberName"};
		String[] values = new String[1];
		values[0] = memberName;
		ArrayList<Member> res = memberDao.find(columns, values);
		if(res!=null&&res.size()==1){
			return !((Member)res.get(0)).getMemberId().equals(memberId);
		}
		else{
			return false;
		}
	}

	public boolean findMemberTel(String tel) {
		String[] columns = {"memberTel"};
		String[] values = new String[1];
		values[0] = tel;
		ArrayList<Member> res = memberDao.find(columns, values);
		return (res!=null)&&(res.size()!=0);
	}

	public boolean findMemberTel(String memberId,String tel){
		String[] columns = {"memberTel"};
		String[] values = new String[1];
		values[0] = tel;
		ArrayList<Member> res = memberDao.find(columns, values);
		if(res!=null&&res.size()==1){
			return !((Member)res.get(0)).getMemberId().equals(memberId);
		}
		else{
			return false;
		}
	}

	public boolean matchMemberAndTel(String memberId, String tel) {
		String[] columns = {"memberId","memberTel"};
		String[] values = new String[2];
		values[0] = memberId;
		values[1] = tel;
		ArrayList<Member> res = memberDao.find(columns, values);
		return (res!=null)&&(res.size()!=0);
	}

	public String verifyByPhone(String phoneNumber) {
		return verify.sendVerifyCode(phoneNumber);
	}

	public boolean register(Member newMember) {
		return memberDao.addMember(newMember);
	}

	public boolean setLoginPassword(MemberPasswd passwd){
		return memberDao.saveMemberPasswd(passwd);
	}
	
	public boolean newLoginPassword(String memberId,String newPassword){
		MemberPasswd memberPW = memberDao.findMemberPasswd(memberId);
		if(memberPW!=null){
			memberPW.setLoginPassword(newPassword);
			return memberDao.updateMemberPasswd(memberPW);
		}
		else{
			return false;
		}
	}

	public boolean checkBankCard(String bankCard) {
		BankCard card = bankcardDao.findBankCard(bankCard);
		return (card!=null);
	}

	public boolean setPayPassword(String memberId, String payPassword) {
		MemberPasswd memberPW = memberDao.findMemberPasswd(memberId);
		if(memberPW!=null){
			memberPW.setPayPassword(payPassword);
			return memberDao.updateMemberPasswd(memberPW);
		}
		else{
			return false;
		}
	}

	public boolean login(String member, String password) {
		String[] columns = {"memberId","loginPassword"};
		String[] values = new String[2];
		values[0] = member;
		values[1] = password;
		ArrayList<Member> res = memberDao.find(columns, values);
		if((res!=null)&&(res.size()==1)){
			Member m = res.get(0);
			if(m.getCardState()==FormulationNumber.cardStop){
				return false;
			}
			if(m.getCardState()==0){
				return true;
			}
			Date curDate = new Date(System.currentTimeMillis());
			Date suspend = m.getSuspendTime();
			if(suspend!=null&&DayTransformer.getNewDate(suspend, FormulationNumber.year).before(curDate)){
				m.setCardState(FormulationNumber.cardStop);
				memberDao.updateMember(m);
				return false;
			}
			Date lastRecharge = m.getLastRecharge();
			Date suspendDue = DayTransformer.getNewDate(lastRecharge, FormulationNumber.year);
			if(suspendDue.before(curDate)){
				if(m.getResidual()<FormulationNumber.cardResidualLevel){
					m.setCardState(FormulationNumber.cardSuspend);
					m.setSuspendTime(suspendDue);
					if(DayTransformer.getNewDate(lastRecharge, FormulationNumber.year).before(curDate)){
						m.setCardState(FormulationNumber.cardStop);
						memberDao.updateMember(m);
						return false;
					}
					memberDao.updateMember(m);
				}
				return true;
			}
			return true;
		}
		else{
			return false;
		}
	}

	public boolean matchMemberAndPayPassword(String memberId,String password){
		String[] columns = {"memberId","payPassword"};
		String[] values = new String[2];
		values[0] = memberId;
		values[1] = password;
		ArrayList<Member> memberList = memberDao.find(columns, values);
		return (memberList!=null)&&(memberList.size()==1);
	}

	public boolean rechargeToMemberCard(String memberId, String bankCard, String payPassword, double money) {
		Member m = memberDao.find(memberId);
		if(m.getBankCardId().equals(bankCard)){
			if(matchMemberAndPayPassword(memberId,payPassword)){
				BankCard b = bankcardDao.findBankCard(bankCard);
				if(b.getResidual()>=money){
					b.setResidual(b.getResidual()-money);
					bankcardDao.updateBankCard(b);
					m.setResidual(m.getResidual()+money);
					m.setTotalRecharge(m.getTotalRecharge()+money);
					m.setLastRecharge(new Date(System.currentTimeMillis()));
					//update member level
					int level = MemberLevelUtility.getInstance().judgeMemberLevel(m.getTotalRecharge());
					m.setMemberLevel(level);
					memberDao.updateMember(m);
					//form a recharge record
					Recharge recharge = new Recharge();
					recharge.setRechargeId(IDProducer.getInstance().produceRechargeId());
					recharge.setMember(m);
					recharge.setRechargeTime(new Date(System.currentTimeMillis()));
					recharge.setRechargeAmount(money);
					memberDao.saveRecharge(recharge);
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}

	public Member checkMemberInfo(String memberId) {
		return memberDao.find(memberId);
	}

	public ArrayList<Member> checkAllMember(){
		return memberDao.findAll();
	}
	
	public ArrayList<Recharge> checkRecharge(String memberId){
		return memberDao.getRecharge(memberId);
	}

	public ArrayList<Recharge> checkAllRecharge(){
		return memberDao.getAllRecharge();
	}
	
	public ArrayList<Bill> checkBillInfo(String memberId){
		return billDao.findBillForMember(memberId);
	}

	public ArrayList<Bill> checkBill(String storeId,Date date){
		return billDao.findBillByDate(storeId,date);
	}
	
	public boolean modifyRegisterInfo(Member memberInfo) {
		if(memberInfo!=null){
			return memberDao.updateMember(memberInfo);
		}
		else{
			return false;
		}
	}

	public void withdrawCardInfo(String memberId){
		Member m = memberDao.find(memberId);
		if(m.getCardState()==FormulationNumber.cardInActive){
			//set card state to stop
			m.setCardState(FormulationNumber.cardStop);
			if(m.getBankCardId()!=null){
				m.setBankCardId(null);
			}
		}
		else{
			//refund residual
			String bankcardId = m.getBankCardId();
			BankCard bc = bankcardDao.findBankCard(bankcardId);
			bc.setResidual(bc.getResidual()+m.getResidual());
			bankcardDao.updateBankCard(bc);
			m.setResidual(0);
			//remove band of bank card
			m.setBankCardId(null);
			//set card state to stop
			m.setCardState(FormulationNumber.cardStop);
		}
		memberDao.updateMember(m);
	}

}
