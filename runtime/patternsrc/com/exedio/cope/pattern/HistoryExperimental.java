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

package com.exedio.cope.pattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.exedio.cope.BooleanField;
import com.exedio.cope.Cope;
import com.exedio.cope.DateField;
import com.exedio.cope.Item;
import com.exedio.cope.ItemField;
import com.exedio.cope.Pattern;
import com.exedio.cope.StringField;
import com.exedio.cope.Type;
import com.exedio.cope.UniqueConstraint;

/**
 * This pattern is still experimental, and its API may change any time.
 */
public final class HistoryExperimental extends Pattern
{
	private ItemField<?> eventParent = null;
	private final DateField eventDate = new DateField().toFinal();
	private UniqueConstraint eventUnique = null;
	private final StringField eventCause = new StringField().toFinal();
	private final BooleanField eventCreation = new BooleanField().toFinal();
	private Type<?> eventType = null;
	
	private ItemField<?> featureEvent = null;
	private final StringField featureId = new StringField().toFinal();
	private UniqueConstraint featureUnique = null;
	private final StringField featureName = new StringField().toFinal();
	private final StringField featureOld = new StringField().toFinal().optional();
	private final StringField featureNew = new StringField().toFinal().optional();
	private Type<?> featureType = null;

	@Override
	public void initialize()
	{
		final Type<?> type = getType();
		
		eventParent = type.newItemField(ItemField.DeletePolicy.CASCADE).toFinal();
		eventUnique = new UniqueConstraint(eventParent, eventDate);
		final LinkedHashMap<String, com.exedio.cope.Feature> features = new LinkedHashMap<String, com.exedio.cope.Feature>();
		features.put("parent", eventParent);
		features.put("date", eventDate);
		features.put("uniqueConstraint", eventUnique);
		features.put("cause", eventCause);
		features.put("creation", eventCreation);
		eventType = newType(features, "Event");
		
		features.clear();
		featureEvent = eventType.newItemField(ItemField.DeletePolicy.CASCADE).toFinal();
		featureUnique = new UniqueConstraint(featureEvent, featureId);
		features.put("event", featureEvent);
		features.put("id", featureId);
		features.put("uniqueConstraint", featureUnique);
		features.put("name", featureName);
		features.put("old", featureOld);
		features.put("new", featureNew);
		featureType = newType(features, "Feature");
	}
	
	public ItemField<?> getEventParent()
	{
		assert eventParent!=null;
		return eventParent;
	}
	
	public DateField getEventDate()
	{
		return eventDate;
	}
	
	public UniqueConstraint getEventUniqueConstraint()
	{
		assert eventUnique!=null;
		return eventUnique;
	}
	
	public StringField getEventCause()
	{
		return eventCause;
	}
	
	public BooleanField getEventCreation()
	{
		return eventCreation;
	}
	
	public Type<? extends Item> getEventType()
	{
		assert eventType!=null;
		return eventType;
	}
	
	public ItemField<?> getFeatureEvent()
	{
		assert featureEvent!=null;
		return featureEvent;
	}
	
	public StringField getFeatureId()
	{
		return featureId;
	}
	
	public UniqueConstraint getFeatureUniqueConstraint()
	{
		assert featureUnique!=null;
		return featureUnique;
	}
	
	public StringField getFeatureName()
	{
		return featureName;
	}
	
	public StringField getFeatureOld()
	{
		return featureOld;
	}
	
	public StringField getFeatureNew()
	{
		return featureNew;
	}
	
	public Type<? extends Item> getFeatureType()
	{
		assert featureType!=null;
		return featureType;
	}
	
	public List<? extends Event> getEvents(final Item item)
	{
		final List<? extends Item> eventItems = eventType.search(Cope.equalAndCast(eventParent, item), eventDate, false);
		final ArrayList<Event> result = new ArrayList<Event>(eventItems.size());
		for(final Item eventItem : eventItems)
			result.add(new Event(eventItem));
		return Collections.unmodifiableList(result);
	}
	
	public Event createEvent(final Item item, final String cause, final boolean creation)
	{
		return new Event(eventType.newItem(
				Cope.mapAndCast(eventParent, item),
				eventDate.map(new Date()),
				eventCause.map(cause),
				eventCreation.map(creation)
			));
	}

	public final class Event
	{
		private final Item event;
		
		Event(final Item event)
		{
			this.event = event;
			assert event!=null;
		}
		
		public Item getParent()
		{
			return eventParent.get(event);
		}
		
		public Date getDate()
		{
			return eventDate.get(event);
		}
		
		public String getCause()
		{
			return eventCause.get(event);
		}
		
		public boolean isCreation()
		{
			return eventCreation.getMandatory(event);
		}
		
		public List<? extends Feature> getFeatures()
		{
			final List<? extends Item> featureItems = featureType.search(Cope.equalAndCast(featureEvent, event), featureType.getThis(), true);
			final ArrayList<Feature> result = new ArrayList<Feature>(featureItems.size());
			for(final Item featureItem : featureItems)
				result.add(new Feature(featureItem));
			return Collections.unmodifiableList(result);
		}
		
		public Feature createFeature(final com.exedio.cope.Feature f, final String name, final Object oldValue, final Object newValue)
		{
			return new Feature(featureType.newItem(
					Cope.mapAndCast(featureEvent, event),
					featureId.map(f.getID()),
					featureName.map(name),
					featureOld.map(oldValue!=null ? oldValue.toString() : null),
					featureNew.map(newValue!=null ? newValue.toString() : null)
				));
		}
		
		@Override
		public boolean equals(final Object other)
		{
			return other instanceof Event && event.equals(((Event)other).event);
		}
		
		@Override
		public int hashCode()
		{
			return event.hashCode() ^ 28736589;
		}
		
		@Override
		public String toString()
		{
			return event.toString();
		}
	}

	public final class Feature
	{
		private final Item feature;
		
		Feature(final Item feature)
		{
			this.feature = feature;
			assert feature!=null;
		}
		
		public Event getEvent()
		{
			return new Event(featureEvent.get(feature));
		}
		
		public com.exedio.cope.Feature getFeature()
		{
			return featureId.getType().getModel().findFeatureByID(featureId.get(feature));
		}
		
		public String getId()
		{
			return featureId.get(feature);
		}
		
		public String getName()
		{
			return featureName.get(feature);
		}
		
		public String getOld()
		{
			return featureOld.get(feature);
		}
		
		public String getNew()
		{
			return featureNew.get(feature);
		}
		
		@Override
		public boolean equals(final Object other)
		{
			return other instanceof Feature && feature.equals(((Feature)other).feature);
		}
		
		@Override
		public int hashCode()
		{
			return feature.hashCode() ^ 125437263;
		}
		
		@Override
		public String toString()
		{
			return feature.toString();
		}
	}
}