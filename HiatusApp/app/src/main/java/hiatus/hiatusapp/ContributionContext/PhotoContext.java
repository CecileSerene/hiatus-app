package hiatus.hiatusapp.ContributionContext;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cecile on 24/05/2017.
 */

public class PhotoContext extends ContributionContext{

    public PhotoContext(String instructions, String title, String theme) {
        super(title, theme, instructions);
        setType(Type.PHOTO);
        this.modifications_allowed = false;
        this.limited_time = Double.POSITIVE_INFINITY;

    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getTitle());
        parcel.writeString(getTheme());
        parcel.writeString(getInstructions());
        parcel.writeInt(modifications_allowed ? 1:0); //Because there is no writeBoolean method
        parcel.writeDouble(limited_time);
    }

    private PhotoContext(Parcel in){
        super(in);
        modifications_allowed = (in.readInt() == 0) ? false : true;
        limited_time = in.readDouble();
    }

    public static final Parcelable.Creator<PhotoContext> CREATOR
            = new Parcelable.Creator<PhotoContext>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public PhotoContext createFromParcel(Parcel in) {
            return new PhotoContext(in) {
            };
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public PhotoContext[] newArray(int size) {
            return new PhotoContext[size];
        }
    };
}
