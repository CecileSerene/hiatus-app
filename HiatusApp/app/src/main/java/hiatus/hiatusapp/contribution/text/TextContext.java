package hiatus.hiatusapp.contribution.text;


import android.os.Parcel;
import android.os.Parcelable;

import hiatus.hiatusapp.contribution.base.ContributionContext;

/**
 * Created by Cecile on 24/05/2017.
 * For a text contribution
 */

public class TextContext extends ContributionContext {

    private int numberOfCharacters;

    public TextContext(String id, String title, String theme, String instructions, int numberOfCharacters) {
        super(id, title, theme, instructions);
        setType(ContributionContext.TYPE_TEXT);
        setModificationsAllowed(true);
        this.numberOfCharacters = numberOfCharacters;
    }

    public TextContext(String id, String title, String theme, String instructions) {
        this(id, title, theme, instructions, -1);
    }

    public TextContext() {}

    public int getNumberOfCharacters() {
        return numberOfCharacters;
    }

    public void setNumberOfCharacters(int numberOfCharacters) {
        this.numberOfCharacters = numberOfCharacters;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(numberOfCharacters);
    }

    private TextContext(Parcel in){
        super(in);
        numberOfCharacters = in.readInt();
    }

    public static final Parcelable.Creator<TextContext> CREATOR
            = new Parcelable.Creator<TextContext>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public TextContext createFromParcel(Parcel in) {
            return new TextContext(in) {
            };
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public TextContext[] newArray(int size) {
            return new TextContext[size];
        }
    };
}
