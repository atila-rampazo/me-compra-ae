package br.com.zensolutions.MeCompraAe.unit.domains.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import br.com.zensolutions.MeCompraAe.domains.entities.categories.Category;
import br.com.zensolutions.MeCompraAe.domains.exceptions.RequiredObjectIsNullException;
import br.com.zensolutions.MeCompraAe.domains.mapper.DozerMapper;
import br.com.zensolutions.MeCompraAe.domains.repositories.CategoryRepository;
import br.com.zensolutions.MeCompraAe.domains.services.CategoryService;
import br.com.zensolutions.MeCompraAe.mocks.MockCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

public class CategoryServiceTest {
    MockCategory input;
    @InjectMocks
    private CategoryService service;
    @Mock
    CategoryRepository repository;

    @BeforeEach
    void setUp() throws  Exception {
        input = new MockCategory();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllCategories(){
        List<Category> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        var categories = service.findAll();

        assertNotNull(categories);
        assertEquals(14,categories.size());
        var category = categories.get(1);
        assertNotNull(category);
        assertNotNull(category.getId());
        assertEquals("Category 1",category.getName());
    }

    @Test
    void testFindById(){
        Category category = input.mockEntity(1);
        when(repository.findById(1L)).thenReturn(Optional.of(category));
        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Category 1",result.getName());
    }

    @Test
    void testCreate(){
        Category category = input.mockEntity(1);
        Category persisted = category;
        persisted.setId(1L);
        when(repository.save(category)).thenReturn(persisted);
        var result = service.create(category);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Category 1",result.getName());
    }

    @Test
    void testUpdate(){
        Category entity = input.mockEntity(1);
        entity.setId(1L);
        Category persisted = entity;
        persisted.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        Category category = input.mockEntity(1);
        category.setName("Informática");
        var result = service.update(category.getId(),category);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Informática",result.getName());
    }
    @Test
    void testDelete() {
        Category entity = input.mockEntity(1);
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        service.delete(1L);
    }

    @Test
    void testCreateWithNullCategory(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });
        String expectMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectMessage));
    }
}
