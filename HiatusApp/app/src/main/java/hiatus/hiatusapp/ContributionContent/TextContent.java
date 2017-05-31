package hiatus.hiatusapp.ContributionContent;


import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import hiatus.hiatusapp.ContributionContext.ContributionContext;

/**
 * Content for text contributions
 * Created by Cecile on 24/05/2017.
 */

public class TextContent extends ContributionContent {

    private String text;

    public TextContent(String contextId, String text) {
        super(contextId);
        this.text = text;
    }

    public TextContent(String contextId) {
        super(contextId);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void display(View titleView, View textView){
        ((TextView) textView).setText(this.text);
        ((TextView) titleView).setText(getTitle());
    }

    @Override
    public String toString() {
        return "TextContent{" +
                "text='" + text + '\'' +
                ", title='" + getTitle() + '\'' +
                '}';
    }

    /*
    Functions needed for a parcelable
     */

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeString(text);
    }

    private TextContent(Parcel in) {
        super(in);
        text = in.readString();
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
