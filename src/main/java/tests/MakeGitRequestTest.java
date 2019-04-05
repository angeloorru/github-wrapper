package tests;

import csv.reader.CSVReader;
import data.model.GitDataFields;
import data.model.SqLiteDAO;
import org.apache.commons.validator.routines.UrlValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MakeGitRequestTest {

    @Mock
    GitDataFields gitDataFields;

    private SqLiteDAO sqLiteDAOToTest = spy(new SqLiteDAO());
    private CSVReader csvReader = spy(new CSVReader());

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDataIntegrityOfStubGitHubReturnedObject() {
        when(gitDataFields.getDescription()).thenReturn("Test Git description");
        when(gitDataFields.getAppName()).thenReturn("Test app Name");
        when(gitDataFields.getLanguage()).thenReturn("Java");
        when(gitDataFields.getNumberOfIssues()).thenReturn(6);
        when(gitDataFields.getLink()).thenReturn("https://github.com/angeloorru/GitHubCrawler.git");
        when(gitDataFields.getCreatedAt()).thenReturn("2017-07-12 16:46");
        when(gitDataFields.getVersionNumber()).thenReturn("2.5.2");
        when(gitDataFields.getPushedAt()).thenReturn("2018/05/01 10:00");
        when(gitDataFields.getUpdatedAt()).thenReturn("2018-06-14 11:49");
        when(gitDataFields.getStars()).thenReturn(5);
        when(gitDataFields.getDate()).thenReturn("2019-04-01 11:49");

        assertEquals(gitDataFields.getDescription(), "Test Git description");
        assertEquals(gitDataFields.getAppName(), "Test app Name");
        assertEquals(gitDataFields.getLanguage(), "Java");
        assertEquals(gitDataFields.getNumberOfIssues(), 6);
        assertEquals(gitDataFields.getLink(), "https://github.com/angeloorru/GitHubCrawler.git");
        assertEquals(gitDataFields.getCreatedAt(), "2017-07-12 16:46");
        assertEquals(gitDataFields.getVersionNumber(), "2.5.2");
        assertEquals(gitDataFields.getPushedAt(), "2018/05/01 10:00");
        assertEquals(gitDataFields.getUpdatedAt(), "2018-06-14 11:49");
        assertEquals(gitDataFields.getStars(), 5);
        assertEquals(gitDataFields.getDate(), "2019-04-01 11:49");
    }

    @Test
    public void testThatHttpLinkIsExtractedCorrectly() {

        UrlValidator urlValidator = new UrlValidator();

        when(gitDataFields.getDescription()).thenReturn("Test Git description");
        when(gitDataFields.getAppName()).thenReturn("Test app Name");
        when(gitDataFields.getLanguage()).thenReturn("Java");
        when(gitDataFields.getNumberOfIssues()).thenReturn(6);
        when(gitDataFields.getLink()).thenReturn("https://github.com/angeloorru/GitHubCrawler.git");
        when(gitDataFields.getCreatedAt()).thenReturn("2017-07-12 16:46");
        when(gitDataFields.getVersionNumber()).thenReturn("2.5.2");
        when(gitDataFields.getPushedAt()).thenReturn("2018/05/01 10:00");
        when(gitDataFields.getUpdatedAt()).thenReturn("2018-06-14 11:49");
        when(gitDataFields.getStars()).thenReturn(5);
        when(gitDataFields.getDate()).thenReturn("2019-04-01 11:49");

        String mockCsvLine = gitDataFields.getDescription() + ","
                + gitDataFields.getDescription() + ","
                + gitDataFields.getAppName() + ","
                + gitDataFields.getLanguage() + ","
                + gitDataFields.getNumberOfIssues() + ","
                + gitDataFields.getLink() + ","
                + gitDataFields.getCreatedAt() + ","
                + gitDataFields.getVersionNumber() + ","
                + gitDataFields.getPushedAt() + ","
                + gitDataFields.getUpdatedAt() + ","
                + gitDataFields.getStars() + ","
                + gitDataFields.getDate() + ",";

        List<String> mockList = new ArrayList<>();
        mockList.add(mockCsvLine);

        csvReader.getHttpsLinksFromCsvList(mockList);

        for (String testUrl : csvReader.getHttpsLinksFromCsvList(mockList)) {
            assertThat(urlValidator.isValid(testUrl), is(true));
        }
    }

    @Test
    public void testThatHttpLinkIsNotValid() {

        UrlValidator urlValidator = new UrlValidator();

        when(gitDataFields.getDescription()).thenReturn("Test Git description");
        when(gitDataFields.getAppName()).thenReturn("Test app Name");
        when(gitDataFields.getLanguage()).thenReturn("Java");
        when(gitDataFields.getNumberOfIssues()).thenReturn(6);
        when(gitDataFields.getLink()).thenReturn("httpl://github.com/angeloorru/fakeUrl");
        when(gitDataFields.getCreatedAt()).thenReturn("2017-07-12 16:46");
        when(gitDataFields.getVersionNumber()).thenReturn("2.5.2");
        when(gitDataFields.getPushedAt()).thenReturn("2018/05/01 10:00");
        when(gitDataFields.getUpdatedAt()).thenReturn("2018-06-14 11:49");
        when(gitDataFields.getStars()).thenReturn(5);
        when(gitDataFields.getDate()).thenReturn("2019-04-01 11:49");

        String mockCsvLine = gitDataFields.getDescription() + ","
                + gitDataFields.getDescription() + ","
                + gitDataFields.getAppName() + ","
                + gitDataFields.getLanguage() + ","
                + gitDataFields.getNumberOfIssues() + ","
                + gitDataFields.getLink() + ","
                + gitDataFields.getCreatedAt() + ","
                + gitDataFields.getVersionNumber() + ","
                + gitDataFields.getPushedAt() + ","
                + gitDataFields.getUpdatedAt() + ","
                + gitDataFields.getStars() + ","
                + gitDataFields.getDate() + ",";

        List<String> mockList = new ArrayList<>();
        mockList.add(mockCsvLine);

        csvReader.getHttpsLinksFromCsvList(mockList);

        for (String testUrl : csvReader.getHttpsLinksFromCsvList(mockList)) {
            assertThat(urlValidator.isValid(testUrl), is(false));
        }
    }

    @Test
    public void testThatDataIsCorrectlyInsertedIntoTheDatabase() {
        when(gitDataFields.getDescription()).thenReturn("Test Git description");
        when(gitDataFields.getAppName()).thenReturn("Test app Name");
        when(gitDataFields.getLanguage()).thenReturn("Java");
        when(gitDataFields.getNumberOfIssues()).thenReturn(6);
        when(gitDataFields.getLink()).thenReturn("https://github.com/angeloorru/GitHubCrawler.git");
        when(gitDataFields.getCreatedAt()).thenReturn("2017-07-12 16:46");
        when(gitDataFields.getVersionNumber()).thenReturn("2.5.2");
        when(gitDataFields.getPushedAt()).thenReturn("2018/05/01 10:00");
        when(gitDataFields.getUpdatedAt()).thenReturn("2018-06-14 11:49");
        when(gitDataFields.getStars()).thenReturn(5);
        when(gitDataFields.getDate()).thenReturn("2019-04-01 11:49");

        Boolean result = sqLiteDAOToTest.insertDataIntoDb(
                gitDataFields.getDescription(), gitDataFields.getAppName(), gitDataFields.getLanguage(),
                gitDataFields.getNumberOfIssues(), gitDataFields.getLink(), gitDataFields.getCreatedAt(),
                gitDataFields.getVersionNumber(), gitDataFields.getPushedAt(), gitDataFields.getUpdatedAt(),
                gitDataFields.getStars(), gitDataFields.getDate());

        assertThat(result, is(true));
    }
}