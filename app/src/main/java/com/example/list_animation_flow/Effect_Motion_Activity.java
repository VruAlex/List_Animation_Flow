package com.example.list_animation_flow;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Effect_Motion_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Hide status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view);

        RecyclerView list = findViewById(R.id.List);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new MessageAdapter(this));
        list.setHasFixedSize(true);
    }


    public class MessageAdapter extends RecyclerView.Adapter<DataHolder>{

        private final int[] COLORS = new int[] { 0xff956689, 0xff80678A, 0xff6A6788, 0xff546683, 0xff3F657B };
        private Activity host;
        private final LayoutInflater inflater;

        MessageAdapter(Activity activity){
            host = activity;
            inflater = LayoutInflater.from(host);
        }
        @NonNull
        @Override
        public DataHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
            return new DataHolder(inflater.inflate(R.layout.list_layout, viewGroup , false));
        }

        @Override
        public void onBindViewHolder(final DataHolder holder, final int position){
            final int color = COLORS[position % COLORS.length];
            holder.Image.setBackgroundTintList(ColorStateList.valueOf(color));
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Intent intent = new Intent(host, DetailsActivity.class);
                    boolean curve = (position % 2 == 0);
                    intent.putExtra(DetailsActivity.COLOR, color);
                    intent.putExtra(DetailsActivity.CURVE, curve);
                    host.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(
                            host, holder.Image, holder.Image.getTransitionName()).toBundle());

            }
        });

    }

    @Override
        public int getItemCount(){
        return 64;
        }
    }

    static class DataHolder extends RecyclerView.ViewHolder{
        View Image;
        View Name;
        View Title;

        DataHolder(View item){
            super(item);
            Image = item.findViewById(R.id.Picture);
            Name = item.findViewById(R.id.Name);
            Title = item.findViewById(R.id.Title);

        }

    }


}

