package proyecto.gestoralmuerzo.repositoryrepositoryservice;

import proyecto.gestoralmuerzo.repositoryrepositoryrepository.IngredientRepository;
import proyecto.gestoralmuerzo.repositoryrepositorymodel.Ingredient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.gestoralmuerzo.repositoryrepositoryexceptions.GestorAlmuerzosAppException;

import java.util.List;
import java.util.Optional;

import static proyecto.gestoralmuerzo.repositoryrepositoryexceptions.GestorAlmuerzosAppException.IngredientInUse;

@Service
public class IngredientServices {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
        return ingredient;
    }

    public Optional<Ingredient> getIngredientById(long ingredientId) {
        return ingredientRepository.findById(ingredientId);
    }

    @Transactional
    public void updateIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    @Transactional
    public void deleteIngredient(Long ingredientId) throws GestorAlmuerzosAppException {
        Ingredient ingredient = ingredientRepository.getReferenceById(ingredientId);
        if(ingredient.getPlates().isEmpty()){
            ingredientRepository.deleteById(ingredientId);
        } else {
            throw new GestorAlmuerzosAppException(IngredientInUse);
        }
    }

    public Ingredient getIngredientByName(String name) {
        return ingredientRepository.findByName(name);
    }

    public List<Ingredient> getAllIngredientsByIds(List<Long> idsIngredients) {
        return ingredientRepository.findAllById(idsIngredients);
    }
}
