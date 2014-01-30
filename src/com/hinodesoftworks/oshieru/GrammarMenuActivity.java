/* 
 * Date: Jan 30, 2014
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

/**
 * The Class GrammarMenuActivity.
 */
public class GrammarMenuActivity extends Activity
{
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grammar_menu);	
		getActionBar().setTitle("Oshieru - Grammar");
	}
	
	/**
	 *  On click callback for button in this activity's fragment.
	 *
	 * @param v the button clicked
	 */
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.button_conjugation:
				Intent ci = new Intent(this, ConjugationSifterActivity.class);
				startActivity(ci);
				break;
				
			case R.id.button_particles:
				Intent pi = new Intent(this, ParticleQuizActivity.class);
				startActivity(pi);
				break;
		}
	}
}
