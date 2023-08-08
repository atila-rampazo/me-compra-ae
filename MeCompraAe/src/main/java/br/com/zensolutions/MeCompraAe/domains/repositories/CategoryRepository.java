package br.com.zensolutions.MeCompraAe.domains.repositories;

import br.com.zensolutions.MeCompraAe.domains.entities.categories.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
