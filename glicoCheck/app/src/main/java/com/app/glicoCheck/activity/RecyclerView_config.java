package com.app.glicoCheck.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.glicoCheck.R;
import com.app.glicoCheck.model.GlicoseUser;

import java.util.List;

public class RecyclerView_config {
    private Context mContext;
    private GlicoAdapter mGlicoAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<GlicoseUser> glicoseUser, List<String> keys) {
         mContext = context;
         mGlicoAdapter = new GlicoAdapter(glicoseUser,keys);
         recyclerView.setAdapter(mGlicoAdapter);

    }

    class userCligoView extends RecyclerView.ViewHolder{
            private TextView mGlico;
            private TextView mDia;

            private String Key;
        private String key;

        public userCligoView(ViewGroup parent){
                super(LayoutInflater.from(mContext).inflate(R.layout.templatelist,parent,false));

                mGlico = (TextView) itemView.findViewById(R.id.txtView_glicosenivel);
                mDia = (TextView) itemView.findViewById(R.id.txtView_Diaglico);
            }

            public void bind(GlicoseUser glicoseUser, String key){
                mGlico.setText(glicoseUser.getGlicose());
                mDia.setText(glicoseUser.getDia());
                this.key = key;
            }


    }

    class GlicoAdapter extends  RecyclerView.Adapter<userCligoView>{
        private List<GlicoseUser> mGlicouserList;
        private List<String> mKey;

        @NonNull
        @Override
        public userCligoView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new userCligoView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull userCligoView holder, int position) {
            holder.bind(mGlicouserList.get(position), mKey.get(position));
        }

        @Override
        public int getItemCount() {
            return mGlicouserList.size();
        }

        public GlicoAdapter(List<GlicoseUser> mGlicouserList, List<String> mKey) {
            this.mGlicouserList = mGlicouserList;
            this.mKey = mKey;
        }
    }
}
