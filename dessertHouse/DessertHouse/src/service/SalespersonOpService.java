package service;

import java.util.ArrayList;

import models.Salesperson;

public interface SalespersonOpService {

	/**
	 * ���һ������Ա����Ϣ
	 * @param salesperson
	 * @return
	 */
	public boolean addSalesperson(Salesperson salesperson);
	
	/**
	 * ɾ��һ������Ա����Ϣ
	 * @param salespersonId
	 * @return
	 */
	public boolean deleteSalesperson(String salespersonId);
	
	/**
	 * �޸�һ������Ա����Ϣ
	 * @param salespersonId
	 * @param salesperson
	 * @return
	 */
	public boolean modifySalesperson(String salespersonId,Salesperson salesperson);
	
	/**
	 * ��÷���Ա����Ϣ
	 * @param salespersonId
	 * @return
	 */
	public Salesperson getSalespersonInfo(String salespersonId);
	
	/**
	 * ������еķ���Ա
	 * @param salespersonId
	 * @return
	 */
	public ArrayList<Salesperson> getAllSalesperson(String salespersonId);
	
	/**
	 * ����һ��������ķ���Ա
	 * @param storeId
	 * @return
	 */
	public ArrayList<Salesperson> retrieveSalesperson(String storeId);
	
}
