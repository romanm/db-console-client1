package org.dbconsoleclient2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import com.sun.rowset.JdbcRowSetImpl;

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
//				Statement statment = conn.createStatement();
				Statement statment = conn.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE
						);
				boolean execute = statment.execute(sql);
				ResultSet executeQuery = statment.executeQuery(sql);
				showColumnNames(executeQuery);
//				showSizeWithWhile(executeQuery);
//				showSizeWithBeforeFirst(executeQuery);
//				showSizeWithLast(executeQuery);
//				testRowSet(executeQuery);
			} catch (SQLException e) {
				System.err.println(e.getMessage());
//				System.err.println(e);
//				throw new RuntimeException(e);
			} finally {
				if (conn != null) 
					try {
						conn.close();
					} catch (SQLException e) {}
			}
			System.out.print("> ");
		}

		private void showColumnNames(ResultSet executeQuery) throws SQLException {
			ResultSetMetaData metaData = executeQuery.getMetaData();
			int columnCount = metaData.getColumnCount();
			for (int i = 1; i < columnCount; i++) {
				System.out.print(metaData.getColumnName(i)+" | ");
			}
			System.out.println("");
			executeQuery.next();
			giveWath(executeQuery);
			executeQuery.next();
			giveWath(executeQuery);
			executeQuery.absolute(1);
			giveWath(executeQuery);
		}

		private void giveWath(ResultSet executeQuery) throws SQLException {
			String columnLabel = "first";
			String string = executeQuery.getString(columnLabel);
			int row = executeQuery.getRow();
			System.out.println(row+" "+string);
		}

		private void testRowSet(ResultSet executeQuery) throws SQLException {
			JdbcRowSetImpl jdbcRowSet = new JdbcRowSetImpl(executeQuery);
			System.out.println(jdbcRowSet.isReadOnly());
			System.out.println(jdbcRowSet.getMaxRows());
			System.out.println(jdbcRowSet.getCommand());
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