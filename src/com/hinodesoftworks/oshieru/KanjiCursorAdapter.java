package com.hinodesoftworks.oshieru;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class KanjiCursorAdapter extends CursorAdapter
{
	public static final int KANJI_CHAR_FIELD = 1;
	public static final int KANJI_RADICAL_FIELD = 4;
	public static final int KANJI_MEANINGS_FIELD = 5;

	private LayoutInflater vi;
	
	public KanjiCursorAdapter(Context context, Cursor c, boolean autoRequery)
	{
		super(context, c, autoRequery);
		this.vi = LayoutInflater.from(context);		
	}
	
	@Override
	public void bindView(View view, Context context, Cursor cursor)
	{
		TextView charField = (TextView)view.findViewById(R.id.kanji_list_char);
		TextView radField = (TextView)view.findViewById(R.id.kanji_list_radical);
		TextView defField = (TextView)view.findViewById(R.id.kanji_list_defs);
		
		charField.setText(cursor.getString(KANJI_CHAR_FIELD));
		radField.setText(cursor.getString(KANJI_RADICAL_FIELD));
		defField.setText(cursor.getString(KANJI_MEANINGS_FIELD));
		
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent)
	{
		View viewToReturn = vi.inflate(R.layout.listview_kanji, parent, false);
		return viewToReturn;
	}
}
