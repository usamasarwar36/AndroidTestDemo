package com.futureuniverse.fgandroidtest.home.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.futureuniverse.fgandroidtest.R;
import com.futureuniverse.fgandroidtest.domain.model.Post;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    public class PostsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_title) TextView textTitle;
        @BindView(R.id.text_body) TextView textBody;

        View view;
        public PostsViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }

        public void bind(Post post, View.OnClickListener clickListener) {
            view.setTag(post);
            view.setOnClickListener(clickListener);
            textTitle.setText(post.getTitle());
            textBody.setText(post.getBody());
        }
    }

    List<Post> posts;
    View.OnClickListener onClickListener;

    public PostsAdapter(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setList(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, null);
        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostsViewHolder holder, int position) {
        holder.bind(posts.get(position), onClickListener);
    }

    @Override
    public int getItemCount() {
        return posts == null ? 0 : posts.size();
    }


}
