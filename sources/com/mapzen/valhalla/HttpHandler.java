package com.mapzen.valhalla;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mapzen.http.Tls12OkHttpClientFactory;
import com.mapzen.valhalla.JSON;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HttpHandler {
    protected static final HttpLoggingInterceptor.Level DEFAULT_LOG_LEVEL = HttpLoggingInterceptor.Level.NONE;
    protected static final String DEFAULT_URL = "https://valhalla.mapzen.com/";
    Retrofit adapter;
    String endpoint;
    /* access modifiers changed from: private */
    public Gson gson;
    HttpLoggingInterceptor.Level logLevel;
    private Interceptor requestInterceptor;
    RoutingService service;

    public HttpHandler() {
        this(DEFAULT_URL, DEFAULT_LOG_LEVEL);
    }

    public HttpHandler(String str) {
        this(str, DEFAULT_LOG_LEVEL);
    }

    public HttpHandler(HttpLoggingInterceptor.Level level) {
        this(DEFAULT_URL, level);
    }

    public HttpHandler(String str, HttpLoggingInterceptor.Level level) {
        this.requestInterceptor = new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                return HttpHandler.this.onRequest(chain);
            }
        };
        this.gson = new GsonBuilder().registerTypeAdapter(JSON.Location.class, new LocationSerializer()).create();
        configure(str, level);
    }

    /* access modifiers changed from: protected */
    public void configure(String str, HttpLoggingInterceptor.Level level) {
        OkHttpClient build = Tls12OkHttpClientFactory.Companion.enableTls12OnPreLollipop(new OkHttpClient.Builder()).addNetworkInterceptor(this.requestInterceptor).addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(level)).build();
        this.endpoint = str;
        this.logLevel = level;
        this.adapter = new Retrofit.Builder().baseUrl(str).client(build).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(new Converter.Factory() {
            public Converter<?, String> stringConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
                return new Converter<Object, String>() {
                    public String convert(Object obj) throws IOException {
                        return HttpHandler.this.gson.toJson(obj);
                    }
                };
            }
        }).build();
        this.service = new RestAdapterFactory(this.adapter).getRoutingService();
    }

    public Call<String> requestRoute(JSON json, Callback<String> callback) {
        Call<String> route = this.service.getRoute(json);
        route.enqueue(callback);
        return route;
    }

    /* access modifiers changed from: protected */
    public Response onRequest(Interceptor.Chain chain) throws IOException {
        return chain.proceed(chain.request());
    }
}
