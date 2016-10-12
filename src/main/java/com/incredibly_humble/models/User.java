package com.incredibly_humble.models;

import java.io.Serializable;

public class User implements Serializable {

    public static enum AccountType {
        USER,
        WORKER,
        MANAGER,
        ADMIN
    }

    private String name;
    private String password;
    private String email = "";
    AccountType type;
    private boolean subscribed;
    private String address = "";
    private String phone = "";

    public User(String name, String email, String password, AccountType type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public User(String name, String email, String password, AccountType type,
                boolean subscribed, String address, String phone) {
        this(name, email, password, type);
        this.phone = phone;
        this.subscribed = subscribed;
        this.address = address;
    }

    /**
     * @return the username of user
     */
    public String getName() {
        return name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the subscribed
     */
    public boolean isSubscribed() {
        return subscribed;
    }

    /**
     * @param subscribed the subscribed to set
     */
    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public AccountType getType() {
        return this.type;
    }

    public boolean getSubscribed(){
        return this.subscribed;
    }

    @Override
    public boolean equals(Object comparison) {
        if (comparison instanceof User) {
            User foo = (User) comparison;
            if (foo.getName().equals(this.name) && foo.getPassword().equals(this.password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
