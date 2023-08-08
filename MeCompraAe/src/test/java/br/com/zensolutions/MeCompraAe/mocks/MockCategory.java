package br.com.zensolutions.MeCompraAe.mocks;

import br.com.zensolutions.MeCompraAe.domains.entities.categories.Category;

import java.util.ArrayList;
import java.util.List;

public class MockCategory {

    public Category mockEntity() {
        return mockEntity(1);
    }

    public Category mockEntity(Integer number) {
        Category category = new Category();
        category.setId(number.longValue());
        category.setName("Category " + number);
        category.setStatus(true);
        return category;
    }

    public List<Category> mockEntityList() {
        List<Category> categories = new ArrayList<Category>();
        for (int i = 0; i < 14; i++) {
            categories.add(mockEntity(i));
        }
        return categories;
    }
}
