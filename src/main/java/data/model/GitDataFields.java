package data.model;

/*
	Immutable Accessor for the git repository object
 */
public class GitDataFields {

    private String description;
    private String date;
    private String createdAt;
    private String pushedAt;
    private String updatedAt;
    private int numberOfIssues;
    private String versionNumber;
    private String link;
    private String language;
    private String appName;
    private int stars;

    /**
     * @param description
     * @param appName
     * @param language
     * @param numberOfIssues
     * @param link
     * @param createdAt
     * @param versionNumber
     * @param pushedAt
     * @param updatedAt
     * @param stars
     * @param date
     * @desc Constructor
     */
    public GitDataFields(String description, String appName, String language, int numberOfIssues, String link,
                         String createdAt, String versionNumber, String pushedAt, String updatedAt, int stars, String date) {
        this.description = description;
        this.appName = appName;
        this.language = language;
        this.numberOfIssues = numberOfIssues;
        this.link = link;
        this.createdAt = createdAt;
        this.versionNumber = versionNumber;
        this.pushedAt = pushedAt;
        this.updatedAt = updatedAt;
        this.stars = stars;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getPushedAt() {
        return pushedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public int getNumberOfIssues() {
        return numberOfIssues;
    }

    public String getLink() {
        return link;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public String getLanguage() {
        return language;
    }

    public String getAppName() {
        return appName;
    }

    public int getStars() {
        return stars;
    }
}
