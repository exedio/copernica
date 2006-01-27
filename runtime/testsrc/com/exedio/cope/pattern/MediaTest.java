/*
 * Copyright (C) 2004-2005  exedio GmbH (www.exedio.com)
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

import com.exedio.cope.DataAttribute;
import com.exedio.cope.DateAttribute;
import com.exedio.cope.Feature;
import com.exedio.cope.StringAttribute;
import com.exedio.cope.TestmodelTest;
import com.exedio.cope.testmodel.MediaItem;

public class MediaTest extends TestmodelTest
{
	// TODO test various combinations of internal, external implicit, and external explicit source

	private MediaItem item;
	private final byte[] data4 = new byte[]{-86,122,-8,23};
	private final byte[] data6 = new byte[]{-97,35,-126,86,19,-8};
	private final byte[] dataFile = new byte[]{-54,104,-63,23,19,-45,71,-23};
	private final byte[] dataEmpty = new byte[]{};
	
	public void setUp() throws Exception
	{
		super.setUp();
		deleteOnTearDown(item = new MediaItem("test media item"));
	}
	
	private void assertExtension(final String contentType, final String extension)
		throws IOException
	{
		final Date before = new Date();
		item.setFile(stream(data6), contentType);
		final Date after = new Date();
		assertStreamClosed();
		assertFile(data6, before, after, contentType, extension);
	}
	
	public void testData() throws IOException
	{
		assertEqualsUnmodifiable(Arrays.asList(new Feature[]{
				item.name,
				item.file,
				item.file.getData(),
				item.file.getMimeMajor(),
				item.file.getMimeMinor(),
				item.file.getLastModified(),
				item.image,
				item.image.getData(),
				item.image.getMimeMinor(),
				item.image.getLastModified(),
				item.photo,
				item.photo.getData(),
				item.photo.getLastModified(),
				item.foto,
				item.nameServer,
			}), item.TYPE.getFeatures());

		// file
		assertEquals(null, item.file.getFixedMimeMajor());
		assertEquals(null, item.file.getFixedMimeMinor());
		final DataAttribute fileData = item.file.getData();
		assertSame(item.TYPE, fileData.getType());
		assertSame("fileData", fileData.getName());
		assertEqualsUnmodifiable(list(item.file), fileData.getPatterns());
		assertSame(item.file, Media.get(fileData));
		final StringAttribute fileMajor = item.file.getMimeMajor();
		assertSame(item.TYPE, fileMajor.getType());
		assertEquals("fileMajor", fileMajor.getName());
		assertEqualsUnmodifiable(list(item.file), fileMajor.getPatterns());
		final StringAttribute fileMinor = item.file.getMimeMinor();
		assertSame(item.TYPE, fileMinor.getType());
		assertEquals("fileMinor", fileMinor.getName());
		assertEqualsUnmodifiable(list(item.file), fileMinor.getPatterns());
		final DateAttribute fileLastModified = item.file.getLastModified();
		assertSame(item.TYPE, fileLastModified.getType());
		assertEquals("fileLastModified", fileLastModified.getName());
		assertEqualsUnmodifiable(list(item.file), fileLastModified.getPatterns());
		assertSame(fileLastModified, item.file.getIsNull());
		
		assertFileNull();
		{
			final Date before = new Date();
			item.setFile(stream(data4), "fileMajor/fileMinor");
			final Date after = new Date();
			assertStreamClosed();
			assertFile(data4, before, after, "fileMajor/fileMinor", ".fileMajor.fileMinor");
		}
		{
			final Date before = new Date();
			item.setFile(stream(data6), "fileMajor2/fileMinor2");
			final Date after = new Date();
			assertStreamClosed();
			assertFile(data6, before, after, "fileMajor2/fileMinor2", ".fileMajor2.fileMinor2");

			try
			{
				item.setFile(stream(data4), "illegalContentType");
				fail();
			}
			catch(IllegalContentTypeException e)
			{
				assertStreamClosed();
				assertEquals("illegalContentType", e.getMessage());
				assertFile(data6, before, after, "fileMajor2/fileMinor2", ".fileMajor2.fileMinor2");
			}
		}
		assertExtension("image/jpeg", ".jpg");
		assertExtension("image/pjpeg", ".jpg");
		assertExtension("image/png", ".png");
		assertExtension("image/gif", ".gif");
		assertExtension("text/html", ".html");
		assertExtension("text/plain", ".txt");
		assertExtension("text/css", ".css");
		assertExtension("application/java-archive", ".jar");
		if(!oracle)
		{
			final Date before = new Date();
			item.setFile(stream(dataEmpty), "emptyMajor/emptyMinor");
			final Date after = new Date();
			assertStreamClosed();
			assertFile(dataEmpty, before, after, "emptyMajor/emptyMinor", ".emptyMajor.emptyMinor");
		}
		item.setFile((InputStream)null, null);
		assertFileNull();
		{
			final Date before = new Date();
			item.setFile(file(dataFile), "emptyMajor/emptyMinor");
			final Date after = new Date();
			assertFile(dataFile, before, after, "emptyMajor/emptyMinor", ".emptyMajor.emptyMinor");
		}
		item.setFile((File)null, null);
		assertFileNull();
		{
			final Date before = new Date();
			item.setFile(dataFile, "emptyMajor/emptyMinor");
			final Date after = new Date();
			assertFile(dataFile, before, after, "emptyMajor/emptyMinor", ".emptyMajor.emptyMinor");
		}
		item.setFile((byte[])null, null);
		assertFileNull();


		// image
		assertEquals("image", item.image.getFixedMimeMajor());
		assertEquals(null, item.image.getFixedMimeMinor());
		final DataAttribute imageData = item.image.getData();
		assertSame(item.TYPE, imageData.getType());
		assertSame("imageData", imageData.getName());
		assertEqualsUnmodifiable(list(item.image), imageData.getPatterns());
		assertSame(item.image, Media.get(imageData));
		assertEquals(null, item.image.getMimeMajor());
		final StringAttribute imageMinor = item.image.getMimeMinor();
		assertSame(item.TYPE, imageMinor.getType());
		assertEquals("imageMinor", imageMinor.getName());
		assertEqualsUnmodifiable(list(item.image), imageMinor.getPatterns());
		final DateAttribute imageLastModified = item.image.getLastModified();
		assertSame(item.TYPE, imageLastModified.getType());
		assertEquals("imageLastModified", imageLastModified.getName());
		assertEqualsUnmodifiable(list(item.image), imageLastModified.getPatterns());
		assertSame(imageLastModified, item.image.getIsNull());

		assertImageNull();

		item.setImage(stream(data4), "image/imageMinor");
		assertStreamClosed();
		assertImage(data4, "image/imageMinor", ".image.imageMinor");

		item.setImage(stream(data6), "image/jpeg");
		assertStreamClosed();
		assertImage(data6, "image/jpeg", ".jpg");

		try
		{
			item.setImage(stream(data4), "illegalContentType");
			fail();
		}
		catch(IllegalContentTypeException e)
		{
			assertStreamClosed();
			assertEquals("illegalContentType", e.getMessage());
			assertImage(data6, "image/jpeg", ".jpg");
		}

		try
		{
			item.setImage(stream(data4), "text/html");
			fail();
		}
		catch(IllegalContentTypeException e)
		{
			assertStreamClosed();
			assertEquals("text/html", e.getMessage());
			assertImage(data6, "image/jpeg", ".jpg");
		}

		item.setImage((InputStream)null, null);
		assertImageNull();
		
		
		// photo
		assertEquals("image", item.photo.getFixedMimeMajor());
		assertEquals("jpeg", item.photo.getFixedMimeMinor());
		final DataAttribute photoData = item.photo.getData();
		assertSame(item.TYPE, photoData.getType());
		assertSame("photoData", photoData.getName());
		assertEqualsUnmodifiable(list(item.photo), photoData.getPatterns());
		assertSame(item.photo, Media.get(photoData));
		assertEquals(null, item.photo.getMimeMajor());
		assertEquals(null, item.photo.getMimeMinor());
		final DateAttribute photoLastModified = item.photo.getLastModified();
		assertSame(item.TYPE, photoLastModified.getType());
		assertEquals("photoLastModified", photoLastModified.getName());
		assertEqualsUnmodifiable(list(item.photo), photoLastModified.getPatterns());
		assertSame(photoLastModified, item.photo.getIsNull());

		assertPhotoNull();

		item.setPhoto(stream(data4), "image/jpeg");
		assertStreamClosed();
		assertPhoto(data4);

		item.setPhoto(stream(data6), "image/jpeg");
		assertStreamClosed();
		assertPhoto(data6);
		
		try
		{
			item.setPhoto(stream(data4), "illegalContentType");
			fail();
		}
		catch(IllegalContentTypeException e)
		{
			assertStreamClosed();
			assertEquals("illegalContentType", e.getMessage());
			assertPhoto(data6);
		}

		try
		{
			item.setPhoto(stream(data4), "image/png");
			fail();
		}
		catch(IllegalContentTypeException e)
		{
			assertStreamClosed();
			assertEquals("image/png", e.getMessage());
			assertPhoto(data6);
		}

		item.setPhoto((InputStream)null, null);
		assertPhotoNull();
		
		
		// foto
		assertEquals(item.TYPE, item.foto.getType());
		assertEquals("foto", item.foto.getName());
		assertSame(item.photo, item.foto.getTarget());
		
		
		// nameServer
		assertEquals(item.TYPE, item.nameServer.getType());
		assertEquals("nameServer", item.nameServer.getName());
		assertSame(item.name, item.nameServer.getSource());
		

		// logs -----------------------------------------------
		
		assertEquals(0, item.photo.noSuchItem.get());
		assertEquals(0, item.photo.dataIsNull.get());
		assertEquals(0, item.photo.notModified.get());
		assertEquals(0, item.photo.delivered.get());
		
		item.photo.noSuchItem.increment();
		assertEquals(1, item.photo.noSuchItem.get());
		assertEquals(0, item.photo.dataIsNull.get());
		assertEquals(0, item.photo.notModified.get());
		assertEquals(0, item.photo.delivered.get());

		item.photo.noSuchItem.increment();
		item.photo.dataIsNull.increment();
		item.photo.notModified.increment();
		item.photo.delivered.increment();
		assertEquals(2, item.photo.noSuchItem.get());
		assertEquals(1, item.photo.dataIsNull.get());
		assertEquals(1, item.photo.notModified.get());
		assertEquals(1, item.photo.delivered.get());
	}

	private void assertFileNull() throws IOException
	{
		assertTrue(item.isFileNull());
		assertEquals(null, item.getFileData());
		assertDataFile(null);
		assertEquals(-1, item.getFileLength());
		assertEquals(-1, item.getFileLastModified());
		assertEquals(null, item.getFileContentType());
		assertEquals(null, item.getFileURL());
	}
	
	private void assertFile(
			final byte[] expectedData,
			final Date before, final Date after,
			final String expectedContentType, final String expectedExtension)
	throws IOException
	{
		assertTrue(!item.isFileNull());
		assertData(expectedData, item.getFileData());
		assertDataFile(expectedData);
		assertEquals(expectedData.length, item.getFileLength());
		assertWithin(before, after, new Date(item.getFileLastModified()));
		assertEquals(expectedContentType, item.getFileContentType());
		assertTrue(item.getFileURL().endsWith(expectedExtension));
	}
	
	private final void assertDataFile(final byte[] expectedData) throws IOException
	{
		final File tempFile = File.createTempFile("cope-MediaTest.", ".tmp");
		assertTrue(tempFile.delete());
		assertFalse(tempFile.exists());
		
		item.getFileData(tempFile);
		assertEqualContent(expectedData, tempFile);
	}
	
	private void assertImageNull() throws IOException
	{
		assertTrue(item.isImageNull());
		assertEquals(null, item.getImageData());
		assertEquals(-1, item.getImageLength());
		assertEquals(null, item.getImageContentType());
		assertEquals(null, item.getImageURL());
	}
	
	private void assertImage(
			final byte[] expectedData,
			final String expectedContentType, final String expectedExtension)
	throws IOException
	{
		assertTrue(!item.isImageNull());
		assertData(expectedData, item.getImageData());
		assertEquals(expectedData.length, item.getImageLength());
		assertEquals(expectedContentType, item.getImageContentType());
		assertTrue(item.getImageURL().endsWith(expectedExtension));
	}
	
	private void assertPhotoNull() throws IOException
	{
		assertTrue(item.photo.isNull(item));
		assertTrue(item.isPhotoNull());
		assertEquals(null, item.getPhotoData());
		assertEquals(-1, item.getPhotoLength());
		assertEquals(null, item.getPhotoContentType());
		assertEquals(null, item.getPhotoURL());
	}
	
	private void assertPhoto(final byte[] expectedData)
	throws IOException
	{
		assertTrue(!item.isPhotoNull());
		assertData(expectedData, item.getPhotoData());
		assertEquals(expectedData.length, item.getPhotoLength());
		assertEquals("image/jpeg", item.getPhotoContentType());
		assertTrue(item.getPhotoURL().endsWith(".jpg"));
	}
	
}
