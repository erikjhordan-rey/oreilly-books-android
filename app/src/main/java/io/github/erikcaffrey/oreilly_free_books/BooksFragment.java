package io.github.erikcaffrey.oreilly_free_books;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BooksFragment extends Fragment {

    public static BooksFragment newInstance() {
        return new BooksFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.books_fragment, container, false);

        return view;
    }
}
