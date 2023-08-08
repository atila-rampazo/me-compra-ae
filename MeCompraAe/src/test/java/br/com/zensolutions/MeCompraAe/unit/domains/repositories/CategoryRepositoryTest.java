package br.com.zensolutions.MeCompraAe.unit.domains.repositories;

import br.com.zensolutions.MeCompraAe.domains.entities.categories.Category;
import br.com.zensolutions.MeCompraAe.domains.repositories.CategoryRepository;
import br.com.zensolutions.MeCompraAe.mocks.MockCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class CategoryRepositoryTest {
    MockCategory input;

    @Mock
    CategoryRepository repository;

    @BeforeEach
    void setUp() throws Exception {
        input = new MockCategory();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Category> categories = input.mockEntityList();
        when(repository.findAll()).thenReturn(categories);

        var categoriesList = repository.findAll();

        assertNotNull(categoriesList);
        assertEquals(14, categoriesList.size());
    }

    @Test
    void testFindById() {
        Category category = input.mockEntity(1);
        category.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(category));
        var result = repository.findById(1L);

        assertNotNull(result);
        assertEquals("Category 1", result.get().getName());
        assertTrue(result.get().getStatus());
    }

    @Test
    void testCreate() {
        Category category = input.mockEntity(1);
        Category persisted = category;
        persisted.setId(1L);
        when(repository.save(category)).thenReturn(persisted);
        var result = repository.save(category);

        assertNotNull(result);
        assertEquals("Category 1", result.getName());
        assertTrue(result.getStatus());
    }

    @Test
    void testUpdate() {
        Category category = input.mockEntity(1);
        category.setId(1L);
        Category persisted = category;
        persisted.setId(1L);
        when(repository.save(category)).thenReturn(persisted);
        var result = repository.save(category);
        assertNotNull(result);
        assertEquals("Category 1", result.getName());
        assertTrue(result.getStatus());
    }

    @Test
    void testDelete() {
        Category category = input.mockEntity(1);
        category.setId(1L);
        repository.delete(category);
    }
}
