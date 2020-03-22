package com.pgauns.infinitescrollablelistview.ui.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pgauns.infinitescrollablelistview.data.DataRepository;
import com.pgauns.infinitescrollablelistview.data.data_models.Repository;

import java.util.List;

public class RepoListViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Repository>> repoList = new MutableLiveData<>();
    private Context context;

    public LiveData<List<Repository>> getRepoList() {
        return repoList;
    }

    public RepoListViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        fetchRepos();
    }

    private void fetchRepos() {
        repoList.postValue(DataRepository.getInstance().getRepositories(context));
    }

    public void loadMore() {
        repoList.postValue(DataRepository.getInstance().getRepositories(context));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
