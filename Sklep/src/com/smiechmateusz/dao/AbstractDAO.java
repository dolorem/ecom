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

/**
 * Abstract DAO containing default implementations of DAO operations.
 * 
 * @param <T> type of the serialized item
 * @author Śmiech Mateusz
 */
@Transactional
public abstract class AbstractDAO<T extends Serializable> 
{
	
	/** Class of the serialized item. */
	private final Class<T> clazz;
    
    /** The session factory. */
    @Autowired
    SessionFactory sessionFactory;
    
    /** The static session shared between multiple DAO instances. */
    public static Session fooSession = null;

    /**
	 * Instantiates a new abstract dao.
	 * 
	 * @param clazzToSet type of the serialized item
	 */
    public AbstractDAO(final Class< T> clazzToSet) 
    {
        this.clazz = clazzToSet;
    }

    /**
	 * Gets item by its ID.
	 * 
	 * @param id the ID of the item to retrieve
	 * @return item of given ID, null if it doesn't exist
	 */
    public T getById(final Long id) 
    {
        if (id != null)
        	return (T) this.getCurrentSession().get(this.clazz, id);
        return null;
    }

    /**
	 * Gets all items of given type.
	 * 
	 * @return list of all items
	 */
    public List<T> getAll() 
    {
        return this.getCurrentSession().createQuery("from " + this.clazz.getName()).list();
    }

    /**
	 * Creates (persists) the item to the database.
	 * 
	 * @param entity the entity to be persisted
	 */
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

    /**
	 * Updates the item to the database.
	 * 
	 * @param entity the entity to be updated
	 */
    public void update(final T entity) 
    {
        if (entity != null)
        {
        	try
        	{
        		this.getCurrentSession().merge(entity);
        		this.getCurrentSession().flush();
        	}
        	catch (Exception e)
        	{
        		e.printStackTrace();
        	}
        }
    }

    /**
	 * Deletes the item.
	 * 
	 * @param entity the entity to be deleted
	 */
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
    	}
    	
    }

    /**
	 * Deletes entity by its id.
	 * 
	 * @param entityId the ID of the entity to delete
	 */
    public void deleteById(final Long entityId) 
    {
        final T entity = this.getById(entityId);
        if (entity != null)
        	this.delete(entity);
    }

    /**
	 * Sets the session factory.
	 * 
	 * @param sessionFactory the new session factory
	 */
    public void setSessionFactory(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
    }

    /**
	 * Gets the current session based on static fooSession.
	 * 
	 * @return the current session
	 */
    protected final Session getCurrentSession() 
    {
    	if (AbstractDAO.fooSession == null)
    		AbstractDAO.fooSession = this.sessionFactory.openSession();
    	return AbstractDAO.fooSession;
    }
    
    /**
	 * Gets all items matching given criteria.
	 * 
	 * @param criteria the criteria
	 * @return the list of the items matching given criteria
	 */
    public List<T> getWithCriteria(List<Criterion> criteria)
    {
    	Criteria c = this.getCurrentSession().createCriteria(this.clazz);
    	for (Criterion cr : criteria)
    	{
    		c.add(cr);
    	}
    	return c.list();
    }
    
    /**
     * Gets criteria for further database process.
     * 
     * @return criteria
     */
	public Criteria getCriteria()
	{
		return getCurrentSession().createCriteria(this.clazz);
	}
}