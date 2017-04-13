package com.lethalskillzz.blockbusters.blockbusters.presentation.Discovery;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lethalskillzz.blockbusters.R;
import com.lethalskillzz.blockbusters.blockbusters.data.model.Result;
import com.lethalskillzz.blockbusters.blockbusters.util.ConnectionDetector;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DiscoveryActivity extends AppCompatActivity implements DiscoveryMvpContract.View {

    private static final String TAG = "DiscoveryActivity";
    DiscoveryMvpContract.Presenter presenter;
    private ConnectionDetector cd;
    private DiscoveryAdapter discoveryAdapter;

    @Bind(R.id.discovery_toolbar)
    Toolbar toolbar;
    @Bind(R.id.discovery_recycler_view)
    RecyclerView rView;
    @Bind(R.id.discovery_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.refresh_text)
    TextView refreshText;
    @Bind(R.id.discovery_coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        // creating connection detector class instance
        cd = new ConnectionDetector(this);

        presenter = new DiscoveryPresenter();
        presenter.attachView(this);

        setupViews();

        discoveryAdapter = new DiscoveryAdapter(this);
        rView.setAdapter(discoveryAdapter);

        presenter.getPage();


    }

    private  void setupViews () {

        LinearLayoutManager lLayout = new LinearLayoutManager(this);
        rView.setLayoutManager(lLayout);
        rView.setHasFixedSize(true);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (cd.isConnectingToInternet()) {

                    presenter.getPage();

                } else {
                    mSwipeRefreshLayout.setRefreshing(false);
                    showError(getString(R.string.error_no_internet));
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showResults(List<Result> results) {
        discoveryAdapter.setResults(results);
    }

    @Override
    public void showError(String msg) {

        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, msg, Snackbar.LENGTH_LONG);

        // Changing message text color
        snackbar.setActionTextColor(Color.RED);

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }


}
