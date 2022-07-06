package recipes.persistance;

import org.springframework.data.repository.CrudRepository;
import recipes.businessLayer.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    Optional<Recipe> findRecipeById(Long id);
    Optional<List<Recipe>> findRecipesByNameContainsIgnoreCaseOrderByDateDesc(String category);
    Optional<List<Recipe>> findRecipesByCategoryIgnoreCaseOrderByDateDesc(String name);
}
