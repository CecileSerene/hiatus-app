package hiatus.hiatusapp.contribution.text;


import android.os.Parcel;
import android.os.Parcelable;

import hiatus.hiatusapp.contribution.base.ContributionContent;

/**
 * Content for text contributions
 * Created by Cecile on 24/05/2017.
 */

public class TextContent extends ContributionContent {

    private String text;

    public TextContent(String contextId) {
        this(contextId, "", "");
    }

    public TextContent(String contextId, String title) {
        this(contextId, title, "");
    }

    public TextContent(String contextId, String title, String text) {
        super(contextId, title);
        this.text = text;
    }

    /*
   Database model
    */

    @Override
    public Model toModel() {
        Model model = new Model(getContextId(), getTitle());
        model.setType(Model.TYPE_TEXT);
        model.putExtra("text", getText());
        return model;
    }

    public TextContent(Model model) {
        this(model.getContextId(), model.getTitle(), model.getExtra("text"));
    }

    /*
    Getters
     */

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
