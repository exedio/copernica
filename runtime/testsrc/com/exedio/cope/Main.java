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

package com.exedio.cope;

import java.io.File;

import com.exedio.cope.pattern.CustomItem;
import com.exedio.cope.pattern.HashItem;
import com.exedio.cope.pattern.JavaViewItem;
import com.exedio.cope.pattern.MD5Item;
import com.exedio.cope.pattern.MediaItem;
import com.exedio.cope.pattern.RelationItem;
import com.exedio.cope.pattern.RelationSourceItem;
import com.exedio.cope.pattern.RelationTargetItem;
import com.exedio.cope.pattern.SerializerItem;
import com.exedio.cope.pattern.VectorItem;


public class Main
{
	public static final Model itemSerializationModel = new Model(new Type[]{ ItemSerializationItem.TYPE });
	public static final Model deleteModel = new Model(new Type[]{ DeleteItem.TYPE, DeleteOtherItem.TYPE} );
	public static final Model enumModel = new Model(new Type[] { EnumItem.TYPE, EnumItem2.TYPE });
	public static final Model dayModel = new Model(new Type[] { DayItem.TYPE });
	public static final Model dataModel = new Model(new Type[]{ DataItem.TYPE, DataSubItem.TYPE });
	public static final Model mediaModel = new Model(new Type[]{ MediaItem.TYPE });
	public static final Model hashModel = new Model(new Type[] { HashItem.TYPE });
	public static final Model md5Model = new Model(new Type[] { MD5Item.TYPE });
	public static final Model vectorModel = new Model(new Type[] { VectorItem.TYPE });
	public static final Model serializerModel = new Model(new Type[] { SerializerItem.TYPE });
	public static final Model customModel = new Model(new Type[] { CustomItem.TYPE, JavaViewItem.TYPE });
	public static final Model cacheIsolationModel = new Model(new Type[] { CacheIsolationItem.TYPE });
	public static final Model relationModel = new Model(new Type[] { RelationItem.TYPE, RelationSourceItem.TYPE, RelationTargetItem.TYPE });
	public static final Model nameModel = new Model(new Type[] {
			NameLongNameLongNameLongNameLongNameLongNameLongItem.TYPE,
			NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.TYPE,
			NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem.TYPE });
	public static final Model matchModel = new Model(new Type[] { MatchItem.TYPE });
	public static final Model hierarchyModel = new Model(new Type[] {
			HierarchyFirstSub.TYPE,
			HierarchySecondSub.TYPE,
			HierarchySuper.TYPE, // deliberately put this type below it's sub types to test correct functionality
			HierarchySingleSuper.TYPE,
			HierarchySingleSub.TYPE,
		});
	public static final Model hierarchyEmptyModel = new Model(new Type[] { HierarchyEmptySub.TYPE, HierarchyEmptySuper.TYPE });
	public static final Model joinFunctionModel = new Model(new Type[] { JoinFunctionItem.TYPE, JoinFunctionItemSingle.TYPE });

	private static final void tearDown(final Model model)
	{
		final File dpf = Properties.getDefaultPropertyFile();
		final java.util.Properties dp = Properties.loadProperties(dpf);
		
		dp.setProperty("database.forcename.StringItem", "STRINGITEMS");
		dp.setProperty("database.forcename.STRINGITEMS.this", "STRINGITEM_ID");
		dp.setProperty("database.forcename.STRINGITEMS.any", "ANY");
		dp.setProperty("database.forcename.STRINGITEMS.mandatory", "MANDATORY");
		dp.setProperty("database.forcename.STRINGITEMS.min4", "MIN_4");
		dp.setProperty("database.forcename.STRINGITEMS.max4", "MAX_4");
		dp.setProperty("database.forcename.STRINGITEMS.min4Max8", "MIN4_MAX8");
		dp.setProperty("database.forcename.STRINGITEMS.exact6", "EXACT_6");
		dp.setProperty("database.forcename.ItemWithSingleUnique", "UNIQUE_ITEMS");
		dp.setProperty("database.forcename.UNIQUE_ITEMS.this", "UNIQUE_ITEM_ID");
		dp.setProperty("database.forcename.UNIQUE_ITEMS.uniqueString", "UNIQUE_STRING");
		dp.setProperty("database.forcename.UNIQUE_ITEMS.otherString", "OTHER_STRING");
		dp.setProperty("database.forcename.ItemWithSingleUnique_uniqueString_Unq", "IX_ITEMWSU_US");
		dp.setProperty("database.forcename.NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem", "NameCollisionlongAItem_F");
		dp.setProperty("database.forcename.NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem_code_Unq", "NameCollisionA_code_Unq_F");
		dp.setProperty("database.forcename.NameCollisionlongAItem_F.collisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber", "collisionlongANumber_F");
		
		model.setPropertiesInitially(new Properties(dp, dpf.getAbsolutePath()+" plus teardown forced names"));
		model.tearDownDatabase();
	}
	
	public static void main(String[] args)
	{
		tearDown(com.exedio.cope.testmodel.Main.model);
		tearDown(itemSerializationModel);
		tearDown(deleteModel);
		tearDown(enumModel);
		tearDown(dayModel);
		tearDown(dataModel);
		tearDown(hashModel);
		tearDown(md5Model);
		tearDown(vectorModel);
		tearDown(serializerModel);
		tearDown(relationModel);
		tearDown(customModel);
		tearDown(cacheIsolationModel);
		tearDown(nameModel);
		tearDown(matchModel);
		tearDown(hierarchyModel);
		tearDown(hierarchyEmptyModel);
		tearDown(joinFunctionModel);
	}

}
