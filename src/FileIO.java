import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//The core of the program, handler of all logic
public class FileIO {
    //Stores all objects in 2 Arraylists
    private ArrayList<Ingredient> ingredients = ReadIngredients("Ingredients.txt");
    private ArrayList<Recipe> recipes = ReadRecipies("Recipies.txt");

//The following 2 methods was only added because of the Stratergy pattern search function
public ArrayList<Ingredient>getingredients(){
    return ingredients;
}
public ArrayList<Recipe>getrecipes(){
    return recipes;
}
//Saves Ingredients to file
public void saveToFile() throws IOException{
        File file1 = new File("Ingredients.txt");
        FileWriter fw = new FileWriter(file1, false);
        for(Ingredient i : ingredients){
            fw.write(i.get_string());
            fw.write("\n");
        }
        fw.flush();
        fw.close();
    saveToFileRecipe();
}

/*Saves Recipes to file
Seperates each type by : and in the lists they are seperated by % and ;
*/
public void saveToFileRecipe() throws IOException{
        FileWriter fw = new FileWriter(new File("Recipies.txt"), false);
        for(Recipe r : recipes){
            String recipe = r.getName()+ ":" + r.getNumber_of_portions() + ":" + r.getUnit() + ":";
            int counter = 0;
            for (RecipeIngredients i : r.getIngredients()){
                recipe += i.getIngredients().getname() + "%" + i.getAmount() + "%" + i.getComments();
                if(r.getIngredients().indexOf(i) == r.getIngredients().size()-1)
                    recipe += ":";
                else
                    recipe += ";";
            }

            recipe += r.getComments();
            fw.write(recipe);
            fw.write("\n");
        }
        fw.flush();
        fw.close();
    }

public void get_string_recipe(){
    for(int i = 0; i < recipes.size(); i++){
        String recipe = recipes.get(i).get_string();
        System.out.println(recipe);
    }
}
public ArrayList<Ingredient> ReadIngredients(String name) {
    ArrayList<Ingredient> list = new ArrayList<Ingredient>();
    FileInputStream stream = null;
    try {
        stream = new FileInputStream(name);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    String strLine;
    try {
        while ((strLine = reader.readLine()) != null) {
            String[] lastWord = strLine.split(":");
            String n = lastWord[0];
            String u = lastWord[1];
            int p = Integer.parseInt(lastWord[2]);
            list.add(new Ingredient(n,u,p));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    try {
        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }

    return list;
}
public ArrayList<Recipe> ReadRecipies(String name){
    ArrayList<Recipe> list = new ArrayList<Recipe>();
    FileInputStream stream = null;
    try {
        stream = new FileInputStream(name);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    String strLine;
    try {
        while ((strLine = reader.readLine()) != null) {
            List<RecipeIngredients> recipeIngredientsObj = new ArrayList<RecipeIngredients>();
            String[] lastWord = strLine.split(":");
            String recipeName = lastWord[0];
            int recipeNumber = Integer.parseInt(lastWord[1]);
            String recipeUnit = lastWord[2];
            String[] recipe_Ingredients = lastWord[3].split(";");
            for (String recipe_Ingredient : recipe_Ingredients) {
                String[] ab = recipe_Ingredient.split("%");
                for (Ingredient ingredient : ingredients) {
                    if (ingredient.getname().equals(ab[0])){
                        recipeIngredientsObj.add(new RecipeIngredients(ingredient,Integer.parseInt(ab[1]),ab[2]));
                    }
                }
            }
            String recipeComments = lastWord[4];
            Recipe obj = new Recipe(recipeName,recipeNumber,recipeUnit,recipeIngredientsObj,recipeComments);
            list.add(obj);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    try {
        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }

    return list;
}

public void CreateRecipe(String name, int number_of_portions, String unit, String comments, List<RecipeIngredients> ingredients){
    List<RecipeIngredients> recipeIngredientsObj = new ArrayList<RecipeIngredients>(); 
        for (RecipeIngredients element : ingredients) { 
            for (Ingredient ingredient : this.ingredients) {
                if (ingredient.getname().equals(element.getIngredients().getname())){
                    recipeIngredientsObj.add(new RecipeIngredients(ingredient,element.getAmount(),element.getComments()));
                }
                }
        }
        Recipe obj = new Recipe(name, number_of_portions, unit,recipeIngredientsObj, comments);
        recipes.add(obj);
    }

public void ShowIngredients(){
    for (int i = 0; i < ingredients.size(); i++) {
        System.out.println(ingredients.get(i).getname());
    }
}

public void CreateIngredient(String name, String unit, int price){
    ingredients.add(new Ingredient(name, unit, price));
}

public String[] LookupIngredient(String name) {
    for (int i = 0; i < ingredients.size(); i++) {
        if (ingredients.get(i).getname().equals(name)){
            String[] infos = {"Unit: " + ingredients.get(i).getunit(), "Price: " + ingredients.get(i).getprice()};
            return infos;
        }
    }
    return null;
}

public String DeleteIngredient(String name){
    ingredients.removeIf(ingredient-> ingredient.getname().equals(name));
    return "Ingredient Deleted";
}

public void View_Recipe(){
    for (Recipe r : recipes){
        System.out.println(r.getName());
    }
}
public void LookupRecipe(String name, int portions){
    int totamount = 0;
    int price = 0;
    for (Recipe r : recipes){
        if(r.getName().equals(name)){
            int compare = portions/r.getNumber_of_portions();
            System.out.println("Recipe: " + r.getName());
            System.out.println("Portions: "+portions);
            System.out.println("Unit: "+r.getUnit());
            System.out.println("Comments: "+r.getComments());
            for (RecipeIngredients ri : r.getIngredients()){
                System.out.println("Ingredient: " + ri.getIngredients().getname());
                totamount += Math.round(ri.getAmount()*compare);
                System.out.println("Amount: " + Math.round(ri.getAmount()*compare));
                System.out.println("Comment: " + ri.getComments());
                System.out.println("Price: " + ri.getIngredients().getprice());
                price += + ri.getIngredients().getprice()+Math.round((ri.getAmount()*compare));
            }
        }
    }
    System.out.println("Price for recipe: " + price);
}
public void DeleteRecipe(String name){
    recipes.removeIf(recipe-> recipe.getName().equals(name));
    System.out.println("Recipe deleted");
}

}