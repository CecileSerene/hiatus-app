package hiatus.hiatusapp.ContributionContext;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cecile on 24/05/2017.
 * Describes a contribution
 * It is described by the administrator
 */

public abstract class ContributionContext implements Parcelable {

    protected String instructions;
    //protected Type type; It seems as it is not needed
    protected String title;
    protected boolean modifications_allowed;
    protected double limited_time;
    protected String theme;

    public ContributionContext(){
        super();
    }



    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isModifications_allowed() {
        return modifications_allowed;
    }

    public void setModifications_allowed(boolean modifications_allowed) {
        this.modifications_allowed = modifications_allowed;
    }

    public double getLimited_time() {
        return limited_time;
    }

    public void setLimited_time(double limited_time) {
        this.limited_time = limited_time;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }


    /*
    Functions needed because it implements Parcelable
     */

    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(instructions);
        parcel.writeString(title);
        parcel.writeInt(modifications_allowed ? 1:0); //Because there is no writeBoolean method
        parcel.writeDouble(limited_time);
        parcel.writeString(theme);

    }

    private ContributionContext(Parcel in){
        instructions = in.readString();
        title = in.readString();
        modifications_allowed = (in.readInt() == 0) ? false : true;
        limited_time = in.readDouble();
        theme = in.readString();
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
    };*/
}
