/* 
 * Date: Jan 16, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.utils
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.utils;

import java.util.ArrayList;

public class QuizQuestion
{
	private String correctAnswer;
	private ArrayList<String> incorrectAnswers;
	private String currentQuestion;
	
	public QuizQuestion(String correctAnswer, String currentQuestion, ArrayList<String> incorrectAnswers)
	{
		this.correctAnswer = correctAnswer;
		this.incorrectAnswers = incorrectAnswers;
		this.currentQuestion = currentQuestion;
	}

	public String getCorrectAnswer()
	{
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer)
	{
		this.correctAnswer = correctAnswer;
	}

	public ArrayList<String> getIncorrectAnswers()
	{
		return incorrectAnswers;
	}

	public void setIncorrectAnswers(ArrayList<String> incorrectAnswers)
	{
		this.incorrectAnswers = incorrectAnswers;
	}
	
	
	public String getCurrentQuestion()
	{
		return currentQuestion;
	}

	public void setCurrentQuestion(String currentQuestion)
	{
		this.currentQuestion = currentQuestion;
	}

	@Override
	public String toString()
	{
		return correctAnswer + " " + incorrectAnswers.toString();
	}
}
