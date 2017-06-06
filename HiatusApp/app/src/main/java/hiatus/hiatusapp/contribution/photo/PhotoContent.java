package hiatus.hiatusapp.contribution.photo;


import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import hiatus.hiatusapp.contribution.base.ContributionContent;

/**
 * Content for photo contributions.
 * Created by Cecile on 24/05/2017.
 */

public class PhotoContent extends ContributionContent {

    private Bitmap photo;
    private String url;

    public PhotoContent(String contextId, String title, Bitmap photo) {
        super(contextId, title);
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
        ContributionContent.Model model = new Model(getContextId(), getTitle());
        model.setType(Model.TYPE_PHOTO);
        model.putExtra("url", url);
        return model;
    }

    public PhotoContent(Model model) {
        super(model.getContextId(), model.getTitle());
        setUrl(model.getExtra("url"));
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /*
    Functions needed for a parcelable
     */

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeString(url);
        out.writeParcelable(photo,flags);
    }

    private PhotoContent(Parcel in) {
        super(in);
        url = in.readString();
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
