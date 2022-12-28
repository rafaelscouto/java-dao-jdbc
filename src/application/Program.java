package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department obj = new Department(1, "Cars");
		
		Seller seller = new Seller(21, "Rafael", "rafaelscouto@gmail.com", new Date(), 12000.0, obj);
		
		System.out.print(seller);
		
	}
}
