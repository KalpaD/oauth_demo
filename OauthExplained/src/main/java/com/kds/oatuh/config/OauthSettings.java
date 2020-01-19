package com.kds.oatuh.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by kalpasenanayake on 4/6/17.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("oauth")
public class OauthSettings {

    public String clientIdGitHub;
    public String clientIdIg;
    public String clientSecretGitHub;
    public String clientSecretIg;
    public String redirectUrlGitHub;
    public String redirectUrlIg;
    public String gitHubUsername;
    public String gitHubPassword;

    public String authUrlGitHub;
    public String authUrlIg;
    public String tokenUrlGitHub;


    public String getClientIdGitHub() {
        return clientIdGitHub;
    }

    public void setClientIdGitHub(String clientIdGitHub) {
        this.clientIdGitHub = clientIdGitHub;
    }

    public String getClientIdIg() {
        return clientIdIg;
    }

    public void setClientIdIg(String clientIdIg) {
        this.clientIdIg = clientIdIg;
    }

    public String getClientSecretGitHub() {
        return clientSecretGitHub;
    }

    public void setClientSecretGitHub(String clientSecretGitHub) {
        this.clientSecretGitHub = clientSecretGitHub;
    }

    public String getClientSecretIg() {
        return clientSecretIg;
    }

    public void setClientSecretIg(String clientSecretIg) {
        this.clientSecretIg = clientSecretIg;
    }

    public String getRedirectUrlGitHub() {
        return redirectUrlGitHub;
    }

    public void setRedirectUrlGitHub(String redirectUrlGitHub) {
        this.redirectUrlGitHub = redirectUrlGitHub;
    }

    public String getRedirectUrlIg() {
        return redirectUrlIg;
    }

    public void setRedirectUrlIg(String redirectUrlIg) {
        this.redirectUrlIg = redirectUrlIg;
    }

    public String getGitHubUsername() {
        return gitHubUsername;
    }

    public void setGitHubUsername(String gitHubUsername) {
        this.gitHubUsername = gitHubUsername;
    }

    public String getGitHubPassword() {
        return gitHubPassword;
    }

    public void setGitHubPassword(String gitHubPassword) {
        this.gitHubPassword = gitHubPassword;
    }

    public String getAuthUrlGitHub() {
        return authUrlGitHub;
    }

    public void setAuthUrlGitHub(String authUrlGitHub) {
        this.authUrlGitHub = authUrlGitHub;
    }

    public String getAuthUrlIg() {
        return authUrlIg;
    }

    public void setAuthUrlIg(String authUrlIg) {
        this.authUrlIg = authUrlIg;
    }

    public String getTokenUrlGitHub() {
        return tokenUrlGitHub;
    }

    public void setTokenUrlGitHub(String tokenUrlGitHub) {
        this.tokenUrlGitHub = tokenUrlGitHub;
    }

    @Override
    public String toString() {
        return "OauthSettings{" +
                "clientIdGitHub='" + clientIdGitHub + '\'' +
                ", clientIdIg='" + clientIdIg + '\'' +
                ", clientSecretGitHub='" + clientSecretGitHub + '\'' +
                ", clientSecretIg='" + clientSecretIg + '\'' +
                ", redirectUrlGitHub='" + redirectUrlGitHub + '\'' +
                ", redirectUrlIg='" + redirectUrlIg + '\'' +
                ", gitHubUsername='" + gitHubUsername + '\'' +
                ", gitHubPassword='" + gitHubPassword + '\'' +
                ", authUrlGitHub='" + authUrlGitHub + '\'' +
                ", authUrlIg='" + authUrlIg + '\'' +
                ", tokenUrlGitHub='" + tokenUrlGitHub + '\'' +
                '}';
    }
}
