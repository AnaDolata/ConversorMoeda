package com.example.conversormoeda.apiCoin;

import com.example.conversormoeda.Model.Coin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CoinService {

    @GET("{moeda}")
    Call<Coin> getCoin(@Path("moeda") String moeda);
}
