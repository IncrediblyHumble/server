package com.incredibly_humble.server;

import com.incredibly_humble.models.Location;
import com.incredibly_humble.models.User;
import com.incredibly_humble.models.WaterReport;
import com.incredibly_humble.models.WaterReports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class LocalDatabase {
    //User Columns
    private static String NAME = "name";
    private static String EMAIL = "email";
    private static String PASS = "password";
    private static String ADDR = "address";
    private static String TYPE = "objType";
    private static String SUBD = "subscribed";
    private static String PHONE = "phone";
    private static String LOG_FAILED = "numFailedLogin";
    private static String DATE = "date";
    private static String LOC = "location";
    private static String COND = "condition";
    private static String LAT = "lattitude";
    private static String LON = "longitude";
    private static String ID = "ID";
    private static String DEFAULT_ERROR_MSG = "Error Occured";
    private static final String DRIVER = "org.h2.Driver";
    private Connection conn = null;

    //keep in order of database rows
    public void establishConnection(String filePath) {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection("jdbc:h2:/" + filePath + ";IFEXISTS=TRUE");
        } catch (Exception e) {
            try {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection("jdbc:h2:/" + filePath + ";");
                createTables();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
    }

    public void closeConnectoin() throws Exception {
        conn.close();
        System.out.println("closed");
    }


    private void createTables() throws Exception {
        String executeString = "CREATE TABLE Users("
                + NAME + " Text,"
                + EMAIL + " Text,"
                + PASS + " Text,"
                + TYPE + " Text,"
                + SUBD + " BIT,"
                + ADDR + " Text,"
                + PHONE + " Text,"
                + LOG_FAILED + " INT NOT NULL);";
        conn.createStatement().execute(executeString);
        executeString = "CREATE TABLE WaterReports("
                + DATE + " BIGINT,"
                + LOC + " TEXT,"
                + NAME + " TEXT,"
                + TYPE + " TEXT,"
                + COND + " TEXT,"
                + LAT + " TEXT,"
                + LON + " TEXT,"
                + ID + " int NOT NULL AUTO_INCREMENT);";
        conn.createStatement().execute(executeString);
    }

    /**
     * attempts to login. If successful will return the user (without password)
     * IF unsuccesful will return user with error instead of name and all else null.
     * Increments login attempts if the email exists but the password is wrong.
     *
     * @param email
     * @param pass
     * @return
     */
    public User login(String email, String pass) {
        try {
            String executeString = String.format("SELECT * FROM  Users WHERE %s='%s'",EMAIL, email);
            ResultSet set = conn.createStatement().executeQuery(executeString);
            if (set.next()) {
                int logFailed = Integer.valueOf(set.getNString(LOG_FAILED));
                if (logFailed >= 3) {
                    return new User("Too many incorrect login attempts. Please reset password", null, null, null);
                } else if (set.getNString(PASS).equals(pass)) {
                    return getUser(set);
                } else {
                    incrementLogFailed(email, logFailed);
                    return new User("Password incorrect", null, null, null);
                }

            } else {
                return new User("User does not exist", null, null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new User(DEFAULT_ERROR_MSG, null, null, null);
    }

    /**
     * adds the user to the databse if the email isn't already taken
     *
     * @param u
     * @return
     */
    public User addUser(User u) {
        try {
            if (exists(u)) {
                return new User("User Already Exists", null, null, null);
            }
            String executeString = "INSERT INTO Users VALUES('" +
                    u.getName() + "', '" + u.getEmail() + "', '" + u.getPassword() + "', '"
                    + u.getType().toString() + "', " + u.getSubscribed() + ", '"
                    + u.getAddress() + "', '" + u.getPhone() + "', 0);";
            conn.createStatement().execute(executeString);
            return u;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new User(DEFAULT_ERROR_MSG, null, null, null);
    }

    public User updateUser(User u) {
        try {
            String executeString = "SELECT * FROM  Users WHERE email = '" + u.getEmail()+"'";
            ResultSet set = conn.createStatement().executeQuery(executeString);
            if (!set.next()) {
                return new User("User Does Not Exist", null, null, null);
            }
            String password = u.getPassword() == null ? set.getNString(PASS) : u.getPassword();
            executeString = String.format("UPDATE Users SET %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s' WHERE %s='%s'",
                    NAME, u.getName(), PASS, password, TYPE, u.getType().toString(),
                    SUBD, u.getSubscribed(), ADDR, u.getAddress(), PHONE, u.getPhone(), EMAIL, u.getEmail());
            conn.createStatement().executeUpdate(executeString);
            return u;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new User(DEFAULT_ERROR_MSG, null, null, null);
    }

    /**
     * check if u exists as a user in the databse
     *
     * @param u
     * @return
     */
    private boolean exists(User u) {
        try {
            String executeString = "SELECT * FROM  Users WHERE email = '" + u.getEmail() + "'";
            ResultSet set = conn.createStatement().executeQuery(executeString);
            return set.next();
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * increments the amount of failed login attempts for the given user.
     *
     * @param email
     * @param logFailed
     * @throws Exception
     */
    private void incrementLogFailed(String email, int logFailed) throws Exception {
        String executeString = "UPDATE USERS SET " + LOG_FAILED + " = '" + (logFailed + 1) +
                "' WHERE " + EMAIL + "='" + email+"'";
        conn.createStatement().executeUpdate(executeString);
    }

    /**
     * takes a result set and parses the columns to return a User object with defined fields, without the password.
     *
     * @param set
     * @return
     * @throws Exception
     */
    public User getUser(ResultSet set) throws Exception {
        return new User(
                set.getNString(NAME),
                set.getNString(EMAIL),
                null,
                User.AccountType.valueOf(set.getNString(TYPE)),
                Boolean.valueOf(set.getNString(SUBD)),
                set.getNString(ADDR),
                set.getNString(PHONE));
    }


    public WaterReport addWaterReoprt(WaterReport report) {
        try {
            String executeString = String.format("INSERT INTO WaterReports (%s, %s, %s, %s, %s, %s) " +
                            "VALUES('%s', '%s', '%s', '%s', '%f', '%f')",
                    DATE, TYPE, COND, NAME, LAT, LON,
                    report.getDateReported().getTime(),
                    report.getType().toString(), report.getCondition().toString(), report.getWorkerName(),
                    report.getLocation().getLatitude(), report.getLocation().getLongitude());
            conn.createStatement().execute(executeString);
            executeString = String.format("SELECT * FROM  WaterReports WHERE %s='%d'",DATE, report.getDateReported().getTime());
            ResultSet set = conn.createStatement().executeQuery(executeString);
            set.next();
            return getWaterReport(set);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WaterReports getWaterReports() {
        ArrayList<WaterReport> reports = new ArrayList<>();
        try {
            String executeString = "SELECT * FROM  WaterReports";
            ResultSet set = conn.createStatement().executeQuery(executeString);
            while (set.next()) {
                reports.add(getWaterReport(set));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new WaterReports(reports);
    }

    private WaterReport getWaterReport(ResultSet set) throws Exception {
        return new WaterReport(
                Integer.valueOf(set.getNString(ID)),
                new Date(Long.valueOf(set.getNString(DATE))),
                new Location(Double.valueOf(set.getNString(LAT)), Double.valueOf(set.getNString(LON))),
                set.getNString(NAME),
                WaterReport.WaterType.valueOf(set.getNString(TYPE)),
                WaterReport.WaterCondition.valueOf(set.getNString(COND))
        );
    }

    public void deleteWaterReport(WaterReport r) throws Exception{
        String executeString = String.format("DELETE FROM WaterReports WHERE %s='%d'",ID,r.getId());
        conn.createStatement().execute(executeString);
    }
}