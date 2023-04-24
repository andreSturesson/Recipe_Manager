
public class RecipeIngredients {
    private Ingredient ingredients;
    private int amount;
    private String comments;

    public Ingredient getIngredients() {
        return ingredients;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void setIngredients(Ingredient ingredients) {
        this.ingredients = ingredients;
    }
    public RecipeIngredients(Ingredient ingredients, int amount, String comments) {
        this.ingredients = ingredients;
        this.amount = amount;
        this.comments = comments;
    }

}