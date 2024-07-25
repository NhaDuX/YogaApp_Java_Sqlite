package edu.huflit.demo_yoga.MenuFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.ArrayList;

import edu.huflit.demo_yoga.R;
import edu.huflit.demo_yoga.VideoAdapter;
import edu.huflit.demo_yoga.VideoDBHelper;
import edu.huflit.demo_yoga.VideoList;

public class SearchFragment extends Fragment {
    private ArrayList<VideoList> videolists;
    SearchView searchView;
    private VideoAdapter videoAdapter;
    VideoDBHelper videoDBHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        final RecyclerView recyclerView =  view.findViewById(R.id.SearchRycyclerView);
        searchView = view.findViewById(R.id.searchView);
        videoDBHelper = new VideoDBHelper(getActivity());
        videolists = videoDBHelper.getVideos();
        videoAdapter = new VideoAdapter(videolists,getActivity());
        recyclerView.setAdapter(videoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (getContext(),LinearLayoutManager.VERTICAL,false));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                videoDBHelper = new VideoDBHelper(getActivity());
                videolists = videoDBHelper.search(searchView.getQuery().toString());
                videoAdapter = new VideoAdapter(videolists,getActivity());
                recyclerView.setAdapter(videoAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager
                        (getContext(),LinearLayoutManager.VERTICAL,false));
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                videoDBHelper = new VideoDBHelper(getActivity());
                videolists = videoDBHelper.search(searchView.getQuery().toString());
                videoAdapter = new VideoAdapter(videolists,getActivity());
                recyclerView.setAdapter(videoAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager
                        (getContext(),LinearLayoutManager.VERTICAL,false));
                return false;
            }
        });
        return view;

    }
}