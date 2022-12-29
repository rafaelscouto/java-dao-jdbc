package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department obj = new Department(1, "Cars");
		
		Seller seller = new Seller(21, "Rafael", "rafaelscouto@gmail.com", new Date(), 12000.0, obj);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.print(seller);
		
	}
}
