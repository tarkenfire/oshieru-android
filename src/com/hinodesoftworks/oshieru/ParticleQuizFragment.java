/* 
 * Date: Jan 11, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ParticleQuizFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_particle_quiz, null);
	}
}
