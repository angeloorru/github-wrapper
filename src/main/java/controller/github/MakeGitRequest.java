package controller.github;

import csv.reader.CSVReader;
import data.model.GitDataFields;
import data.model.SqLiteDAO;
import helper.date.DateFormatConverter;
import org.apache.commons.validator.routines.UrlValidator;
import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @desc A thread class that allow to login and parse git hub repositories.
 */
public class MakeGitRequest implements Runnable {
    /**
     * @desc To be set to your credential login.
     */
    private static final String TOKEN = null;
    private static final String USER_LOGIN = null;
    private static final String USER_PASSWORD = null;

    private static final String REGEX_LETTERS_AND_SPACE_ONLY = "[a-zA-Z ]";
    private static final String GIT_PATH = ".git";
    private static final String ON_FORWARD_SLASH = "/";

    private GitHub gitHub;

    private static final Logger LOGGER = Logger.getLogger(MakeGitRequest.class.getName());

    /**
     * @return Boolean login
     * @throws IOException
     * @desc Performs the login to github
     */
    private Boolean loginToGitHub() {
        // Login to github...
        try {
            if (TOKEN != null) {
                gitHub = GitHub.connectUsingOAuth(TOKEN);
                LOGGER.info("[Attempting to connect using TOKEN]");
                return true;
            } else if (USER_LOGIN != null && USER_PASSWORD != null) {
                gitHub = GitHub.connectUsingPassword(USER_LOGIN, USER_PASSWORD);
                LOGGER.info("[Attempting to connect using username and password]");
                return true;
            } else if (USER_LOGIN == null && USER_PASSWORD == null) {
                gitHub = GitHub.connectAnonymously();
                LOGGER.info("[Attempting to connect anonymously]");
            }
        } catch (IOException e) {
            LOGGER.severe("Cannot login to GitHub:\n");
            LOGGER.severe(e.getMessage());
        }
        return false;
    }

    /**
     * @param url
     * @return Boolean valid
     * @desc Validate any given URL
     */
    private Boolean validateUrl(String url) {

        Boolean valid = false;

        UrlValidator urlValidator = new UrlValidator();
        if (urlValidator.isValid(url)) {
            valid = true;
        }
        return valid;
    }

    /**
     * @param url
     * @return
     * @desc Extracts the params that need to be passed to the GHRepository object.
     */
    private String getRepoNameForAPIRequest(String url) {
        String repoName = null;

        if (!url.isEmpty()) {
            String[] tmpArray = url.split(ON_FORWARD_SLASH);

            repoName = tmpArray[3] + "/" + tmpArray[4].replace(GIT_PATH, "");
        }
        return repoName;
    }

    /**
     * @param releaseBody
     * @return
     * @desc Format the version number if exists. Else it replaces with meaningful
     * text.
     */
    private String getReleaseNumberFromBody(GHRelease releaseBody) {
        String releaseVersion;

        if (releaseBody != null) {
            releaseVersion = releaseBody.getTagName().replaceAll(REGEX_LETTERS_AND_SPACE_ONLY, "");
        } else {
            releaseVersion = "n/a";
        }
        return releaseVersion;
    }

    /**
     * @param description
     * @return
     */
    private String getDescriptionFromResponseBody(String description) {
        if (description == null) {
            description = "n/a";
        }
        return description;
    }

    /**
     * @param link
     * @return GitDataFields
     * @throws IOException
     */
    private GitDataFields getGitDataFields(String link) throws IOException {

        GHRepository repository = gitHub.getRepository(getRepoNameForAPIRequest(link));
        DateFormatConverter dateHelper = new DateFormatConverter();

        return new GitDataFields(
                repository.getDescription(),
                repository.getFullName(),
                repository.getLanguage(),
                repository.getOpenIssueCount(),
                link,
                dateHelper.convertDate(repository.getCreatedAt()),
                getReleaseNumberFromBody(repository.getLatestRelease()),
                dateHelper.convertDate(repository.getPushedAt()),
                dateHelper.convertDate(repository.getUpdatedAt()),
                repository.getStargazersCount(),
                dateHelper.getCurrentDate()
        );
    }

    /**
     * @desc Thread run()
     */
    public void run() {
        // Get the list of URLs...
        CSVReader reader = new CSVReader();
        List<String> data = reader.getDataFromCsv();
        List<String> dataLinks = reader.getHttpsLinksFromCsvList(data);
        Boolean isLoggedIn = loginToGitHub();

        try {
            for (String link : dataLinks) {
                if (validateUrl(link)) {
                    GitDataFields gitFields = getGitDataFields(link);

                    // Insert the data in the database....
                    SqLiteDAO sqLiteDao = new SqLiteDAO();
                    sqLiteDao.insertDataIntoDb(getDescriptionFromResponseBody(gitFields.getDescription()),
                            gitFields.getAppName(), gitFields.getLanguage(), gitFields.getNumberOfIssues(),
                            gitFields.getLink(), gitFields.getCreatedAt(), gitFields.getVersionNumber(),
                            gitFields.getPushedAt(), gitFields.getUpdatedAt(), gitFields.getStars(),
                            gitFields.getDate());
                }
                /*
                 ** 1 minute sleep if anonymous login. Current github policy
                 *  If the policy has changed, this if block can be removed
                 */
                if (!isLoggedIn) {
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        LOGGER.severe(e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
