package hiatus.hiatusapp.ContributionContent;


import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import hiatus.hiatusapp.ContributionContext.ContributionContext;

/**
 * Created by Cecile on 24/05/2017.
 */

public class TextContent implements ContributionContent, Parcelable {

    private String text;
    private String title;
    private ContributionContext context;

    public TextContent(String text, ContributionContext context) {
        this.text = text;
        this.context = context;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ContributionContext getContext() {
        return context;
    }

    public void setContext(ContributionContext context) {
        this.context = context;
    }

    public void display(View view){
        ((TextView) view).setText(this.text);

    }

    @Override
    public void sendToDatabase() {
        //TODO :maybe someday
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
//TODO I probably need to put something in here
    }
}