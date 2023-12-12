package com.example.animelist.utilities;

import com.example.animelist.network.ApiService;
import com.example.animelist.network.OAuth2Client;

public class ApiServiceSingleton {

    private static ApiService instance;
    private static String accessToken;

//    Private constructor to prevent user to initialize the singleton
    private ApiServiceSingleton(){}

    public static void setAccessToken(String token) {
        accessToken = token;
        if (instance != null) {
            instance = createApiService();
        }
    }

    public static ApiService getInstance() {
        if (accessToken != null) {
            if (instance == null) {
                instance = createApiService();
            }
            return instance;
        } else {
            return null;
        }
    }

    public static void deleteApiService() {
        instance = null;
        accessToken = null;
    }

    private static ApiService createApiService() {
        return OAuth2Client.getClient(accessToken).create(ApiService.class);
    }
}
