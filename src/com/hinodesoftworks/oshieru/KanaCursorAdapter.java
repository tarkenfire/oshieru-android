/* 
 * Date: Jan 30, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * The Class KanaCursorAdapter.
 */
public class KanaCursorAdapter extends CursorAdapter
{
	public static final int KANA_CHAR_FIELD = 1;
	public static final int KANA_ROMA_FIELD = 2;
	
	private LayoutInflater vi;
	
	/**
	 * Instantiates a new kana cursor adapter.
	 *
	 * @param context the context
	 * @param c the cursor to manage
	 * @param autoRequery the auto requery flag
	 */
	public KanaCursorAdapter(Context context, Cursor c, boolean autoRequery)
	{
		super(context, c, autoRequery);
		this.vi = LayoutInflater.from(context);		
	}

	/* (non-Javadoc)
	 * @see android.widget.CursorAdapter#bindView(android.view.View, android.content.Context, android.database.Cursor)
	 */
	@Override
	public void bindView(View view, Context context, Cursor cursor)
	{
		TextView charField = (TextView)view.findViewById(R.id.kana_list_char);
		TextView romaField = (TextView)view.findViewById(R.id.kana_list_roma);
		
		charField.setText(cursor.getString(KANA_CHAR_FIELD));
		romaField.setText(cursor.getString(KANA_ROMA_FIELD));
		
	}

	/* (non-Javadoc)
	 * @see android.widget.CursorAdapter#newView(android.content.Context, android.database.Cursor, android.view.ViewGroup)
	 */
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent)
	{
		View viewToReturn = vi.inflate(R.layout.listview_kana, parent, false);
		return viewToReturn;
	}


}
