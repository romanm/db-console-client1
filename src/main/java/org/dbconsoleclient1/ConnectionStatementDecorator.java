package org.dbconsoleclient1;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbconsoleclient1.dao.DecoratedTableDAO;
import org.dbconsoleclient1.domain.Customer;

public class ConnectionStatementDecorator extends DecoratedTableDAO{
	private DataSource dataSource;
	public ConnectionStatementDecorator(DataSource dataSource, DecoratedTableDAO decoratedTableDAO){
		this.dataSource=dataSource;
		super.setDecoratedTableDAO(decoratedTableDAO);
	}
/*
	public void setTableDAO(DecoratedTableDAO decoratedTableDAO) {
		super.setDecoratedTableDAO(decoratedTableDAO);
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
 * */

	public void persist(Customer customer) {
		try {
			if(null==conn){
				super.setConn(dataSource.getConnection());
			}
			super.persist(customer);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) 
				try {
					conn.close();
				} catch (SQLException e) {}
		}		
	}
	@Override
	public void insert(Customer customer) {
		try {
			if(null==conn)
				super.setConn(dataSource.getConnection());
			super.insert(customer);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) 
				try {
					conn.close();
				} catch (SQLException e) {}
		}
	}

	public Customer findByCustomerId(int custId) {
		return null;
	}

	@Override
	public int findLastId() {
		int lastId = 0;
		try {
			if(null==conn)
				conn = dataSource.getConnection();
			lastId=super.findLastId();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) 
				try {
					conn.close();
				} catch (SQLException e) {}
		}
		return lastId;
	}

}
