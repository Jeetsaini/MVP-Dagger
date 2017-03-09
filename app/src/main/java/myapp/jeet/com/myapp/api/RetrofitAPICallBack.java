package myapp.jeet.com.myapp.api;

/**
 * Created by Admin on 3/8/2017.
 */

public interface RetrofitAPICallBack<T> {
    void onSuccess(T t);
    void onFailure(String message);
}
