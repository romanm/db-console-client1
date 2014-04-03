package org.dbconsoleclient2;

import java.util.Observable;
import java.util.Observer;

import javax.sql.DataSource;


public class SqlHandler implements Observer{
	DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private void update(SqlObservableConsole sqlConsole, String sql) {
//		System.out.println("\n Received Response: " + sql );
		System.out.println(" < Received Response: " + sql );
		if(
				"quit".equals(sql.toLowerCase())
				||"<".equals(sql.toLowerCase())
				){
			sqlConsole.quit();
			System.out.println("bey!");
		}else{
			Thread thread = new Thread(new SqlThread(this, sql));
			thread.start();
		}
	}
	public void update(Observable o, Object arg) {
		update((SqlObservableConsole)o, (String)arg);
	}
}
