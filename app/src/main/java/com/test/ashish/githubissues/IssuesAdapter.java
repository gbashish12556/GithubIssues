package com.test.ashish.githubissues;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.ashish.githubissues.Pojo.Issues;

import java.util.List;

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.ViewHolder> {

    private List<Issues> mItems;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView prNumberTextView;
        private TextView titleTextView;
        private TextView userTextView;
        private TextView patchUrlTextView;
        private TextView stateTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            prNumberTextView = (TextView) itemView.findViewById(R.id.prNumber);
            titleTextView = (TextView) itemView.findViewById(R.id.title);
            userTextView = (TextView) itemView.findViewById(R.id.userName);
            patchUrlTextView = (TextView) itemView.findViewById(R.id.patch_url);
            stateTextView = (TextView) itemView.findViewById(R.id.state);

        }

    }

    public IssuesAdapter(Context context, List<Issues> issues) {

        this.mItems = issues;
        this.mContext = context;

    }

    @Override
    public IssuesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.issues_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(IssuesAdapter.ViewHolder holder, int position) {

        Issues item = mItems.get(position);
        holder.prNumberTextView.setText(String.valueOf(item.getPrNumber()));
        holder.titleTextView.setText(item.getTitle());
        holder.userTextView.setText(item.getUser());
        holder.patchUrlTextView.setText(item.getPatchUrl());
        holder.stateTextView.setText(item.getState());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private Issues getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

    public void resetList(List<Issues> issues){
        mItems = issues;
        notifyDataSetChanged();
    }


}