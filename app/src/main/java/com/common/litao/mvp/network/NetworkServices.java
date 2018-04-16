package com.common.litao.mvp.network;

import com.common.litao.mvp.model.LoginModel;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by isfaaghyth on 6/17/17.
 */

public interface NetworkServices {

    @FormUrlEncoded @POST("/api/login.php")
    Observable<LoginModel> postLogin (
            @Field("email") String email,
            @Field("password") String password);
}
