package hiatus.hiatusapp.ContributionContent;


import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hiatus.hiatusapp.ContributionContext.ContributionPhoto;

/**
 * Created by Cecile on 24/05/2017.
 */

public class PhotoContent implements ContributionContent {

    private Bitmap photo;
    private String title;
    private ContributionPhoto context;

    public PhotoContent(Bitmap photo, ContributionPhoto context) {
        this.photo = photo;
        this.title = "";
        this.context = context;
    }

    public PhotoContent(Bitmap photo, String title, ContributionPhoto context) {
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

    public ContributionPhoto getContext() {
        return context;
    }

    public void setContext(ContributionPhoto context) {
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
}
