package com.incredibly_humble.server.db;


import com.incredibly_humble.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDatabase {
    //User Columns
    private static String NAME = "name";
    private static String EMAIL = "email";
    private static String PASS = "password";
    private static String ADDR = "address";
    private static String TYPE = "objType";
    private static String SUBD = "subscribed";
    private static String PHONE = "phone";
    private static String LOG_FAILED = "numFailedLogin";
    private static String DEFAULT_ERROR_MSG = "Error Occured";

    private Connection conn;
    public UserDatabase(Connection conn){
        this.conn = conn;
    }

    public void createTable() throws SQLException {
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
                    return get(set);
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
    public User add(User u) {
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

    public User update(User u) {
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
    public User get(ResultSet set) throws Exception {
        return new User(
                set.getNString(NAME),
                set.getNString(EMAIL),
                null,
                User.AccountType.valueOf(set.getNString(TYPE)),
                Boolean.valueOf(set.getNString(SUBD)),
                set.getNString(ADDR),
                set.getNString(PHONE));
    }

}
