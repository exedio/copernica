/*
 * Copyright (C) 2004-2006  exedio GmbH (www.exedio.com)
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

package com.exedio.copernica;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import com.exedio.cope.Attribute;
import com.exedio.cope.AttributeValue;
import com.exedio.cope.BooleanAttribute;
import com.exedio.cope.DataAttribute;
import com.exedio.cope.DateAttribute;
import com.exedio.cope.DoubleAttribute;
import com.exedio.cope.EnumAttribute;
import com.exedio.cope.IntegerAttribute;
import com.exedio.cope.IntegrityViolationException;
import com.exedio.cope.Item;
import com.exedio.cope.ItemAttribute;
import com.exedio.cope.LengthViolationException;
import com.exedio.cope.LongAttribute;
import com.exedio.cope.MandatoryViolationException;
import com.exedio.cope.Model;
import com.exedio.cope.NoSuchIDException;
import com.exedio.cope.FunctionAttribute;
import com.exedio.cope.FinalViolationException;
import com.exedio.cope.StringAttribute;
import com.exedio.cope.Type;
import com.exedio.cope.UniqueViolationException;
import com.exedio.cope.pattern.CustomAttributeException;
import com.exedio.cope.pattern.Media;
import com.exedio.cops.CheckboxField;
import com.exedio.cops.DateField;
import com.exedio.cops.DoubleField;
import com.exedio.cops.Field;
import com.exedio.cops.Form;
import com.exedio.cops.IntegerField;
import com.exedio.cops.LongField;
import com.exedio.cops.RadioField;
import com.exedio.cops.StringField;
import com.exedio.cops.TextField;

final class ItemForm extends Form
{
	static final String SAVE_BUTTON = "SAVE";
	static final String CHECK_BUTTON = "CHECK";
	static final String DELETE_BUTTON = "DELETE";
	static final String SECTION = "section";
	
	final Item item;
	final Type type;
	/*TODO final*/ boolean hasFiles;
	boolean toSave = false;
	final CopernicaSection currentSection;
	final List<Attribute> displayedAttributes;
	boolean deleted = false;
	String deletedName = null;
	String deletedError = null;
	
	ItemForm(final ItemCop cop, final HttpServletRequest request)
	{
		super(request);
		
		this.item = cop.item;
		this.type = item.getCopeType();
		final CopernicaProvider provider = cop.provider;
		final Model model = provider.getModel();
		final List<Attribute> hiddenAttributes;
		final Collection sections = provider.getSections(type);
		final ArrayList visibleFields = new ArrayList();

		boolean sectionButton = false;
		if(sections!=null)
		{
			{
				CopernicaSection buttonSection = null;
				CopernicaSection previousSection = null;
				CopernicaSection firstSection = null;
				final String previousSectionParam = getParameter(SECTION);
				
				for(Iterator i = sections.iterator(); i.hasNext(); )
				{
					final CopernicaSection section = (CopernicaSection)i.next();
					if(firstSection==null)
						firstSection = section;
					
					final String id = section.getCopernicaID();
					if(getParameter(id)!=null)
					{
						buttonSection = section;
						sectionButton = true;
						break;
					}

					if(id.equals(previousSectionParam))
						previousSection = section;
				}
				if(buttonSection!=null)
					currentSection = buttonSection;
				else if(previousSection!=null)
					currentSection = previousSection;
				else
					currentSection = firstSection;
			}

			displayedAttributes = new ArrayList<Attribute>(provider.getMainAttributes(type));
			hiddenAttributes = new ArrayList<Attribute>();
			for(Iterator i = sections.iterator(); i.hasNext(); )
			{
				final CopernicaSection section = (CopernicaSection)i.next();
				new Section(section.getCopernicaID(), section.getCopernicaName(cop.language));
				final Collection<? extends Attribute> sectionAttributes = section.getCopernicaAttributes();
				if(section.equals(currentSection))
					displayedAttributes.addAll(sectionAttributes);
				else
					hiddenAttributes.addAll(sectionAttributes);
			}
		}
		else
		{
			currentSection = null;
			displayedAttributes = type.getAttributes();
			hiddenAttributes = Collections.<Attribute>emptyList();
		}
		final ArrayList<Attribute> attributes = new ArrayList<Attribute>(displayedAttributes.size()+hiddenAttributes.size());
		attributes.addAll(displayedAttributes);
		attributes.addAll(hiddenAttributes);

		final boolean delete = getParameter(DELETE_BUTTON)!=null;
		if(delete)
		{
			deletedName = cop.provider.getDisplayName(cop, cop.language, item);
			try
			{
				item.deleteCopeItem();
				deleted = true;
				return;
			}
			catch(IntegrityViolationException e)
			{
				deletedError = deletedName + " could not be deleted.";
			}
		}

		final boolean save = getParameter(SAVE_BUTTON)!=null;
		final boolean post = save || sectionButton || getParameter(CHECK_BUTTON)!=null;
		boolean hasFilesTemp = false;
		
		for(Iterator j = attributes.iterator(); j.hasNext(); )
		{
			final Attribute anyAttribute = (Attribute)j.next();
			if(!anyAttribute.isFinal())
			{
				if(anyAttribute instanceof FunctionAttribute)
				{
					final Field field = createField((FunctionAttribute)anyAttribute, post, cop, model);
					toSave = true;
					if(displayedAttributes.contains(anyAttribute))
						visibleFields.add(field);
				}
				else if(anyAttribute instanceof DataAttribute)
				{
					toSave = true;
					hasFilesTemp = true;
				}
			}
		}
		this.hasFiles = hasFilesTemp;

		if(save)
			save();
	}
	
	private final Field createField(
			final FunctionAttribute attribute,
			final boolean post, final ItemCop cop, final Model model)
	{
		return createField(attribute, this.item, attribute.getName(), post, cop, model);
	}
	
	private final Field createField(
			final FunctionAttribute attribute, final Item item, final String name,
			final boolean post, final ItemCop cop, final Model model)
	{
		if(attribute.isFinal())
			throw new RuntimeException(attribute.toString());
		
		if(attribute instanceof EnumAttribute)
		{
			final EnumAttribute enumAttribute = (EnumAttribute)attribute;
			if(post)
				return new EnumField(enumAttribute, cop);
			else
				return new EnumField(enumAttribute, enumAttribute.get(item), cop);
		}
		else if(attribute instanceof BooleanAttribute)
		{
			final BooleanAttribute boolAttribute = (BooleanAttribute)attribute;
			if(attribute.isMandatory())
			{
				if(post)
					return new CheckboxField(this, attribute, name);
				else
					return new CheckboxField(this, attribute, name, boolAttribute.get(item).booleanValue());
			}
			else
			{
				if(post)
					return new BooleanEnumField(boolAttribute, cop);
				else
					return new BooleanEnumField(boolAttribute, boolAttribute.get(item), cop);
			}
		}
		else if(attribute instanceof IntegerAttribute)
		{
			if(post)
				return new IntegerField(this, attribute, name);
			else
				return new IntegerField(this, attribute, name, ((IntegerAttribute)attribute).get(item));
		}
		else if(attribute instanceof LongAttribute)
		{
			if(post)
				return new LongField(this, attribute, name);
			else
				return new LongField(this, attribute, name, ((LongAttribute)attribute).get(item));
		}
		else if(attribute instanceof DoubleAttribute)
		{
			if(post)
				return new DoubleField(this, attribute, name);
			else
				return new DoubleField(this, attribute, name, ((DoubleAttribute)attribute).get(item));
		}
		else if(attribute instanceof DateAttribute)
		{
			if(post)
				return new DateField(this, attribute, name);
			else
				return new DateField(this, attribute, name, ((DateAttribute)attribute).get(item));
		}
		else if(attribute instanceof StringAttribute)
		{
			if(post)
				return new StringField(this, attribute, name);
			else
				return new StringField(this, attribute, name, ((StringAttribute)attribute).get(item));
		}
		else if(attribute instanceof ItemAttribute)
		{
			if(post)
				return new ItemField(attribute, name, model, cop);
			else
				return new ItemField(attribute, name, ((ItemAttribute)attribute).get(item), model, cop);
		}
		else
		{
			throw new RuntimeException(attribute.getClass().toString());
		}
	}
	
	public class ItemField extends TextField
	{
		final Model model;
		final ItemCop cop;
		final Item content;
		
		/**
		 * Constructs a form field with an initial value.
		 */
		public ItemField(final Object key, final String name, final Item value, final Model model, final ItemCop cop)
		{
			super(ItemForm.this, key, name, (value==null) ? "" : value.getCopeID());

			this.model = model;
			this.cop = cop;
			this.content = value;
		}
		
		/**
		 * Constructs a form field with a value obtained from the submitted form.
		 */
		public ItemField(final Object key, final String name, final Model model, final ItemCop cop)
		{
			super(ItemForm.this, key, name);
			this.model = model;
			this.cop = cop;

			final String value = this.value;
			if(value.length()>0)
			{
				Item parsed = null;
				try
				{
					parsed = model.findByID(value);
				}
				catch(NoSuchIDException e)
				{
					error = e.getMessage();
				}
				content = error==null ? parsed : null;
			}
			else
				content = null;
		}

		public void writeIt(final PrintStream out) throws IOException
		{
			super.writeIt(out);
			ItemCop_Jspm.write(out, this);
		}
		
		public Object getContent()
		{
			return content;
		}
		
	}
	
	final class EnumField extends RadioField
	{
		private static final String VALUE_NULL = "null";

		final EnumAttribute attribute;
		final Enum content;

		/**
		 * Constructs a form field with an initial value.
		 */
		EnumField(final EnumAttribute attribute, final Enum value, final ItemCop cop)
		{
			super(ItemForm.this, attribute, attribute.getName(), (value==null) ? VALUE_NULL : value.name());
			
			this.attribute = attribute;
			this.content = value;
			addOptions(cop);
		}
	
		/**
		 * Constructs a form field with a value obtained from the submitted form.
		 */
		EnumField(final EnumAttribute attribute, final ItemCop cop)
		{
			super(ItemForm.this, attribute, attribute.getName());
			
			this.attribute = attribute;
			addOptions(cop);

			final String value = this.value;
			if(VALUE_NULL.equals(value))
				content = null;
			else
			{
				content = attribute.getValue(value);
				if(content==null)
					throw new RuntimeException(value);
			}
		}
		
		private void addOptions(final ItemCop cop)
		{
			if(!attribute.isMandatory())
			{
				addOption(VALUE_NULL, cop.getDisplayNameNull());
			}
			for(final Enum currentValue : attribute.getValues())
			{
				final String currentCode = currentValue.name();
				final String currentName = cop.getDisplayName(currentValue);
				addOption(currentCode, currentName);
			}
		}
	
		public Object getContent()
		{
			return content;
		}

	}
	
	final class BooleanEnumField extends RadioField
	{
		private static final String VALUE_NULL = "null";
		private static final String VALUE_ON = "on";
		private static final String VALUE_OFF = "off";
		
		final Boolean content;

		/**
		 * Constructs a form field with an initial value.
		 */
		BooleanEnumField(final BooleanAttribute attribute, final Boolean value, final ItemCop cop)
		{
			super(ItemForm.this, attribute, attribute.getName(), value==null ? VALUE_NULL : value.booleanValue() ? VALUE_ON : VALUE_OFF);
			
			this.content = value;
			addOptions(cop);
		}
		
		/**
		 * Constructs a form field with a value obtained from the submitted form.
		 */
		BooleanEnumField(final BooleanAttribute attribute, final ItemCop cop)
		{
			super(ItemForm.this, attribute, attribute.getName());
			addOptions(cop);

			final String value = this.value;
			if(VALUE_NULL.equals(value))
				content = null;
			else if(VALUE_ON.equals(value))
				content = Boolean.TRUE;
			else if(VALUE_OFF.equals(value))
				content = Boolean.FALSE;
			else
				throw new RuntimeException(value);
		}
		
		private final void addOptions(final ItemCop cop)
		{
			addOption(VALUE_NULL, cop.getDisplayNameNull());
			addOption(VALUE_ON, cop.getDisplayNameOn());
			addOption(VALUE_OFF, cop.getDisplayNameOff());
		}
		
		public Object getContent()
		{
			return content;
		}
	}
	

	private void save()
	{
		final ArrayList<AttributeValue> attributeValues = new ArrayList<AttributeValue>();
		
		for(Iterator i = getFields().iterator(); i.hasNext(); )
		{
			final Field field = (Field)i.next();
			if(field.key instanceof DataAttribute)
			{
				final DataAttribute attribute = (DataAttribute)field.key;
				final Media media = Media.get(attribute);
				final FileItem fileItem = getParameterFile(attribute.getName());
				
				if(fileItem!=null)
				{
					String contentType = fileItem.getContentType();
					if(contentType!=null)
					{
						// fix for MSIE behaviour
						if("image/pjpeg".equals(contentType))
							contentType = "image/jpeg";
						
						try
						{
							final InputStream data = fileItem.getInputStream();
							media.set(item, data, contentType);
						}
						catch(IOException e)
						{
							throw new RuntimeException(e);
						}
					}
				}
			}
			if(field.error==null)
			{
				final FunctionAttribute attribute = (FunctionAttribute)field.key;
				attributeValues.add(new AttributeValue(attribute, field.getContent()));
			}
		}
		try
		{
			item.set(attributeValues.toArray(new AttributeValue[attributeValues.size()]));
		}
		catch(MandatoryViolationException e)
		{
			final Field field = getFieldByKey(e.getMandatoryAttribute());
			field.error = "error.notnull:"+e.getMandatoryAttribute().toString();
		}
		catch(FinalViolationException e)
		{
			throw new RuntimeException(e);
		}
		catch(UniqueViolationException e)
		{
			final Field field = getFieldByKey((FunctionAttribute)e.getConstraint().getUniqueAttributes().iterator().next());
			field.error = e.getClass().getName();
		}
		catch(LengthViolationException e)
		{
			final Field field = getFieldByKey(e.getStringAttribute());
			field.error = e.getClass().getName();
		}
		catch(CustomAttributeException e)
		{
			final Field field = getFieldByKey(e.getAttribute());
			field.error = e.getClass().getName();
		}
	}
	
}
