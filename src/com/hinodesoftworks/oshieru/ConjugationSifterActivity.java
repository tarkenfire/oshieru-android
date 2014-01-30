/* 
 * Date: Jan 30, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hinodesoftworks.utils.ConjugationItem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

/**
 * The Class ConjugationSifterActivity.
 */
public class ConjugationSifterActivity extends Activity
{
	public static final int FLAG_TYPE1_VERB = 1; //"u verbs" 
	public static final int FLAG_TYPE2_VERB = 2; // "ru verbs"
	public static final int FLAG_TYPE3_VERB = 3; // "irregular verbs"
	public static final int FLAG_TYPE1_VERB_KANA = 11;
	public static final int FLAG_TYPE2_VERB_KANA = 12;
	public static final int FLAG_TYPE3_VERB_KANA = 13;
	
	//only very common and formal verb conjugatuions in this release
	//casual and less common conjugations can be added in future
	//releases by adding new "get?Tense?" form methods.
	EditText inputField;
	ListView resultView;
	int verbTypeFlag = FLAG_TYPE2_VERB;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conj_sifter);
		getActionBar().setTitle("Oshieru - Verb Conjugation");
		
		inputField = (EditText)findViewById(R.id.conj_sifter_input);
		resultView = (ListView)findViewById(R.id.conj_sifter_results);
	}
	
	/**
	 *  On click callback for button in this activity's fragment.
	 *
	 * @param v the button clicked
	 */
	public void onClick(View v)
	{
		String inputValue = inputField.getText().toString();
	
		//regardless of outcome, close keyboard.
		InputMethodManager imm = (InputMethodManager)getSystemService(
			      Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(inputField.getWindowToken(), 0);
		
		if (setVerbTypeFlag(inputValue))
		{
		
			ArrayList<ConjugationItem>conjAL = new ArrayList<ConjugationItem>();
			
			String formalForm = makeVerbFormalForm(inputValue);
			ConjugationItem formal = new ConjugationItem(formalForm, "Formal Form");
			conjAL.add(formal);
			
			ConjugationItem pastTense = new ConjugationItem(getPastTenseFormal(formalForm), "Past Tense (Formal)");
			conjAL.add(pastTense);
			
			ConjugationItem negativeTense = new ConjugationItem(getPresentNegativeTenseFormal(formalForm), "Negative Tense (Formal)");
			conjAL.add(negativeTense);
			
			ConjugationItem negativeTensePast = new ConjugationItem(getPastNegativeTenseFormal(formalForm), "Negative Past Tense (Formal)");
			conjAL.add(negativeTensePast);
			
			ConjugationItemAdapter adapter = new ConjugationItemAdapter(this, conjAL);
			resultView.setAdapter(adapter);
		}
		else
		{
			AlertDialog.Builder adBuilder = new AlertDialog.Builder(this);
			
			adBuilder.setTitle("Invalid Entry");
			
			adBuilder.setMessage("Entry was not recognized as a valid verb. Please check spelling and try again.");
			
			adBuilder.setCancelable(false);
			adBuilder.setPositiveButton("Ok", 
					new DialogInterface.OnClickListener()
					{
						
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							//do nothing
						}
					}
					);
			
			AlertDialog dialog = adBuilder.create();
			dialog.show();		
		}
	}
	
	/**
	 * Sets the verb type flag.
	 *
	 * @param verb the potential verb
	 * @return true, if input is a verb
	 */
	private boolean setVerbTypeFlag(String verb)
	{
		//the app will go on good faith that the user will enter actual japanese
		//verbs and will try and conjugate anything that ends in ru or u
		if (isEnglishOnly(verb))
		{
			//check for irregular first
			if (verb.toLowerCase(Locale.US).equals("suru") 
					|| verb.toLowerCase(Locale.US).equals("kuru"))
			{
				verbTypeFlag = FLAG_TYPE3_VERB;
				return true;
			} 
			else if (verb.toLowerCase(Locale.US).endsWith("ru"))
			{
				verbTypeFlag = FLAG_TYPE2_VERB;
				return true;
			}
			else if (verb.toLowerCase(Locale.US).endsWith("u"))
			{
				verbTypeFlag = FLAG_TYPE1_VERB;
				return true;
			}
		}
		else //is non-english (or contains no normal english chars), check for japanese characters
		{
			if (verb.equals("する") || verb.equals("剃る") || verb.equals("くる") || verb.equals(""))
			{
				verbTypeFlag = FLAG_TYPE3_VERB_KANA;
				return true;
			}
			else if (verb.endsWith("る"))
			{
				verbTypeFlag = FLAG_TYPE2_VERB_KANA;
				return true;
			}
			else if (verb.endsWith("う"))
			{
				verbTypeFlag = FLAG_TYPE1_VERB_KANA;
				return true;
			}	
		}	
		return false;
	}
	
	/**
	 * Checks if verb is in english only.
	 *
	 * @param verb the verb
	 * @return true, if verb is english only
	 */
	private boolean isEnglishOnly(String verb)
	{
		Pattern basicEngChars = Pattern.compile("[A-Za-z]+$");
		Matcher match = basicEngChars.matcher(verb);
		
		return match.matches();
	}
	
	/**
	 * Conjugates verb to the formal form.
	 *
	 * @param verb the verb
	 * @return the conjugated verb
	 */
	private String makeVerbFormalForm(String verb)
	{
		//u verb - remove last u, replace with imasu
		//ru verb, remove last ru, replace with masu
		//iregs, specific for each type.
		
		String stringToReturn = "";
		stringToReturn = verb.substring(0, verb.length() - 1);
		
		switch(verbTypeFlag)
		{
			case FLAG_TYPE1_VERB:
				stringToReturn = verb.substring(0, verb.length() - 1);
				stringToReturn += "masu";
				break;
			case FLAG_TYPE2_VERB:
				stringToReturn = verb.substring(0, verb.length() - 2);
				stringToReturn += "masu";
				break;
			case FLAG_TYPE3_VERB:
				if (verb.toLowerCase(Locale.US).equals("suru"))
				{
					return "shimasu";
				}
				else if (verb.toLowerCase(Locale.US).equals("kuru"))
				{
					return "kimasu";
				}
				break;
			case FLAG_TYPE1_VERB_KANA:
				stringToReturn = verb.substring(0, verb.length() - 1);
				stringToReturn += "ます";
				break;
			case FLAG_TYPE2_VERB_KANA:
				stringToReturn = verb.substring(0, verb.length() - 1);
				stringToReturn += "ます";
				break;
			case FLAG_TYPE3_VERB_KANA:
				if (verb.equals("する") || verb.equals("剃る"))
				{
					return "します";
					
				}
				else if (verb.equals("くる") || verb.equals("来る"))
				{
					return "来ます";
				}
				break;
		}
		
		
		Log.i("Verb Formal", stringToReturn);
		return stringToReturn;
	}
	
	/**
	 * Gets the past tense formal.
	 *
	 * @param verbFormal the verb formal
	 * @return the past tense formal
	 */
	private String getPastTenseFormal(String verbFormal)
	{
		switch(verbTypeFlag)
		{
			case FLAG_TYPE1_VERB:
				return verbFormal.substring(0, verbFormal.length() - 4) + "mashita";
			case FLAG_TYPE2_VERB:
				return verbFormal.substring(0, verbFormal.length() - 4) + "mashita";
			case FLAG_TYPE3_VERB:
				if (verbFormal.toLowerCase(Locale.US).equals("suru"))
				{
					return "shimashita";
				}
				else if (verbFormal.toLowerCase(Locale.US).equals("kuru"))
				{
					return "kimashita";
				}
				break;
			case FLAG_TYPE1_VERB_KANA:
				return verbFormal.substring(0, verbFormal.length() - 2) + "ました";
			case FLAG_TYPE2_VERB_KANA:
				return verbFormal.substring(0, verbFormal.length() - 2) + "ました";
			case FLAG_TYPE3_VERB_KANA:
				if (verbFormal.equals("する") || verbFormal.equals("剃る"))
				{
					return "しました";
				}
				else if (verbFormal.equals("くる") || verbFormal.equals("来る"))
				{
					return "来た";
				}
				break;
		}
		
		
		
		return "";
	}
	
	/**
	 * Gets the present negative tense formal conjugatuion.
	 *
	 * @param verbFormal the verb in formal form
	 * @return the present negative tense formal conjugation
	 */
	private String getPresentNegativeTenseFormal(String verbFormal)
	{
		switch(verbTypeFlag)
		{
			case FLAG_TYPE1_VERB:
				return verbFormal.substring(0, verbFormal.length() - 4) + "masen";
			case FLAG_TYPE2_VERB:
				return verbFormal.substring(0, verbFormal.length() - 4) + "masen";
			case FLAG_TYPE3_VERB:
				if (verbFormal.toLowerCase(Locale.US).equals("suru"))
				{
					return "shinai";
				}
				else if (verbFormal.toLowerCase(Locale.US).equals("kuru"))
				{
					return "konai";
				}
				break;
			case FLAG_TYPE1_VERB_KANA:
				return verbFormal.substring(0, verbFormal.length() - 2) + "ません";
			case FLAG_TYPE2_VERB_KANA:
				return verbFormal.substring(0, verbFormal.length() - 2) + "ません";
			case FLAG_TYPE3_VERB_KANA:
				if (verbFormal.equals("する") || verbFormal.equals("剃る"))
				{
					return "しない";
				}
				else if (verbFormal.equals("くる") || verbFormal.equals("来る"))
				{
					return "こない";
				}
				break;
		}
		
		return "";
	}	
	
	/**
	 * Gets the past negative tense formal conjugation.
	 *
	 * @param verbFormal the verb in formal form
	 * @return the past negative tense formal conjugation
	 */
	private String getPastNegativeTenseFormal(String verbFormal)
	{
		switch(verbTypeFlag)
		{
			case FLAG_TYPE1_VERB:
				return getPresentNegativeTenseFormal(verbFormal) + " deshita";
			case FLAG_TYPE2_VERB:
				return getPresentNegativeTenseFormal(verbFormal) + " deshita";
			case FLAG_TYPE3_VERB:
				if (verbFormal.toLowerCase(Locale.US).equals("suru"))
				{
					return "shimashita deshita";
				}
				else if (verbFormal.toLowerCase(Locale.US).equals("kuru"))
				{
					return "kimashita deshita";
				}
				break;
			case FLAG_TYPE1_VERB_KANA:
				return getPresentNegativeTenseFormal(verbFormal) + "でした";
			case FLAG_TYPE2_VERB_KANA:
				return getPresentNegativeTenseFormal(verbFormal) + "でした";
			case FLAG_TYPE3_VERB_KANA:
				if (verbFormal.equals("する") || verbFormal.equals("剃る"))
				{
					return "しましたせした";
				}
				else if (verbFormal.equals("くる") || verbFormal.equals("来る"))
				{
					return "きましたでした";
				}
				break;
		}
		return "";
	}	
}
