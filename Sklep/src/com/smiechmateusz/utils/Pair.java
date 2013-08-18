package com.smiechmateusz.utils;

/**
 * The Class Pair.
 * 
 * @param <T> the generic type
 * @param <U> the generic type
 */
public class Pair<T, U>
{
	
	/** The left. */
	private T left;
	
	/** The right. */
	private U right;
	
	/**
	 * Instantiates a new pair.
	 * 
	 * @param left
	 *            the left
	 * @param right
	 *            the right
	 */
	public Pair(T left, U right)
	{
		this.left = left;
		this.right = right;
	}

	/**
	 * Gets the left.
	 * 
	 * @return the left
	 */
	public T getLeft()
	{
		return left;
	}

	/**
	 * Sets the left.
	 * 
	 * @param left
	 *            the new left
	 */
	public void setLeft(T left)
	{
		this.left = left;
	}

	/**
	 * Gets the right.
	 * 
	 * @return the right
	 */
	public U getRight()
	{
		return right;
	}

	/**
	 * Sets the right.
	 * 
	 * @param right
	 *            the new right
	 */
	public void setRight(U right)
	{
		this.right = right;
	}
}
