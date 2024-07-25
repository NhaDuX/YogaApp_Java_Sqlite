package edu.huflit.demo_yoga;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.google.firebase.firestore.auth.User;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideolistViewHolder> {

    private ArrayList<VideoList> videolists;
    Context context;
    VideoDBHelper videoDBHelper;
    public static class VideolistViewHolder extends RecyclerView.ViewHolder{
        TextView mName, mDe1 , mDe2, mvideoID;
        ImageView mIMGView,favoriteIV;
        public VideolistViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tvName);
            mDe1 = itemView.findViewById(R.id.tvDe1);
            mDe2 = itemView.findViewById(R.id.tvDe2);
            mIMGView = itemView.findViewById(R.id.imgItemView);
            mvideoID = itemView.findViewById(R.id.videoID);
            favoriteIV = itemView.findViewById(R.id.favoriteIV);
        }
    }
    public VideoAdapter(ArrayList<VideoList> videolists, Context context) {
        this.videolists = videolists;
        this.context = context;
    }
    @NonNull
    @Override
    public VideolistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View videolistView = inflater.inflate(R.layout.videolistitemlayout, parent, false);
        VideolistViewHolder viewHolder = new VideolistViewHolder(videolistView) ;
        return viewHolder ;
    }

    public void onBindViewHolder(@NonNull VideolistViewHolder holder, int position) {
        VideoList item = videolists.get(position);
        Glide.with(this.context)
                        .load(item.getImageurl())
                                .into(holder.mIMGView);
        holder.mName.setText(item.getName());
        holder.mDe1.setText(item.getDe1());
        holder.mDe2.setText(item.getDe2());
        //khai báo thêm vào đây --------

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, VideoplayActivity.class);
                i.putExtra("VideoID@#", videolists.get(position).getId());
                i.putExtra("NAME@#", videolists.get(position).getName());
                i.putExtra("Details#@", videolists.get(position).getDetails());
                i.putExtra("Favorite#@",videolists.get(position).getFavorite());
                i.putExtra("Viewed#@",videolists.get(position).getViewed());
                i.putExtra("vID#@",videolists.get(position).getVid());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return videolists.size();
    }
}
