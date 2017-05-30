package hiatus.hiatusapp.ContributionContent;


import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.ContributionContext.PhotoContext;

/**
 * Created by Cecile on 24/05/2017.
 */

public class PhotoContent extends ContributionContent {

    private Bitmap photo;
    private String title;
    private ContributionContext context;


    public PhotoContent(Bitmap photo, PhotoContext context) {
        this.photo = photo;
        this.title = "";
        this.context = context;
    }

    public PhotoContent(Bitmap photo, String title, PhotoContext context) {
        this.photo = photo;
        this.title = title;
        this.context = context;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ContributionContext getContext() {
        return context;
    }

    public void setContext(PhotoContext context) {
        this.context = context;
    }

    @Override
    public void display(View titleView, View photoView) {
        ((ImageView) photoView).setImageBitmap(this.photo);
        ((TextView) titleView).setText(this.title);
    }

    @Override
    public void sendToDatabase() {
        //TODO

    }

     /*
    Functions needed for a parcelable
     */

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(photo,flags);
        out.writeString(title);
        out.writeParcelable(context, flags);
    }

    private PhotoContent(Parcel in) {
        photo = in.readParcelable(Bitmap.class.getClassLoader());
        title = in.readString();
        context = in.readParcelable(ContributionContext.class.getClassLoader());
    }

    public PhotoContent() {
        // Normal actions performed by class, since this is still a normal object!
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
