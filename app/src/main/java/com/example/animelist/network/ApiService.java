package com.example.animelist.network;


import com.example.animelist.model.BodyAnime;
import com.example.animelist.model.BodyAnimeList;
import com.example.animelist.model.BodyAnimeListCollection;
import com.example.animelist.model.BodyUpdateAnimeListEntry;
import com.example.animelist.model.BodyUser;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/")
    Call<BodyAnimeList> getAnimeList(@retrofit2.http.Body RequestMethod requestMethod);
    @POST("/")
    Call<BodyAnime> getAnime(@retrofit2.http.Body RequestMethod requestMethod);
    @POST("/")
    Call<BodyUser> getUser(@retrofit2.http.Body RequestMethod requestMethod);
    @POST("/")
    Call<BodyUpdateAnimeListEntry> updateAnimeListEntry(@retrofit2.http.Body RequestMethod requestMethod);
    @POST("/")
    Call<BodyAnimeListCollection> getAnimeListCollection(@retrofit2.http.Body RequestMethod requestMethod);

}
