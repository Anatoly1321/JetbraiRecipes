package recipes.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import myExceptions.*;
import recipes.Responce;
import recipes.businessLayer.Recipe;
import recipes.businessLayer.RecipeService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
public class RecipesController {


    @Autowired
    RecipeService recipeService;

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipeById(@PathVariable Long id) {
        if (recipeService.findRecipeById(id) == null) {
            throw new RecipeDoesNotExistException(id.toString());
        }

        return recipeService.findRecipeById(id);

    }

    @GetMapping("/api/recipe/search")
    public List<Recipe> getRecipesByCategoryOrName(@RequestParam(required = false) String category,
                                                   @RequestParam(required = false) String name) {

        if ((category == null && name == null) || (category != null && name != null)
        || (category != null && category.length() < 1) || (name != null && name.length() < 1)) {
            throw new WrongInputDataException();
        }

        if (category != null) {
            return recipeService.findRecipesByCategory(category);
        } else {
            return recipeService.findRecipesByName(name);
        }
    }

    @PostMapping(value = "/api/recipe/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public Responce postRecipe(@Valid @RequestBody Recipe newRecipe,
                               @AuthenticationPrincipal UserDetails details) {

        newRecipe.setOwner(details.getUsername());
        recipeService.save(newRecipe);
        return new Responce(newRecipe.getId());
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id,
                                               @AuthenticationPrincipal UserDetails details) {
        Recipe oldRecipe = recipeService.findRecipeById(id);

        if (oldRecipe == null) {
            throw new RecipeDoesNotExistException(id.toString());
        } else {
            if (!oldRecipe.getOwner().equals(details.getUsername())) {
                throw new PermissionDeniedException(id.toString());
            }
        }

        recipeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<String>  putRecipe(@Valid @RequestBody Recipe recipeForUpdate, @PathVariable @NotNull Long id,
                                             @AuthenticationPrincipal UserDetails details) {

        Recipe oldRecipe = recipeService.findRecipeById(id);

        if (oldRecipe == null) {
            throw new RecipeDoesNotExistException(id.toString());
        } else {
            if (!oldRecipe.getOwner().equals(details.getUsername())) {
                throw new PermissionDeniedException(id.toString());
            }
        }

        recipeForUpdate.setId(id);
        recipeForUpdate.setOwner(details.getUsername());
        recipeService.save(recipeForUpdate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
