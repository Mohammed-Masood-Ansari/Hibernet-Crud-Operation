package com.ty.hibernet.Demo1.assignment;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.hibernet.Demo2.Student;
import com.ty.hibernet.Demo2.studentException.IdNotFoundException;

public class StudentAssignment {
	
	static Student student;
	static EntityManagerFactory entityManagerFactory = null;
	static EntityManager entityManager=null;
	static EntityTransaction entityTransaction = null;
	static Scanner sc = new Scanner(System.in);
	static Query query =null;
	
	public static void displayStudent(Student student) throws IdNotFoundException
	{
		entityManagerFactory = Persistence.createEntityManagerFactory("student");
		entityManager = entityManagerFactory.createEntityManager();
		
		System.out.println("1.DisplayAllData\n2.DisplayFromid");
		int ch = sc.nextInt();
		switch(ch)
		{
		  case 1:{
			  		String displayDataString="from Student";
			  		query=entityManager.createQuery(displayDataString);
				
			  		List<Student> list = query.getResultList();
				
					for(Student student1:list)
					{
						System.out.println(student1);
					}
		  		}
		  break;
		  case 2:{
			  		System.out.print("Enter your id to display data");
			  		int id = sc.nextInt();
			  		student  = entityManager.getReference(Student.class,id);
			  		if(student.getS_id()!=id)
			  		{
			  			throw new IdNotFoundException("Please Enter Valid Id");
			  		}
			  		else
			  		{
			  			System.out.println(student.getS_id()+"\tt"+student.getS_name()+"\t\t"+student.getPhone_no()+"\t\t"+student.getS_branch());
			  		}
		  }
		  break;
		}
	}
	
	public static void updateData(Student student) {
		
		try {
			entityManagerFactory=Persistence.createEntityManagerFactory("student");
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		System.out.println("Enter the id to be updated!!!");
		int id = sc.nextInt();
		System.out.println("Do you want to update the name 'y/n'");
		String y=sc.next(); 
		if(y.equalsIgnoreCase("y"))
		{
			System.out.println("Enter the name!!!");
			String name= sc.next();
			try {
				String updateName="update from Student set s_name=:name where s_id=:id";
				query=entityManager.createQuery(updateName);
				query.setParameter("name",name);
				query.setParameter("id",id);
				query.executeUpdate();
				entityTransaction.commit();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			System.out.println("Do you want to update the phone no 'y/n'");
			char z = sc.next().charAt(0);
			if(z=='y') {
				System.out.println("Enter the Phone Number");
				int phone = sc.nextInt();
				try {
					String updatePhone="update from Student set phone_no=:phone where s_id=:id";
					query=entityManager.createQuery(updatePhone);
					query.setParameter("phone",phone);
					query.setParameter("id",id);
					query.executeUpdate();
					entityTransaction.commit();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		}
		else {
			
			System.out.println("Do you want to update the phone no 'y/n'");
			char z = sc.next().charAt(0);
			if(z=='y') {
				System.out.println("Enter the Phone Number");
				int phone = sc.nextInt();
				try {
					String updatePhone="update from Student set phone_no=:phone where s_id=:id";
					query=entityManager.createQuery(updatePhone);
					query.setParameter("phone",phone);
					query.setParameter("id",id);
					query.executeUpdate();
					entityTransaction.commit();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public static void deleteStudent(Student student) throws IdNotFoundException {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("student");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		System.out.println("Enter the id to be deleted");
		int id = sc.nextInt();
		student = entityManager.find(Student.class,id);
		
		if(student.getS_id()!=id)
		{
			throw new IdNotFoundException("Invalid Id Please Check your id");
		}
		else
		{	
			String deleteData = "delete from Student where id=:id";
			query = entityManager.createQuery(deleteData);
			query.setParameter("id",id);
			query.executeUpdate();
			entityTransaction.commit();
		}
	}
	
	public static void main(String[] args) throws IdNotFoundException  {
	
		StudentAssignment assignment = new StudentAssignment();
		boolean exit = false;
		while(!exit)
		{
			System.out.println("Enter Your Choice!!!");
			System.out.println("1.Display\n2.Update\n3.Delete\n4.Exit");
			int ch = sc.nextInt();
			
			switch(ch) {
			
					case 1:{
						
							try {
								assignment.displayStudent(student);
							} catch (Exception e) {
								throw new IdNotFoundException("Id is Inavlid");
							}
					}
					break;
					
					case 2:{
						assignment.updateData(student);
					}
					break;
					
					case 3:{
						try {
							assignment.deleteStudent(student);
						} catch (Exception e) {
							throw new IdNotFoundException("Id is Inavlid");
						}
						
					}
					break;
					case 4: System.exit(0);
			}
		}
	}
}
