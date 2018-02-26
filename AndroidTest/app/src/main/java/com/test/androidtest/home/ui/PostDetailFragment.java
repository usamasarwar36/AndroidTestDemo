package com.test.androidtest.home.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.test.androidtest.R;
import com.test.androidtest.base.Utils;
import com.test.androidtest.base.ui.BaseActivity;
import com.test.androidtest.base.ui.BaseFragment;
import com.test.androidtest.domain.model.Comment;
import com.test.androidtest.domain.model.Post;
import com.test.androidtest.domain.model.User;
import com.test.androidtest.presenter.CommentsPresenter;
import com.test.androidtest.presenter.UserPresenter;
import com.test.androidtest.presenter.interfaces.GetPostCommentsCallback;
import com.test.androidtest.presenter.interfaces.GetUserCallback;

import java.util.List;

import butterknife.BindView;

public class PostDetailFragment extends BaseFragment {
    private OnFragmentInteractionListener listener;

    @BindView(R.id.text_title) TextView textTitle;
    @BindView(R.id.text_user_name) TextView textAuthorName;
    @BindView(R.id.text_comments_count) TextView textCommentsCount;
    @BindView(R.id.text_body) TextView textBody;

    private Post post;

    private CommentsPresenter commentsPresenter;
    private UserPresenter userPresenter;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_post_detail;
    }

    public PostDetailFragment() {
        // Required empty public constructor
    }

    public static PostDetailFragment newInstance(Post post) {
        PostDetailFragment fragment = new PostDetailFragment();
        fragment.post = post;
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar();
        initializePresenters();
        setupViews();
        if(Utils.isNetworkAvailable(getContext())) {
            getUser();
            getComments();
        } else {
            Toast.makeText(getContext(), getString(R.string.no_network_error), Toast.LENGTH_LONG).show();
        }
    }

    private void getComments() {
        commentsPresenter.getPostComments(post.getId(), new GetPostCommentsCallback() {
            @Override
            public void onPostCommentsFetched(List<Comment> comments) {
                if(getContext() == null) {
                    return;
                }
                textCommentsCount.setVisibility(View.VISIBLE);
                textCommentsCount.setText(String.valueOf(comments.size()));
            }

            @Override
            public void onPostCommentsError(String errorMessage) {
                if(getContext() == null) {
                    return;
                }
                textCommentsCount.setVisibility(View.GONE);
            }
        });
    }

    private void getUser() {
        userPresenter.getUserById(post.getUserId(), new GetUserCallback() {
            @Override
            public void onUserFetched(User user) {
                if(getContext() == null) {
                    return;
                }
                if(user != null) {
                    textAuthorName.setVisibility(View.VISIBLE);
                    textAuthorName.setText(user.getName());
                } else {
                    textAuthorName.setVisibility(View.GONE);
                }
            }

            @Override
            public void onUserError(String errorMessage) {
                if(getContext() == null) {
                    return;
                }
                textAuthorName.setVisibility(View.GONE);
            }
        });
    }

    private void initializePresenters() {
        commentsPresenter = new CommentsPresenter();
        userPresenter = new UserPresenter();
    }

    private void setupViews() {
        textTitle.setText(post.getTitle());
        textBody.setText(post.getBody());
    }

    private void setToolbar() {
        ((BaseActivity)getActivity()).setToolbarTitle(getString(R.string.post_detials));
        if(((BaseActivity)getActivity()).getSupportActionBar() != null) {
            ((BaseActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroyView() {
        if(listener != null) {
            listener.onPostDetailsFragmentClosed();
        }
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {
        void onPostDetailsFragmentClosed();
    }
}
