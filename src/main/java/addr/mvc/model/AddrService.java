package addr.mvc.model;

import java.util.ArrayList;

import mvc.domain.Address;

public class AddrService {
	private AddrDAO dao;
	
	
	//SingleTon Object Model
	private static final AddrService instance = new AddrService();
	private AddrService() {
		dao = new AddrDAO();
	}
	
	public static AddrService getInstance()	{
		return instance;
	}
	
	//커다란 여러개의 데이터 베이스를 핸들링 
	public ArrayList<Address> listS() {
		//구체적인 데이터베이스 하나를 핸들링
		return dao.list();
	}
	
	public boolean insertS(Address address) {
		return dao.insert(address);
	}
	
	public boolean deleteS(long seq) {
		return dao.delete(seq);
	}
}
