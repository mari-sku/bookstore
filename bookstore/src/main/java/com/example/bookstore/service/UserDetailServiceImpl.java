package com.example.bookstore.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import com.example.bookstore.domain.AppUser;
import com.example.bookstore.domain.AppUserRepository;

// tämä luokka yhddistää tietokannan Spring Securityyn: kun joku yrittää kirjautua, Spring Security kutsuu metodia loadUserByUsername()
// metodi hakee AppUserRepositorysta käyttäjän ja palauttaa Spring Securityn User-olion (EI AppUser!!), jolla on attribuutit username, passwordHash ja role autentikointia varten

@Service
public class UserDetailServiceImpl implements UserDetailsService  {

	private final AppUserRepository repository;

	public UserDetailServiceImpl(AppUserRepository userRepository) {
		this.repository = userRepository;
	}
	@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AppUser curruser = repository.findByUsername(username);
    if (curruser == null) {
        throw new UsernameNotFoundException("User not found: " + username);
    }
    return new org.springframework.security.core.userdetails.User(
        curruser.getUsername(),
        curruser.getPasswordHash(),
        AuthorityUtils.createAuthorityList(curruser.getRole())
    );
}

}