package br.com.zensolutions.MeCompraAe.unit.domains.entities.categories;

import br.com.zensolutions.MeCompraAe.domains.entities.categories.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class CategoryTest {
    @Test
    public void testPrePersist() {
        Category category = new Category();
        category.prePersist();

        LocalDateTime expectedCreatedAt = LocalDateTime.now(ZoneId.of("UTC")).minusHours(3);
        Assertions.assertEquals(expectedCreatedAt, category.getCreated_at());
    }

    @Test
    public void testPreUpdate() {
        Category category = new Category();
        category.preUpdate();

        LocalDateTime expectedUpdatedAt = LocalDateTime.now(ZoneId.of("UTC")).minusHours(3);
        Assertions.assertEquals(expectedUpdatedAt, category.getUpdated_at());
    }
    @Test
    public void testEntityAttributes(){
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Category 1");
        category1.setStatus(true);
        category1.preUpdate();
        category1.prePersist();
        Assertions.assertEquals("Category 1",category1.getName());
        Assertions.assertTrue(category1.getStatus());


    }
    @Test
    public void testEqualsAndHashCode() {
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Category 1");
        category1.setStatus(true);

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Category 1");
        category2.setStatus(true);

        Category category3 = new Category();
        category3.setId(2L);
        category3.setName("Category 2");
        category3.setStatus(false);

        Assertions.assertEquals(category1, category2);
        Assertions.assertEquals(category1.hashCode(), category2.hashCode());

        Assertions.assertNotEquals(category1, category3);
        Assertions.assertNotEquals(category1.hashCode(), category3.hashCode());
    }
}
