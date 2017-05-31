package hiatus.hiatusapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;

import hiatus.hiatusapp.ContributionContent.ContributionContent;
import hiatus.hiatusapp.ContributionContext.ContributionContext;

/**
 * A contribution bundle packs together the contribution context, content and user information.
 */
public class ContributionBundle {

    private String userId;
    private String contextId;
    private Date date;
    private ContributionContent content;

    /**
     *
     * @param userId the id of the user associated to this bundle
     * @param contextId the id of the contribution context associated to this bundle
     * @param content the content of the contribution
     * @param date the date of the bundle creation
     */
    public ContributionBundle(String userId, String contextId, ContributionContent content, Date date) {
        this.userId = userId;
        this.contextId = contextId;
        this.content = content;
        this.date = date;
    }

    public ContributionBundle(String userId, String contextId, ContributionContent content) {
        this.userId = userId;
        this.contextId = contextId;
        this.content = content;
        setDateToCurrentDate();
    }

    /**
     * Attaches the current date and time to the contribution bundle.
     * Can be called just before saving the bundle into the DB.
     */
    public void setDateToCurrentDate() {
        this.date = new Date();
    }

}
