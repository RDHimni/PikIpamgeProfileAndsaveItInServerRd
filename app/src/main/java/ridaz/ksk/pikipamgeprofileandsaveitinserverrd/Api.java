package ridaz.ksk.pikipamgeprofileandsaveitinserverrd;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Rida Dhimni
 * 08/04/2021
 **/

public interface Api {
/*
    @FormUrlEncoded
    @POST("upload_image.php")
    Call<Pojo> uploadImageToServer(
            @Field("EN_IMAGE") String encodedImage
    );

 */

    @FormUrlEncoded
    @POST("UserInscription.php")
    Call<Pojo> uploadImageToServer(
            @Field("EN_IMAGE") String encodedImage
    );
}
