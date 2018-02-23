package com.futureuniverse.fgandroidtest.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.futureuniverse.fgandroidtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Usama Sarwar on 3/31/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Nullable @BindView(R.id.toolbar) Toolbar toolbar;
    @Nullable @BindView(R.id.progress) ProgressBar progressBar;

    private Unbinder unbinder;

    protected abstract int getLayoutResource();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResource());

        unbinder = ButterKnife.bind(this);
        setToolbar(toolbar);
    }

    private void setToolbar(Toolbar toolbar) {
        if(toolbar == null) {
            return;
        }
        setSupportActionBar(toolbar);
    }

    public void setToolbarTitle(String title) {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void showLoading(boolean show) {
        if(progressBar == null) {
            return;
        }
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void loadFragment(int containerId, Fragment fragment, String tag, boolean addToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(containerId, fragment, tag);
        if(addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.commitAllowingStateLoss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
