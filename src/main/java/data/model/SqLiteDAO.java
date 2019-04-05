package data.model;

import java.sql.*;
import java.util.logging.Logger;

public class SqLiteDAO {

    private static final String SQLITE_JDBC = "org.sqlite.JDBC";
    private static final String DATABASE_CONNECTION_URL = "jdbc:sqlite:";
    private static final String DATABASE_SCHEMA = "wagtail_plugins";

    private static final String ADD_DATABASE_TABLE = "CREATE TABLE IF NOT EXISTS `plugin_details`("
            + "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + "`description` TEXT, "
            + "`app_name` TEXT, " + "`language` TEXT, " + " `no_of_issues` TEXT, "
            + "`link` TEXT, " + "`version_number` TEXT, " + "`created_at` TEXT, "
            + "`pushed_at`	TEXT, " + "`updated_at`	TEXT," + "`stars` INTEGER, "
            + "`date` TEXT" + ");";

    private static String INSERT_INTO_TABLE = "INSERT INTO plugin_details(description, app_name, language, "
            + "no_of_issues, link, created_at, version_number, pushed_at, "
            + "updated_at, stars, date) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

    private static final Logger LOGGER = Logger.getLogger(SqLiteDAO.class.getName());

    private static Connection connection;

    /**
     * @param database
     * @return Connection connection
     * @desc Establishes the connection to SQLite database using the jdbc driver.
     */
    private static Connection connect(String database) {
        try {
            Class.forName(SQLITE_JDBC);
            StringBuilder sqliteDatabase = new StringBuilder(DATABASE_CONNECTION_URL.concat(database).concat(".db"));

            //Start the connection to the database
            connection = DriverManager.getConnection(sqliteDatabase.toString());

            LOGGER.info("[Connection to SQLite database has been established]");
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return connection;
    }

    /**
     * @desc Create an sqlite table.
     */
    private static void createTable() {
        Statement createTableStatement;

        try {
            String sql = ADD_DATABASE_TABLE;

            createTableStatement = connection.createStatement();
            createTableStatement.executeUpdate(sql);
            createTableStatement.close();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }

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
     * @desc Executes insert statement against the specified schema.
     */
    public Boolean insertDataIntoDb(
            String description, String appName, String language, int numberOfIssues, String link, String createdAt,
            String versionNumber, String pushedAt, String updatedAt, int stars, String date) {

        Boolean inserted = false;
        String sql = INSERT_INTO_TABLE;

        try {
            connection = connect(DATABASE_SCHEMA);
            createTable();

            PreparedStatement preparedStatement = getPreparedStatement(
                    description, appName, language, numberOfIssues, link, createdAt,
                    versionNumber, pushedAt, updatedAt, stars, date, sql, connection);

            if (preparedStatement.executeUpdate() == 1) {
                LOGGER.info("[The data has been inserted successfully into the database]");
                inserted = true;
            } else {
                LOGGER.info("[The data was not inserted]");
                inserted = false;
            }

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return inserted;
    }

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
     * @param sql
     * @param conn
     * @return Prepared Statement
     * @throws SQLException
     */
    private static PreparedStatement getPreparedStatement(
            String description, String appName, String language, int numberOfIssues, String link, String createdAt,
            String versionNumber, String pushedAt, String updatedAt, int stars, String date, String sql,
            Connection conn) throws SQLException {

        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setString(1, description);
        preparedStatement.setString(2, appName);
        preparedStatement.setString(3, language);
        preparedStatement.setInt(4, numberOfIssues);
        preparedStatement.setString(5, link);
        preparedStatement.setString(6, createdAt);
        preparedStatement.setString(7, versionNumber);
        preparedStatement.setString(8, pushedAt);
        preparedStatement.setString(9, updatedAt);
        preparedStatement.setInt(10, stars);
        preparedStatement.setString(11, date);

        return preparedStatement;
    }
}
