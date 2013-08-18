package com.smiechmateusz.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.smiechmateusz.dao.UserDAO;
import com.smiechmateusz.model.MyUser;
import com.smiechmateusz.model.Role;

/**
 * An UserDetailsService implementation handling login and previliges.
 * 
 * @author Śmiech Mateusz
 */
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	/** The user dao. */
	@Autowired
	UserDAO userDAO;

	/**
	 * Loads the user from given username.
	 * 
	 * @param username the username of the user to retrieve
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception if they don't exist
	 * @throws DataAccessException the data access exception 
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException 
	{
		UserDetails user = null;
		try 
		{
			MyUser dbUser = userDAO.findByUsername(username);
			user =  new User(dbUser.getUsername(), dbUser.getPasswordHash().toLowerCase(),
						true, true,	true, true, getUserPreviliges(dbUser.getRole()));
		} 
		catch (Exception e) 
		{
			throw new UsernameNotFoundException("Error in retrieving user");
		}
		return user;
	}

	/**
	 * Gets the collection of roles of type GrantedAuthority from list of Role models.
	 * 
	 * @param roles the list of role models
	 * @return collection of granted previliges
	 */
	public Collection<GrantedAuthority> getUserPreviliges(List<Role> roles) 
	{
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>(2);
		auth.add(new GrantedAuthorityImpl("ROLE_USER"));
		for (Role r : roles)
		{
			if (r.getDescription().equals("admin") || r.getDescription().equals("administrator"))
				auth.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		}
		return auth;
	}
}