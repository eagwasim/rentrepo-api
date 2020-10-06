package com.noubug.rentrepo.infrastructure.web.security;

import com.noubug.rentrepo.infrastructure.persistence.repositories.UserEntityRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("CustomUserDetailsService")
public class SpringUserDetailsServiceImpl implements UserDetailsService {
    private final UserEntityRepository userEntityRepository;

    public SpringUserDetailsServiceImpl(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userEntityRepository.findFirstByEmail(username.toLowerCase().trim()).orElseThrow(() -> new UsernameNotFoundException("Email: " + username + " not found"));
    }
}
