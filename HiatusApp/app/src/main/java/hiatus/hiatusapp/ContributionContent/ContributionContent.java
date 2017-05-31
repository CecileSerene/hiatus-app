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

    private ContributionContext context;
    private String title;

    public ContributionContent(ContributionContext context) {
        this.context = context;
        this.title = ""; // empty title by default
    }

    public ContributionContent(String title, ContributionContext context) {
        this.context = context;
        this.title = title;
    }

    public ContributionContent() {}

    /*
    Getters and setters
     */

    public ContributionContext getContext() {
        return context;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
        out.writeParcelable(context, flags);
        out.writeString(title);
    }

    protected ContributionContent(Parcel in) {
        context = in.readParcelable(ContributionContext.class.getClassLoader());
        title = in.readString();
    }


}
