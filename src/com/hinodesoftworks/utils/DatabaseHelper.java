/* 
 * Date: Jan 15, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.utils
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper
{
	public static final String DATABASE_NAME = "oishDB";
	public static final int DATABASE_VERSION = 1;
	
	@SuppressLint("SdCardPath")
	public static final String DATABASE_PATH = "/data/data/com.hinodesoftworks.oshieru/databases/";
	
	private Context ctx;
	private SQLiteDatabase database = null;
	
	private String internalDir;

	public DatabaseHelper (Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.ctx = context;
		createDatabase();		
	}

	private void createDatabase()
	{
		if (databaseExists())
		{
			return;
		}
		else
		{
			try
			{
				this.getReadableDatabase();
				
				InputStream is = ctx.getAssets().open(DATABASE_NAME + ".sqlite");
				String fullPath =  DATABASE_PATH + DATABASE_NAME;
				OutputStream os = new FileOutputStream(fullPath);
				
				 byte[] buffer = new byte[1024];
				 int length;
				 
				 while ((length = is.read(buffer)) >0)
				 {
					 os.write(buffer, 0, length);
				 }
				 
				 os.flush();
				 os.close();
				 is.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private boolean databaseExists()
	{
		String path = internalDir + "/" + DATABASE_NAME;
		File testFile = new File(path);
		
		return testFile.exists();
	}
	
	public void openDatabase() 
	{
		String path = DATABASE_PATH + DATABASE_NAME;
		database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
	}
	
	public SQLiteDatabase getDatabase()
	{
		if (database != null)
			return database;
		else
			throw new NullPointerException();
	}
	
	

	//database is static, creation and upgrade is unneeded.
	@Override
	public void onCreate(SQLiteDatabase db){}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
	
	
}
