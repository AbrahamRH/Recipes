package recipes.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recipes.Model.Recipe;
import recipes.RecipesApplication;
import recipes.Service.RecipeService;

@RestController
public class RecipeController {
  private final RecipeService service;
  Logger log = LoggerFactory.getLogger(RecipesApplication.class);

  @Autowired
  public RecipeController(RecipeService service) {
    this.service = service;
  }
  @PostMapping(path="/api/recipe")
  public ResponseEntity<String> addRecipe(@RequestBody Recipe recipe){
    try {
      service.setRecipe(recipe);
      return ResponseEntity.ok().body("");
    } catch (Exception ex) {
      log.error("[POST] /api/recipe: - {}", ex.getMessage());
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping(path="/api/recipe")
  public ResponseEntity<Recipe> getRecipe(){
    try {
      return ResponseEntity.ok().body(service.getRecipe());
    } catch (Exception ex) {
      log.error("[GET] /api/recipe: - {}", ex.getMessage());
      return ResponseEntity.notFound().build();
    }
  }
}
