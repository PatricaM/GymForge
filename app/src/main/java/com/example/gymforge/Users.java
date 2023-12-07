package com.example.gymforge;

public class Users {

    private String Uid;
    private  String Name;
    private  String PhoneNumber;
    private  String Email;
    private  String Password;
    private int usertype;

    public int getUsertype() {
        return usertype;
    }
    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public Users() {
    }

    public Users(String uid, String name, String phoneNumber, String email, String password, int usertype) {
        Uid = uid;
        Name = name;
        PhoneNumber = phoneNumber;
        Email = email;
        Password = password;
        usertype = usertype;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        this.Uid = uid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
