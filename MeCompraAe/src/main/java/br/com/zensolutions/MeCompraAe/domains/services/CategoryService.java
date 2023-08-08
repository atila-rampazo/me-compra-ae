package br.com.zensolutions.MeCompraAe.domains.services;

import br.com.zensolutions.MeCompraAe.domains.entities.categories.Category;
import br.com.zensolutions.MeCompraAe.domains.exceptions.RequiredObjectIsNullException;
import br.com.zensolutions.MeCompraAe.domains.exceptions.ResourceNotFoundException;
import br.com.zensolutions.MeCompraAe.domains.mapper.DozerMapper;
import br.com.zensolutions.MeCompraAe.domains.records.category.CategoryRecord;
import br.com.zensolutions.MeCompraAe.domains.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("No record founded for this reference")
                );
    }

    public Category create(Category category) {
        if (category == null) throw new RequiredObjectIsNullException();

        return repository.save(category);
    }

    public Category update(Long id,Category category) {
        if (category == null) throw new RequiredObjectIsNullException();
        var entity = findById(id);
        entity.setName(category.getName());
        entity.setStatus(category.getStatus());

        return repository.save(entity);
    }

    public void delete(Long id) {
        var entity = findById(id);
        repository.delete(entity);
    }
}
