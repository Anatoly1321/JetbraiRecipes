package recipes.businessLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.persistance.RecipeRepository;

import java.util.Collections;
import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe findRecipeById(Long id) {

        return recipeRepository.findRecipeById(id).orElse(null);

    }

    public Recipe save(Recipe toSave) {
        return recipeRepository.save(toSave);
    }

    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    public List<Recipe> findRecipesByCategory(String category) {
        return recipeRepository.findRecipesByCategoryIgnoreCaseOrderByDateDesc(category).orElse(Collections.emptyList());
    }

    public List<Recipe> findRecipesByName(String name) {
        return recipeRepository.findRecipesByNameContainsIgnoreCaseOrderByDateDesc(name).orElse(Collections.emptyList());
    }
}
