
public class Ingredient{
    private String name;
    private String unit;
    private int price;

public Ingredient(String name, String unit, int price){
    this.name = name;
    this.unit = unit;
    this.price = price;
}
public String getname(){
    return name;
}
public void setname(String name){
    this.name = name;
}
public String getunit(){
    return unit;
}
public void setunit(String unit){
    this.unit = unit;
}
public int getprice(){
    return price;
}
public void setprice(int price){
    this.price = price;
}
public String get_string(){
    return name + ":" +  unit + ":" + price;
}
}