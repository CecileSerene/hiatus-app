package hiatus.hiatusapp.ContributionContext;

/**
 * Created by Cecile on 24/05/2017.
 * Describes a contribution
 * It is described by the administrator
 */

public abstract class ContributionContext {

    protected String instructions;
    protected Type type;
    protected String title;
    protected boolean modifications_allowed;
    protected double limited_time;
    protected String theme;

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isModifications_allowed() {
        return modifications_allowed;
    }

    public void setModifications_allowed(boolean modifications_allowed) {
        this.modifications_allowed = modifications_allowed;
    }

    public double getLimited_time() {
        return limited_time;
    }

    public void setLimited_time(double limited_time) {
        this.limited_time = limited_time;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
