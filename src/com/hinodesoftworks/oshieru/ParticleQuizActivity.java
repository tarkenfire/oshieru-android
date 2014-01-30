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
 * The Class ParticleQuizActivity.
 */
public class ParticleQuizActivity extends Activity implements OnClickListener, QuizQuestionListener
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
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_particle_quiz);
		getActionBar().setTitle("Oshieru - Particle Quiz");
		
		databaseHelper = new DatabaseHelper(this);
		databaseHelper.openDatabase();
		SQLiteDatabase database = databaseHelper.getDatabase();
		DatabaseManager databaseManager = new DatabaseManager(database);
		
		Cursor questions = databaseManager.querySingleTableData("particle_quiz");
		ArrayList<QuizQuestion> quizQuestions = new ArrayList<QuizQuestion>();
		
		while (questions.moveToNext())
		{
			String question = questions.getString(1);
			String answer = questions.getString(2);
			ArrayList<String> incs = new ArrayList<String>();
			
			incs.add(questions.getString(3));
			incs.add(questions.getString(4));
			incs.add(questions.getString(5));
			
			quizQuestions.add(new QuizQuestion(answer, question, incs));
		}
		
		quizManager = new QuizManager();
		quizManager.createNewQuiz(quizQuestions.size(), quizQuestions);
		quizManager.setQuizQuestionListener(this);
		
		
		//get UI handles
		scoreView = (TextView)findViewById(R.id.part_quiz_score);
		questionView = (TextView)findViewById(R.id.part_quiz_question);
		
		answer1 = (Button)findViewById(R.id.part_quiz_button1);
		answer2 = (Button)findViewById(R.id.part_quiz_button2);
		answer3 = (Button)findViewById(R.id.part_quiz_button3);
		answer4 = (Button)findViewById(R.id.part_quiz_button4);
		
		answer1.setOnClickListener(this);
		answer2.setOnClickListener(this);
		answer3.setOnClickListener(this);
		answer4.setOnClickListener(this);
		
		quizManager.startQuiz();
		
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


	/* (non-Javadoc)
	 * @see com.hinodesoftworks.utils.QuizManager.QuizQuestionListener#onNewQuestion(java.lang.String, java.lang.String, java.util.ArrayList, int, int)
	 */
	@Override
	public void onNewQuestion(String question, String answer,
			ArrayList<String> incAnswers, int correct, int incorrect)
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
				" You answered " + incorrect + " questions incorrectly. Quiz will be restarted.");
		
		adBuilder.setCancelable(false);
		adBuilder.setPositiveButton("Ok", 
				new DialogInterface.OnClickListener()
				{
					
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						ParticleQuizActivity.this.quizManager.startQuiz();
					}
				}
				);
		
		AlertDialog dialog = adBuilder.create();
		dialog.show();
	}

}
