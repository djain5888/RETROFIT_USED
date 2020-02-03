package com.news.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroViewHolder>
{
    List<Hero>heroList;
    Context context;
    static int currentposition=0;
    public  HeroAdapter(List<Hero>h1,Context context)
    {
        this.heroList=h1;
        this.context=context;
    }

    @NonNull
    @Override
    public HeroAdapter.HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.labelss,parent,false);
        return new HeroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroAdapter.HeroViewHolder holder, final int position) {
        Hero hero=heroList.get(position);

        holder.textViewName.setText(hero.getName());
        holder.textViewRealName.setText(hero.getRealname());
        holder.textViewTeam.setText(hero.getTeam());
        holder.textViewFirstAppearance.setText(hero.getFirstappearance());
        holder.textViewCreatedBy.setText(hero.getCreatedby());
        holder.textViewPublisher.setText(hero.getPublisher());
        holder.textViewBio.setText(hero.getBio().trim());

        Glide.with(context).load(hero.getImageurl()).into(holder.imageView);
        holder.linearLayout.setVisibility(View.GONE);

        if (currentposition == position) {
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.anim);

            //toggling visibility
            holder.linearLayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.linearLayout.startAnimation(slideDown);
        }

        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the position of the item to expand it
                currentposition = position;

                //reloding the list
                notifyDataSetChanged();
            }
        });
    }

//        holder.l1.setVisibility(View.GONE);
//        if(currentposition!=position)
//        {
//            Animation slidedown= AnimationUtils.loadAnimation(context,R.anim.anim);
//            holder.l1.setVisibility(View.VISIBLE);
//            holder.l1.startAnimation(slidedown);
//        }
//        holder.c1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentposition=position;
//                notifyDataSetChanged();
//
//            }
//        });






    @Override
    public int getItemCount() {
        Log.i("hero",Integer.toString(heroList.size()));
        return heroList.size();
    }

    public class HeroViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewRealName, textViewTeam, textViewFirstAppearance,
                textViewCreatedBy, textViewPublisher, textViewBio;
        ImageView imageView;
        LinearLayout linearLayout;;
        LinearLayout l1;
        CardView c1;
        public HeroViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewRealName = (TextView) itemView.findViewById(R.id.textViewRealName);
            textViewTeam = (TextView) itemView.findViewById(R.id.textViewTeam);
            textViewFirstAppearance = (TextView) itemView.findViewById(R.id.textViewFirstAppearance);
            textViewCreatedBy = (TextView) itemView.findViewById(R.id.textViewCreatedBy);
            textViewPublisher = (TextView) itemView.findViewById(R.id.textViewPublisher);
            textViewBio = (TextView) itemView.findViewById(R.id.textViewBio);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            linearLayout=itemView.findViewById(R.id.linearLayout);
           c1=itemView.findViewById(R.id.card);


        }
    }
}
