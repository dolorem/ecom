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

/**
 * Model for user, containing data like username, password hash, transactions and previliges.
 * 
 * @author Śmiech Mateusz
 */
@Entity
@Table(name="User")
public class MyUser implements Serializable
{
	
	/** The id. */
	@Id
	@GeneratedValue
	@Column(name="id")
	long id;
	
	/** The username. */
	@Column(name="username")
	String username;
	
	/** The password hash. */
	@Column(name="passwordHash")
	String passwordHash;
	
	/** The email. */
	@Column(name="email")
	String email;
	
	/** The transactions. */
	@Transient	
	List<Transaction> transactions;
	
	/** The role. */
	@ManyToMany(cascade=CascadeType.PERSIST, targetEntity=Role.class)
	@JoinTable(name="user_role", joinColumns={@JoinColumn(name="userId", referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(name="roleId", referencedColumnName="id")})
	List<Role> role;
	
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
	 * Gets the username.
	 * 
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}
	
	/**
	 * Sets the username.
	 * 
	 * @param username the new username
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	/**
	 * Gets the password hash.
	 * 
	 * @return the password hash
	 */
	public String getPasswordHash()
	{
		return passwordHash;
	}
	
	/**
	 * Sets the password hash.
	 * 
	 * @param passwordHash the new password hash
	 */
	public void setPasswordHash(String passwordHash)
	{
		this.passwordHash = passwordHash;
	}
	
	/**
	 * Gets the email.
	 * 
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 * Sets the email.
	 * 
	 * @param email the new email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	/**
	 * Gets the transactions.
	 * 
	 * @return the transactions
	 */
	public List<Transaction> getTransactions()
	{
		return transactions;
	}
	
	/**
	 * Sets the transactions.
	 * 
	 * @param transactions the new transactions
	 */
	public void setTransactions(List<Transaction> transactions)
	{
		this.transactions = transactions;
	}
	
	/**
	 * Gets the role.
	 * 
	 * @return the role
	 */
	public List<Role> getRole()
	{
		return role;
	}
	
	/**
	 * Sets the role.
	 * 
	 * @param role the new role
	 */
	public void setRole(List<Role> role)
	{
		this.role = role;
	}
}
