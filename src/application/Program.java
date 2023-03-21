package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DAOFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
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
		
		System.out.println("---Test 3: Seller findAll---");
		list = sellerDao.findAll();
		list.forEach(System.out::println);
		System.out.println();
		
		System.out.println("---Test 4: Seller insert---");
		Seller newSeller = new Seller(null, "Bruce", "batman@gmail.com", new Date(), 4000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New ID = " + newSeller.getId());
		System.out.println();
		
		System.out.println("---Test 5: Seller update---");
		seller = sellerDao.findById(1);
		seller.setName("Martha Waine");
		sellerDao.update(seller);
		System.out.println("Update Completed");
		System.out.println();
		
		System.out.println("---Test 6: Seller delete---");
		System.out.println("Enter id for delete test: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		sc.nextLine();
		System.out.println("Delete Completed");
		System.out.println();
		
		sc.close();
	}

}
