/* 
 * Date: Jan 11, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import android.app.Activity;
import android.os.Bundle;

public class VocabMasterListActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vocab_list);
		
		//TODO: Get Title from resource rather than hard code
		getActionBar().setTitle("Oshieru - Vocab Sets");
	}
}
