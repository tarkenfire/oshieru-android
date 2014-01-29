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
import android.widget.NumberPicker;

public class CharacterQuizSetupFragment extends Fragment
{
	NumberPicker questionNumPicker;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_quiz_setup, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		
		//there is no xml for setting these values, must be done in code.
		questionNumPicker = (NumberPicker) getActivity().findViewById(R.id.quiz_setup_num_question_picker);
		questionNumPicker.setMinValue(1);
		questionNumPicker.setMaxValue(20);
		questionNumPicker.setWrapSelectorWheel(false);
		
	}
	
	
	
	
}
