package com.app.edukt.edukt.petitions;
    import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Petition {
    /**
     * Libreria retrofit para consultar la API
     */
    private Retrofit retrofit;


    // URL base a la API
    private final String API_BASE = "http://167.99.99.255:3001/";

    private static Petition ourInstance;

    private Petition() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Usa el patron de diseño singleton para conseguir instancia unica de la clase Petition
     * @return
     */
    public static Petition getInstance() {
        if(ourInstance == null) {
            ourInstance = new Petition();
        }
        return ourInstance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
