package hiatus.hiatusapp.ContributionContent;


import android.os.Parcelable;
import android.view.View;


/**
 * Created by Cecile on 24/05/2017.
 */

public abstract class ContributionContent implements Parcelable {

    public abstract void display(View titleView, View contentView);

    public abstract void sendToDatabase();

    @Override
    public int describeContents() {
        return 0;
    }




}
