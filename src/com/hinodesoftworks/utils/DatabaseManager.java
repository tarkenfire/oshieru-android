/* 
 * Date: Jan 30, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.utils
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * The Class DatabaseManager.
 */
public class DatabaseManager
{

	private SQLiteDatabase database;
	
	/**
	 * Instantiates a new database manager.
	 *
	 * @param database the database
	 */
	public DatabaseManager(SQLiteDatabase database)
	{
		this.database = database;
	}
	
	/**
	 * Query all rows from a single table.
	 *
	 * @param tableName the table name
	 * @return the cursor
	 */
	public Cursor querySingleTableData(String tableName)
	{
		return database.query(tableName, null, null, null, null, null, null);
	}
	
	/**
	 * Query a set of random results from a table
	 *
	 * @param numOfResults the number of results
	 * @param tableName the table name
	 * @return the cursor
	 */
	public Cursor queryRandomTableData(int numOfResults, String tableName)
	{
		return database.query(tableName, null, null, 
				null, null, null, "RANDOM()", String.valueOf(numOfResults));
	}
	
	/**
	 * Query data excluding a specific row based on a field and it's value
	 *
	 * @param table the table
	 * @param limit the amount of items to return in the cursor
	 * @param excludeField the exclude field
	 * @param exclude the exclude
	 * @return the cursor
	 */
	public Cursor queryLimitedTableData(String table, int limit, 
			String excludeField, String exclude)
	{
		return database.query(table, null, excludeField +" != \'" + exclude + "\'", 
				null, null, null, "RANDOM()", String.valueOf(limit));
		
		
	}

	
	
	
}
