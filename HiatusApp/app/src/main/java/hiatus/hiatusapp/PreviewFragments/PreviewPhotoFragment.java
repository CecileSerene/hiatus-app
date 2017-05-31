package hiatus.hiatusapp.PreviewFragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import hiatus.hiatusapp.ContributionContent.PhotoContent;
import hiatus.hiatusapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PreviewPhotoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PreviewPhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreviewPhotoFragment extends Fragment {

    // TODO: Rename and change types of parameters
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
