package com.pgauns.infinitescrollablelistview.di;

import android.app.Application;

import com.pgauns.infinitescrollablelistview.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

@Singleton
@Component(modules = {AppModule.class, AndroidInjectionModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {
    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
