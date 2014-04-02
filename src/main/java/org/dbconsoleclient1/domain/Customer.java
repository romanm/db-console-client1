package org.dbconsoleclient1.domain;

public class Customer
//extends TableObject 
{
	int custId;
	String name;
	int age;
	public Customer(String name, int age) {
		this.name=name;
		this.age=age;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public String toString() {
		return "Customer [age=" + age + ", custId=" + custId + ", name=" + name
				+ "]";
	}

	public int getId() {
		return getCustId();
	}
}
