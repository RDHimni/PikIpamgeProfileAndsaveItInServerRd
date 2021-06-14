package ridaz.ksk.pikipamgeprofileandsaveitinserverrd;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }
    private Bitmap bitmap;
    private Uri imageUri;

    CircleImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_blank, container, false);

        imageView = root.findViewById(R.id.profile_imagef);

      //  imageView.setImageURI(constants.uri2);


        constants.uri.observe(getActivity(), new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                imageView.setImageURI(uri);
            }
        });


        Button button =  root.findViewById(R.id.selectImage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity().setAspectRatio(1,1).start(getActivity());
            }
        });




        return root;
    }





}