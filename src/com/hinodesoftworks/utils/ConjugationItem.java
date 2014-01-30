/* 
 * Date: Jan 30, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.utils
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.utils;

/**
 * The Class ConjugationItem. A modal data class representing a single conjugated verb.
 */
public class ConjugationItem
{
	private String conjugatedForm;
	private String conjugationType;
	
	/**
	 * Instantiates a new conjugation item.
	 *
	 * @param conjForm the conj form
	 * @param conjType the conj type
	 */
	public ConjugationItem(String conjForm, String conjType)
	{
		this.conjugatedForm = conjForm;
		this.conjugationType = conjType;
	}
	
	/**
	 * Gets the conjugated form.
	 *
	 * @return the conjugated form
	 */
	public String getConjugatedForm()
	{
		return conjugatedForm;
	}
	
	/**
	 * Sets the conjugated form.
	 *
	 * @param conjugatedForm the new conjugated form
	 */
	public void setConjugatedForm(String conjugatedForm)
	{
		this.conjugatedForm = conjugatedForm;
	}
	
	/**
	 * Gets the conjugation type.
	 *
	 * @return the conjugation type
	 */
	public String getConjugationType()
	{
		return conjugationType;
	}
	
	/**
	 * Sets the conjugation type.
	 *
	 * @param conjugationType the new conjugation type
	 */
	public void setConjugationType(String conjugationType)
	{
		this.conjugationType = conjugationType;
	}
	
}
