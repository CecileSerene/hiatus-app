package hiatus.hiatusapp.ContributionContext;

/**
 * Created by Cecile on 24/05/2017.
 */

public class ContributionPhoto extends ContributionContext{

    public ContributionPhoto(String instructions,String title,String theme, int nb_of_characters) {
        this.title = title;
        this.instructions = instructions;
        this.modifications_allowed = false;
        this.limited_time = Double.POSITIVE_INFINITY;
        this.theme = theme;


    }
}
