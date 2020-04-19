package com.dmdddm.sws;

public class User {
    private String UserName;
    private String UserPwd;
    private String isLogin;
    private String isAuto;

    public User(String userName, String userPwd, String isLogin, String isAuto) {
        UserName = userName;
        UserPwd = userPwd;
        this.isLogin = isLogin;
        this.isAuto = isAuto;
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

    public String getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(String isLogin) {
        this.isLogin = isLogin;
    }

    public String getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(String isAuto) {
        this.isAuto = isAuto;
    }
}
