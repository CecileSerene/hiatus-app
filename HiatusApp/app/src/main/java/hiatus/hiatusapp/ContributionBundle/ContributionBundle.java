package hiatus.hiatusapp.ContributionBundle;


import java.util.Date;

import hiatus.hiatusapp.ContributionContent.ContributionContent;

/**
 * A contribution bundle packs together the contribution context, content and user information.
 */
public class ContributionBundle {

    public static int WAITING = 0;
    public static int DENIED = 1;
    public static int ACCEPTED = 2;

    private String id;
    private String date;
    private ContributionContent content;
    private String contextId;
    private int state;

    /**
     * @param contextId the contribution context associated to this bundle
     * @param content the content of the contribution
     * @param date the date of the bundle creation
     */
    private ContributionBundle(String id, String contextId, ContributionContent content, String date) {
        this.id = id;
        this.contextId = contextId;
        this.content = content;
        this.date = date;
        this.state = WAITING;
    }

    public ContributionBundle(String id, String contextId, ContributionContent content) {
        this(id, contextId, content, (new Date()).toString());
    }

    // Empty constructor
    public ContributionBundle() {}

    /*
    Getters
     */

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public ContributionContent getContent() {
        return content;
    }

    public String getContextId() {
        return contextId;
    }

    public int getState() {
        return state;
    }
}
