package proyecto.gestoralmuerzo.service;

import proyecto.gestoralmuerzo.repository.IngredientRepository;
import proyecto.gestoralmuerzo.model.Ingredient;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import proyecto.gestoralmuerzo.exceptions.GestorAlmuerzosAppException;

import java.util.List;
import java.util.Optional;

import static proyecto.gestoralmuerzo.exceptions.GestorAlmuerzosAppException.ingredientinuse;

@Service
public class IngredientServices {

    private final IngredientRepository ingredientRepository;

    public IngredientServices(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

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
            throw new GestorAlmuerzosAppException(ingredientinuse);
        }
    }

    public Ingredient getIngredientByName(String name) {
        return ingredientRepository.findByName(name);
    }

    public List<Ingredient> getAllIngredientsByIds(List<Long> idsIngredients) {
        return ingredientRepository.findAllById(idsIngredients);
    }
}
