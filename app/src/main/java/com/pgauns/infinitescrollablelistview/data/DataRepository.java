package com.pgauns.infinitescrollablelistview.data;

import androidx.lifecycle.MutableLiveData;

import com.pgauns.infinitescrollablelistview.data.data_models.Repository;
import com.pgauns.infinitescrollablelistview.network.RetrofitInterface;
import com.pgauns.infinitescrollablelistview.network.RetrofitService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {

    private static DataRepository dataRepository;

    public static DataRepository getInstance(){
        if (dataRepository == null){
            dataRepository = new DataRepository();
        }
        return dataRepository;
    }

    private RetrofitInterface retrofit;

    private DataRepository(){
        retrofit = RetrofitService.createService(RetrofitInterface.class);
    }

    public Single<List<Repository>> getRepositories(){
        return retrofit.getRepositories();
    }
}
