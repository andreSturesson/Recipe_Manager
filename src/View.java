import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//The handler for most of the information displayed in terminal, also Scanner related error handling
public class View {
    private FileIO file = new FileIO();
    private Scanner input = new Scanner(System.in);

    //The first method that start the program
    public void gui(){
        System.out.println("Welcome to the Recipe program");
        int value = 1;
        while (value != 0){
            System.out.println("Choose what you want to do");
            System.out.println("----------------Ingredient--------------");
            System.out.println("Choose 1: For a list of all ingredients");
            System.out.println("Choose 2: To create an ingredient");
            System.out.println("Choose 3: To lookup details of an ingredient");
            System.out.println("Choose 4: To delete an ingredient");
            System.out.println("----------------Recipe------------------");
            System.out.println("Choose 5: Create a recipe");
            System.out.println("Choose 6: To view all Recipes");
            System.out.println("Choose 7: Lookup recipe");
            System.out.println("Choose 8: Search recipes by price");
            System.out.println("Choose 9: Get Recipe by name");
            System.out.println("Choose 10: Delete Recipe");
            System.out.println("----------------Exit--------------------");
            System.out.println("Choose 11: To exit the program and save your changes");
            value = input.nextInt();
            input.nextLine();
            System.out.println("Your value is: " + value);
            if (value == 1){
                printViewIngredients();
            }
            else if (value == 2){
                printCreateIngredient();
            }
            else if (value == 3){
                printIngredientsInfo();
            }
            else if (value == 4){
                printDeleteIngredient();
            }
            else if (value == 5){
                printCreateRecipe();
            }
            else if (value == 6){
                file.View_Recipe();
                System.out.println("Press any key to countinue...");
                input.nextLine();
            }
            else if (value == 7){
                System.out.println("Enter a recipe name:");
                String name = input.nextLine();
                System.out.println("How many portions do you want?");
                int portions = input.nextInt();
                file.LookupRecipe(name,portions);
                input.nextLine();
                System.out.println("Press any key to countinue...");
            }
            else if (value == 8){
                printRecipesByPrice();
            }
            else if (value == 9){
                printRecipesByName();
            }
            else if (value == 10){
            System.out.println("Enter name of Recipe you want deleted...");
            String name = input.nextLine();
            file.DeleteRecipe(name);
            System.out.println("Recipe deleted...");
            input.nextLine();
            System.out.println("Press any key to countinue...");
            }
            else if (value == 11){
                try {
                    file.saveToFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.exit(0);
            }
            else{
                System.out.println("Please enter a number between 1 and 10..");
            }
        }
        System.out.println("Exiting program..");
    }

    private void printViewIngredients() {
        System.out.println("Ingredients:");
        file.ShowIngredients();
        System.out.println("Press any key to countinue...");
        input.nextLine();
    }

    private void printCreateIngredient(){
        System.out.println("To Create an ingredient, please enter name,unit,price");
        System.out.println("Enter name: ");
        String name = input.nextLine();
        System.out.println("Enter unit: ");
        String unit = input.next();
        System.out.println("Enter price: ");
        int price = input.nextInt();
        String[] error = {name,unit};
        if(isStringOnlyAlphabet(error) == false){
        file.CreateIngredient(name, unit, price);
        System.out.println("Ingredient Created...");
        }
        else{
            System.out.println("Illeagal chars..");
        }
        System.out.println("Press any key to countinue...");
        input.nextLine();

    }
    private void printIngredientsInfo(){
        System.out.println("Choose Ingredient you want to lookup: ");
        String name = input.next();
        for (String i : file.LookupIngredient(name)) {
            System.out.println(i);
        }
        System.out.println("Press any key to countinue...");
        input.nextLine();
        input.nextLine();
    }
    private void printDeleteIngredient(){
        System.out.println("Enter the name of the ingredient you want deleted...");
        System.out.println("Name: ");
        String name = input.next();
        file.DeleteIngredient(name);
        System.out.println("Press any key to countinue...");
        input.nextLine();
    }
    private void printCreateRecipe(){
        List<RecipeIngredients> recipeIngredientsObj = new ArrayList<RecipeIngredients>();
        System.out.println("Enter the details specified...");
        System.out.println("Enter name: ");
        String name = input.nextLine();
        System.out.println("Enter Number of portions: ");
        int number_of_portions = input.nextInt();
        System.out.println("Enter unit: ");
        String unit = input.next();
        System.out.println("Enter the ingredient name (To finish enter 0): ");
        input.nextLine();
        while(true) {
            System.out.println("Enter Ingredient name:");
            String inputs = input.nextLine();
            if(inputs.equals("0")){
                break;
            }
            else{
                String ingredientName = inputs;
                System.out.println("How many do you want?(of the ingredient entered):");
                int ingredients_amount = input.nextInt();
                input.nextLine();
                System.out.println("Comment on that ingredient:");
                String ingredientComment = input.nextLine();
                recipeIngredientsObj.add(new RecipeIngredients(new Ingredient(ingredientName,"",0),ingredients_amount,ingredientComment));
                input.nextLine();
            }
        }
        System.out.println("Enter the ingredient ammounts: ");
        System.out.println("Enter comments");
        String comments = input.nextLine();
        file.CreateRecipe(name, number_of_portions, unit, comments, recipeIngredientsObj);
        System.out.println("Press any key to countinue...");
        input.nextLine();
    }
    private void printRecipesByPrice(){
        Context context = new Context(new RecipePrice());
        int price = 50;
        List<Recipe> r = context.getRecipesByPrice(input);
        System.out.println("Recipes cheaper than " + price);
        for(Recipe re : r){
            System.out.println("Name: " + re.getName());
        }
        System.out.println("Press any key to countinue...");
        input.nextLine();
    }

    private void printRecipesByName(){
        Context context = new Context(new RecipebyIngredientName());
        List<Recipe> r = context.getRecipesByIngredientName(input);
        for (Recipe ri : r){
            System.out.println("Name: " + ri.getName());;
        }
        System.out.println("Press any key to countinue...");
        input.nextLine();
    }
    private boolean isStringOnlyAlphabet(String[] name) {
        for (String n : name){
            if(((n != null) 
            && (!n.equals("")) 
            && (n.matches("^[a-zA-Z]*$")))){
                return false;
            }
        }
        return true;
    }
}
