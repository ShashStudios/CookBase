package com.cookbase.cookbaseapi.models;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class Recipe {

    private String id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotEmpty(message = "Ingredients cannot be empty")
    private List<String> ingredients;

    @NotEmpty(message = "Instructions cannot be empty")
    private List<String> instructions;

    // Constructors
    public Recipe() {}

    public Recipe(String id, String title, List<String> ingredients, List<String> instructions) {
        this.id = id;
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
}
