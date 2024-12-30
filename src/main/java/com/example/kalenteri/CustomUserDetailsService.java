package com.example.kalenteri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Hae käyttäjä suoraan UserRepositorysta
        User user = userRepository.findByUsername(username);

        // Jos käyttäjä ei löydy, heitetään poikkeus
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // Palauta UserDetails-olio
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")  // Voit lisätä rooleja tai muita tietoja, jos tarpeen
                .build();
    }
}


