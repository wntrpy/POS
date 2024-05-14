package application;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    // These are all the variables related to the System User
    private String branchID;
    private String userID;
    private String userType;
    private String loginError;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthdate;
    private Date hiredate;

    private String userImage;

    // TODO paano yung login sa main server say kung nahack yung local database and gumawa ng acc na wala dun sa main server.

    public Login(ConnectDB connection,String branchID, String userID, String password){

        try {
            PreparedStatement logindeets = connection.getConnection().prepareStatement("SELECT UserType, FirstName, Middlename, LastName, Birthdate, HireDate, UserPhoto FROM SYSUSER WHERE UserID = ? and UserPassword = ?");
            logindeets.setString(1,userID.trim());
            logindeets.setString(2,Security.toSecret(password).trim());
            System.out.println("Passed UserID: " + userID );
            System.out.println("Password: " + Security.toSecret(password));

            // validates credentials then stores in variables
            ResultSet type = logindeets.executeQuery();
            type.next();
            this.userType = type.getString("UserType");
            this.firstName = type.getString("FirstName");
            this.middleName = type.getString("MiddleName");
            this.lastName = type.getString("LastName");
            this.birthdate = type.getDate("Birthdate");
            this.hiredate = type.getDate("HireDate");
            this.userImage = type.getString("UserPhoto");
            // stores input values into the variables above.
            this.userID = userID;
            this.branchID = branchID;

            // logs into USERLOG table in the database
            PreparedStatement datetimelog = connection.getConnection().prepareStatement("INSERT INTO USERLOG (UserID,LogDateTime,BranchID,LogType) values(?,getdate(),?,'in')");
            datetimelog.setString(1,userID);
            datetimelog.setString(2,branchID);
            datetimelog.execute();

        }
        catch(SQLException e) {
            // catches errors during login
            loginError = "Wrong Username or Password.";
        }

    }

    // a buncha go gettas
    public String getBranchID() {
        return branchID;
    }

    public String getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public String getUserType() {
        return userType;
    }

    public String getUserImage(){
        return userImage;
    }

    public String getLoginError() {
        return loginError;
    }

}