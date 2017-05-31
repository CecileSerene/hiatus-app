package hiatus.hiatusapp.ContributionContext;

/**
 * Enum of
 * Created by Cecile on 03/05/2017.
 */

public enum Type {
    DRAWING("Drawing"), TEXT("Text"), PHOTO("Photo");

    private String type;

    Type(String type) {
        this.type = type;
    }

    String getType() {return type;}

    @Override
    public String toString() {
        return type;
    }
}

