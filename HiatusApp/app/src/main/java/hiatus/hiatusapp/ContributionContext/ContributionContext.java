package hiatus.hiatusapp.ContributionContext;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cecile on 24/05/2017.
 * Describes a contribution
 * It is described by the administrator
 */

public abstract class ContributionContext implements Parcelable {

    public static int TYPE_TEXT = 0;
    public static int TYPE_PHOTO = 1;

    private String id;
    private String title;
    private String theme;
    private String instructions;
    private int type;
    protected boolean modificationsAllowed;
    protected double limitedTime;

    // Empty constructor
    public ContributionContext() {}

    public ContributionContext(String id, String title, String theme, String instructions) {
        this.id = id;
        this.title = title;
        this.theme = theme;
        this.instructions = instructions;
    }

    /**
     * Getters and setters
     */

    public String getId() {return id;}
    // Title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Theme
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    // Instructions
    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    // Type
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    // misc

    public boolean isModificationsAllowed() {
        return modificationsAllowed;
    }

    public void setModificationsAllowed(boolean modificationsAllowed) {
        this.modificationsAllowed = modificationsAllowed;
    }

    public double getLimitedTime() {
        return limitedTime;
    }

    public void setLimitedTime(double limitedTime) {
        this.limitedTime = limitedTime;
    }



    /*
    Functions needed because it implements Parcelable
     */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(theme);
        parcel.writeString(instructions);
        parcel.writeInt(type);
        parcel.writeInt(modificationsAllowed ? 1:0); //Because there is no writeBoolean method
        parcel.writeDouble(limitedTime);
    }

    protected ContributionContext(Parcel in){
        id = in.readString();
        title = in.readString();
        theme = in.readString();
        instructions = in.readString();
        type = in.readInt();
        modificationsAllowed = (in.readInt() != 0);
        limitedTime = in.readDouble();
    }

    public static final Parcelable.Creator<ContributionContext> CREATOR
            = new Parcelable.Creator<ContributionContext>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public ContributionContext createFromParcel(Parcel in) {
            return new ContributionContext(in) {
            };
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public ContributionContext[] newArray(int size) {
            return new ContributionContext[size];
        }
    };
}
