package recipes.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.Model.Recipe;
import recipes.RecipesApplication;

@Service
public class RecipeService {
  private Recipe recipe;

  @Autowired
  public RecipeService(Recipe recipe){
    this.recipe = recipe;
  }

  public void setRecipe(Recipe recipe) {
   this.recipe = recipe;
  }

  public Recipe getRecipe(){
    return this.recipe;
  }

}
