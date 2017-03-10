package io.github.erikcaffrey.oreilly_free_books;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DownloadsFragment extends Fragment {

    public static DownloadsFragment newInstance() {
        return new DownloadsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.downloads_fragment, container, false);

        return view;
    }
}
