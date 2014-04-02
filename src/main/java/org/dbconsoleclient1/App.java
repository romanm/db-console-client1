package org.dbconsoleclient1;

import org.dbconsoleclient1.domain.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		ConnectionStatementDecorator customerDAO
			= (ConnectionStatementDecorator) context.getBean("customerDAO");
		Customer customer = new Customer( "begemot",54);
		
		System.out.println(customer);
		customerDAO.persist(customer);
//		customerDAO.insert(customer);
		System.out.println(customer);
	}
}
