package com.example.kalenteri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Inject the UserRepository

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Method to save a new user with an encoded password
    public void registerUser(User user) {
        // Encrypt the user's password before saving
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user); // Save user to the database
    }

    // Method to check user credentials
    public boolean checkPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return bCryptPasswordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}

