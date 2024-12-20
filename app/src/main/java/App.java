import data.RecipeFileHandler;
import ui.RecipeUI;

public class App {
    public static void main(String[] args) {
        RecipeUI ui = new RecipeUI();
        // RecipeFileHandler recipeFileHandler = new RecipeFileHandler();
        ui.displayMenu();
    }
}
