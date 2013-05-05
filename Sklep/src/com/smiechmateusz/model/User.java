package com.smiechmateusz.model;

import java.util.List;

public class User 
{
	long id;
	String username;
	String passwordHash;
	String email;
	List<Transaction> transactions;
	Role role;
}
