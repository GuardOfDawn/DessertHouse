package dessert.remoteService.saleperformance;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.dao.BillDao;
import dessert.dao.MemberDao;
import dessert.dao.OrderDao;
import dessert.dao.ProductDao;
import dessert.dao.StoreDao;
import dessert.models.Bill;
import dessert.models.BillDetail;
import dessert.models.Member;
import dessert.models.Order;
import dessert.models.Store;
import dessert.utility.DayTransformer;
import dessert.utility.FormulationNumber;

@Service
public class SaleperformanceManage implements SaleperformanceService{

	@Autowired
	private MemberDao memberDao;
	@Autowired
	private BillDao billDao;
	@Autowired
	private StoreDao storeDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ProductDao productDao;
	
	private String[] ageParts = {"14以下","15~24","25~34","35~44","45~54","55以上"};
	private String[] genderParts = {"男","女"};
	private String[] cardParts = {"未激活","激活","暂停","停止","取消"};
	
	private String[] consumeParts = {"200以下","200~500","500~1000","1000~5000","5000以上"};
	private String[] residualParts = {"30以下","30~100","100~400","400~1000","1000以上"};
	
	
	public String[][] getMemberAgeDistribution(String monthAndyear) {
		String[] columns = {"cardState"};
		String[] values = new String[1];
		values[0] = String.valueOf(FormulationNumber.cardActive);
		ArrayList<Member> memberList = memberDao.find(columns, values);
		int[] counts = new int[ageParts.length];
		for(Member m:memberList){
			if(DayTransformer.transformToMonth(m.getRegisterTime()).compareTo(monthAndyear)<=0){
				if(m.getMemberAge()<15){
					counts[0]++;
				}
				else if(m.getMemberAge()<25){
					counts[1]++;
				}
				else if(m.getMemberAge()<35){
					counts[2]++;
				}
				else if(m.getMemberAge()<45){
					counts[3]++;
				}
				else if(m.getMemberAge()<55){
					counts[4]++;
				}
				else{
					counts[5]++;
				}
			}
		}
		String[][] memberAge = new String[ageParts.length][2];
		for(int i=0;i<memberAge.length;i++){
			memberAge[i][0] = ageParts[i];
			memberAge[i][1] = String.valueOf(counts[i]);
		}
		return memberAge;
	}

	public String[][] getMemberGenderDistribution(String monthAndyear) {
		String[] columns = {"cardState"};
		String[] values = new String[1];
		values[0] = String.valueOf(FormulationNumber.cardActive);
		ArrayList<Member> memberList = memberDao.find(columns, values);
		int[] counts = new int[genderParts.length];
		for(Member m:memberList){
			if(DayTransformer.transformToMonth(m.getRegisterTime()).compareTo(monthAndyear)<=0){
				counts[m.getMemberGender()]++;
			}
		}
		String[][] memberGender = new String[genderParts.length][2];
		for(int i=0;i<memberGender.length;i++){
			memberGender[i][0] = genderParts[i];
			memberGender[i][1] = String.valueOf(counts[i]);
		}
		return memberGender;
	}
	
	public String[][] getMemberCardDistribution(String monthAndyear){
		ArrayList<Member> memberList = memberDao.findAll();
		int[] counts = new int[cardParts.length];
		for(Member m:memberList){
			if(DayTransformer.transformToMonth(m.getRegisterTime()).compareTo(monthAndyear)<=0){
				counts[m.getCardState()]++;
			}
		}
		String[][] memberCard = new String[cardParts.length][2];
		for(int i=0;i<memberCard.length;i++){
			memberCard[i][0] = cardParts[i];
			memberCard[i][1] = String.valueOf(counts[i]);
		}
		return memberCard;
	}
	
	public String[][] getMemberConsumeDis(String monthAndyear){
		String[] columns = {"cardState"};
		String[] values = new String[1];
		values[0] = String.valueOf(FormulationNumber.cardActive);
		ArrayList<Member> memberList = memberDao.find(columns, values);
		int[] counts = new int[consumeParts.length];
		for(Member m:memberList){
			double consume = 0;
			ArrayList<Bill> billList = billDao.findBillForMember(m.getMemberId());
			for(Bill b:billList){
				if(DayTransformer.transformToMonth(b.getBillTime()).compareTo(monthAndyear)==0){
					consume += b.getCostAfterDiscount();
				}
			}
			if(consume<200){
				counts[0]++;
			}
			else if(consume<500){
				counts[1]++;
			}
			else if(consume<1000){
				counts[2]++;
			}
			else if(consume<5000){
				counts[3]++;
			}
			else{
				counts[4]++;
			}
		}
		String[][] memberConsume = new String[consumeParts.length][2];
		for(int i=0;i<memberConsume.length;i++){
			memberConsume[i][0] = consumeParts[i];
			memberConsume[i][1] = String.valueOf(counts[i]);
		}
		return memberConsume;
	}

	public String[][] getMemberResidualDis(String monthAndyear){
		String[] columns = {"cardState"};
		String[] values = new String[1];
		values[0] = String.valueOf(FormulationNumber.cardActive);
		ArrayList<Member> memberList = memberDao.find(columns, values);
		int[] counts = new int[residualParts.length];
		for(Member m:memberList){
			if(DayTransformer.transformToMonth(m.getRegisterTime()).compareTo(monthAndyear)<=0){
				double residual = m.getResidual();
				if(residual<30){
					counts[0]++;
				}
				else if(residual<100){
					counts[1]++;
				}
				else if(residual<400){
					counts[2]++;
				}
				else if(residual<1000){
					counts[3]++;
				}
				else{
					counts[4]++;
				}
			}
		}
		String[][] memberResidual = new String[residualParts.length][2];
		for(int i=0;i<memberResidual.length;i++){
			memberResidual[i][0] = residualParts[i];
			memberResidual[i][1] = String.valueOf(counts[i]);
		}
		return memberResidual;
	}
	
	public String[][] getStoreSellingDis(String monthAndyear){
		ArrayList<Store> storeList = storeDao.findAllStore();
		String startDateString = monthAndyear + "-01";
		Date startDate = DayTransformer.transform(startDateString);
		Date endDate = DayTransformer.getNewMonth(startDate, 1);
		String[][] storeSelling = new String[storeList.size()][2];
		for(int i=0;i<storeList.size();i++){
			Store s = storeList.get(i);
			ArrayList<Bill> billList = billDao.findBillByStore(s.getStoreId(), startDate, endDate);
			double selling = 0;
			for(Bill b:billList){
				selling += b.getCostAfterDiscount();
			}
			storeSelling[i][0] = s.getStoreName();
			storeSelling[i][1] = String.valueOf(selling);
		}
		return storeSelling;
	}
	
	public String[][] getStoreOrderingDis(String monthAndyear){
		ArrayList<Store> storeList = storeDao.findAllStore();
		String startDateString = monthAndyear + "-01";
		Date startDate = DayTransformer.transform(startDateString);
		Date endDate = DayTransformer.getNewMonth(startDate, 1);
		String[][] storeOrdering = new String[storeList.size()][2];
		for(int i=0;i<storeList.size();i++){
			Store s = storeList.get(i);
			ArrayList<Order> orderList = orderDao.findOrderForStore(s.getStoreId(), startDate, endDate);
			double selling = 0;
			for(Order o:orderList){
				selling += o.getCostAfterDiscount();
			}
			storeOrdering[i][0] = s.getStoreName();
			storeOrdering[i][1] = String.valueOf(selling);
		}
		return storeOrdering;
	}
	
	@SuppressWarnings("rawtypes")
	public String[][] getTotalPopProductDis(String monthAndyear){
		String startDateString = monthAndyear + "-01";
		Date startDate = DayTransformer.transform(startDateString);
		Date endDate = DayTransformer.getNewMonth(startDate, 1);
		ArrayList<BillDetail> billDetailList = billDao.findBillDetailAll(startDate, endDate);
		Map<String,Integer> productList = new HashMap<String,Integer>();
		for(BillDetail bd:billDetailList){
			String productId = bd.getProduct().getProductId();
			if(productList.containsKey(productId)){
				Integer count = productList.get(productId);
				count += bd.getProductCount();
				productList.put(productId, count);
			}
			else{
				Integer count = bd.getProductCount();
				productList.put(productId, count);
			}
		}
		int size = (FormulationNumber.popluarProductCount<=productList.size())
				?FormulationNumber.popluarProductCount:productList.size();
		String[][] popProductDis = new String[size][2];
		for(int i=0;i<popProductDis.length;i++){
			String productId = null;
			Integer countMax = 0;
			Iterator iter = productList.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Integer val = (Integer) entry.getValue();
				if(val>countMax){
					productId = (String) entry.getKey();
					countMax = val;
				}
			}
			popProductDis[i][0] = productDao.findProduct(productId).getProductName();
			popProductDis[i][1] = String.valueOf(countMax);
			productList.remove(productId);
		}
		return popProductDis;
	}
	
	@SuppressWarnings("rawtypes")
	public String[][] getPopProductDis(String storeId,String monthAndyear){
		String startDateString = monthAndyear + "-01";
		Date startDate = DayTransformer.transform(startDateString);
		Date endDate = DayTransformer.getNewMonth(startDate, 1);
		ArrayList<BillDetail> billDetailList = billDao.findBillDetailByStore(storeId, startDate, endDate);
		Map<String,Integer> productList = new HashMap<String,Integer>();
		for(BillDetail bd:billDetailList){
			String productId = bd.getProduct().getProductId();
			if(productList.containsKey(productId)){
				Integer count = productList.get(productId);
				count += bd.getProductCount();
				productList.put(productId, count);
			}
			else{
				Integer count = bd.getProductCount();
				productList.put(productId, count);
			}
		}
		int size = (FormulationNumber.popluarProductCount<=productList.size())
						?FormulationNumber.popluarProductCount:productList.size();
		String[][] popProductDis = new String[size][2];
		for(int i=0;i<popProductDis.length;i++){
			String productId = null;
			Integer countMax = 0;
			Iterator iter = productList.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Integer val = (Integer) entry.getValue();
				if(val>countMax){
					productId = (String) entry.getKey();
					countMax = val;
				}
			}
			popProductDis[i][0] = productDao.findProduct(productId).getProductName();
			popProductDis[i][1] = String.valueOf(countMax);
			productList.remove(productId);
		}
		return popProductDis;
	}
	
}
