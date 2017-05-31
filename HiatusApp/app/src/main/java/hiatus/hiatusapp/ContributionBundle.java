package hiatus.hiatusapp;

import java.util.Date;

import hiatus.hiatusapp.ContributionContent.ContributionContent;

/**
 * A contribution bundle packs together the contribution context, content and user information.
 */
public class ContributionBundle {

    private int userId;
    private int contextId;
    private Date date;
    private ContributionContent content;

    /**
     *
     * @param userId the id of the user associated to this bundle
     * @param contextId the id of the contribution context associated to this bundle
     * @param content the content of the contribution
     * @param date the date of the bundle creation
     */
    public ContributionBundle(int userId, int contextId, ContributionContent content, Date date) {
        this.userId = userId;
        this.contextId = contextId;
        this.content = content;
        this.date = date;
    }

    public ContributionBundle(int userId, int contextId, ContributionContent content) {
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
