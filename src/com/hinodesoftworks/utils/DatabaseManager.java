/* 
 * Date: Jan 16, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.utils
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager
{

	private SQLiteDatabase database;
	
	public DatabaseManager(SQLiteDatabase database)
	{
		this.database = database;
	}
	
	public Cursor querySingleTableData(String tableName)
	{
		return database.query(tableName, null, null, null, null, null, null);
	}
	
	public Cursor queryRandomTableData(int numOfResults, String tableName)
	{
		return null;
	}

	
	
	
}
