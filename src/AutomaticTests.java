import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AutomaticTests {
    @Test   
    public void test1(){
        assertEquals("Egg",new Ingredient("Egg","Piece",15).getname());
        assertEquals("Hej",new Recipe("Gingerbread",5,"Piece",new RecipeIngredients(new Ingredient("Egg","Piece",10),5,"Stir"),"Hej").getComments());
    }    
}
