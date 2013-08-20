package com.smiechmateusz.model.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.smiechmateusz.dao.ArticleDAO;
import com.smiechmateusz.dao.CategoryDAO;
import com.smiechmateusz.dao.ImageDAO;
import com.smiechmateusz.dao.ManufacturerDAO;
import com.smiechmateusz.model.Article;
import com.smiechmateusz.model.Category;
import com.smiechmateusz.model.Image;
import com.smiechmateusz.model.Manufacturer;
import com.smiechmateusz.utils.WebUtils;

/**
 * Article model for use in forms. Contains both original and target data.
 * 
 *  @author Śmiech Mateusz
 */
public class ArticleFormModel extends Article
{
	
	/** The categories id. */
	@Transient
	private Set<Long> categoriesId;
	
	/** The deleted images id. */
	@Transient
	private Set<Long> deletedImagesId;
	
	/** The deleted main image. */
	@Transient
	private boolean deletedMainImage;
	
	/** The amount of added images. */
	@Transient
	private int amountOfAddedImages;
	
	/** The new main image. */
	@Transient
	private CommonsMultipartFile newMainImage;
	
	/** The new manufacturer id. */
	@Transient
	private long manufacturerId;
	
	/** The new additional images. */
	@Transient
	private List<CommonsMultipartFile> newAdditionalImages;
	
	/** The error. */
	@Transient
	String error;
	
	/** The success. */
	@Transient
	String success;
	
	/**
	 * Instantiates a new article form model.
	 */
	public ArticleFormModel()
	{
		
	}
	
	/**
	 * Instantiates a new article form model from an article.
	 * 
	 * @param a the article to copy fields from
	 */
	public ArticleFormModel(Article a)
	{
		this.id = a.getId();
		this.images = a.getImages();
		this.description = a.getDescription();
		this.categories = a.getCategories();
		this.addDate = a.getAddDate();
		this.name = a.getName();
		this.available = a.isAvailable();
		this.manufacturer = a.getManufacturer();
		this.price = a.getPrice();
	}
	
	/**
	 * Parses the model and updates the article.
	 * 
	 * @param article the target article
	 * @param articleDAO the article dao
	 * @param categoryDAO the category dao
	 * @param imageDAO the image dao
	 */
	public void parseModel(Article article, ArticleDAO articleDAO, CategoryDAO categoryDAO, 
			ImageDAO imageDAO, ManufacturerDAO manufacturerDAO)
	{
		parseModelMain(article);
		parseModelCategories(article, categoryDAO);
		parseModelMainImage(article, imageDAO);
		parseModelDeletedImages(article, imageDAO);
		parseModelNewImages(article, imageDAO);
		parseModelManufacturer(article, manufacturerDAO);
		articleDAO.update(article);
	}
	
	/**
	 * Parses the model new manufacturer.
	 * 
	 * @param article the target article
	 * @param manufacturerDAO the manufacturer dao
	 */
	private void parseModelManufacturer(Article article, ManufacturerDAO manufacturerDAO)
	{
		Manufacturer m = manufacturerDAO.getById(manufacturerId);
		if (article.getManufacturer() != null)
		{
			article.getManufacturer().removeArticle(article);
			manufacturerDAO.update(article.getManufacturer());
		}
		article.setManufacturer(m);
	}

	/**
	 * Parses the model new images.
	 * 
	 * @param article the target article
	 * @param imageDAO the image dao
	 */
	private void parseModelNewImages(Article article, ImageDAO imageDAO)
	{
		if (this.newAdditionalImages != null)
		{
			ArrayList<Image> newImages = new ArrayList<Image>();
			for (int i = 0; i < this.newAdditionalImages.size(); i++)
			{
				CommonsMultipartFile f = this.newAdditionalImages.get(i);
				if (f != null)
				{
					WebUtils.saveFileTwice(f);
					Image img = new Image(article, f.getOriginalFilename(), 2);
					imageDAO.create(img);
					newImages.add(img);
				}
			}
			article.addImages(newImages);
		}
	}

	/**
	 * Parses the model deleted images.
	 * 
	 * @param article the target article
	 * @param imageDAO the image dao
	 */
	private void parseModelDeletedImages(Article article, ImageDAO imageDAO)
	{
		if (this.deletedImagesId != null)
		{
			article.deleteImagesById(this.deletedImagesId);
			for (Long l : this.deletedImagesId)
				imageDAO.deleteById(l);
		}
	}

	/**
	 * Parses the model main image.
	 * 
	 * @param article the target article
	 * @param imageDAO the image dao
	 */
	private void parseModelMainImage(Article article, ImageDAO imageDAO)
	{
		if (this.deletedMainImage && this.newMainImage != null)
		{
			if (article.getMainImage() != null) //article already has got a main image, we will just update it
			{
				WebUtils.removeFileTwice(article.getMainImage().getPath());
				Image i = article.getMainImage();
				String filename = this.newMainImage.getOriginalFilename();
				i.setPath(filename);
				WebUtils.saveFileTwice(this.newMainImage);
				imageDAO.update(i);
			}
			else //article hasn't yet got a main image, we need to create it
			{
				Image i = new Image();
				i.setType(Image.TYPE_MAIN);
				String filename = this.newMainImage.getOriginalFilename();
				i.setPath(filename);
				i.setArticle(article);
				WebUtils.saveFileTwice(this.newMainImage);
				imageDAO.create(i);
				article.addImage(i);
			}
		}
	}

	/**
	 * Parses the model categories.
	 * 
	 * @param article the target article
	 * @param categoryDAO the category dao
	 */
	private void parseModelCategories(Article article, CategoryDAO categoryDAO)
	{
		ArrayList<Category> categories = new ArrayList<Category>();
		if (this.categoriesId != null)
		{
			for (Long i : this.categoriesId)
			{
				Category c = (Category) categoryDAO.getById(i);
				if (c != null)
					categories.add(c);
			}
		}
		article.setCategories(categories);
	}

	/**
	 * Parses the model main data.
	 * 
	 * @param article the target article
	 */
	private void parseModelMain(Article article)
	{
		article.setDescription(this.description);
		article.setName(this.name);
		article.setAvailable(this.available);
	}
		
	/**
	 * Gets the new categories IDs.
	 * 
	 * @return the new categories IDs
	 */
	public Set<Long> getCategoriesId()
	{
		return categoriesId;
	}

	/**
	 * Sets the new categories IDs.
	 * 
	 * @param categoriesId the new categories IDs
	 */
	public void setCategoriesId(Set<Long> categoriesId)
	{
		this.categoriesId = categoriesId;
	}

	/**
	 * Gets the deleted images IDs.
	 * 
	 * @return the deleted images IDs
	 */
	public Set<Long> getDeletedImagesId()
	{
		return deletedImagesId;
	}

	/**
	 * Sets the deleted images IDs.
	 * 
	 * @param deletedImagesId
	 *            the new deleted images IDs
	 */
	public void setDeletedImagesId(Set<Long> deletedImagesId)
	{
		this.deletedImagesId = deletedImagesId;
	}

	/**
	 * Checks if is deleted main image.
	 * 
	 * @return true, if is deleted main image, otherwise false
	 */
	public boolean isDeletedMainImage()
	{
		return deletedMainImage;
	}

	/**
	 * Sets the deleted main image.
	 * 
	 * @param deletedMainImage the new deleted main image
	 */
	public void setDeletedMainImage(boolean deletedMainImage)
	{
		this.deletedMainImage = deletedMainImage;
	}

	/**
	 * Gets the amount of added images.
	 * 
	 * @return the amount of added images
	 */
	public int getAmountOfAddedImages()
	{
		return amountOfAddedImages;
	}

	/**
	 * Sets the amount of added images.
	 * 
	 * @param amountOfAddedImages the new amount of added images
	 */
	public void setAmountOfAddedImages(int amountOfAddedImages)
	{
		this.amountOfAddedImages = amountOfAddedImages;
	}

	/**
	 * Gets the new main image.
	 * 
	 * @return the new main image
	 */
	public CommonsMultipartFile getNewMainImage()
	{
		return newMainImage;
	}

	/**
	 * Sets the new main image.
	 * 
	 * @param newMainImage the new new main image
	 */
	public void setNewMainImage(CommonsMultipartFile newMainImage)
	{
		this.newMainImage = newMainImage;
	}

	/**
	 * Gets the new additional images.
	 * 
	 * @return the new additional images
	 */
	public List<CommonsMultipartFile> getNewAdditionalImages()
	{
		return newAdditionalImages;
	}

	/**
	 * Sets the new additional images.
	 * 
	 * @param newAdditionalImages the new new additional images
	 */
	public void setNewAdditionalImages(
			List<CommonsMultipartFile> newAdditionalImages)
	{
		this.newAdditionalImages = newAdditionalImages;
	}

	/**
	 * Gets the error.
	 * 
	 * @return the error
	 */
	public String getError()
	{
		return error;
	}

	/**
	 * Sets the error.
	 * 
	 * @param error
	 *            the new error
	 */
	public void setError(String error)
	{
		this.error = error;
	}

	/**
	 * Gets the success.
	 * 
	 * @return the success
	 */
	public String getSuccess()
	{
		return success;
	}

	/**
	 * Sets the success.
	 * 
	 * @param success
	 *            the new success
	 */
	public void setSuccess(String success)
	{
		this.success = success;
	}
	
	/**
	 * Gets the manufacturer id.
	 * 
	 * @return the manufacturer id
	 */
	public long getManufacturerId()
	{
		return this.manufacturerId;
	}
	
	/**
	 * Sets the manufacturer id
	 * 
	 * @param manufacturerId the manufacturer id
	 */
	public void setManufacturerId(long manufacturerId)
	{
		this.manufacturerId = manufacturerId;
	}
}
