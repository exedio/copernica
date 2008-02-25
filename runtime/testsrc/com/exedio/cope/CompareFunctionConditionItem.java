package com.exedio.cope;


import java.util.Date;

import com.exedio.cope.util.Day;

class CompareFunctionConditionItem extends Item
{
	/** @cope.initial */ static final StringField leftString = new StringField().optional();
	/** @cope.initial */ static final StringField rightString = new StringField().optional();

	/** @cope.initial */ static final IntegerField leftInt = new IntegerField().optional();
	/** @cope.initial */ static final IntegerField rightInt = new IntegerField().optional();

	/** @cope.initial */ static final LongField leftLong = new LongField().optional();
	/** @cope.initial */ static final LongField rightLong = new LongField().optional();

	/** @cope.initial */ static final DoubleField leftDouble = new DoubleField().optional();
	/** @cope.initial */ static final DoubleField rightDouble = new DoubleField().optional();

	/** @cope.initial */ static final DateField leftDate = new DateField().optional();
	/** @cope.initial */ static final DateField rightDate = new DateField().optional();

	/** @cope.initial */ static final DayField leftDay = new DayField().optional();
	/** @cope.initial */ static final DayField rightDay = new DayField();

	/** @cope.initial */ static final EnumField<XEnum> leftEnum = newEnumField(XEnum.class).optional();
	/** @cope.initial */ static final EnumField<XEnum> rightEnum = newEnumField(XEnum.class).optional();

	static enum XEnum
	{
		V1, V2, V3, V4, V5;
	}
	
	static final ItemField<CompareFunctionConditionItem> leftItem = newItemField(CompareFunctionConditionItem.class, NULLIFY);
	static final ItemField<CompareFunctionConditionItem> rightItem = newItemField(CompareFunctionConditionItem.class, NULLIFY);

	static final Date date = new Date(1087365298214l);
	static final Day day = new Day(2007, 4, 28);

	CompareFunctionConditionItem(
			final java.lang.String leftString,
			final java.lang.Integer leftInt,
			final java.lang.Long leftLong,
			final java.lang.Double leftDouble,
			final java.util.Date leftDate,
			final Day leftDay,
			final XEnum leftEnum)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		this(leftString, "string3", leftInt, 3, leftLong, 13l, leftDouble, 2.3, leftDate, date, leftDay, day, leftEnum, XEnum.V3);
		setLeftItem(this);
	}
	
	/**

	 **
	 * Creates a new CompareFunctionConditionItem with all the fields initially needed.
	 * @param leftString the initial value for field {@link #leftString}.
	 * @param rightString the initial value for field {@link #rightString}.
	 * @param leftInt the initial value for field {@link #leftInt}.
	 * @param rightInt the initial value for field {@link #rightInt}.
	 * @param leftLong the initial value for field {@link #leftLong}.
	 * @param rightLong the initial value for field {@link #rightLong}.
	 * @param leftDouble the initial value for field {@link #leftDouble}.
	 * @param rightDouble the initial value for field {@link #rightDouble}.
	 * @param leftDate the initial value for field {@link #leftDate}.
	 * @param rightDate the initial value for field {@link #rightDate}.
	 * @param leftDay the initial value for field {@link #leftDay}.
	 * @param rightDay the initial value for field {@link #rightDay}.
	 * @param leftEnum the initial value for field {@link #leftEnum}.
	 * @param rightEnum the initial value for field {@link #rightEnum}.
	 * @throws com.exedio.cope.LengthViolationException if leftString, rightString violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if rightDay is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	CompareFunctionConditionItem(
				final java.lang.String leftString,
				final java.lang.String rightString,
				final java.lang.Integer leftInt,
				final java.lang.Integer rightInt,
				final java.lang.Long leftLong,
				final java.lang.Long rightLong,
				final java.lang.Double leftDouble,
				final java.lang.Double rightDouble,
				final java.util.Date leftDate,
				final java.util.Date rightDate,
				final com.exedio.cope.util.Day leftDay,
				final com.exedio.cope.util.Day rightDay,
				final XEnum leftEnum,
				final XEnum rightEnum)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			CompareFunctionConditionItem.leftString.map(leftString),
			CompareFunctionConditionItem.rightString.map(rightString),
			CompareFunctionConditionItem.leftInt.map(leftInt),
			CompareFunctionConditionItem.rightInt.map(rightInt),
			CompareFunctionConditionItem.leftLong.map(leftLong),
			CompareFunctionConditionItem.rightLong.map(rightLong),
			CompareFunctionConditionItem.leftDouble.map(leftDouble),
			CompareFunctionConditionItem.rightDouble.map(rightDouble),
			CompareFunctionConditionItem.leftDate.map(leftDate),
			CompareFunctionConditionItem.rightDate.map(rightDate),
			CompareFunctionConditionItem.leftDay.map(leftDay),
			CompareFunctionConditionItem.rightDay.map(rightDay),
			CompareFunctionConditionItem.leftEnum.map(leftEnum),
			CompareFunctionConditionItem.rightEnum.map(rightEnum),
		});
	}/**

	 **
	 * Creates a new CompareFunctionConditionItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private CompareFunctionConditionItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private CompareFunctionConditionItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #leftString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getLeftString()
	{
		return CompareFunctionConditionItem.leftString.get(this);
	}/**

	 **
	 * Sets a new value for {@link #leftString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setLeftString(final java.lang.String leftString)
			throws
				com.exedio.cope.LengthViolationException
	{
		CompareFunctionConditionItem.leftString.set(this,leftString);
	}/**

	 **
	 * Returns the value of {@link #rightString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getRightString()
	{
		return CompareFunctionConditionItem.rightString.get(this);
	}/**

	 **
	 * Sets a new value for {@link #rightString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setRightString(final java.lang.String rightString)
			throws
				com.exedio.cope.LengthViolationException
	{
		CompareFunctionConditionItem.rightString.set(this,rightString);
	}/**

	 **
	 * Returns the value of {@link #leftInt}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.Integer getLeftInt()
	{
		return CompareFunctionConditionItem.leftInt.get(this);
	}/**

	 **
	 * Sets a new value for {@link #leftInt}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setLeftInt(final java.lang.Integer leftInt)
	{
		CompareFunctionConditionItem.leftInt.set(this,leftInt);
	}/**

	 **
	 * Returns the value of {@link #rightInt}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.Integer getRightInt()
	{
		return CompareFunctionConditionItem.rightInt.get(this);
	}/**

	 **
	 * Sets a new value for {@link #rightInt}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setRightInt(final java.lang.Integer rightInt)
	{
		CompareFunctionConditionItem.rightInt.set(this,rightInt);
	}/**

	 **
	 * Returns the value of {@link #leftLong}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.Long getLeftLong()
	{
		return CompareFunctionConditionItem.leftLong.get(this);
	}/**

	 **
	 * Sets a new value for {@link #leftLong}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setLeftLong(final java.lang.Long leftLong)
	{
		CompareFunctionConditionItem.leftLong.set(this,leftLong);
	}/**

	 **
	 * Returns the value of {@link #rightLong}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.Long getRightLong()
	{
		return CompareFunctionConditionItem.rightLong.get(this);
	}/**

	 **
	 * Sets a new value for {@link #rightLong}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setRightLong(final java.lang.Long rightLong)
	{
		CompareFunctionConditionItem.rightLong.set(this,rightLong);
	}/**

	 **
	 * Returns the value of {@link #leftDouble}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.Double getLeftDouble()
	{
		return CompareFunctionConditionItem.leftDouble.get(this);
	}/**

	 **
	 * Sets a new value for {@link #leftDouble}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setLeftDouble(final java.lang.Double leftDouble)
	{
		CompareFunctionConditionItem.leftDouble.set(this,leftDouble);
	}/**

	 **
	 * Returns the value of {@link #rightDouble}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.Double getRightDouble()
	{
		return CompareFunctionConditionItem.rightDouble.get(this);
	}/**

	 **
	 * Sets a new value for {@link #rightDouble}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setRightDouble(final java.lang.Double rightDouble)
	{
		CompareFunctionConditionItem.rightDouble.set(this,rightDouble);
	}/**

	 **
	 * Returns the value of {@link #leftDate}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.util.Date getLeftDate()
	{
		return CompareFunctionConditionItem.leftDate.get(this);
	}/**

	 **
	 * Sets a new value for {@link #leftDate}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setLeftDate(final java.util.Date leftDate)
	{
		CompareFunctionConditionItem.leftDate.set(this,leftDate);
	}/**

	 **
	 * Sets the current date for the date field {@link #leftDate}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.touch public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void touchLeftDate()
	{
		CompareFunctionConditionItem.leftDate.touch(this);
	}/**

	 **
	 * Returns the value of {@link #rightDate}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.util.Date getRightDate()
	{
		return CompareFunctionConditionItem.rightDate.get(this);
	}/**

	 **
	 * Sets a new value for {@link #rightDate}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setRightDate(final java.util.Date rightDate)
	{
		CompareFunctionConditionItem.rightDate.set(this,rightDate);
	}/**

	 **
	 * Sets the current date for the date field {@link #rightDate}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.touch public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void touchRightDate()
	{
		CompareFunctionConditionItem.rightDate.touch(this);
	}/**

	 **
	 * Returns the value of {@link #leftDay}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final com.exedio.cope.util.Day getLeftDay()
	{
		return CompareFunctionConditionItem.leftDay.get(this);
	}/**

	 **
	 * Sets a new value for {@link #leftDay}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setLeftDay(final com.exedio.cope.util.Day leftDay)
	{
		CompareFunctionConditionItem.leftDay.set(this,leftDay);
	}/**

	 **
	 * Returns the value of {@link #rightDay}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final com.exedio.cope.util.Day getRightDay()
	{
		return CompareFunctionConditionItem.rightDay.get(this);
	}/**

	 **
	 * Sets a new value for {@link #rightDay}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setRightDay(final com.exedio.cope.util.Day rightDay)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		CompareFunctionConditionItem.rightDay.set(this,rightDay);
	}/**

	 **
	 * Returns the value of {@link #leftEnum}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final XEnum getLeftEnum()
	{
		return CompareFunctionConditionItem.leftEnum.get(this);
	}/**

	 **
	 * Sets a new value for {@link #leftEnum}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setLeftEnum(final XEnum leftEnum)
	{
		CompareFunctionConditionItem.leftEnum.set(this,leftEnum);
	}/**

	 **
	 * Returns the value of {@link #rightEnum}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final XEnum getRightEnum()
	{
		return CompareFunctionConditionItem.rightEnum.get(this);
	}/**

	 **
	 * Sets a new value for {@link #rightEnum}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setRightEnum(final XEnum rightEnum)
	{
		CompareFunctionConditionItem.rightEnum.set(this,rightEnum);
	}/**

	 **
	 * Returns the value of {@link #leftItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final com.exedio.cope.CompareFunctionConditionItem getLeftItem()
	{
		return CompareFunctionConditionItem.leftItem.get(this);
	}/**

	 **
	 * Sets a new value for {@link #leftItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setLeftItem(final com.exedio.cope.CompareFunctionConditionItem leftItem)
	{
		CompareFunctionConditionItem.leftItem.set(this,leftItem);
	}/**

	 **
	 * Returns the value of {@link #rightItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final com.exedio.cope.CompareFunctionConditionItem getRightItem()
	{
		return CompareFunctionConditionItem.rightItem.get(this);
	}/**

	 **
	 * Sets a new value for {@link #rightItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setRightItem(final com.exedio.cope.CompareFunctionConditionItem rightItem)
	{
		CompareFunctionConditionItem.rightItem.set(this,rightItem);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for compareFunctionConditionItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	static final com.exedio.cope.Type<CompareFunctionConditionItem> TYPE = newType(CompareFunctionConditionItem.class)
;}
