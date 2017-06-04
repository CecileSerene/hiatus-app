package hiatus.hiatusapp.previews;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import hiatus.hiatusapp.contribution.photo.PhotoContent;
import hiatus.hiatusapp.R;

public class PreviewPhotoFragment extends Fragment {

    private Bitmap mPhoto;

    public PreviewPhotoFragment() {
        // Required empty public constructor
    }

    public static PreviewPhotoFragment newInstance(PhotoContent content) {
        PreviewPhotoFragment fragment = new PreviewPhotoFragment();
        Bundle args = new Bundle();
        args.putParcelable("photo", content.getPhoto());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPhoto = getArguments().getParcelable("photo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preview_photo, container, false);
        ((ImageView) view.findViewById(R.id.photo)).setImageBitmap(mPhoto);
        return view;
    }

    public void updateImage(Bitmap image) {
        ((ImageView) getView().findViewById(R.id.photo)).setImageBitmap(image);
    }


}
