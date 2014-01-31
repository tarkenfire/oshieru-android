/* 
 * Date: Jan 30, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.oshieru
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.oshieru;

import java.util.ArrayList;
import java.util.Random;

import com.hinodesoftworks.utils.DatabaseHelper;
import com.hinodesoftworks.utils.DatabaseManager;
import com.hinodesoftworks.utils.QuizManager;
import com.hinodesoftworks.utils.QuizManager.QuizQuestionListener;
import com.hinodesoftworks.utils.QuizQuestion;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * The Class CharacterQuizActivity.
 */
public class CharacterQuizActivity extends Activity implements QuizQuestionListener, OnClickListener
{
	QuizManager quizManager;
	DatabaseHelper databaseHelper;
	DatabaseManager databaseManager;
	
	//UI Handles
	TextView scoreView;
	TextView questionView;
	
	Button answer1;
	Button answer2;
	Button answer3;
	Button answer4;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character_quiz);
		getActionBar().setTitle("Oshieru - Character Quiz");
		
		//get UI handles
		scoreView = (TextView)findViewById(R.id.char_quiz_score);
		questionView = (TextView)findViewById(R.id.char_quiz_question);
		
		answer1 = (Button)findViewById(R.id.char_quiz_button1);
		answer2 = (Button)findViewById(R.id.char_quiz_button2);
		answer3 = (Button)findViewById(R.id.char_quiz_button3);
		answer4 = (Button)findViewById(R.id.char_quiz_button4);
		
		answer1.setOnClickListener(this);
		answer2.setOnClickListener(this);
		answer3.setOnClickListener(this);
		answer4.setOnClickListener(this);
		

	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart()
	{
		super.onStart();
		
		databaseHelper = new DatabaseHelper(this);
		databaseHelper.openDatabase();
		SQLiteDatabase database = databaseHelper.getDatabase();
		databaseManager = new DatabaseManager(database);
	
		ArrayList<QuizQuestion> quizQuestions = this.getQuizQuestions();
			
		quizManager = new QuizManager();
		quizManager.createNewQuiz(quizQuestions.size(), quizQuestions);
		quizManager.setQuizQuestionListener(this);	
		quizManager.startQuiz();
		
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(onStop)
	 */
	@Override
	protected void onStop()
	{
		super.onStop();
		databaseHelper.closeDatabase();
	}





	/* (non-Javadoc)
	 * @see com.hinodesoftworks.utils.QuizManager.QuizQuestionListener#onNewQuestion(java.lang.String, java.lang.String, java.util.ArrayList, int, int)
	 */
	@Override
	public void onNewQuestion(String question, String answer, ArrayList<String> incAnswers, 
			int correct, int incorrect)
	{
		questionView.setText(question);
		scoreView.setText("Score: " + correct + " Correct - " + incorrect + " Incorrect");
		
		//set buttons
		Random rand = new Random();
		int correctPos = rand.nextInt(4) + 1; //0-3 + 1
		
		switch (correctPos)
		{
		case 1:
			answer1.setText(answer);
			answer2.setText(incAnswers.get(0));
			answer3.setText(incAnswers.get(1));
			answer4.setText(incAnswers.get(2));
			break;
		case 2:
			answer1.setText(incAnswers.get(0));
			answer2.setText(answer);
			answer3.setText(incAnswers.get(1));
			answer4.setText(incAnswers.get(2));
			break;
		case 3:			
			answer1.setText(incAnswers.get(0));
			answer2.setText(incAnswers.get(1));
			answer3.setText(answer);
			answer4.setText(incAnswers.get(2));
			break;
		case 4:
			answer1.setText(incAnswers.get(0));
			answer2.setText(incAnswers.get(1));
			answer3.setText(incAnswers.get(2));
			answer4.setText(answer);
			break;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.hinodesoftworks.utils.QuizManager.QuizQuestionListener#onQuizFinished(int, int)
	 */
	@Override
	public void onQuizFinished(int correct, int incorrect)
	{
		AlertDialog.Builder adBuilder = new AlertDialog.Builder(this);
		
		adBuilder.setTitle("Quiz Finished");
		
		adBuilder.setMessage("Quiz complete. You answered " + correct + " questions correctly." + 
				" You answered " + incorrect + " questions incorrectly.");
		
		adBuilder.setCancelable(false);
		adBuilder.setPositiveButton("Ok", 
				new DialogInterface.OnClickListener()
				{	
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						CharacterQuizActivity.this.databaseHelper.closeDatabase();
						CharacterQuizActivity.this.finish();
					}
				}
				);
		
		AlertDialog dialog = adBuilder.create();
		dialog.show();
		
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v)
	{
		Button b = (Button)v;
		quizManager.answerQuestion(b.getText().toString());	
	}
	
	/**
	 * Gets the quiz questions.
	 *
	 * @return the quiz questions
	 */
	private ArrayList<QuizQuestion> getQuizQuestions()
	{
		//get flags from the bundle. 
		Bundle extras = this.getIntent().getExtras();
		boolean kataFlag = extras.getBoolean("flag_kata");
		boolean hiraFlag = extras.getBoolean("flag_hira");
		boolean kanjiFlag = extras.getBoolean("flag_kanji");
		int maxCount = extras.getInt("question_num_value");
		int count = 0;
		ArrayList<QuizQuestion> questionsToReturn = new ArrayList<QuizQuestion>();
		Cursor questionCursor = null;
		Cursor wrongCursor = null;

		
		//add items in a round robin fashion till all of the questions are answered.
		while (count < maxCount)
		{	
			if (kataFlag)
			{
				ArrayList<String> wrongs = new ArrayList<String>();
				questionCursor = databaseManager.queryRandomTableData(1, "katakana");
				questionCursor.moveToFirst();
				
				String selectedChar = questionCursor.getString(1);
				String selectedAnswer = questionCursor.getString(2);
				
				wrongCursor = databaseManager.queryLimitedTableData("katakana", 3, "romj", selectedAnswer);
				
				while (wrongCursor.moveToNext())
				{
					wrongs.add(wrongCursor.getString(2));
				}
				
				QuizQuestion q = new QuizQuestion(selectedAnswer, selectedChar, wrongs);
				questionsToReturn.add(q);
				
				questionCursor.close();
				wrongCursor.close();
				
				count++;
			}
			
			if (hiraFlag)
			{
				ArrayList<String> wrongs = new ArrayList<String>();
				questionCursor = databaseManager.queryRandomTableData(1, "hiragana");
				questionCursor.moveToFirst();
				
				String selectedChar = questionCursor.getString(1);
				String selectedAnswer = questionCursor.getString(2);
				
				wrongCursor = databaseManager.queryLimitedTableData("hiragana", 3, "romj", selectedAnswer);
				
				while (wrongCursor.moveToNext())
				{
					wrongs.add(wrongCursor.getString(2));
				}
				
				QuizQuestion q = new QuizQuestion(selectedAnswer, selectedChar, wrongs);
				questionsToReturn.add(q);
				
				//close the cursors for memory reasons.
				questionCursor.close();
				wrongCursor.close();
				
				count++;
			}
		
			
			if (kanjiFlag)
			{
				ArrayList<String> wrongs = new ArrayList<String>();
				//same as others, but because of longer answers, I substring them before
				//adding.
				questionCursor = databaseManager.queryRandomTableData(1, "kanji_g1");
				questionCursor.moveToFirst();
				
				String selectedChar = questionCursor.getString(1);
				String selectedAnswer = questionCursor.getString(5);
				
				wrongCursor = databaseManager.queryLimitedTableData("kanji_g1", 3, "meaning", selectedAnswer);
				
				while (wrongCursor.moveToNext())
				{
					wrongs.add(wrongCursor.getString(5));
				}
				
				QuizQuestion q = new QuizQuestion(selectedAnswer, selectedChar, wrongs);
				questionsToReturn.add(q);
			
				questionCursor.close();
				wrongCursor.close();
				
				count++;
			}
		}
		
		return questionsToReturn;
	}
	
}
