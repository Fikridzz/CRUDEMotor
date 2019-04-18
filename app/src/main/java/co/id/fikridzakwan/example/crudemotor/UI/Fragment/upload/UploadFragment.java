package co.id.fikridzakwan.example.crudemotor.UI.Fragment.upload;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.id.fikridzakwan.example.crudemotor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadFragment extends Fragment {


    public UploadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }

}
