package com.smiechmateusz.model;

import java.util.List;

public class Category 
{
	long id;
	Category parent;
	String name;
	List<Article> articles;
}
