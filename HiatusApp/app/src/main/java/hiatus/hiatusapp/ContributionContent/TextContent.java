package hiatus.hiatusapp.ContributionContent;


import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import hiatus.hiatusapp.ContributionContext.ContributionContext;

/**
 * Created by Cecile on 24/05/2017.
 */

public class TextContent extends ContributionContent {

    private String text;
    private String title;
    private ContributionContext context;

    public TextContent(String text, ContributionContext context) {
        this.text = text;
        this.context = context;
        this.title = ""; //by default, there is no title
    }

    public TextContent(String title, String text, ContributionContext context) {
        this.text = text;
        this.title = title;
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

    public void display(View titleView, View textView){
        ((TextView) textView).setText(this.text);
        ((TextView) titleView).setText(this.title);

    }

    @Override
    public void sendToDatabase() {
        //TODO :maybe someday
    }


    @Override
    public String toString() {
        return "TextContent{" +
                "text='" + text + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    /*
    Functions needed for a parcelable
     */

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(text);
        out.writeString(title);
        out.writeParcelable(context, flags);
    }

    private TextContent(Parcel in) {
        text = in.readString();
        title = in.readString();
        context = in.readParcelable(ContributionContext.class.getClassLoader());
    }

    public TextContent() {
        // Normal actions performed by class, since this is still a normal object!
    }

    public static final Parcelable.Creator<TextContent> CREATOR
            = new Parcelable.Creator<TextContent>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public TextContent createFromParcel(Parcel in) {
            return new TextContent(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public TextContent[] newArray(int size) {
            return new TextContent[size];
        }
    };
}
