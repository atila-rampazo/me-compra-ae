package br.com.zensolutions.MeCompraAe.unit.domains.entities.products;

import br.com.zensolutions.MeCompraAe.domains.entities.categories.Category;
import br.com.zensolutions.MeCompraAe.domains.entities.products.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ProductTest {

    @Test
    public void testPrePersist() {
        Product product = new Product();
        product.prePersist();

        LocalDateTime expectedCreatedAt = LocalDateTime.now(ZoneId.of("UTC")).minusHours(3);
        Assertions.assertEquals(expectedCreatedAt, product.getCreated_at());
    }

    @Test
    public void testPreUpdate() {
        Product product = new Product();
        product.preUpdate();

        LocalDateTime expectedUpdatedAt = LocalDateTime.now(ZoneId.of("UTC")).minusHours(3);
        Assertions.assertEquals(expectedUpdatedAt, product.getUpdated_at());
    }

    @Test
    public void testEntityAttributes(){
        Category category = new Category();
        category.setId(1L);
        category.setName("Informática");
        category.setStatus(true);

        Product product = new Product();
        product.setName("Teclado Target RGBA");
        product.setPrice(253.60);
        product.setCategoryId(category);
        product.setStatus(true);
        Assertions.assertTrue(product.isStatus());
        Assertions.assertEquals("Teclado Target RGBA",product.getName());
        Assertions.assertEquals("Informática",product.getCategory().getName());
    }
}
