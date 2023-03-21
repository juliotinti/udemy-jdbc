package application;

import model.dao.DAOFactory;
import model.dao.SellerDAO;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDAO sellerDao = DAOFactory.createSellerDao();
		
		System.out.println("---Test 1: Seller findById---");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);

	}

}
