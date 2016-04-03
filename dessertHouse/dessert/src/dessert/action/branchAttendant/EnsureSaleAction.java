package dessert.action.branchAttendant;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.Bill;
import dessert.models.BillDetail;
import dessert.models.Member;
import dessert.models.Product;
import dessert.models.ScheduleDetail;
import dessert.models.Store;
import dessert.models.WeekSchedule;
import dessert.remoteService.memberManage.MemberManageService;
import dessert.remoteService.productManage.ProductManageService;
import dessert.remoteService.saleManage.SaleManageService;
import dessert.remoteService.scheduleManage.ScheduleManageService;
import dessert.remoteService.storeManage.StoreManageService;
import dessert.utility.BonusUtility;
import dessert.utility.DayTransformer;
import dessert.utility.FormulationNumber;
import dessert.utility.IDProducer;
import dessert.utility.MemberLevelUtility;

@Controller
public class EnsureSaleAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProductManageService productManage;
	@Autowired
	private MemberManageService memberManage;
	@Autowired
	private StoreManageService storeManage;
	@Autowired
	private SaleManageService saleManage;
	@Autowired
	private ScheduleManageService scheduleManage;
	
	public String execute(){
		if(session.get("user")!=null){
			String saleString = request.getParameter("sale");
			if(saleString!=null){
				String[] saleListString = saleString.split(";");
				ArrayList<BillDetail> billItem = new ArrayList<BillDetail>();
				double newBillCost = 0;
				for(int i=0;i<saleListString.length;i++){
					String[] parts = saleListString[i].split(",");
					BillDetail item = new BillDetail();
					String productId = parts[0];
					Product p = productManage.findProduct(productId);
					item.setProduct(p);
					item.setProductPrice(Double.parseDouble(parts[1]));
					item.setProductCount(Integer.parseInt(parts[2]));
					newBillCost += item.getProductPrice()*item.getProductCount();
					billItem.add(item);
				}
				Bill bill = new Bill();
				bill.setBillId(IDProducer.getInstance().produceBillId());
				bill.setBillTime(new Date(System.currentTimeMillis()));
				String storeId = request.getParameter("storeId");
				Store store = storeManage.getStoreInfo(storeId);
				bill.setBillStore(store);
				Member m = null;
				String memberId = request.getParameter("memberId");
				if(memberId!=null){
					m = memberManage.checkMemberInfo(memberId);
					bill.setBillMember(m);
					bill.setBillType(FormulationNumber.cardPay);
					bill.setBillCost(newBillCost);
					double favorRate = MemberLevelUtility.getInstance().getFavorRate(m.getCardState());
					bill.setFavorRate(favorRate);
					double costAfterRate = newBillCost*favorRate;
					int bonusUsed = Integer.parseInt(request.getParameter("bonusUsed"));
					bill.setBonusUsed(bonusUsed);
					double finalCost = costAfterRate-bonusUsed*FormulationNumber.bonusRate;
					bill.setCostAfterDiscount(finalCost);
					bill.setPayment(finalCost);
					bill.setChangeGiven(0);
					int bonusGiven = BonusUtility.getInstance().getBonus(finalCost);
					bill.setBonusGiven(bonusGiven);
					int bonusLeft = m.getBonusPoint()-bonusUsed+bonusGiven;
					m.setBonusPoint(bonusLeft);
					double residual = m.getResidual()-finalCost;
					m.setResidual(residual);
					m.setLatestUsage(new Date(System.currentTimeMillis()));
				}
				else{
					bill.setBillType(FormulationNumber.cashPay);
					bill.setBillMember(memberManage.checkMemberInfo(FormulationNumber.cashPayMemberId));
					bill.setBillCost(newBillCost);
					bill.setFavorRate(1);
					bill.setBonusUsed(0);
					bill.setCostAfterDiscount(newBillCost);
					double payment = Double.parseDouble(request.getParameter("payment"));
					bill.setPayment(payment);
					double change = payment-newBillCost;
					bill.setChangeGiven(change);
					bill.setBonusGiven(0);
				}
				boolean res = saleManage.saveBill(bill);
				if(res){
					Date curDate = new Date(System.currentTimeMillis());
					curDate = DayTransformer.transform(DayTransformer.transform(curDate));
					WeekSchedule schedule = scheduleManage.retrieveSchedule(store.getStoreId(), curDate);
					ArrayList<ScheduleDetail> detailList = scheduleManage.retrieveScheduleDetail(schedule.getScheduleId(),curDate);
					for(BillDetail detail:billItem){
						detail.setBill(bill);
						saleManage.saveBillDetail(detail);
						//modify remaining count
						for(ScheduleDetail item:detailList){
							if(item.getProductId().equals(detail.getProduct().getProductId())){
								item.setRemainingCount(item.getRemainingCount()-detail.getProductCount());
								scheduleManage.updateScheduleDetail(item);
								break;
							}
						}
					}
					if(m!=null){
						memberManage.modifyRegisterInfo(m);
					}
					ListBean billDetailList = new ListBean();
					billDetailList.setListBean(billItem);
					request.setAttribute("saleRes", "1");
					request.setAttribute("saleBill", bill);
					request.setAttribute("billDetailList", billDetailList);
					return SUCCESS;
				}
				else{
					request.setAttribute("saleRes", "0");
					return "failure";
				}
			}
			else{
				request.setAttribute("saleRes", "0");
				return "failure";
			}
		}
		else{
			return INPUT;
		}
	}

}
