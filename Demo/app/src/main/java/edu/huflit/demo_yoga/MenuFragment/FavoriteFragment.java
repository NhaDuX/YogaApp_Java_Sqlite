package edu.huflit.demo_yoga.MenuFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import edu.huflit.demo_yoga.MainActivity;
import edu.huflit.demo_yoga.R;
import edu.huflit.demo_yoga.VideoAdapter;
import edu.huflit.demo_yoga.VideoDBHelper;
import edu.huflit.demo_yoga.VideoList;

public class FavoriteFragment extends Fragment {
    VideoAdapter videoAdapter;
    ArrayList<VideoList> videolists;
    VideoDBHelper videoDBHelper;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        final RecyclerView recyclerView =  view.findViewById(R.id.rvFavorite);
        videoDBHelper = new VideoDBHelper(getActivity());
        videolists = videoDBHelper.getFAV();
        videoAdapter = new VideoAdapter(videolists,getActivity());
        recyclerView.setAdapter(videoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (getContext(),LinearLayoutManager.VERTICAL,false));

        videoAdapter.notifyDataSetChanged();
        FloatingActionButton delete = view.findViewById(R.id.deleteFAV);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Delete Favorite list?")
                        .setMessage("Are you sure you want to delete favorites list?")
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                videoDBHelper.deleteFAV();
                                videoDBHelper = new VideoDBHelper(getActivity());
                                videolists = videoDBHelper.getFAV();
                                videoAdapter = new VideoAdapter(videolists,getActivity());
                                recyclerView.setAdapter(videoAdapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager
                                        (getContext(),LinearLayoutManager.VERTICAL,false));
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
        return view;
    }
}