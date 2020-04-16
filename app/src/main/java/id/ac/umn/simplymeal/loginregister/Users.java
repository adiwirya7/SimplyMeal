package id.ac.umn.simplymeal.loginregister;

public class Users {

    public String userName;
    public String emailUser;
    public String passUser;
    public Integer phoneUser;
    public String firstName;
    public String lastName;
    public  Users() {

    }

    public Users(String userName, String emailUser, String passUser, Integer phoneUser, String firstName, String lastName){
        this.userName = userName;
        this.emailUser = emailUser;
        this.passUser = passUser;
        this.phoneUser = phoneUser;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPassUser() {
        return passUser;
    }

    public void setPassUser(String passUser) {
        this.passUser = passUser;
    }

    public Integer getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(Integer phoneUser) {
        this.phoneUser = phoneUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
