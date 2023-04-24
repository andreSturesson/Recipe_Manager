import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class RecipePrice implements Search {
    @Override
    public List<Recipe> SearchRecipe(Scanner input){
        int price = input.nextInt();
        System.out.println(price);
        FileIO file = new FileIO();
        ArrayList<Recipe> recipes = file.getrecipes();
        List<Recipe> prices = new ArrayList<Recipe>();

        int recipePrice = 0;
        for (Recipe r : recipes){
                for (RecipeIngredients ri : r.getIngredients()){
                    recipePrice += + ri.getIngredients().getprice()*ri.getAmount();
                    }
                if(recipePrice < price){
                    System.out.println(recipePrice);
                    prices.add(r);
                }
            recipePrice = 0;
        }
        
        return prices;
    }

}
