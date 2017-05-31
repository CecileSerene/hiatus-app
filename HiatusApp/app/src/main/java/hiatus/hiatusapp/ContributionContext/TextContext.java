package hiatus.hiatusapp.ContributionContext;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cecile on 24/05/2017.
 * For a text contribution
 */

public class TextContext extends ContributionContext {

    private int nb_of_characters;

    public TextContext(String title, String theme, String instructions, int nb_of_characters) {
        super(title, theme, instructions);
        setType(Type.TEXT);
        this.modifications_allowed = true;
        this.limited_time = Double.POSITIVE_INFINITY;
        this.nb_of_characters = nb_of_characters;

    }

    public int getNb_of_characters() {
        return nb_of_characters;
    }

    public void setNb_of_characters(int nb_of_characters) {
        this.nb_of_characters = nb_of_characters;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(modifications_allowed ? 1:0); //Because there is no writeBoolean method
        parcel.writeDouble(limited_time);
        parcel.writeInt(nb_of_characters);
    }

    private TextContext(Parcel in){
        super(in);
        modifications_allowed = (in.readInt() == 0) ? false : true;
        limited_time = in.readDouble();
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
