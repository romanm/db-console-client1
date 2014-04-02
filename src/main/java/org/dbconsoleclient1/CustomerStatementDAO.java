package org.dbconsoleclient1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dbconsoleclient1.dao.DecoratedTableDAO;
import org.dbconsoleclient1.domain.Customer;
import org.dbconsoleclient1.domain.TableObject;

public class CustomerStatementDAO extends DecoratedTableDAO{

	final String SQL_SELECTLASTID = "SELECT cust_id FROM customer ORDER BY cust_id DESC;";
	final String SQL_SELECTBIID = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
	final String SQL_INSERT = "INSERT INTO CUSTOMER ( NAME, AGE) VALUES ( ?, ?)";

	public void persist(Customer tableObject) throws SQLException {
		Customer customer = (Customer)tableObject;
		insert(customer);
		int findLastId = findLastId();
		customer.setCustId(findLastId);
	}
	public void insert(Customer tableObject) throws SQLException {
		Customer customer = (Customer)tableObject;
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
		PreparedStatement ps = conn.prepareStatement(SQL_SELECTLASTID);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			int lastId = rs.getInt(1);
			return lastId;
		}
		return 0;
	}

}
