import java.util.List;

public class Recipe {
    private String name;
    private int number_of_portions;
    private String unit;
    private List<RecipeIngredients> ingredients;
    private String comments;

    public Recipe(String name, int number_of_portions, String unit, List<RecipeIngredients> isd,
        String comments) {
        this.name = name;
        this.number_of_portions = number_of_portions;
        this.unit = unit;
        this.ingredients = isd;
        this.comments = comments;
    }

    public Recipe(String recipeName, int recipeNumber, String recipeUnit, RecipeIngredients recipeIngredients,
            String recipeComments) {
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public List<RecipeIngredients> getIngredients() {
        return ingredients;
    }
    public void setIngredients(List<RecipeIngredients> ingredients) {
        this.ingredients = ingredients;
    }
    public int getNumber_of_portions() {
        return number_of_portions;
    }
    public void setNumber_of_portions(int number_of_portions) {
        this.number_of_portions = number_of_portions;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String get_string(){
        return name + ":" + number_of_portions + ":" + unit + ":" + ingredients + ":" + comments;
    }
}
