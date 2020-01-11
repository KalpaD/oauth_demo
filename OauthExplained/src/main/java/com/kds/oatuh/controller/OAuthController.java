package com.kds.oatuh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kds.oatuh.config.OauthSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;

/**
 * This controller demonstrate how to use REST APIs provided by GitHub and Instagram to acquire
 * access_token using various OAuth 2.0 grant_types.
 *
 * Created by kalpasenanayake on 3/6/17.
 */
@RestController
public class OAuthController {

    private Logger logger = LoggerFactory.getLogger(OAuthController.class);
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private OauthSettings oauthSettings;

    private String CLIENT_ID_GITHUB;
    private String CLIENT_ID_INSTAGRAM;
    private String CLIENT_SECRET_GITHUB;
    private String CLIENT_SECRET_INSTAGRAM;
    private String REDIRECT_URL_GITHUB;
    private String REDIRECT_URL_INSTAGRAM;
    private String USERNAME_GITHUB;
    private String PASSWORD_GITHUG;
    private String GITHUB_AUTH_URL;
    private String INSTAGRAM_AUTH_URL;
    private String GITHUB_TOKEN_URL;

    @PostConstruct
    public void setUplocalSettings() {
        CLIENT_ID_GITHUB         =   oauthSettings.getClientIdGitHub();
        CLIENT_ID_INSTAGRAM      =   oauthSettings.getClientIdIg();
        CLIENT_SECRET_GITHUB     =   oauthSettings.getClientSecretGitHub();
        CLIENT_SECRET_INSTAGRAM  =   oauthSettings.getClientSecretIg();
        REDIRECT_URL_GITHUB      =   oauthSettings.getRedirectUrlGitHub();
        REDIRECT_URL_INSTAGRAM   =   oauthSettings.getRedirectUrlIg();
        USERNAME_GITHUB          =   oauthSettings.getGitHubUsername();
        PASSWORD_GITHUG          =   oauthSettings.getGitHubPassword();
        GITHUB_AUTH_URL          =   oauthSettings.getAuthUrlGitHub();
        INSTAGRAM_AUTH_URL       =   oauthSettings.getAuthUrlIg();
        GITHUB_TOKEN_URL         =   oauthSettings.getTokenUrlGitHub();
    }

    public enum FLOW_TYPE {
        AuthorisationCodeGrant("auth_code"), ImplicitGrant("implicit"), ROPC("ropc"), ClientCredential("cc");
        private String value;

        FLOW_TYPE(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * Respond with a String representation of a configuration object.
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/configs")
    public ResponseEntity<String> tets() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String body = mapper.writeValueAsString(oauthSettings.toString());
            return new ResponseEntity<String>(body, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getStackTrace().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Main entry point of the controller which facilitate four types of oauth grant types.
     * @param token_type
     * @return
     */
    @RequestMapping("/flows")
    public ResponseEntity authCode(@RequestParam(name = "flow_type") String token_type) {

        if (token_type.equals(FLOW_TYPE.AuthorisationCodeGrant.getValue())) {

            return handleAuthorizationCodeFlow();

        } else if(token_type.equals(FLOW_TYPE.ImplicitGrant.getValue())){

            return handleImplicitFlow();

        } else if (token_type.equals(FLOW_TYPE.ROPC.getValue())) {

            return handleROPCFlow();

        } else if (token_type.equals(FLOW_TYPE.ClientCredential.getValue())) {

            return handleClientCredentialFlow();

        }  else {

            return new ResponseEntity<String>("Inavlid oauth flow type", HttpStatus.BAD_REQUEST);

        }
    }

    /**
     * Handle the Authorization Code grant type using GitHub API.
     * @return
     */
    private ResponseEntity<String> handleAuthorizationCodeFlow() {
        UriComponents uriComponentsBuilder = UriComponentsBuilder.fromUriString(GITHUB_AUTH_URL)
                .queryParam("response_type", "code")
                .queryParam("client_id", CLIENT_ID_GITHUB)
                .queryParam("redirect_uri", REDIRECT_URL_GITHUB)
                .build();
        logger.info("Initializing Authorization Code Flow With Following Request : {} ", uriComponentsBuilder.toUriString());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponentsBuilder.toUri());

        return (new ResponseEntity<String>("", httpHeaders, HttpStatus.TEMPORARY_REDIRECT));
    }

    /**
     * Handle the redirection of the first step of the Authorization Code flow and exchange the
     * Authorization code for access token.
     * @param code
     * @return
     */
    @RequestMapping("/redirect")
    public String typeCode(@RequestParam(name = "code") String code) {
        logger.info("Authorization code recived as : {}", code);
        logger.info("Exchange authorization code for access_token will commence.");
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(GITHUB_TOKEN_URL)
                .build();

        HttpHeaders headers = new HttpHeaders();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", CLIENT_ID_GITHUB);
        body.add("client_secret", CLIENT_SECRET_GITHUB);
        body.add("code", code);
        body.add("redirect_uri", REDIRECT_URL_GITHUB);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toUriString(),
                HttpMethod.POST, entity, String.class);
        logger.info("Response received from access token endpoint : {}", response.getBody());
        return response.getBody();
    }

    /**
     * Handle the implicit flow.
     * @return
     */
    private ResponseEntity<String> handleImplicitFlow() {

        UriComponents uriComponentsBuilder = UriComponentsBuilder.fromUriString(INSTAGRAM_AUTH_URL)
                .queryParam("response_type", "token")
                .queryParam("client_id", CLIENT_ID_INSTAGRAM)
                .queryParam("redirect_uri", REDIRECT_URL_INSTAGRAM)
                .build();
        logger.info("Initializing Implicit Flow With Following Request : {} ", uriComponentsBuilder.toUriString());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponentsBuilder.toUri());

        return (new ResponseEntity<String>("", httpHeaders, HttpStatus.TEMPORARY_REDIRECT));
    }

    /**
     * Handle the ROPC flow
     * @return
     */
    private ResponseEntity<String> handleROPCFlow() {

        /*
        * This grant type has higher risk because it maintains the UID/PASSWORD_GITHUG anti-pattern.
        Additionally, because the user does not have control over the
        authorization process, clients using this grant type are not limited by scope but
        instead have potentially the same capabilities as the
        user themselves. As there is no authorization step, the ability to
        offer token revocation is bypassed.
        https://github.com/ory/hydra/pull/297#issuecomment-294282671

        * */
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(GITHUB_TOKEN_URL)
                .build();

        HttpHeaders headers = new HttpHeaders();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("grant_type", "PASSWORD_GITHUG");
        body.add("client_id", CLIENT_ID_GITHUB);
        body.add("client_secret", CLIENT_SECRET_GITHUB);
        body.add("USERNAME_GITHUB", USERNAME_GITHUB);
        body.add("PASSWORD_GITHUG", PASSWORD_GITHUG);
        body.add("scope", "repo");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toUriString(),
                HttpMethod.POST, entity, String.class);
        logger.info("Response for ROPC : {}", response.getBody());
        return response;

    }

    /**
     * Handle the client credential flow.
     * @return
     */
    private ResponseEntity<String> handleClientCredentialFlow() {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(GITHUB_TOKEN_URL)
                .build();

        HttpHeaders headers = new HttpHeaders();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", CLIENT_ID_GITHUB);
        body.add("client_secret", CLIENT_SECRET_GITHUB);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toUriString(),
                HttpMethod.POST, entity, String.class);
        logger.info("Response for client credential : {}", response.getBody());
        return response;
    }

    /**
     * Extract the access_token from the response and respond with OK in text.
     * @param access_token
     * @return
     */
    @RequestMapping("/code")
    public String code(@RequestParam(name = "access_token", required = false) String access_token) {
        logger.info("Handling redirection on successful authentication");
        return "OK";
    }

}
