package hiatus.hiatusapp.ContributionContext;



/**
 * Created by Cecile on 24/05/2017.
 * For a text contribution
 */

public class ContributionText extends ContributionContext {

    private int nb_of_characters;

    public ContributionText(String instructions,String title,String theme, int nb_of_characters) {
        this.title = title;
        this.instructions = instructions;
        this.modifications_allowed = true;
        this.limited_time = Double.POSITIVE_INFINITY;
        this.theme = theme;
        this.nb_of_characters = nb_of_characters;

    }

    public int getNb_of_characters() {
        return nb_of_characters;
    }

    public void setNb_of_characters(int nb_of_characters) {
        this.nb_of_characters = nb_of_characters;
    }
}
