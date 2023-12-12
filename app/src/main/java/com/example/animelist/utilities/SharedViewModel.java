package com.example.animelist.utilities;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.animelist.model.Anime;
import com.example.animelist.model.Viewer;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<Anime> anime = new MutableLiveData<>();
    private MutableLiveData<Viewer> viewer = new MutableLiveData<>();

    public LiveData<Anime> getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime.setValue(anime);
    }

    public MutableLiveData<Viewer> getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer.setValue(viewer);
    }
}
