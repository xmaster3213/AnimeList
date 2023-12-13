package com.example.animelist.network;

import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.animelist.model.AnimeListEntry;
import com.example.animelist.model.BodyAnime;
import com.example.animelist.model.BodyAnimeList;
import com.example.animelist.model.BodyAnimeListCollection;
import com.example.animelist.model.BodyCharacter;
import com.example.animelist.model.BodyUpdateAnimeListEntry;
import com.example.animelist.model.BodyUser;
import com.example.animelist.model.enums.Format;
import com.example.animelist.model.enums.MediaListSort;
import com.example.animelist.model.enums.Season;
import com.example.animelist.utilities.ApiServiceSingleton;
import com.example.animelist.utilities.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataViewModel extends ViewModel {

    private MutableLiveData<BodyAnimeList> animesPopularThisSeason = new MutableLiveData<>();
    private MutableLiveData<BodyAnimeList> animesJustAdded = new MutableLiveData<>();
    private MutableLiveData<BodyAnimeList> animesAllTimePopular = new MutableLiveData<>();
    private MutableLiveData<BodyAnimeList> animesUpcomingNextSesaon = new MutableLiveData<>();
    private MutableLiveData<BodyAnimeList> animesFilter = new MutableLiveData<>();
    private MutableLiveData<BodyAnime> anime = new MutableLiveData<>();
    private MutableLiveData<BodyUpdateAnimeListEntry> updatedAnimeListEntry = new MutableLiveData<>();
    private MutableLiveData<BodyAnimeListCollection> animeListCollection = new MutableLiveData<>();
    private MutableLiveData<BodyUser> userInfo = new MutableLiveData<>();
    private MutableLiveData<BodyCharacter> character = new MutableLiveData<>();

    public MutableLiveData<BodyAnimeList> getAnimesPopularThisSeasonLiveData() {
        return animesPopularThisSeason;
    }
    public MutableLiveData<BodyAnimeList> getAnimesJustAddedLiveData() {
        return animesJustAdded;
    }
    public MutableLiveData<BodyAnimeList> getAnimesAllTimePopularLiveData() {
        return animesAllTimePopular;
    }
    public MutableLiveData<BodyAnimeList> getAnimesUpcomingNextSeasonLiveData() {
        return animesUpcomingNextSesaon;
    }
    public MutableLiveData<BodyAnimeList> getAnimesFilter() {
        return animesFilter;
    }
    public MutableLiveData<BodyAnime> getAnimesLiveData() {
        return anime;
    }
    public MutableLiveData<BodyUpdateAnimeListEntry> getUpdatedAnimeListEntryLiveData() {
        return updatedAnimeListEntry;
    }
    public MutableLiveData<BodyAnimeListCollection> getanimeListCollection() {
        return animeListCollection;
    }
    public MutableLiveData<BodyUser> getUserInfoLiveData() {
        return userInfo;
    }
    public MutableLiveData<BodyCharacter> getCharacterLiveData() {
        return character;
    }

    public LiveData<BodyAnimeList> getAnimesPopularThisSeason() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Season season = Utilities.getSeasonGivenMonth(Calendar.getInstance().get(Calendar.MONTH));
        String variables = createVariables(new ArrayList<>(Arrays.asList(
                new Pair<>("year", Integer.toString(year)),
                new Pair<>("season", season.toString()),
                new Pair<>("format", Format.TV.toString()),
                new Pair<>("page", Integer.toString(1)),
                new Pair<>("perPage", Integer.toString(50)),
                new Pair<>("sort", "[\"POPULARITY_DESC\"]"))));
//        String variables =
//                "{\n" +
//                        "  \"season\": \"" + season + "\",\n" +
//                        "  \"year\": " + year + ",\n" +
//                        "  \"format\": \"TV\",\n" +
//                        "  \"page\": 1,\n" +
//                        "  \"perPage\": 50,\n" +
//                        "  \"sortOrder\": [\"POPULARITY_DESC\"]\n" +
//                        "}";
        return makeRequestAnimeList(Queries.ANIME_LIST, variables, animesPopularThisSeason);
    }

    public LiveData<BodyAnimeList> getAnimesJustAdded() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Season season = Utilities.getSeasonGivenMonth(Calendar.getInstance().get(Calendar.MONTH));
        String variables = createVariables(new ArrayList<>(Arrays.asList(
                new Pair<>("year", Integer.toString(year)),
                new Pair<>("season", season.toString()),
                new Pair<>("format", Format.TV.toString()),
                new Pair<>("page", Integer.toString(1)),
                new Pair<>("perPage", Integer.toString(50)),
                new Pair<>("sort", "[\"START_DATE_DESC\"]"))));
//        String variables =
//                "{\n" +
//                        "  \"season\": \"" + season + "\",\n" +
//                        "  \"year\": " + year + ",\n" +
//                        "  \"format\": \"TV\",\n" +
//                        "  \"page\": 1,\n" +
//                        "  \"perPage\": 50,\n" +
//                        "  \"sortOrder\": [\"START_DATE_DESC\"]\n" +
//                        "}";
        return makeRequestAnimeList(Queries.ANIME_LIST, variables, animesJustAdded);
    }

    public LiveData<BodyAnimeList> getAnimesAllTimePopular() {
        String variables = createVariables(new ArrayList<>(Arrays.asList(
                new Pair<>("format", Format.TV.toString()),
                new Pair<>("page", Integer.toString(1)),
                new Pair<>("perPage", Integer.toString(50)),
                new Pair<>("sort", "[\"POPULARITY_DESC\"]"))));
//        String variables =
//                "{\n" +
//                        "  \"format\": \"TV\",\n" +
//                        "  \"page\": 1,\n" +
//                        "  \"perPage\": 50,\n" +
//                        "  \"sortOrder\": [\"POPULARITY_DESC\"]\n" +
//                        "}";
        return makeRequestAnimeList(Queries.ANIME_LIST, variables, animesAllTimePopular);
    }

    public LiveData<BodyAnimeList> getAnimesUpcomingNextSesaon() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        Season season = Utilities.getSeasonGivenMonth(((Calendar.getInstance().get(Calendar.MONTH) - 1 + 3) % 12) + 1);
        Integer[] nextYearMonths = {12, 11, 10, 9};
        if (Arrays.asList(nextYearMonths).contains(month)) {
            year += 1;
        }
        String variables = createVariables(new ArrayList<>(Arrays.asList(
                new Pair<>("year", Integer.toString(year)),
                new Pair<>("season", season.toString()),
                new Pair<>("format", Format.TV.toString()),
                new Pair<>("page", Integer.toString(1)),
                new Pair<>("perPage", Integer.toString(50)),
                new Pair<>("sort", "[\"POPULARITY_DESC\"]"))));
//        String variables =
//                "{\n" +
//                        "  \"season\": \"" + season + "\",\n" +
//                        "  \"year\": " + year + ",\n" +
//                        "  \"format\": \"TV\",\n" +
//                        "  \"page\": 1,\n" +
//                        "  \"perPage\": 50,\n" +
//                        "  \"sortOrder\": [\"POPULARITY_DESC\"]\n" +
//                        "}";
        return makeRequestAnimeList(Queries.ANIME_LIST, variables, animesUpcomingNextSesaon);
    }


    /**
     * Make a request to the Api Service to get a list of anime with corresponding filters.
     * @param year The year the anime was released, value can be -1 if we want year = any as a result.
     * @param season The season the anime was released, value can be null if we want season = any as a result.
     * @param format The format the anime has, value can be null if we want format = any as a result.
     * @return A LiveData representing the List of anime following the filters.
     */
    public LiveData<BodyAnimeList> getAnimesFiltered(int year, Season season, Format format, String search) {
        List<Pair<String, String>> list = new ArrayList<>();
        if (year != -1) {
            list.add(new Pair<>("year", Integer.toString(year)));
        }
        if (season != null) {
            list.add(new Pair<>("season", season.toString()));
        }
        if (format != null) {
            list.add(new Pair<>("format", format.toString()));
        }
        if (!Objects.equals(search, "")) {
            list.add(new Pair<>("search", search));
        }
        String variables = createVariables(list);
        return makeRequestAnimeList(Queries.ANIME_LIST, variables, animesFilter);
    }

    public LiveData<BodyAnime> getAnime(int id) {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("id", Integer.toString(id)));
        String variables = createVariables(list);
        return makeRequestAnime(Queries.ANIME, variables, anime);
    }

    public LiveData<BodyCharacter> getCharacter(int id) {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("id", Integer.toString(id)));
        String variables = createVariables(list);
        return makeRequestCharacter(Queries.CHARACTER, variables, character);
    }

    public LiveData<BodyUpdateAnimeListEntry> updateAnimeEntry(AnimeListEntry animeListEntry) {
        List<Pair<String, String>> list = new ArrayList<>();
        if (animeListEntry.getId() != null) {
            list.add(new Pair<>("id", animeListEntry.getId().toString()));
        }
        if (animeListEntry.getScore() != null) {
            list.add(new Pair<>("score", animeListEntry.getScore().toString()));
        }
        if (animeListEntry.getProgress() != null) {
            list.add(new Pair<>("progress", animeListEntry.getProgress().toString()));
        }
        if (animeListEntry.getStartedAt() != null) {
            list.add(new Pair<>("startedAt", animeListEntry.getStartedAt().toJsonString()));
        }
        if (animeListEntry.getCompletedAt() != null) {
            list.add(new Pair<>("completedAt", animeListEntry.getCompletedAt().toJsonString()));
        }
        if (animeListEntry.getStatus() != null) {
            list.add(new Pair<>("status", animeListEntry.getStatus().toString()));
        }
        list.add(new Pair<>("animeId", animeListEntry.getMediaId().toString()));
        String variables = createVariables(list);
        return makeRequestUpdateAnimeListEntry(Queries.UPDATE_ANIME_LIST_ENTRY, variables,
                updatedAnimeListEntry);
    }

    public LiveData<BodyAnimeListCollection> getAnimeListCollection(Integer userId, MediaListSort sort) {
        List<Pair<String, String>> list = new ArrayList<>();
        if (sort != null) {
            list.add(new Pair<>("sort", "[\"" + sort  + "\"]"));
        } else {
            list.add(new Pair<>("sort", "[\"" + MediaListSort.SCORE + "\"]"));
        }
        list.add(new Pair<>("userId", Integer.toString(userId)));
        String variables = createVariables(list);
        return makeRequestAnimeListCollection(Queries.ANIME_LIST_COLLECTION, variables,
                animeListCollection);
    }

    public LiveData<BodyUser> getUserInfo() {
        String variables = "{}";
        return makeRequestUser(Queries.USER, variables, userInfo);
    }

    private String createVariables(List<Pair<String, String>> elements) {
        if (elements == null || elements.size() == 0) {
            return "{ }";
        }
        List<String> list = new ArrayList<>();
        for (Pair<String, String> element: elements) {
            if (element.second != null && element.first != null) {
                if (element.first.equals("sort")) {
                    list.add("\"" + element.first + "\": " + element.second);
                } else {
                    list.add("\"" + element.first + "\": \"" + element.second + "\"");
                }
            }
        }
        return "{ " + String.join(",", list) + " }";
    }

    private RequestMethod createRequestMethod(String query, String variables) {
        RequestMethod requestMethod = new RequestMethod();
        requestMethod.setQuery(query);
        requestMethod.setVariables(variables);
        return  requestMethod;
    }

    private LiveData<BodyAnimeList> makeRequestAnimeList(String query, String variables, MutableLiveData<BodyAnimeList> mutableLiveData) {
        RequestMethod requestMethod = createRequestMethod(query, variables);
        ApiServiceSingleton.getInstance().getAnimeList(requestMethod).enqueue(new Callback<BodyAnimeList>() {
            @Override
            public void onResponse(Call<BodyAnimeList> call, Response<BodyAnimeList> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                    Log.e("idk", "Response successful: ");
                }
            }

            @Override
            public void onFailure(Call<BodyAnimeList> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return mutableLiveData;
    }

    private LiveData<BodyAnime> makeRequestAnime(String query, String variables, MutableLiveData<BodyAnime> mutableLiveData) {
        RequestMethod requestMethod = createRequestMethod(query, variables);
        ApiServiceSingleton.getInstance().getAnime(requestMethod).enqueue(new Callback<BodyAnime>() {
            @Override
            public void onResponse(Call<BodyAnime> call, Response<BodyAnime> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                    Log.e("idk", "Response successful: ");
                }
            }

            @Override
            public void onFailure(Call<BodyAnime> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return mutableLiveData;
    }

    private LiveData<BodyCharacter> makeRequestCharacter(String query, String variables, MutableLiveData<BodyCharacter> mutableLiveData) {
        RequestMethod requestMethod = createRequestMethod(query, variables);
        ApiServiceSingleton.getInstance().getCharacter(requestMethod).enqueue(new Callback<BodyCharacter>() {
            @Override
            public void onResponse(Call<BodyCharacter> call, Response<BodyCharacter> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                    Log.e("idk", "Response successful: ");
                }
            }

            @Override
            public void onFailure(Call<BodyCharacter> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return mutableLiveData;
    }

    private LiveData<BodyUpdateAnimeListEntry> makeRequestUpdateAnimeListEntry(
            String query, String variables, MutableLiveData<BodyUpdateAnimeListEntry> mutableLiveData
    ) {
        RequestMethod requestMethod = createRequestMethod(query, variables);
        Log.e("TAG", "makeRequestUpdateAnimeListEntry: " + variables);
        ApiServiceSingleton.getInstance().updateAnimeListEntry(requestMethod).enqueue(new Callback<BodyUpdateAnimeListEntry>() {
            @Override
            public void onResponse(Call<BodyUpdateAnimeListEntry> call, Response<BodyUpdateAnimeListEntry> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                    Log.e("idk", "Response successful: ");
                }
            }

            @Override
            public void onFailure(Call<BodyUpdateAnimeListEntry> call, Throwable t) {
                Log.e("idk", "Response failure");
                t.printStackTrace();
            }
        });

        return mutableLiveData;
    }

    private LiveData<BodyAnimeListCollection> makeRequestAnimeListCollection(
            String query,String variables, MutableLiveData<BodyAnimeListCollection> mutableLiveData
    ) {
        RequestMethod requestMethod = createRequestMethod(query, variables);
        ApiServiceSingleton.getInstance().getAnimeListCollection(requestMethod).enqueue(new Callback<BodyAnimeListCollection>() {
            @Override
            public void onResponse(Call<BodyAnimeListCollection> call, Response<BodyAnimeListCollection> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                    Log.e("idk", "Response successful: ");
                }
            }

            @Override
            public void onFailure(Call<BodyAnimeListCollection> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return mutableLiveData;
    }

    private LiveData<BodyUser> makeRequestUser(String query, String variables, MutableLiveData<BodyUser> mutableLiveData) {
        RequestMethod requestMethod = createRequestMethod(query, variables);
        ApiServiceSingleton.getInstance().getUser(requestMethod).enqueue(new Callback<BodyUser>() {
            @Override
            public void onResponse(Call<BodyUser> call, Response<BodyUser> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                    Log.e("idk", "Response successful: ");
                }
            }

            @Override
            public void onFailure(Call<BodyUser> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return mutableLiveData;
    }

}
