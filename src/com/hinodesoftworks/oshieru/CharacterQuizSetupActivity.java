/* 
 * Date: Jan 11, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.NumberPicker;

public class CharacterQuizSetupActivity extends Activity
{
	NumberPicker questionNumPicker;
	CheckBox hiraCheck;
	CheckBox kataCheck;
	CheckBox kanjiCheck;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_setup);
		
		//TODO: Get Title from resource rather than hard code
		getActionBar().setTitle("Oshieru - Quiz Setup");
		
		questionNumPicker = (NumberPicker)findViewById(R.id.quiz_setup_num_question_picker);
		hiraCheck = (CheckBox)findViewById(R.id.quiz_setup_hira_check);
		kataCheck = (CheckBox)findViewById(R.id.quiz_setup_kata_check);
		kanjiCheck = (CheckBox)findViewById(R.id.quiz_setup_kanji_check);
	}
	
	public void onClick(View v)
	{
		//having none of the checkboxes checked will crash the next activity and must be stopped.
		if (!hiraCheck.isChecked() && !kataCheck.isChecked() && !kanjiCheck.isChecked())
		{
			AlertDialog.Builder adBuilder = new AlertDialog.Builder(this);
			
			adBuilder.setTitle("Must Select Quiz Set(s)");
			
			adBuilder.setMessage("At least one quiz set must be selected to continue.");
			
			adBuilder.setCancelable(false);
			adBuilder.setPositiveButton("Ok", 
					new DialogInterface.OnClickListener()
					{	
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							//does nothing
						}
					}
					);
			
			AlertDialog dialog = adBuilder.create();
			dialog.show();
			
			
			return;
		}
		
		
		Intent qi = new Intent(this, CharacterQuizActivity.class);
		qi.putExtra("question_num_value", questionNumPicker.getValue());
		qi.putExtra("flag_hira", hiraCheck.isChecked());
		qi.putExtra("flag_kata", kataCheck.isChecked());
		qi.putExtra("flag_kanji", kanjiCheck.isChecked());
		startActivity(qi);
	}
}
