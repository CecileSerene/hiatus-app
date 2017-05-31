package hiatus.hiatusapp.ContributionContent;


import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import hiatus.hiatusapp.ContributionContext.ContributionContext;


/**
 * Content of a contribution.
 * Created by Cecile on 24/05/2017.
 */

public abstract class ContributionContent implements Parcelable {

    private String contextId;
    private String title;

    public ContributionContent(String contextId) {
        this.contextId = contextId;
        this.title = ""; // empty title by default
    }

    // necessary empty constructor
    public ContributionContent() {}

    /*
    Getters and setters
     */

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // TODO @Cecile add a doc? what should this function do in subclasses?
    public abstract void display(View titleView, View contentView);

    /*
    Functions needed for a parcelable
     */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(contextId);
        out.writeString(title);
    }

    protected ContributionContent(Parcel in) {
        contextId = in.readString();
        title = in.readString();
    }


}
