package org.dbconsoleclient1.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dbconsoleclient1.dao.DecoratedTableDAO;
import org.dbconsoleclient1.domain.Customer;

public class CustomerStatementDAO extends DecoratedTableDAO {

	final String SQL_SELECTLASTID = "SELECT cust_id FROM customer ORDER BY cust_id DESC;";
	final String SQL_SELECTBIID = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
	final String SQL_INSERT = "INSERT INTO CUSTOMER ( NAME, AGE) VALUES ( ?, ?)";

	public void persist(Customer customer) throws SQLException {
		insert(customer);
		int findLastId = findLastId();
		customer.setCustId(findLastId);
	}
	public void insert(Customer customer) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_INSERT);
		ps.setString(1, customer.getName());
		ps.setInt(2, customer.getAge());
		ps.executeUpdate();
		ps.close();
	}

	public Customer findByCustomerId(int custId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int findLastId() throws SQLException {
		int lastId = 0;
		PreparedStatement ps = conn.prepareStatement(SQL_SELECTLASTID);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			lastId = rs.getInt(1);
		}
		rs.close();
		ps.close();
		return lastId;
	}

}
