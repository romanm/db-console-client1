package org.dbconsoleclient1.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.dbconsoleclient1.domain.Customer;

public abstract class DecoratedTableDAO implements TableDAO {
	protected Connection conn = null;

	TableDAO decoratedTableDAO;
	
	public void setDecoratedTableDAO(TableDAO decoratedTableDAO) {
		this.decoratedTableDAO = decoratedTableDAO;
	}

	public void persist(Customer tableObject) throws SQLException {
		System.out.println(1001);
		System.out.println(getClass());
		System.out.println(decoratedTableDAO.getClass());
		decoratedTableDAO.persist(tableObject);
	}
	public void insert(Customer tableObject) throws SQLException {
		System.out.println(decoratedTableDAO);
		System.out.println(tableObject);
		decoratedTableDAO.insert(tableObject);
	}

	public Customer findByCustomerId(int custId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int findLastId() throws SQLException {
		return decoratedTableDAO.findLastId();
	}

	public void setConn(Connection conn) {
		if(null==this.conn)
			this.conn=conn;
		if(null!=decoratedTableDAO)
			decoratedTableDAO.setConn(conn);
	}

}
