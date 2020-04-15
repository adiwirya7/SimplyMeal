package id.ac.umn.simplymeal;

public class Menu {
    private String menuName;
    private String[] imageMenu = new String[2];
    private String category;
    private String idMenu;
    private String[] idIngredient = new String[30];
    private String[] idPortion = new String[2];

    public Menu(String menuName, String[] imageMenu, String category, String idMenu, String[] idIngredient, String[] idPortion) {
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

    public String[] getImageMenu() {
        return imageMenu;
    }

    public void setImageMenu(String[] imageMenu) {
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

    public String[] getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(String[] idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String[] getIdPortion() {
        return idPortion;
    }

    public void setIdPortion(String[] idPortion) {
        this.idPortion = idPortion;
    }
}
