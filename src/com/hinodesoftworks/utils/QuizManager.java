/* 
 * Date: Jan 16, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.utils
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.utils;

import java.util.ArrayList;
import java.util.Collections;

public class QuizManager
{
	private int numOfQuestions = 0;
	private int currentQuestion = 0;
	private int correctAnswers = 0;
	private int incorrectAnswers = 0;
	private boolean isQuizFinished = false;
	private ArrayList<QuizQuestion> questions;
	private QuizQuestionListener listener;
	
	
	public interface QuizQuestionListener
	{
		public void onNewQuestion(String question, String answer, ArrayList<String> incAnswers, int correct, int incorrect);
		public void onQuizFinished(int correct, int incorrect);
	}
	
	
	
	public void createNewQuiz(int numOfQuestions, ArrayList<QuizQuestion> questions)
	{
		this.numOfQuestions = numOfQuestions;
		this.questions = questions;
	}
	
	public void setQuizQuestionListener(QuizQuestionListener listener)
	{
		this.listener = listener;
	}
	
	public void startQuiz()
	{
		//reset all numbers
		currentQuestion = 0;
		correctAnswers = 0;
		incorrectAnswers = 0;
		
		//shuffle the questions
		Collections.shuffle(questions);
		
		isQuizFinished = false;
		
		QuizQuestion currQuestion = questions.get(currentQuestion);
		listener.onNewQuestion(currQuestion.getCurrentQuestion(), currQuestion.getCorrectAnswer()
				, currQuestion.getIncorrectAnswers(), correctAnswers, incorrectAnswers);
	}
	
	public void answerQuestion(String answer)
	{
		if (isQuizFinished)
		{
			listener.onQuizFinished(correctAnswers, incorrectAnswers);
			return;
		}
		
		String correctAnswer = questions.get(currentQuestion).getCorrectAnswer();
		if (answer.equals(correctAnswer))
		{
			correctAnswers++;
		}
		else
		{
			incorrectAnswers++;
		}
		
		nextQuestion();
	}
	
	private void nextQuestion()
	{
		if (++currentQuestion >= numOfQuestions)
		{
			isQuizFinished = true;
			listener.onQuizFinished(correctAnswers, incorrectAnswers);
		}
		else
		{
			QuizQuestion currQuestion = questions.get(currentQuestion);
			listener.onNewQuestion(currQuestion.getCurrentQuestion(), currQuestion.getCorrectAnswer()
					, currQuestion.getIncorrectAnswers(), correctAnswers, incorrectAnswers);
		}
	}
	
	
}
