package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // 設問1: 一覧表示機能
                        displayRecipes();
                        break;
                    case "2":
                        // 設問2: 新規登録機能
                        addNewRecipe();
                        break;
                    case "3":
                        // 設問3: 検索機能
                        searchRecipe();
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 設問1: 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */
    private void displayRecipes() {
        // レシピを取得
        ArrayList<String> recipes = new ArrayList<>();
        recipes = fileHandler.readRecipes();

        // データが空の場合
        if (recipes.isEmpty()) {
            System.out.println("No recipes available.");
        }
        // データが存在する場合
        else {
            // 出力
            System.out.println("Recipes:");
            for (String recipe : recipes) {
                ArrayList<String> line = new ArrayList<>();
                String[] splitRecipe = recipe.split(",");
                for (String str : splitRecipe) {
                    line.add(str);
                }
                System.out.println("-----------------------------------");
                System.out.println("Recipe Name: " + line.get(0));
                System.out.print("Main Ingredients: ");
                for (int i = 1; i < line.size(); i++){
                    if (i == line.size() - 1) {
                        System.out.print(line.get(i));
                    }
                    else {
                        System.out.print(line.get(i) + ",");
                    }
                }
                System.out.println();
                line.clear();
            }
            System.out.println("-----------------------------------");
        }
    }

    /**
     * 設問2: 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void addNewRecipe() throws IOException {
        try {
            // 入力受付
            System.out.print("Enter recipe name: ");
            String recipeName = reader.readLine();
            System.out.print("Enter main ingredients (comma separated): ");
            String mainIngredients = reader.readLine();
            System.out.print("Recipe added successfully.");

            // 入力値を渡す
            fileHandler.addRecipe(recipeName, mainIngredients);
        }
        catch (Exception ex) {
            throw new IOException();
        }
        
    }

    /**
     * 設問3: 検索機能
     * ユーザーから検索クエリを入力させ、そのクエリに基づいてレシピを検索し、一致するレシピをコンソールに表示します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void searchRecipe() throws IOException {
        // // 入力受付
        // System.out.print("Enter search query (e.g., 'name=Tomato&ingredient=Garlic'): ");
        // String searchWord = reader.readLine();
        // String name;
        // String ingredient;

        // // name,ingredientを取り出す
        // name = searchWord.substring(searchWord.indexOf('=') + 1, searchWord.indexOf('&'));
        // ingredient = searchWord.substring(searchWord.indexOf('&') + 1);
    }

}

