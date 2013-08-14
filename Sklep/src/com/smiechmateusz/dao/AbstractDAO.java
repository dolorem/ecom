package com.smiechmateusz.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractDAO<T extends Serializable> 
{
	private final Class<T> clazz;
    @Autowired
    SessionFactory sessionFactory;
    public static Session fooSession = null;

    public AbstractDAO(final Class< T> clazzToSet) 
    {
        this.clazz = clazzToSet;
    }

    public T getById(final Long id) 
    {
        if (id != null)
        	return (T) this.getCurrentSession().get(this.clazz, id);
        return null;
    }

    public List<T> getAll() 
    {
        return this.getCurrentSession().createQuery("from " + this.clazz.getName()).list();
    }

    public void create(final T entity) 
    {
    	boolean persisted = false;
        if (entity != null)
        {
	        try
	        {
	        	this.getCurrentSession().persist(entity);
	        	this.getCurrentSession().save(entity);
	        	this.getCurrentSession().flush();
	        	persisted = true;
	        }
	        catch (PersistentObjectException e)
	        {
	        	try
	        	{
	        		if (!persisted)
	        		{
	        			this.getCurrentSession().saveOrUpdate(entity);
	        			this.getCurrentSession().flush();
	        		}
	        	}
	        	catch(Exception e2)
	        	{
	        		e2.printStackTrace();
	        	}
	        	e.printStackTrace();
	        }
	        this.getCurrentSession().flush();
        }
    }

    public void update(final T entity) 
    {
        if (entity != null)
        {
        	try
        	{
        		this.getCurrentSession().merge(entity);
        		//this.getCurrentSession().update(entity);
        		this.getCurrentSession().flush();
        	}
        	catch (Exception e)
        	{
        		e.printStackTrace();
        	}
        }
    }

    public void delete(final T entity) 
    {
    	try 
        {
    		if (entity != null)
            	this.getCurrentSession().delete(entity);
        	this.getCurrentSession().flush();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    	try
    	{
    		this.getCurrentSession().flush();
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
//    		delete(entity);
    	}
    	
    }

    public void deleteById(final Long entityId) 
    {
        final T entity = this.getById(entityId);
        if (entity != null)
        	this.delete(entity);
    }

    public void setSessionFactory(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
    }

    protected final Session getCurrentSession() 
    {
//        return this.sessionFactory.getCurrentSession();
    	if (AbstractDAO.fooSession == null)
    	
    		AbstractDAO.fooSession = this.sessionFactory.openSession();
    	
//    		AbstractDAO.fooSession = this.sessionFactory.getCurrentSession();
    	return AbstractDAO.fooSession;
    }
    
    public List<T> getWithCriteria(List<Criterion> criteria)
    {
    	Criteria c = this.getCurrentSession().createCriteria(this.clazz);
    	for (Criterion cr : criteria)
    	{
    		c.add(cr);
    	}
    	return c.list();
    }
}