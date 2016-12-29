package com.hellopay.test;

/**
 * Created by vuthaiduong on 12/29/16.
 */
public class UserInfo {

    private String country;
    private String name;
    private String prefix;
    private String phoneNumber;
    private String email;
    private String password;
    private String inCase;


    public UserInfo(){
    }

    public UserInfo(String name, String password, String email, String phoneNumber, String country, String prefix, String inCase){
        this.country = country;
        this.name = name;
        this.prefix = prefix;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.inCase = inCase;

    }


    public void setCountry(String country){
        this.country = country;
    }
    public String getCountry(){
        return country;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setPrefix(String prefix){
        this.prefix = prefix;
    }
    public String getPrefix(){
        return prefix;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

    public void setInCase(String inCase){this.inCase = inCase;}

    public String getInCase(){
        return inCase;
    }

}
