package com.pgauns.infinitescrollablelistview;

import android.app.Application;

import com.pgauns.infinitescrollablelistview.di.AppComponent;
import com.pgauns.infinitescrollablelistview.di.AppModule;
import com.pgauns.infinitescrollablelistview.di.DaggerAppComponent;

public class RLVApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
