/* 
 * Date: Jan 11, 2014
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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CharacterQuizActivity extends Activity implements QuizQuestionListener, OnClickListener
{
	QuizManager quizManager;
	DatabaseHelper databaseHelper;
	
	//UI Handles
	TextView scoreView;
	TextView questionView;
	
	Button answer1;
	Button answer2;
	Button answer3;
	Button answer4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character_quiz);
		
		//TODO: Get Title from resource rather than hard code
		getActionBar().setTitle("Oshieru - Character Quiz");
		
		
		databaseHelper = new DatabaseHelper(this);
		databaseHelper.openDatabase();
		SQLiteDatabase database = databaseHelper.getDatabase();
		DatabaseManager databaseManager = new DatabaseManager(database);
		
		//TODO: Quiz currently just selects everything from the hiragana table for the questions
		Cursor questions = databaseManager.querySingleTableData("hiragana");
		ArrayList<QuizQuestion> quizQuestions = new ArrayList<QuizQuestion>();
		
		while (questions.moveToNext())
		{
			String currChar = questions.getString(1);
			String currQuest = questions.getString(2);
			Cursor wrongAnswers = databaseManager.queryLimitedTableData("hiragana", 3, "char", currChar);
			ArrayList<String> wrongs = new ArrayList<String>();
			
			while (wrongAnswers.moveToNext())
			{
				wrongs.add(wrongAnswers.getString(1));
			}
			
			QuizQuestion q = new QuizQuestion(currChar, currQuest, wrongs);
			quizQuestions.add(q);
		}
		
		quizManager = new QuizManager();
		quizManager.createNewQuiz(quizQuestions.size(), quizQuestions);
		quizManager.setQuizQuestionListener(this);
		
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
		
		quizManager.startQuiz();
	}

	
	
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

	@Override
	public void onClick(View v)
	{
		Button b = (Button)v;
		quizManager.answerQuestion(b.getText().toString());	
	}
}
