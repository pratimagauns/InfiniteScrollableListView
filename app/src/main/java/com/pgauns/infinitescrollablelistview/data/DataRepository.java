package com.pgauns.infinitescrollablelistview.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pgauns.infinitescrollablelistview.data.data_models.Repository;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    private static DataRepository dataRepository;
//    @Inject
//    Context context;

    public static DataRepository getInstance(){
        if (dataRepository == null){
            dataRepository = new DataRepository();
        }
        return dataRepository;
    }
//
//    @Inject
//    private DataRepository(@Named("ApplicationContext") Context context){
//    }

    public List<Repository> getRepositories(Context context){
        return readJSONFromAsset("sample_data.json", context);
    }

    // Loading demo data from local json file
    // Real world scenario would be to get the data from DB or Service
    private List<Repository> readJSONFromAsset(String filename, Context context){
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>(); //TODO: Send error scenario
        }

        Type type = new TypeToken<List<Repository>>(){}.getType();
        return new Gson().fromJson(json,type);
    }
}
