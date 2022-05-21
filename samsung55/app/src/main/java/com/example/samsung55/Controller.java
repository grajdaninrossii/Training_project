package com.example.samsung55;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {

    String TAG_ERROR = "ERROR_";
    private String URL = "http://192.168.0.100:8080/";

    private Api service;
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL) // Добавить со спринга
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(Api.class);
    }

    public LiveData<List<Model>> getUser(){

        MutableLiveData<List<Model>> models = new MutableLiveData<>();

        Call<List<Model>> call = service.getUser();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                try{
                    models.setValue(response.body());

                    Log.d("INFO_RESPONSE", response.headers().toString());
                } catch (Exception e){
                    Log.d("INFO_RESPONSE", "{D{DS");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Log.e(TAG_ERROR,"Не удалось соверить GET-запрос");
            }
        });

        return models;
    }

    public boolean setUser(Model model){

        Call<Model> call = service.setUser(
                model.getName(),
                model.getLastname(),
                model.getClassname()
        );

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                try {
                    Log.d("GOODR", "Succes create user");
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                t.printStackTrace();
                Log.e(TAG_ERROR,"Не удалось обновить user");
            }
        });

        return true;
    }
}
