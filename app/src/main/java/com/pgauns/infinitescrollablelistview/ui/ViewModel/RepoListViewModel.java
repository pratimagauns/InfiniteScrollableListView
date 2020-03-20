package com.pgauns.infinitescrollablelistview.ui.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pgauns.infinitescrollablelistview.data.DataRepository;
import com.pgauns.infinitescrollablelistview.data.data_models.Repository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class RepoListViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Repository>> repoList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();


    private CompositeDisposable disposable;

    public RepoListViewModel(@NonNull Application application) {
        super(application);
        disposable = new CompositeDisposable();
    }

    private void fetchRepos() {
        loading.setValue(true);

        disposable.add(DataRepository.getInstance().getRepositories().subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Repository>>() {
                    @Override
                    public void onSuccess(List<Repository> value) {
                        repoLoadError.setValue(false);
                        repoList.setValue(value);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        repoLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
