package application;

import java.util.List;

import model.dao.DAOFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDAO sellerDao = DAOFactory.createSellerDao();
		
		System.out.println("---Test 1: Seller findById---");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		System.out.println();
		
		System.out.println("---Test 2: Seller findByDepartmentId---");
		Department department = new Department(1, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		list.forEach(System.out::println);
		System.out.println();
	}

}
