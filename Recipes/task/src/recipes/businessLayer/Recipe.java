package recipes.businessLayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myExceptions.RecipeDoesNotExistException;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    @JsonIgnore
    private long id;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    private String description;

    @Column
    @NotBlank
    private String category;

    @Column
    private LocalDateTime date = LocalDateTime.now();

    @Column
    @ElementCollection
    @Size(min = 1)
    @NotNull
    private List<String> ingredients;

    @Column
    @ElementCollection
    @Size(min = 1)
    @NotNull
    private List<String> directions;

    @JsonIgnore
    private String owner;

}
