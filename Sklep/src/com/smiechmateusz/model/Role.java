package com.smiechmateusz.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * Model for role containing data like users or type.
 * 
 * @author Śmiech Mateusz
 */
@Entity
@Table(name="Role")
public class Role implements Serializable
{
	
	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	long id;
	
	/** The description. */
	@Column(name="description")
	String description;
	
	/** The users. */
	@ManyToMany(cascade=CascadeType.PERSIST, targetEntity=MyUser.class)
	@JoinTable(name="user_role", joinColumns={@JoinColumn(name="roleId", referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(name="userId", referencedColumnName="id")})
	List<MyUser> users;
	
	/**
	 * Instantiates a new role.
	 */
	public Role()
	{
		
	}
	
	/**
	 * Gets the users.
	 * 
	 * @return the users
	 */
	public List<MyUser> getUsers()
	{
		return users;
	}
	
	/**
	 * Sets the users.
	 * 
	 * @param users the new users
	 */
	public void setUsers(List<MyUser> users)
	{
		this.users = users;
	}
	
	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public long getId()
	{
		return id;
	}
	
	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(long id)
	{
		this.id = id;
	}
	
	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Sets the description.
	 * 
	 * @param description the new description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
}
