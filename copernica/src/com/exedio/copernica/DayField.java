package com.exedio.copernica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.exedio.cope.util.Day;
import com.exedio.cope.util.TimeZoneStrict;

final class DayField extends TextField
{
	private static final String DATE_FORMAT_FULL = "dd.MM.yyyy";
	private static final TimeZone ZONE = TimeZoneStrict.getTimeZone("Europe/Berlin");

	final Day content;
	final String pattern;

	/**
	 * Constructs a form field with an initial value.
	 */
	public DayField(final Form form, final Object key, final String name, final Day value)
	{
		this(DATE_FORMAT_FULL, form, key, name, value);
	}

	/**
	 * Constructs a form field with an initial value.
	 */
	public DayField(final String pattern, final Form form, final Object key, final String name, final Day value)
	{
		super(form, key, name, (value==null) ? "" : (new SimpleDateFormat(pattern)).format(new Date(value.getTimeInMillisFrom(ZONE))));

		this.pattern = pattern;
		this.content = value;

		if(pattern==null)
			throw new NullPointerException("pattern");
	}

	/**
	 * Constructs a form field with a value obtained from the submitted form.
	 */
	public DayField(final Form form, final Object key, final String name)
	{
		this(DATE_FORMAT_FULL, form, key, name);
	}

	/**
	 * Constructs a form field with a value obtained from the submitted form.
	 */
	public DayField(final String pattern, final Form form, final Object key, final String name)
	{
		super(form, key, name);

		this.pattern = pattern;

		final String value = this.value;
		if(value.length()>0)
		{
			Day parsed = null;
			try
			{
				final SimpleDateFormat df = new SimpleDateFormat(pattern);
				parsed = new Day(df.parse(value), ZONE);
			}
			catch(ParseException e)
			{
				error = "bad date: "+e.getMessage();
			}
			content = error==null ? parsed : null;
		}
		else
			content = null;
	}

	@Override
	public Object getContent()
	{
		return content;
	}

}
