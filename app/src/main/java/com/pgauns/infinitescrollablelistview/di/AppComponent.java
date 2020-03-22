package com.pgauns.infinitescrollablelistview.di;

import com.pgauns.infinitescrollablelistview.RepoListActivity;
import com.pgauns.infinitescrollablelistview.ui.viewmodel.RepoListViewModel;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(RepoListActivity activity);
}
