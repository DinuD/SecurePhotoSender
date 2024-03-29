package tk.dinud11.securephotosender;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import model.Photo;
import model.PhotoAdapter;
import model.PhotoDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private ArrayList<Photo> arrayList;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    private static ArrayList<Photo> getAllPhotos(PhotoDatabase db) {
        return new ArrayList<>(db.photoDao().getAll());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        arrayList = getAllPhotos(MainActivity.db);
        MainActivity.adapter = new PhotoAdapter(getContext(), arrayList);
        ListView listView = view.findViewById(R.id.history_list);
        listView.setAdapter(MainActivity.adapter);

        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.pull_to_refresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("refresh", "Refreshing");
                arrayList.clear();
                arrayList = getAllPhotos(MainActivity.db);
                pullToRefresh.setRefreshing(false);
            }
        });
    }
}
