import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Context {
    private Search search;

    public Context(Search search){
        this.search = search;
    }
    public List<Recipe> getRecipesByPrice(Scanner input){
        return search.SearchRecipe(input);
    }
    public List<Recipe> getRecipesByIngredientName(Scanner input){
        return search.SearchRecipe(input);
    }
}
