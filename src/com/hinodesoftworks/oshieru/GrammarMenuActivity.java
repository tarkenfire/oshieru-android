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

public class GrammarMenuActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grammar_menu);	
	}
	
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
