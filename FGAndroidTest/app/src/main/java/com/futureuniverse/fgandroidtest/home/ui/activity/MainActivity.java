package com.futureuniverse.fgandroidtest.home.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.futureuniverse.fgandroidtest.R;
import com.futureuniverse.fgandroidtest.base.Utils;
import com.futureuniverse.fgandroidtest.base.ui.BaseActivity;
import com.futureuniverse.fgandroidtest.domain.logic.implementation.GetPostsImpl;
import com.futureuniverse.fgandroidtest.domain.logic.interfaces.GetPosts;
import com.futureuniverse.fgandroidtest.domain.model.Post;
import com.futureuniverse.fgandroidtest.home.ui.PostDetailFragment;
import com.futureuniverse.fgandroidtest.home.ui.adapter.PostsAdapter;
import com.futureuniverse.fgandroidtest.presenter.PostPresenter;
import com.futureuniverse.fgandroidtest.presenter.interfaces.GetPostsCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.paperdb.Paper;

public class MainActivity extends BaseActivity implements PostDetailFragment.OnFragmentInteractionListener, GetPostsCallback {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.swipe_to_refresh) SwipeRefreshLayout swipeToRefresh;
    @BindView(R.id.text_error) TextView textError;

    private PostsAdapter postsAdapter;

    private PostPresenter postPresenter;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar();
        setupRecyclerView();
        postPresenter = new PostPresenter();
        getPosts();
    }

    private void setToolbar() {
        setToolbarTitle(getString(R.string.posts));
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    private void setupRecyclerView() {
        postsAdapter = new PostsAdapter(onItemClickListener);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(postsAdapter);
        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeToRefresh.setRefreshing(true);
                getPosts();
            }
        });
    }

    View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Post post = (Post) v.getTag();
            PostDetailFragment fragment = PostDetailFragment.newInstance(post);
            loadFragment(R.id.fragment_container, fragment, "post_detials_" + post.getId(), true);
        }
    };

    private void getPosts() {
        if(Utils.isNetworkAvailable(this)) {
            showLoading(true);
            postPresenter.getAllPosts(this);
        } else {
            List<Post> posts = Paper.book().read(GetPosts.KEY_PAPER_POSTS, null);
            if(posts != null) {
                onPostsFetched(posts);
                return;
            }
            textError.setText(getString(R.string.no_network_error));
            textError.setVisibility(View.VISIBLE);
            swipeToRefresh.setRefreshing(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostsFetched(List<Post> posts) {
        if(!isDestroyed()) {
            swipeToRefresh.setRefreshing(false);
            showLoading(false);
            postsAdapter.setList(posts);
            textError.setText(getString(R.string.empty_list));
            textError.setVisibility(posts.size() == 0 ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onPostsError(String errorMessage) {
        if(!isDestroyed()) {
            swipeToRefresh.setRefreshing(false);
            showLoading(false);
            postsAdapter.setList(null);
            textError.setText(errorMessage);
            textError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPostDetailsFragmentClosed() {
        setToolbar();
    }
}
