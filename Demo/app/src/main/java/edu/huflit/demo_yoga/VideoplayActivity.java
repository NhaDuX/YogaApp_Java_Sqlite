package edu.huflit.demo_yoga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class VideoplayActivity extends AppCompatActivity {
    TextView mvideoid, mvideoname, mtvdetails;
    ImageView back;
    ImageButton favorite;
    VideoDBHelper videoDBHelper;
    ArrayList<VideoList> videolists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplay);


        //ánh xạ chi tiết của video


        mvideoname = findViewById(R.id.Video_NAME);
        mtvdetails = findViewById(R.id.tvDetails);
        back = findViewById(R.id.backIV);
        favorite = findViewById(R.id.favoriteIV);



        // nhận intent từ VideolistActivity và gán vào ID
        mvideoname.setText(getIntent().getStringExtra("NAME@#"));
        mtvdetails.setText(getIntent().getStringExtra("Details#@"));
        String ID = getIntent().getStringExtra("VideoID@#");
        int fav = getIntent().getIntExtra("Favorite#@",0);
        int vid = getIntent().getIntExtra("vID#@",0);



        //Dùng thư viện youtubeplayerView để chạy video từ YTID đã được intent và gán ở trên
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = ID;
                youTubePlayer.loadVideo(videoId, 0);
            }
        });


        videoDBHelper = new VideoDBHelper(VideoplayActivity.this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(VideoplayActivity.this, VideoListActivity.class);
                startActivity(back);
            }
        });
        videoDBHelper.viewed(vid);

        if(fav==1){
            favorite.setBackground(getDrawable(R.drawable.baseline_favorite_24));
        }
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fav==1){
                    favorite.setBackground(getDrawable(R.drawable.baseline_favorite_border_24));
                    videoDBHelper.unfavorite(vid);
                }
                else if(fav==0){
                    favorite.setBackground(getDrawable(R.drawable.baseline_favorite_24));
                    videoDBHelper.favorite(vid);
                }
            }
        });
    }
}