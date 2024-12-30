package com.example.kalenteri;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Lisätään metodi, joka hakee käyttäjän käyttäjänimen perusteella
    User findByUsername(String username);
}

