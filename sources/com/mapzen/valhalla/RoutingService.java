package com.mapzen.valhalla;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RoutingService {
    @GET("/route")
    Call<String> getRoute(@Query("json") JSON json);
}
