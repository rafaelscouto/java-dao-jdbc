package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		// Test 1.
		System.out.println("### TEST 1: Seller findById ###");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		System.out.println();
		
		// Test 2.
		System.out.println("### TEST 2: Seller findByDepartment ###");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for(Seller obj : list) {
			System.out.println(obj);
		}
		System.out.println();
		
		// Test 3.
		System.out.println("### TEST 3: Seller findAll ###");
		list = sellerDao.findAll();
		for(Seller obj : list) {
			System.out.println(obj);
		}
		System.out.println();
		
		// Test 4.
		System.out.println("### TEST 4: Seller insert ###");
		Seller newSeller = new Seller(null, "Rafael", "rafaelscouto@gmail.com", new Date(), 12000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id: " + newSeller.getId());
		System.out.println();
		
		// Test 5.
		System.out.println("### TEST 5: Seller update ###");
		seller = sellerDao.findById(1);
		seller.setName("Maria das Neves");
		sellerDao.update(seller);
		System.out.println("Updated complete!" + seller.getName());
		System.out.println();
		
		// Test 5.
		System.out.println("### TEST 6: Seller delete ###");
		System.out.print("Enter id seller for delete: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete complete!");
		System.out.println();
		
		sc.close();
	}
}
