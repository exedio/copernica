
package com.exedio.cope.lib;

/**
 * @persistent
 */
public class MediaItem extends Item
{
	static final MediaAttribute file = new MediaAttribute(DEFAULT);

	static final MediaAttribute image = new MediaAttribute(DEFAULT, "image");
	
	static final MediaAttributeVariant BB240 = new MediaAttributeVariant(image);

	static final MediaAttribute photo = new MediaAttribute(DEFAULT, "image", "jpeg");
	
	static final MediaAttributeVariant BB65 = new MediaAttributeVariant(photo);

	static final MediaAttributeVariant Progressive = new MediaAttributeVariant(photo);
	
/**

	 **
	 * Constructs a new MediaItem with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public MediaItem()
	{
		super(new com.exedio.cope.lib.AttributeValue[]{
		});
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private MediaItem(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns a URL pointing to the data of the persistent attribute {@link #file}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.lang.String getFileURL()
	{
		return getMediaURL(this.file,null);
	}/**

	 **
	 * Returns the major mime type of the persistent media attribute {@link #file}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.lang.String getFileMimeMajor()
	{
		return getMediaMimeMajor(this.file);
	}/**

	 **
	 * Returns the minor mime type of the persistent media attribute {@link #file}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.lang.String getFileMimeMinor()
	{
		return getMediaMimeMinor(this.file);
	}/**

	 **
	 * Returns a stream for fetching the data of the persistent media attribute {@link #file}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.io.InputStream getFileData()
	{
		return getMediaData(this.file);
	}/**

	 **
	 * Provides data for the persistent media attribute {@link #file}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final void setFileData(final java.io.InputStream data,final java.lang.String mimeMajor,final java.lang.String mimeMinor)throws java.io.IOException
	{
		try
		{
			setMediaData(this.file,data,mimeMajor,mimeMinor);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns a URL pointing to the data of the persistent attribute {@link #image}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.lang.String getImageURL()
	{
		return getMediaURL(this.image,null);
	}/**

	 **
	 * Returns a URL pointing to the varied data of the persistent attribute {@link #image}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.lang.String getImageURLBB240()
	{
		return getMediaURL(this.image,"BB240");
	}/**

	 **
	 * Returns the major mime type of the persistent media attribute {@link #image}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.lang.String getImageMimeMajor()
	{
		return getMediaMimeMajor(this.image);
	}/**

	 **
	 * Returns the minor mime type of the persistent media attribute {@link #image}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.lang.String getImageMimeMinor()
	{
		return getMediaMimeMinor(this.image);
	}/**

	 **
	 * Returns a stream for fetching the data of the persistent media attribute {@link #image}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.io.InputStream getImageData()
	{
		return getMediaData(this.image);
	}/**

	 **
	 * Provides data for the persistent media attribute {@link #image}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final void setImageData(final java.io.InputStream data,final java.lang.String mimeMinor)throws java.io.IOException
	{
		try
		{
			setMediaData(this.image,data,null,mimeMinor);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns a URL pointing to the data of the persistent attribute {@link #photo}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.lang.String getPhotoURL()
	{
		return getMediaURL(this.photo,null);
	}/**

	 **
	 * Returns a URL pointing to the varied data of the persistent attribute {@link #photo}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.lang.String getPhotoURLBB65()
	{
		return getMediaURL(this.photo,"BB65");
	}/**

	 **
	 * Returns a URL pointing to the varied data of the persistent attribute {@link #photo}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.lang.String getPhotoURLProgressive()
	{
		return getMediaURL(this.photo,"Progressive");
	}/**

	 **
	 * Returns the major mime type of the persistent media attribute {@link #photo}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.lang.String getPhotoMimeMajor()
	{
		return getMediaMimeMajor(this.photo);
	}/**

	 **
	 * Returns the minor mime type of the persistent media attribute {@link #photo}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.lang.String getPhotoMimeMinor()
	{
		return getMediaMimeMinor(this.photo);
	}/**

	 **
	 * Returns a stream for fetching the data of the persistent media attribute {@link #photo}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.io.InputStream getPhotoData()
	{
		return getMediaData(this.photo);
	}/**

	 **
	 * Provides data for the persistent media attribute {@link #photo}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final void setPhotoData(final java.io.InputStream data)throws java.io.IOException
	{
		try
		{
			setMediaData(this.photo,data,null,null);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * The persistent type information for mediaItem.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(MediaItem.class)
;}
