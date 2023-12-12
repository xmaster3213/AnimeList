package com.example.animelist.model;

import com.example.animelist.model.enums.Format;
import com.example.animelist.model.enums.MediaStatus;
import com.example.animelist.model.enums.Season;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Anime {
    private int id;
    private Title title;
    private Date startDate;
    private Date endDate;
    @SerializedName("status")
    private MediaStatus mediaStatus;
    private Season season;
    private Format format;
    private List<String> synonyms;
    private int duration;
    private int popularity;
    private Integer episodes;
    private int averageScore;
    private String description;
    private String bannerImage;
    private CoverImage coverImage;
    private List<Ranking> rankings;
    private Characters characters;
    @SerializedName("streamingEpisodes")
    private List<Episode> episodeList;
    @SerializedName("mediaListEntry")
    private AnimeListEntry animeListEntry;

    public List<Episode> getEpisodesList() {
        return episodeList;
    }

    public void setEpisodesList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    public AnimeListEntry getAnimeListEntry() {
        return animeListEntry;
    }

    public void setAnimeListEntry(AnimeListEntry animeListEntry) {
        this.animeListEntry = animeListEntry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public MediaStatus getMediaStatus() {
        return mediaStatus;
    }

    public void setMediaStatus(MediaStatus mediaStatus) {
        this.mediaStatus = mediaStatus;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    public int getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(int averageScore) {
        this.averageScore = averageScore;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(CoverImage coverImage) {
        this.coverImage = coverImage;
    }

    public List<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(List<Ranking> rankings) {
        this.rankings = rankings;
    }

    public Characters getCharacters() {
        return characters;
    }

    public void setCharacters(Characters characters) {
        this.characters = characters;
    }
}