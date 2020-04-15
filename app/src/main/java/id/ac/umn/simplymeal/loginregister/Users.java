package id.ac.umn.simplymeal.loginregister;

public class Users {

    private String image;
    public String userName;
    public String emailUser;
    public String passUser;
    public Integer phoneUser;
    public String firstName;
    public String lastName;
    private String address;
    private String gender;
    private String phoneNumber;
    private String birthOfDate;

    public  Users() {

    }

    public Users(String userName, String emailUser, String passUser, Integer phoneUser,
                 String firstName, String lastName, String birthOfDate, String gender,
                 String address, String phoneNumber, String image){
        this.userName = userName;
        this.emailUser = emailUser;
        this.passUser = passUser;
        this.phoneUser = phoneUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthOfDate = birthOfDate;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.image = image;
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

    public String getBirthOfDate() {
        return birthOfDate;
    }

    public void setBirthOfDate(String birthOfDate) {
        this.birthOfDate = birthOfDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
