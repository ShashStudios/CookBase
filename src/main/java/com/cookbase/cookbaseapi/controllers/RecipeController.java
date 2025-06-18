package com.cookbase.cookbaseapi.controllers;

import com.cookbase.cookbaseapi.models.Recipe;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recipes")
@Validated
public class RecipeController {

    private final Map<String, Recipe> recipeMap = new HashMap<>();

    // Get all recipes
    @GetMapping
    public Collection<Recipe> getAllRecipes() {
        return recipeMap.values();
    }

    // Get recipe by ID
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable String id) {
        Recipe recipe = recipeMap.get(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    // Create a new recipe with validation
    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@Valid @RequestBody Recipe recipe) {
        String id = UUID.randomUUID().toString();
        recipe.setId(id);
        recipeMap.put(id, recipe);
        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }

    // Update a recipe with validation
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable String id, @Valid @RequestBody Recipe recipe) {
        if (!recipeMap.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        recipe.setId(id);
        recipeMap.put(id, recipe);
        return ResponseEntity.ok(recipe);
    }

    // Delete a recipe
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable String id) {
        if (!recipeMap.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        recipeMap.remove(id);
        return ResponseEntity.noContent().build();
    }

    // Search recipes by query (title or ingredients)
    @GetMapping("/search")
    public List<Recipe> searchRecipes(@RequestParam String query) {
        String lowerQuery = query.toLowerCase();

        return recipeMap.values().stream()
                .filter(recipe ->
                        recipe.getTitle().toLowerCase().contains(lowerQuery) ||
                                recipe.getIngredients().stream()
                                        .anyMatch(ingredient -> ingredient.toLowerCase().contains(lowerQuery))
                )
                .collect(Collectors.toList());
    }
}
