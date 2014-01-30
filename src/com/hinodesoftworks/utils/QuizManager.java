/* 
 * Date: Jan 30, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.utils
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.utils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The Class QuizManager.
 */
public class QuizManager
{
	private int numOfQuestions = 0;
	private int currentQuestion = 0;
	private int correctAnswers = 0;
	private int incorrectAnswers = 0;
	private boolean isQuizFinished = false;
	private ArrayList<QuizQuestion> questions;
	private QuizQuestionListener listener;
	
	
	/**
	 * The listener interface for receiving quizQuestion events.
	 * The class that is interested in processing a quizQuestion
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addQuizQuestionListener<code> method. When
	 * the quizQuestion event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see QuizQuestionEvent
	 */
	public interface QuizQuestionListener
	{
		
		/**
		 * On new question.
		 *
		 * @param question the question
		 * @param answer the answer
		 * @param incAnswers the incorrect answers
		 * @param correct the number of items correct
		 * @param incorrect the number of items incorrect
		 */
		public void onNewQuestion(String question, String answer, ArrayList<String> incAnswers, int correct, int incorrect);
		
		/**
		 * On quiz finished.
		 *
		 * @param correct the number of items correct correct
		 * @param incorrect the number of items incorrect
		 */
		public void onQuizFinished(int correct, int incorrect);
	}
	
	
	
	/**
	 * Creates the new quiz.
	 *
	 * @param numOfQuestions the num of questions
	 * @param questions the questions
	 */
	public void createNewQuiz(int numOfQuestions, ArrayList<QuizQuestion> questions)
	{
		this.numOfQuestions = numOfQuestions;
		this.questions = questions;
	}
	
	/**
	 * Sets the quiz question listener.
	 *
	 * @param listener the new quiz question listener
	 */
	public void setQuizQuestionListener(QuizQuestionListener listener)
	{
		this.listener = listener;
	}
	
	/**
	 * Start quiz.
	 */
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
	
	/**
	 * Check a submited answer to the current question.
	 *
	 * @param answer the answer
	 */
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
	
	/**
	 * Move to the next question.
	 */
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
