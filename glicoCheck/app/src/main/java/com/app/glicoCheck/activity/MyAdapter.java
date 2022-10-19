package com.app.glicoCheck.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.glicoCheck.R;
import com.app.glicoCheck.model.GlicoseUser;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<GlicoseUser> mList;
    Context context;
    private OnItemClickListener listener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public  void setOnItemClickListener(OnItemClickListener clickListener){
        listener = clickListener;
    }

    public MyAdapter(Context context,ArrayList<GlicoseUser>mList){
        this.mList = mList;
        this.context = context;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemtemplate,parent,false);
        return new MyViewHolder(v,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GlicoseUser glicoseUser = mList.get(position);
        //precisa ser verificado
        holder.glicose.setText(glicoseUser.getGlicose());
        holder.dia.setText(glicoseUser.getDia());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView glicose, dia;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView,OnItemClickListener listener){
            super(itemView);
            imageView = itemView.findViewById(R.id.iconbtncDelete);
            glicose = itemView.findViewById(R.id.txtGlicose);
            dia = itemView.findViewById(R.id.txtDia);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
