package com.example.animelist.model;

import com.example.animelist.model.enums.MediaListStatus;
import com.example.animelist.model.enums.MediaStatus;
import com.google.gson.annotations.SerializedName;

public class AnimeListEntry {

    private Integer id;
    private Integer mediaId;
    private MediaListStatus status;
    private Integer score;
    private Integer progress;
    private Date startedAt;
    private Date completedAt;
    @SerializedName("media")
    private Anime anime;

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MediaListStatus getStatus() {
        return status;
    }

    public void setStatus(MediaListStatus status) {
        this.status = status;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }
}
