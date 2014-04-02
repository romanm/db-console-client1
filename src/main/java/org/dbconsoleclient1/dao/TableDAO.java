package org.dbconsoleclient1.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.dbconsoleclient1.domain.Customer;

public interface TableDAO {
	void setConn(Connection conn);
	void persist(Customer tableObject) throws SQLException;
	public void insert(Customer tableObject) throws SQLException;
	public Customer findByCustomerId(int custId);
	public int findLastId() throws SQLException;
}
