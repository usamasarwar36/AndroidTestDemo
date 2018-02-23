package com.futureuniverse.fgandroidtest.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.futureuniverse.fgandroidtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    protected abstract int getFragmentLayout();

    @Nullable @BindView(R.id.progress) ProgressBar progressBar;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void showLoading(boolean show) {
        if(progressBar == null) {
            return;
        }
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
