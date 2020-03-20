package com.pgauns.infinitescrollablelistview.network;

import com.pgauns.infinitescrollablelistview.data.data_models.Repository;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("orgs/square/repos")
    Single<List<Repository>> getRepositories();
}
