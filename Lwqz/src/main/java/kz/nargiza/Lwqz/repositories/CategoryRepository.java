package kz.nargiza.Lwqz.repositories;

import kz.nargiza.Lwqz.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findCategoryById(Long  id);

    Optional<Category> findCategoryByName(String name);

}
