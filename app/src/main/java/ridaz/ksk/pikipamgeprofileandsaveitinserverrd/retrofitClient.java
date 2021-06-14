package ridaz.ksk.pikipamgeprofileandsaveitinserverrd;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Rida Dhimni
 * 08/04/2021
 **/

public class retrofitClient {
    private static final String BASE_URL="http://192.168.1.125/apps/Android%20Tutorials/";
    private static retrofitClient myClient;
    private Retrofit retrofit;


    private retrofitClient() {
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized retrofitClient getInstance(){
        if (myClient==null){
            myClient=new retrofitClient();
        }
        return myClient;
    }

    public Api getApi(){
        return retrofit.create(Api.class);

    }
}