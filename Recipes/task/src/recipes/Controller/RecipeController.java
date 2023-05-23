package recipes.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipes.Exception.NotFound;
import recipes.Model.Recipe;
import recipes.RecipesApplication;
import recipes.Service.RecipeService;

import java.util.Map;

@RestController
public class RecipeController {
  private final RecipeService service;
  ObjectMapper om = new ObjectMapper();
  Logger log = LoggerFactory.getLogger(RecipesApplication.class);

  @Autowired
  public RecipeController(RecipeService service) {
    this.service = service;
  }
  @PostMapping(path="/api/recipe/new")
  public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe){
    try {
      return ResponseEntity.ok().body(Map.of("id",service.createRecipe(recipe)));
    } catch (Exception ex) {
      log.error("[POST] /api/recipe/new: - {}", ex.getMessage());
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping(path="/api/recipe/{id}")
  public ResponseEntity<Recipe> getRecipe(@PathVariable int id){
    Recipe recipe = service.findById(id);
    if (recipe != null) {
      return ResponseEntity.ok().body(recipe);
    } else {
    log.warn("[GET] /api/recipe/{} - Not found",id);
    return ResponseEntity.notFound().build();
    }
  }
}
