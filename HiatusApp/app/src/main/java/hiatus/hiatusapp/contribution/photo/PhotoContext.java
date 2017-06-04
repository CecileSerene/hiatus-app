package hiatus.hiatusapp.contribution.photo;

import android.os.Parcel;
import android.os.Parcelable;

import hiatus.hiatusapp.contribution.base.ContributionContext;

/**
 * Created by Cecile on 24/05/2017.
 */

public class PhotoContext extends ContributionContext{

    public PhotoContext(String id, String title, String theme, String instructions) {
        super(id, title, theme, instructions);
        setType(ContributionContext.TYPE_PHOTO);
        setModificationsAllowed(true);
    }

    public PhotoContext() {}

    private PhotoContext(Parcel in){
        super(in);
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
