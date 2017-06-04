package hiatus.hiatusapp.ContributionContent;


import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.net.URL;

import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.ContributionContext.PhotoContext;

/**
 * Content for photo contributions.
 * Created by Cecile on 24/05/2017.
 */

public class PhotoContent extends ContributionContent {

    private Bitmap photo;

    public PhotoContent(String contextId, Bitmap photo) {
        super(contextId);
        this.photo = photo;
    }

    /*
    Constructors
     */

    public PhotoContent(String contextId) {
        super(contextId);
    }

    /*
    Database model
     */

    @Override
    public ContributionContent.Model toModel() {
        // TODO add url creation (send photo to database)
        ContributionContent.Model model = new Model(getContextId(), getTitle());
        model.putExtra("url", "http://www.example.com");
        return model;
    }

    public PhotoContent(PhotoContent.Model model) {
        super(model.getContextId());
        // TODO load photo from URL via Firebase Storage and set it
    }

    /*
    Getters
     */

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    /*
    Functions needed for a parcelable
     */

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeParcelable(photo,flags);
    }

    private PhotoContent(Parcel in) {
        super(in);
        photo = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Parcelable.Creator<PhotoContent> CREATOR
            = new Parcelable.Creator<PhotoContent>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public PhotoContent createFromParcel(Parcel in) {
            return new PhotoContent(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public PhotoContent[] newArray(int size) {
            return new PhotoContent[size];
        }
    };

}
