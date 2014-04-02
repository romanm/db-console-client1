package org.dbconsoleclient2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App2 {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		System.out.println("------------------------------------------------------------");
		System.out.println("select * from customer");
		System.out.println("Welcome DB console");
		System.out.println("Enter Text >");
		SqlObservableConsole sqlObservableConsole
			= (SqlObservableConsole) context.getBean("sqlObservableConsole");
		sqlObservableConsole.getThread().start();
		System.out.println("------------------------------------------------------------");
	}
}
