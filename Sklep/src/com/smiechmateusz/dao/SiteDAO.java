package com.smiechmateusz.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.smiechmateusz.model.Site;

public class SiteDAO extends AbstractDAO
{

	public SiteDAO()
	{
		super(Site.class);
	}
	
	public Site getWithPath(String path)
	{
		Criteria c = this.getCriteria();
		c.add(Restrictions.like("path", path));
		List<Site> list = c.list();
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
}
