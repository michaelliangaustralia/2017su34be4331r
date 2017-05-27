/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.backend.member.login;

import java.sql.ResultSet;
import suber.Suber;
import suber.backend.SuberDB;
import suber.backend.member.session.Session;
import suber.backend.security.Crypto;

/**
 *
 * @author Andrew
 */
public class Login {

    SuberDB db;
    Session session;

    public Login() {
        db = new SuberDB();
        session = Suber.session;
    }

    /**
     * Attempts to log into database with provided credentials
     *
     * @param email Email as a string input
     * @param password Password as a string input
     * @return boolean depending on login success
     */
    public boolean tryLogin(String email, String password) throws Exception {
        email = email.toLowerCase();
        // If true check accounttype based on 
        String query = "SELECT `user_ID`, `email`, `password` FROM " + db.getDatabaseName() + ".user_list "
                + "WHERE email = \"" + email + "\" "
                + "AND password = \"" + Crypto.encryptString(password) + "\"";
        
        try {
            ResultSet results = db.executeQuery(query);
            results.next();
            String retrievedID = results.getString(1);
            String retrievedUser = results.getString(2);
            String retrievedPass = results.getString(3);
            if (retrievedUser.equals(email) && retrievedPass.equals(Crypto.encryptString(password))) {
                System.out.println("Successfully logged in!");
                session.setEmail(email);
                session.setUserId(retrievedID);
                session.setPassword(Crypto.encryptString(password));
                checkAccountType(email);
                return true;
            } return false;
        } catch (Exception e) {
            return false;
        }
    }

    
    /**
     * This function checks the account type and sets it for the session
     * @param email Input email as string
     */
    public void checkAccountType(String email) {

        String checkStaff = "SELECT staff_id from " + db.getDatabaseName() + ".staff_list s JOIN user_list u WHERE (s.staff_id = u.user_id) AND u.email = '" + email.toLowerCase() + "';";
        String checkDriver = "SELECT driver_id from " + db.getDatabaseName() + ".driver_list WHERE `driver_id` = '" + session.getUserId() + "';";
        try {
            ResultSet results = db.executeQuery(checkStaff);
            while (results.next()) {
                String id = results.getString(1);
                if (id != null) {
                    session.setAccountType("Staff");
                    System.out.println("Staff");
                }
            }
        } catch (Exception notStaff) {
            try {
                ResultSet results = db.executeQuery(checkDriver);
                while (results.next()) {
                    String id = results.getString(1);
                    if (id != null ) {
                        session.setAccountType("Driver");
                        System.out.println("Driver");
                    }
                }
            } catch (Exception notDriver) {
                session.setAccountType("Rider");
                System.out.println("Rider");
            }
        }
    }

}
