package hiatus.hiatusapp.DbSaveTest;

/**
 * Created by Florimond on 31/05/2017.
 */

public class MyContext {

    private String title;
    private String theme;
    private String instructions;

    public MyContext() {}

    public MyContext(String title, String theme, String instructions) {
        this.title = title;
        this.theme = theme;
        this.instructions = instructions;
    }

    public String getTheme() {
        return theme;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getTitle() {
        return title;
    }
}
