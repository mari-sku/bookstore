package com.example.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.example.bookstore.domain.AppUser;
import com.example.bookstore.domain.AppUserRepository;

@SpringBootTest
public class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    // CREATE
    @Test
    public void createNewUser() {
        AppUser user = new AppUser("testi", "testi", "USER");
        appUserRepository.save(user);
        assertThat(user.getId()).isNotNull();
    }

    // SEARCH
    @Test
    public void findByUsernameShouldReturnUser() {
        AppUser user = new AppUser("testi1", "testi1", "USER");
        appUserRepository.save(user);
        AppUser found = appUserRepository.findByUsername("testi1");
        assertThat(found).isNotNull();
        assertThat(found.getUsername()).isEqualTo("testi1");
        assertThat(found.getRole()).isEqualTo("USER");
    }

    // DELETE
    @Test
    public void deleteUserShouldRemoveFromRepository() {
        AppUser user = new AppUser("testi2", "testi2", "USER");
        appUserRepository.save(user);
        Long id = user.getId();
        appUserRepository.deleteById(id);
        assertThat(appUserRepository.findById(id)).isEmpty();
    }
}

