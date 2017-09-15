package com.example.abd.intermediate_challenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by abd on 9/14/2017.
 */
// Object for the Username, Profile photo url, link to the profile.
public class Item {
    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("Avatar")
    @Expose
    private String Avatarurl;

    @SerializedName("html_url")
    @Expose
    private String htmlurl;

    public Item(String login, String avatarurl, String htmlurl) {
        this.login = login;
        this.Avatarurl = avatarurl;
        this.htmlurl = htmlurl;
    }

    // this is the get method for login
    public String getLogin() {
        return login;
    }

    // Here we Get the method for login
    public void setlogin(String login) {
        this.login = login;
    }

    // this is the get method for Image
    public String getAvatarurl() {
        return Avatarurl;
    }

    // Here we set the method for Image
    public void setAvatarurl(String avatarurl) {
        this.Avatarurl = avatarurl;
    }

    // Here we Get the method for githubLink
    public String getHtmlurl() {
        return htmlurl;
    }

    // Here we set the method for githubLink
    public void setHtmlurl(String htmlurl) {
        this.htmlurl = htmlurl;
    }

}
