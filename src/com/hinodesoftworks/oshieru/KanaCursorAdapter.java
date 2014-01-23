package com.hinodesoftworks.oshieru;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class KanaCursorAdapter extends CursorAdapter
{
	public static final int KANA_CHAR_FIELD = 1;
	public static final int KANA_ROMA_FIELD = 2;
	
	private LayoutInflater vi;
	
	public KanaCursorAdapter(Context context, Cursor c, boolean autoRequery)
	{
		super(context, c, autoRequery);
		this.vi = LayoutInflater.from(context);		
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor)
	{
		TextView charField = (TextView)view.findViewById(R.id.kana_list_char);
		TextView romaField = (TextView)view.findViewById(R.id.kana_list_roma);
		
		charField.setText(cursor.getString(KANA_CHAR_FIELD));
		romaField.setText(cursor.getString(KANA_ROMA_FIELD));
		
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent)
	{
		View viewToReturn = vi.inflate(R.layout.listview_kana, parent, false);
		return viewToReturn;
	}


}
