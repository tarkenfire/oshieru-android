/* 
 * Date: Jan 30, 2014
 * Project: Oshieru
 * Package: com.hinodesoftworks.utils
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.utils;

public class ConjugationItem
{
	private String conjugatedForm;
	private String conjugationType;
	
	public ConjugationItem(String conjForm, String conjType)
	{
		this.conjugatedForm = conjForm;
		this.conjugationType = conjType;
	}
	
	public String getConjugatedForm()
	{
		return conjugatedForm;
	}
	public void setConjugatedForm(String conjugatedForm)
	{
		this.conjugatedForm = conjugatedForm;
	}
	
	public String getConjugationType()
	{
		return conjugationType;
	}
	public void setConjugationType(String conjugationType)
	{
		this.conjugationType = conjugationType;
	}
	
}
