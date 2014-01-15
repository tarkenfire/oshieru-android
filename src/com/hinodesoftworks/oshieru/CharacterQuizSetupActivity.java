/* 
 * Date: Jan 11, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CharacterQuizSetupActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_setup);
	}
	
	public void onClick(View v)
	{
		Intent qi = new Intent(this, CharacterQuizActivity.class);
		startActivity(qi);
	}
}
