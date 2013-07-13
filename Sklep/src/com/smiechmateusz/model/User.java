package com.smiechmateusz.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="User")
public class User implements Serializable
{
	@Id
	@GeneratedValue
	@Column(name="id")
	long id;
	@Column(name="username")
	String username;
	@Column(name="passwordHash")
	String passwordHash;
	@Column(name="email")
	String email;
	@Transient	
	List<Transaction> transactions;
	@ManyToMany(cascade=CascadeType.ALL, targetEntity=Role.class)
	@JoinTable(name="user_role", joinColumns={@JoinColumn(name="userId", referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(name="roleId", referencedColumnName="id")})
	List<Role> role;
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPasswordHash()
	{
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash)
	{
		this.passwordHash = passwordHash;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public List<Transaction> getTransactions()
	{
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions)
	{
		this.transactions = transactions;
	}
	public List<Role> getRole()
	{
		return role;
	}
	public void setRole(List<Role> role)
	{
		this.role = role;
	}
}
