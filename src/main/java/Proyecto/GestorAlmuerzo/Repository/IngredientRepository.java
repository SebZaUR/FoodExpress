package proyecto.gestoralmuerzo.repositoryrepositoryrepository;

import proyecto.gestoralmuerzo.repositoryrepositorymodel.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Ingredient findByName(String name);

}

