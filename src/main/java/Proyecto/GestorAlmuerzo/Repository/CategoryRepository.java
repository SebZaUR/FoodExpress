package proyecto.gestoralmuerzo.repository;

import proyecto.gestoralmuerzo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String categoryName);
}
