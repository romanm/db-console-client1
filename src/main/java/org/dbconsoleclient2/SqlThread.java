package org.dbconsoleclient2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class SqlThread implements Runnable{

		/**
		 * 
		 */
		private final SqlHandler sqlHandler;
		private String sql;

		public SqlThread(SqlHandler sqlHandler, String sql) {
			super();
			this.sqlHandler = sqlHandler;
			this.sql=sql;
		}

		public void run() {
			Connection conn=null;
			try {
				conn = this.sqlHandler.dataSource.getConnection();
				Statement statment = conn.createStatement();
				boolean execute = statment.execute(sql);
				ResultSet executeQuery = statment.executeQuery(sql);
				showSizeWithWhile(executeQuery);
//				showSizeWithBeforeFirst(executeQuery);
//				showSizeWithLast(executeQuery);
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				System.err.println(e);
				throw new RuntimeException(e);
			} finally {
				if (conn != null) 
					try {
						conn.close();
					} catch (SQLException e) {}
			}
			System.out.print("> ");
		}

		private void showSizeWithWhile(ResultSet executeQuery) throws SQLException {
			int size=0;
			for(;executeQuery.next();size++){}
			System.out.println(size);
		}

		private void showSizeWithBeforeFirst(ResultSet executeQuery) throws SQLException {
			int size= 0;
			executeQuery.beforeFirst();  
			boolean last = executeQuery.last();  
			int row = executeQuery.getRow();
			System.out.println(row);
		}

		private void showSizeWithLast(ResultSet executeQuery) throws SQLException {
			boolean last = executeQuery.last();
			int row = executeQuery.getRow();
			System.out.println(row);
		}
		
	}