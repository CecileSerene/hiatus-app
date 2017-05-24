package hiatus.hiatusapp.ContributionContext;

/**
 * Created by Cecile on 24/05/2017.
 */

public class ContributionDrawing extends ContributionContext {

    public ContributionDrawing(String instructions,String title,double limited_time,String theme) {
        this.title = title;
        this.instructions = instructions;
        this.limited_time = limited_time;
        this.theme = theme;

        //The modifications are allowed only if there is no limited time
        if (limited_time < Double.POSITIVE_INFINITY){
            this.modifications_allowed = false;
        }
        else {
            this.modifications_allowed = true;
        }


    }
}
