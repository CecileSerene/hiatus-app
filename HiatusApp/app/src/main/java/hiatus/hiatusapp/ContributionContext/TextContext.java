package hiatus.hiatusapp.ContributionContext;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cecile on 24/05/2017.
 * For a text contribution
 */

public class TextContext extends ContributionContext {

    private int nb_of_characters;

    public TextContext(String instructions, String title, String theme, int nb_of_characters) {
        super();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(instructions);
        parcel.writeString(title);
        parcel.writeInt(modifications_allowed ? 1:0); //Because there is no writeBoolean method
        parcel.writeDouble(limited_time);
        parcel.writeString(theme);
        parcel.writeInt(nb_of_characters);

    }

    private TextContext(Parcel in){
        instructions = in.readString();
        title = in.readString();
        modifications_allowed = (in.readInt() == 0) ? false : true;
        limited_time = in.readDouble();
        theme = in.readString();
        nb_of_characters = in.readInt();
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
