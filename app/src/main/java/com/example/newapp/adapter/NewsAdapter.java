package com.example.newapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newapp.onClickListener.OnClickListener;
import com.example.newapp.R;
import com.example.newapp.model.Result;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.RecycleViewHolder> {

    private final String TAB = NewsAdapter.class.getSimpleName();
    private Context context;
    private List<Result> mlist;
    private OnClickListener mOnItemClickListener;


    public NewsAdapter(List<Result> list) {

        this.mlist = list;

    }

    public void SetOnItemClickListener(OnClickListener OnClickListener) {
        this.mOnItemClickListener = OnClickListener;
    }


    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        Result currentitem = mlist.get(position);
        holder.mId.setText("Id: " + currentitem.getId());
        holder.mType.setText("Type: " + currentitem.getType());
        holder.mSectionId.setText("SectionId: " + currentitem.getSectionId());
        holder.mSectioName.setText("SectionName: " + currentitem.getSectionName());
        holder.mWebPub.setText("Web PublicationDate " + currentitem.getWebPublicationDate());
        holder.mWebTitle.setText("Web Title: " + currentitem.getWebTitle());
        holder.mWeburl.setText("Web Url: " + currentitem.getWebUrl());
        holder.mApiurl.setText("Api Url: " + currentitem.getApiUrl());
        holder.mIsHosted.setText("Is Hosted: " + currentitem.getIsHosted());
        holder.mPillarId.setText("PillarId: " + currentitem.getPillarId());
        holder.mPillarName.setText("PillarName: " + currentitem.getPillarName());
        holder.bind(mlist.get(position), mOnItemClickListener);


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    static class RecycleViewHolder extends RecyclerView.ViewHolder {

        private TextView mId;
        private TextView mType;
        private TextView mSectionId;
        private TextView mSectioName;
        private TextView mWebPub;
        private TextView mWebTitle;
        private TextView mWeburl;
        private TextView mApiurl;
        private TextView mIsHosted;
        private TextView mPillarId;
        private TextView mPillarName;


        RecycleViewHolder(View itemView) {
            super(itemView);
            mId = itemView.findViewById(R.id.tvId);
            mType = itemView.findViewById(R.id.tvType);
            mSectionId = itemView.findViewById(R.id.tvSid);
            mSectioName = itemView.findViewById(R.id.tvName);
            mWebPub = itemView.findViewById(R.id.tvWebPu);
            mWebTitle = itemView.findViewById(R.id.tvWebT);
            mWeburl = itemView.findViewById(R.id.tvWebU);
            mApiurl = itemView.findViewById(R.id.tvApiU);
            mIsHosted = itemView.findViewById(R.id.tvIshosted);
            mPillarId = itemView.findViewById(R.id.tvPillarId);
            mPillarName = itemView.findViewById(R.id.tvPillarName);


        }

        void bind(final Result result, final OnClickListener OnItemListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OnItemListener.OnItemClick(result, getAdapterPosition());
                }
            });
        }

    }
}