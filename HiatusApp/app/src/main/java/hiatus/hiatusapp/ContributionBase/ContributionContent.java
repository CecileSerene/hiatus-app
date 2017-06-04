package hiatus.hiatusapp.ContributionBase;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;


/**
 * Content of a contribution.
 * Created by Cecile on 24/05/2017.
 */

public abstract class ContributionContent implements Parcelable {

    private String contextId;
    private String title;

    public ContributionContent(String contextId) {
        this(contextId, "");  // empty title by default
    }

    public ContributionContent(String contextId, String title) {
        this.contextId = contextId;
        this.title = title;
    }

    // necessary empty constructor
    public ContributionContent() {}

    /*
    Database model
     */

    /**
     * This class allows us to decouple the Java content of a ContributionContent and the way
     * we represent it in the cloud database (only through strings, e.g. referencing other database
     * nodes).
     * Each subclass of ContributionContent must define toModel() method and Content(Model model) constructor.
     */
    public static class Model {
        public static final int TYPE_TEXT = 0;
        public static final int TYPE_PHOTO = 1;

        private String contextId;
        private int type;
        private String title;
        private Map<String, String> data;

        public Model() {}

        public Model(String contextId, String title) {
            this.contextId = contextId;
            this.title = title;
            data = new HashMap<>();
        }

        public void putExtra(String key, String value) {
            data.put(key, value);
        }
        public String getExtra(String key) {return data.get(key);}

        public String getContextId() {return contextId;}
        public int getType() {return type;}
        public void setType(int type) {
            this.type = type;
        }
        public String getTitle() {return title;}

        public Map<String, String> getData() {
            return data;
        }
    }

    public abstract Model toModel();

    /*
    Getters and setters
     */

    public String getContextId() {
        return contextId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    /*
    Functions needed for a parcelable
     */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(contextId);
        out.writeString(title);
    }

    protected ContributionContent(Parcel in) {
        contextId = in.readString();
        title = in.readString();
    }


}
