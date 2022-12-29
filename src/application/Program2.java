package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		// Test 1.
		System.out.println("### TEST 1: Department findById ###");
		Department department = departmentDao.findById(3);
		System.out.println(department);
		System.out.println();
		
		// Test 2.
		System.out.println("### TEST 2: Department findAll ###");
		List<Department> list = departmentDao.findAll();
		for(Department obj : list) {
			System.out.println(obj);
		}
		System.out.println();
		
		// Test 3.
		System.out.println("### TEST 3: Department insert ###");
		Department newDep = new Department(null, "Music");
		departmentDao.insert(newDep);
		System.out.println("Inserted! New id: " + newDep.getId());
		System.out.println();
		
		// Test 4.
		System.out.println("### TEST 4: Department update ###");
		department = departmentDao.findById(5);
		department.setName("Infraestrutura");
		departmentDao.update(department);
		System.out.println("Updated complete!" + department.getName());
		System.out.println();
		
		// Test 5.
		System.out.println("### TEST 5: Department delete ###");
		System.out.print("Enter id department for delete: ");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Delete complete!");
		System.out.println();
				
		sc.close();
	}
}
