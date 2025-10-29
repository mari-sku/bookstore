package com.example.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

// @DataJpaTest     ei toimit tällä
@SpringBootTest
public class CategoryRepositoryTests {

@Autowired
private CategoryRepository categoryRepository;

// CREATE

@Test
    public void createNewCategory() {
        Category category = new Category("Sci-Fi");
       Category saved = categoryRepository.save(category);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Sci-Fi");
    }
// FIND
    @Test
    public void findCategoryByIdShouldReturnCategory() {
        Category category = new Category("Fantasy");
        Category found = categoryRepository.save(category);
        Optional<Category> foundCategory = categoryRepository.findById(found.getId());   // optional, koska kategoria saattaa tai ei saata löytyä = ei nullpointerexception
        assertThat(foundCategory).isPresent();
        assertThat(foundCategory.get().getName()).isEqualTo("Fantasy");
    }
// DELETE
    @Test
    public void deleteCategoryShouldRemoveItFromRepository() {
        Category category = new Category("Biography");
        Category delete = categoryRepository.save(category);
        Long id = delete.getId();
        categoryRepository.deleteById(id);
        Optional<Category> deletedCategory = categoryRepository.findById(id);       // optional, koska kategoria saattaa tai ei saata löytyä = ei nullpointerexception
        assertThat(deletedCategory).isEmpty();
    }
}
