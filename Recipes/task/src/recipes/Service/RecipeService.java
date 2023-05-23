package recipes.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.Exception.NotFound;
import recipes.Model.Recipe;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

  private static int nextId = 0;
  private List<Recipe> recipes;
  @Autowired
  public RecipeService(){
    this.recipes = new ArrayList<>();
  }

  public int createRecipe(Recipe recipe) {
    recipe.setId(++nextId);
    recipes.add(recipe);
    return recipe.getId();
  }

  public Recipe findById(int id){
    return recipes.parallelStream().filter(recipe -> recipe.getId() == id).findFirst().orElse(null);
  }

}
