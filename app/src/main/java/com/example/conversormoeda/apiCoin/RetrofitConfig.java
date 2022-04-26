package com.example.conversormoeda.apiCoin;

import com.example.conversormoeda.Model.Coin;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://economia.awesomeapi.com.br/last/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public CoinService getCoinService(){
        return this.retrofit.create(CoinService.class);
    }

}
