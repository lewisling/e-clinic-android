package sg.edu.ntu.cz3002.enigma.eclinic.model;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Api manager
 */
public class ApiManager {

    private static ApiManager ourInstance = new ApiManager();
    private Retrofit _retrofit;
    private ApiService _apiService;
    private static final String _url = "http://192.168.0.112:8000/api/";

    public static ApiManager getInstance() {
        return ourInstance;
    }

    private ApiManager() {
        _retrofit = new Retrofit.Builder()
                .baseUrl(_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        _apiService = _retrofit.create(ApiService.class);
    }

    public Observable<AuthToken> authenticate(String username, String password) {
        return _apiService.authenticate(new User(username, password));
    }
}