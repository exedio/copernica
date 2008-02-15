/*
 * Copyright (C) 2004-2007  exedio GmbH (www.exedio.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.exedio.cope.testmodel;

import java.util.Date;

import com.exedio.cope.Item;
import com.exedio.cope.LengthView;
import com.exedio.cope.LengthViolationException;
import com.exedio.cope.MandatoryViolationException;
import com.exedio.cope.PlusView;
import com.exedio.cope.SetValue;
import com.exedio.cope.CopeSchemaName;
import com.exedio.cope.StringField;
import com.exedio.cope.UppercaseView;

/**
 * @cope.generic.constructor public
 * @author Ralf Wiebicke
 */
@CopeSchemaName("STRINGITEMS")
public class StringItem extends Item
{
	public static final StringField any = new StringField().optional();

	public static final StringField mandatory = new StringField();
	
	@CopeSchemaName("MIN_4")
	public static final StringField min4 = new StringField().optional().lengthMin(4);
	@CopeSchemaName("MAX_4")
	public static final StringField max4 = new StringField().optional().lengthMax(4);
	@CopeSchemaName("MIN4_MAX8")
	public static final StringField min4Max8 = new StringField().optional().lengthRange(4, 8);
	@CopeSchemaName("EXACT_6")
	public static final StringField exact6 = new StringField().optional().lengthExact(6);
	
	public static final StringField long1K = new StringField().optional().lengthMax(1000);
	public static final StringField long1M = new StringField().optional().lengthMax(1000*1000);
	public static final StringField long40M = new StringField().optional().lengthMax(40*1000*1000);
	
	public static final StringField oracleNoCLOB = new StringField().optional().lengthMax(4000/3);
	public static final StringField oracleCLOB = new StringField().optional().lengthMax((4000/3)+1);
	
	public static final UppercaseView min4Upper = min4.toUpperCase();
	public static final UppercaseView max4Upper = max4.toUpperCase();

	public static final LengthView min4UpperLength = min4Upper.length();
	public static final LengthView max4UpperLength = max4Upper.length();
	
	public static final PlusView min4AndMax4UpperLength = min4UpperLength.plus(max4UpperLength);
	
	/**
	 * @param dummy is a dummy
	 */
	public StringItem(final String any, boolean dummy)
	{
		this(
				StringItem.mandatory.map("defaultByAny"),
				StringItem.any.map(any)
		);
	}
	
	/**
	 * @param dummy is a dummy
	 */
	public StringItem(final String mandatory, double dummy) throws MandatoryViolationException
	{
		this(
				StringItem.mandatory.map(mandatory)
		);
	}
	
	/**
	 * @param dummy is a dummy
	 */
	public StringItem(final String exact6, int dummy) throws LengthViolationException
	{
		this(
				StringItem.mandatory.map("defaultByExact6"),
				StringItem.exact6.map(exact6)
		);
	}
	
	/**
	 * @param dummy is a dummy
	 */
	public StringItem(final String max4, Date dummy) throws LengthViolationException
	{
		this(new SetValue[]{
				StringItem.mandatory.map("defaultByMax4"),
				StringItem.max4.map(max4)
		});
	}
	
/**

	 **
	 * Creates a new StringItem with all the fields initially needed.
	 * @param mandatory the initial value for field {@link #mandatory}.
	 * @throws com.exedio.cope.LengthViolationException if mandatory violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if mandatory is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public StringItem(
				final java.lang.String mandatory)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			StringItem.mandatory.map(mandatory),
		});
	}/**

	 **
	 * Creates a new StringItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	public StringItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private StringItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #any}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getAny()
	{
		return StringItem.any.get(this);
	}/**

	 **
	 * Sets a new value for {@link #any}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setAny(final java.lang.String any)
			throws
				com.exedio.cope.LengthViolationException
	{
		StringItem.any.set(this,any);
	}/**

	 **
	 * Returns the value of {@link #mandatory}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getMandatory()
	{
		return StringItem.mandatory.get(this);
	}/**

	 **
	 * Sets a new value for {@link #mandatory}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setMandatory(final java.lang.String mandatory)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		StringItem.mandatory.set(this,mandatory);
	}/**

	 **
	 * Returns the value of {@link #min4}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getMin4()
	{
		return StringItem.min4.get(this);
	}/**

	 **
	 * Sets a new value for {@link #min4}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setMin4(final java.lang.String min4)
			throws
				com.exedio.cope.LengthViolationException
	{
		StringItem.min4.set(this,min4);
	}/**

	 **
	 * Returns the value of {@link #max4}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getMax4()
	{
		return StringItem.max4.get(this);
	}/**

	 **
	 * Sets a new value for {@link #max4}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setMax4(final java.lang.String max4)
			throws
				com.exedio.cope.LengthViolationException
	{
		StringItem.max4.set(this,max4);
	}/**

	 **
	 * Returns the value of {@link #min4Max8}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getMin4Max8()
	{
		return StringItem.min4Max8.get(this);
	}/**

	 **
	 * Sets a new value for {@link #min4Max8}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setMin4Max8(final java.lang.String min4Max8)
			throws
				com.exedio.cope.LengthViolationException
	{
		StringItem.min4Max8.set(this,min4Max8);
	}/**

	 **
	 * Returns the value of {@link #exact6}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getExact6()
	{
		return StringItem.exact6.get(this);
	}/**

	 **
	 * Sets a new value for {@link #exact6}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setExact6(final java.lang.String exact6)
			throws
				com.exedio.cope.LengthViolationException
	{
		StringItem.exact6.set(this,exact6);
	}/**

	 **
	 * Returns the value of {@link #long1K}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getLong1K()
	{
		return StringItem.long1K.get(this);
	}/**

	 **
	 * Sets a new value for {@link #long1K}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setLong1K(final java.lang.String long1K)
			throws
				com.exedio.cope.LengthViolationException
	{
		StringItem.long1K.set(this,long1K);
	}/**

	 **
	 * Returns the value of {@link #long1M}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getLong1M()
	{
		return StringItem.long1M.get(this);
	}/**

	 **
	 * Sets a new value for {@link #long1M}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setLong1M(final java.lang.String long1M)
			throws
				com.exedio.cope.LengthViolationException
	{
		StringItem.long1M.set(this,long1M);
	}/**

	 **
	 * Returns the value of {@link #long40M}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getLong40M()
	{
		return StringItem.long40M.get(this);
	}/**

	 **
	 * Sets a new value for {@link #long40M}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setLong40M(final java.lang.String long40M)
			throws
				com.exedio.cope.LengthViolationException
	{
		StringItem.long40M.set(this,long40M);
	}/**

	 **
	 * Returns the value of {@link #oracleNoCLOB}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getOracleNoCLOB()
	{
		return StringItem.oracleNoCLOB.get(this);
	}/**

	 **
	 * Sets a new value for {@link #oracleNoCLOB}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setOracleNoCLOB(final java.lang.String oracleNoCLOB)
			throws
				com.exedio.cope.LengthViolationException
	{
		StringItem.oracleNoCLOB.set(this,oracleNoCLOB);
	}/**

	 **
	 * Returns the value of {@link #oracleCLOB}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getOracleCLOB()
	{
		return StringItem.oracleCLOB.get(this);
	}/**

	 **
	 * Sets a new value for {@link #oracleCLOB}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setOracleCLOB(final java.lang.String oracleCLOB)
			throws
				com.exedio.cope.LengthViolationException
	{
		StringItem.oracleCLOB.set(this,oracleCLOB);
	}/**

	 **
	 * Returns the value of {@link #min4Upper}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getMin4Upper()
	{
		return StringItem.min4Upper.get(this);
	}/**

	 **
	 * Returns the value of {@link #max4Upper}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getMax4Upper()
	{
		return StringItem.max4Upper.get(this);
	}/**

	 **
	 * Returns the value of {@link #min4UpperLength}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Integer getMin4UpperLength()
	{
		return StringItem.min4UpperLength.get(this);
	}/**

	 **
	 * Returns the value of {@link #max4UpperLength}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Integer getMax4UpperLength()
	{
		return StringItem.max4UpperLength.get(this);
	}/**

	 **
	 * Returns the value of {@link #min4AndMax4UpperLength}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Integer getMin4AndMax4UpperLength()
	{
		return StringItem.min4AndMax4UpperLength.get(this);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for stringItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<StringItem> TYPE = newType(StringItem.class)
;}
