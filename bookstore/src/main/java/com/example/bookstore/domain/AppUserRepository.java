package com.example.bookstore.domain;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    // hakee käyttäjän käyttäjänimen perusteella. tulee hyödylliseksi Spring Securitylle esim. autentikoinnissa
 AppUser findByUsername(String username);
}
