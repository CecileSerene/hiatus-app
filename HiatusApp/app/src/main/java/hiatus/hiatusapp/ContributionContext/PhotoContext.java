package hiatus.hiatusapp.ContributionContext;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cecile on 24/05/2017.
 */

public class PhotoContext extends ContributionContext{

    public PhotoContext(String id, String title, String theme, String instructions) {
        super(id, title, theme, instructions);
        setType(ContributionContext.TYPE_PHOTO);
        setModificationsAllowed(true);
        setLimitedTime(-1);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(getInstructions());
        parcel.writeInt(modificationsAllowed ? 1:0); //Because there is no writeBoolean method
        parcel.writeDouble(limitedTime);
    }

    private PhotoContext(Parcel in){
        super(in);
        modificationsAllowed = (in.readInt() == 0) ? false : true;
        limitedTime = in.readDouble();
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
