package com.smiechmateusz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.smiechmateusz.dao.ArticleDAO;
import com.smiechmateusz.dao.CategoryDAO;
import com.smiechmateusz.dao.ImageDAO;
import com.smiechmateusz.utils.WebUtils;

public class ArticleFormModel extends Article
{
	@Transient
	private Set<Long> categoriesId;
	@Transient
	private Set<Long> deletedImagesId;
	@Transient
	private boolean deletedMainImage;
	@Transient
	private int amountOfAddedImages;
	@Transient
	private CommonsMultipartFile newMainImage;
	@Transient
	private List<CommonsMultipartFile> newAdditionalImages;
	
	public ArticleFormModel()
	{
		
	}
	
	public ArticleFormModel(int i) {
		
	}
	
	public ArticleFormModel(Article a)
	{
		this.id = a.getId();
		this.images = a.getImages();
		this.description = a.getDescription();
		this.categories = a.getCategories();
		this.addDate = a.getAddDate();
		this.name = a.getName();
		this.available = a.isAvailable();
	}
	
	public void parseModel(Article article, ArticleDAO articleDAO, CategoryDAO categoryDAO, ImageDAO imageDAO)
	{
		parseModelMain(article);
		parseModelCategories(article, categoryDAO);
		parseModelMainImage(article, imageDAO);
		parseModelDeletedImages(article, imageDAO);
		parseModelNewImages(article, imageDAO);
		articleDAO.update(article);
	}

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

	private void parseModelDeletedImages(Article article, ImageDAO imageDAO)
	{
		if (this.deletedImagesId != null)
		{
			article.deleteImagesById(this.deletedImagesId);
			for (Long l : this.deletedImagesId)
				imageDAO.deleteById(l);
		}
	}

	private void parseModelMainImage(Article article, ImageDAO imageDAO)
	{
		if (this.deletedMainImage && this.newMainImage != null)
		{
			if (article.getMainImage() != null)
			{
				WebUtils.removeFileTwice(article.getMainImage().getPath());
				Image i = article.getMainImage();
				String filename = this.newMainImage.getOriginalFilename();
				i.setPath(filename);
				WebUtils.saveFileTwice(this.newMainImage);
				imageDAO.update(i);
			}
			else
			{
				Image i = new Image();
				i.setType(1);
				String filename = this.newMainImage.getOriginalFilename();
				i.setPath(filename);
				i.setArticle(article);
				WebUtils.saveFileTwice(this.newMainImage);
				imageDAO.create(i);
				article.addImage(i);
			}
		}
	}

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

	private void parseModelMain(Article article)
	{
		article.setDescription(this.description);
		article.setName(this.name);
		article.setAvailable(this.available);
	}
		
	public Set<Long> getCategoriesId()
	{
		return categoriesId;
	}

	public void setCategoriesId(Set<Long> categoriesId)
	{
		this.categoriesId = categoriesId;
	}

	public Set<Long> getDeletedImagesId()
	{
		return deletedImagesId;
	}

	public void setDeletedImagesId(Set<Long> deletedImagesId)
	{
		this.deletedImagesId = deletedImagesId;
	}

	public boolean isDeletedMainImage()
	{
		return deletedMainImage;
	}

	public void setDeletedMainImage(boolean deletedMainImage)
	{
		this.deletedMainImage = deletedMainImage;
	}

	public int getAmountOfAddedImages()
	{
		return amountOfAddedImages;
	}

	public void setAmountOfAddedImages(int amountOfAddedImages)
	{
		this.amountOfAddedImages = amountOfAddedImages;
	}

	public CommonsMultipartFile getNewMainImage()
	{
		return newMainImage;
	}

	public void setNewMainImage(CommonsMultipartFile newMainImage)
	{
		this.newMainImage = newMainImage;
	}

	public List<CommonsMultipartFile> getNewAdditionalImages()
	{
		return newAdditionalImages;
	}

	public void setNewAdditionalImages(
			List<CommonsMultipartFile> newAdditionalImages)
	{
		this.newAdditionalImages = newAdditionalImages;
	}
}
