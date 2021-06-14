package ridaz.ksk.pikipamgeprofileandsaveitinserverrd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private CircleImageView profileImageView;


    private Bitmap bitmap;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        profileImageView = findViewById(R.id.profile_image);


    }

    public void selectImage(View view) {

        CropImage.activity().setAspectRatio(1,1).start(MainActivity.this);

    }



    public void uploadImage(View view) {
        uploadImage();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }

        if (requestCode  == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);



            imageUri = result.getUri();
            constants.uri.setValue(imageUri);

            constants.uri2 = imageUri;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                profileImageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

            //Glide.with(this).load(url).into(profileImageView);
           // profileImageView.setImageURI(imageUri);

        }
        else {
            Toast.makeText(this, "Error, Try again", Toast.LENGTH_SHORT).show();
        }
    }



    private void uploadImage() {

        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream);
            byte[] imageInByte = byteArrayOutputStream.toByteArray();
            String encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);
            Log.d(TAG, "aly uploadImage: "+ encodedImage);
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();

            Call<Pojo> call =retrofitClient.getInstance().getApi().uploadImageToServer(encodedImage);
            call.enqueue(new Callback<Pojo>() {
                @Override
                public void onResponse(Call<Pojo> call, Response<Pojo> response) {

                    Toast.makeText(MainActivity.this, ""+response.body().getRemarks(), Toast.LENGTH_SHORT).show();



                }

                @Override
                public void onFailure(Call<Pojo> call, Throwable t) {

                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }
        else {
            Toast.makeText(this, "you must select image", Toast.LENGTH_SHORT).show();
        }



    }
}