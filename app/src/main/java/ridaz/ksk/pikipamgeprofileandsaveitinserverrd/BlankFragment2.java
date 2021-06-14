package ridaz.ksk.pikipamgeprofileandsaveitinserverrd;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.hdodenhof.circleimageview.CircleImageView;

public class BlankFragment2 extends Fragment {


    CircleImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_blank2, container, false);

        imageView = root.findViewById(R.id.profile_imagef);
        // Inflate the layout for this fragment

        constants.uri.observe(getActivity(), new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                imageView.setImageURI(uri);
            }
        });

        return root;
    }
}