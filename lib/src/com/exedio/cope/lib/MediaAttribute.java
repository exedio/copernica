
package com.exedio.cope.lib;

import java.util.ArrayList;
import java.util.List;

public final class MediaAttribute extends Attribute
{
	final String fixedMimeMajor;
	final String fixedMimeMinor;
	
	StringColumn mimeMajor = null;
	StringColumn mimeMinor = null;
	IntegerColumn exists = null;

	public MediaAttribute(final Option option, final String fixedMimeMajor, final String fixedMimeMinor)
	{
		super(option);
		this.fixedMimeMajor = fixedMimeMajor;
		this.fixedMimeMinor = fixedMimeMinor;
		
		// make sure, media configuration properties are set
		Properties.getInstance().getMediaDirectory();
	}
	
	public MediaAttribute(final Option option, final String fixedMimeMajor)
	{
		this(option, fixedMimeMajor, null);
	}
	
	public MediaAttribute(final Option option)
	{
		this(option, null, null);
	}
	
	public final String getFixedMimeMajor()
	{
		return fixedMimeMajor;
	}
	
	public final String getFixedMimeMinor()
	{
		return fixedMimeMinor;
	}
	
	protected List createColumns(final String name, final boolean notNull)
	{
		final Type type = getType();
		final ArrayList result = new ArrayList(2);
		if(fixedMimeMajor==null)
		{
			mimeMajor = new StringColumn(type, name + "Major", notNull, 30);
			result.add(mimeMajor);
		}
		if(fixedMimeMinor==null)
		{
			mimeMinor = new StringColumn(type, name + "Minor", notNull, 30);
			result.add(mimeMinor);
		}
		if(fixedMimeMajor!=null && fixedMimeMinor!=null && !notNull)
		{
			// TODO: make that column not-null
			exists = new IntegerColumn(type, name + "Exists", false, 1, false);
			result.add(exists);
		}
		return result;
	}
	
}
