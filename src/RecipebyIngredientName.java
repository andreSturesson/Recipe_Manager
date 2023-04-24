import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecipebyIngredientName  implements Search{

    @Override
    public List<Recipe> SearchRecipe(Scanner input) {
        System.out.println("------------List Recipes-------------------");
        System.out.println("Enter Ingredient Name: ");
        String name = input.nextLine();
        FileIO file = new FileIO();
        ArrayList<Recipe>recipes = file.getrecipes();
        ArrayList<Recipe>temp = new ArrayList<Recipe>();
        for (Recipe r : recipes){
            for (RecipeIngredients i : r.getIngredients()){
                if(i.getIngredients().getname().equals(name)){
                    temp.add(r);
                }

                }
            }
        System.out.println("Recipes with " + name + " In them");
        return temp;
        }
    }
