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
}
