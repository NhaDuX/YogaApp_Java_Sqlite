
package edu.huflit.demo_yoga;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class VideoListActivity extends AppCompatActivity  {
    RecyclerView recyclerView;
    ArrayList<VideoList> videolists;
    VideoAdapter videolistAdapter;
    VideoDBHelper videoDBHelper;
    FloatingActionButton nosort,sortaz,sortza;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        videoDBHelper = new VideoDBHelper(VideoListActivity.this);

        recyclerView = findViewById(R.id.RViewVList);

        videolists = videoDBHelper.getVideos();

        videolistAdapter= new VideoAdapter(videolists, VideoListActivity.this);

        recyclerView.setAdapter(videolistAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Back imageview to intent form videolist to mainactivity
        ImageView back = findViewById(R.id.backIV);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(VideoListActivity.this,MainActivity.class);
                startActivity(back);
            }
        });
        //sort
        nosort = findViewById(R.id.noSort);
        sortaz = findViewById(R.id.sortaz);
        sortza = findViewById(R.id.sortza);

        nosort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoDBHelper = new VideoDBHelper(VideoListActivity.this);
                recyclerView = findViewById(R.id.RViewVList);
                videolists = videoDBHelper.getVideos();
                videolistAdapter= new VideoAdapter(videolists, VideoListActivity.this);
                recyclerView.setAdapter(videolistAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(VideoListActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });
        sortaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videolists = videoDBHelper.azSortbyNAME();
                videolistAdapter = new VideoAdapter(videolists,VideoListActivity.this);
                LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(VideoListActivity.this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(linearLayoutManager2);
                recyclerView.setAdapter(videolistAdapter);
            }
        });
        sortza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videolists = videoDBHelper.zaSortbyNAME();
                videolistAdapter = new VideoAdapter(videolists,VideoListActivity.this);
                LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(VideoListActivity.this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(linearLayoutManager3);
                recyclerView.setAdapter(videolistAdapter);
            }
        });
    }
}