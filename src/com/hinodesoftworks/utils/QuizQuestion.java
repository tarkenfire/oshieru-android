/* 
 * Date: Jan 30, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.utils
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.utils;

import java.util.ArrayList;

/**
 * The Class QuizQuestion. A model data class to represent a single quiz question.
 */
public class QuizQuestion
{
	private String correctAnswer;
	private ArrayList<String> incorrectAnswers;
	private String currentQuestion;
	
	/**
	 * Instantiates a new quiz question.
	 *
	 * @param correctAnswer the correct answer
	 * @param currentQuestion the current question
	 * @param incorrectAnswers the incorrect answers
	 */
	public QuizQuestion(String correctAnswer, String currentQuestion, ArrayList<String> incorrectAnswers)
	{
		this.correctAnswer = correctAnswer;
		this.incorrectAnswers = incorrectAnswers;
		this.currentQuestion = currentQuestion;
	}

	/**
	 * Gets the correct answer.
	 *
	 * @return the correct answer
	 */
	public String getCorrectAnswer()
	{
		return correctAnswer;
	}

	/**
	 * Sets the correct answer.
	 *
	 * @param correctAnswer the new correct answer
	 */
	public void setCorrectAnswer(String correctAnswer)
	{
		this.correctAnswer = correctAnswer;
	}

	/**
	 * Gets the incorrect answers.
	 *
	 * @return the incorrect answers
	 */
	public ArrayList<String> getIncorrectAnswers()
	{
		return incorrectAnswers;
	}

	/**
	 * Sets the incorrect answers.
	 *
	 * @param incorrectAnswers the new incorrect answers
	 */
	public void setIncorrectAnswers(ArrayList<String> incorrectAnswers)
	{
		this.incorrectAnswers = incorrectAnswers;
	}
	
	
	/**
	 * Gets the current question.
	 *
	 * @return the current question
	 */
	public String getCurrentQuestion()
	{
		return currentQuestion;
	}

	/**
	 * Sets the current question.
	 *
	 * @param currentQuestion the new current question
	 */
	public void setCurrentQuestion(String currentQuestion)
	{
		this.currentQuestion = currentQuestion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return correctAnswer + " " + incorrectAnswers.toString();
	}
}
