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


@Entity
@Table(name="Role")
public class Role implements Serializable
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	long id;
	@Column(name="description")
	String description;
	@ManyToMany(cascade=CascadeType.PERSIST, targetEntity=MyUser.class)
	@JoinTable(name="user_role", joinColumns={@JoinColumn(name="roleId", referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(name="userId", referencedColumnName="id")})
	List<MyUser> users;
	
	public Role()
	{
		
	}
	
	public List<MyUser> getUsers()
	{
		return users;
	}
	public void setUsers(List<MyUser> users)
	{
		this.users = users;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
}
