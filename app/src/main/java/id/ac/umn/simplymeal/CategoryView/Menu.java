package id.ac.umn.simplymeal.CategoryView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private String menuName;
    private Map<String, Object> imageMenu ;
    private String category;
    private String idMenu;
    private  Map<String, Object> idIngredient;
    private Map<String, Object> idPortion;

    public Menu(String menuName, String idMenu,  Map<String, Object> imageMenu, String category, Map<String, Object> idIngredient, Map<String, Object> idPortion) {
        this.menuName = menuName;
        this.imageMenu = imageMenu;
        this.category = category;
        this.idMenu = idMenu;
        this.idIngredient = idIngredient;
        this.idPortion = idPortion;
    }

    public  Menu(){

    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public  Map<String, Object> getImageMenu() {
        return imageMenu;
    }

    public void setImageMenu( Map<String, Object> imageMenu) {
        this.imageMenu = imageMenu;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public  Map<String, Object> getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Map<String, Object> idIngredient) {
        this.idIngredient = idIngredient;
    }

    public  Map<String, Object> getIdPortion() {
        return idPortion;
    }

    public void setIdPortion( Map<String, Object> idPortion) {
        this.idPortion = idPortion;
    }
}
