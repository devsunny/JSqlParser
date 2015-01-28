package com.asksunny.demo.domain;


public class Users3 implements Serializable
{

    private static final long serialVersionUID = 1L;
    private String userId;
    private String firstName;
    private String lastName;
    private String password;


    public String getUserId(){ return this.userId; }
    public void setUserId(String userId){ this.userId = userId; }
    public String getFirstName(){ return this.firstName; }
    public void setFirstName(String firstName){ this.firstName = firstName; }
    public String getLastName(){ return this.lastName; }
    public void setLastName(String lastName){ this.lastName = lastName; }
    public String getPassword(){ return this.password; }
    public void setPassword(String password){ this.password = password; }


}

