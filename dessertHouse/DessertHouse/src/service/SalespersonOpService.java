package service;

import java.util.ArrayList;

import models.Salesperson;

public interface SalespersonOpService {

	/**
	 * 添加一个服务员的信息
	 * @param salesperson
	 * @return
	 */
	public boolean addSalesperson(Salesperson salesperson);
	
	/**
	 * 删除一个服务员的信息
	 * @param salespersonId
	 * @return
	 */
	public boolean deleteSalesperson(String salespersonId);
	
	/**
	 * 修改一个服务员的信息
	 * @param salespersonId
	 * @param salesperson
	 * @return
	 */
	public boolean modifySalesperson(String salespersonId,Salesperson salesperson);
	
	/**
	 * 获得服务员的信息
	 * @param salespersonId
	 * @return
	 */
	public Salesperson getSalespersonInfo(String salespersonId);
	
	/**
	 * 获得所有的服务员
	 * @param salespersonId
	 * @return
	 */
	public ArrayList<Salesperson> getAllSalesperson(String salespersonId);
	
	/**
	 * 查找一个店铺里的服务员
	 * @param storeId
	 * @return
	 */
	public ArrayList<Salesperson> retrieveSalesperson(String storeId);
	
}
