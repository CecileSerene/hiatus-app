package hiatus.hiatusapp.ContributionContext;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cecile on 24/05/2017.
 */

public class ContributionPhoto extends ContributionContext{

    public ContributionPhoto(String instructions,String title,String theme, int nb_of_characters) {
        super();
        this.title = title;
        this.instructions = instructions;
        this.modifications_allowed = false;
        this.limited_time = Double.POSITIVE_INFINITY;
        this.theme = theme;


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

    }

    private ContributionPhoto(Parcel in){
        instructions = in.readString();
        title = in.readString();
        modifications_allowed = (in.readInt() == 0) ? false : true;
        limited_time = in.readDouble();
        theme = in.readString();
    }

    public static final Parcelable.Creator<ContributionPhoto> CREATOR
            = new Parcelable.Creator<ContributionPhoto>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public ContributionPhoto createFromParcel(Parcel in) {
            return new ContributionPhoto(in) {
            };
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public ContributionPhoto[] newArray(int size) {
            return new ContributionPhoto[size];
        }
    };


}
