package com.dmdddm.sws;

public class User {
    private String UserName;
    private String UserPwd;
    public User(String name,String pwd){
        UserName = name;
        UserPwd = pwd;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPwd() {
        return UserPwd;
    }

    public void setUserPwd(String userPwd) {
        UserPwd = userPwd;
    }
}
