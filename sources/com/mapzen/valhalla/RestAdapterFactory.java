package com.mapzen.valhalla;

import retrofit2.Retrofit;

public class RestAdapterFactory {
    Retrofit adapter;

    public RestAdapterFactory(Retrofit retrofit) {
        this.adapter = retrofit;
    }

    public RoutingService getRoutingService() {
        return (RoutingService) this.adapter.create(RoutingService.class);
    }
}
