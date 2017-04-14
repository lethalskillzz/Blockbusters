package com.lethalskillzz.blockbusters.blockbusters.presentation.Discovery;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lethalskillzz.blockbusters.R;
import com.lethalskillzz.blockbusters.blockbusters.data.model.Result;
import com.lethalskillzz.blockbusters.blockbusters.util.ConnectionDetector;
import com.lethalskillzz.blockbusters.blockbusters.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.lethalskillzz.blockbusters.blockbusters.manager.AppConfig.ORDER_TYPE_KEY;

public class DiscoveryActivity extends AppCompatActivity implements DiscoveryMvpContract.View {

    private static final String TAG = "DiscoveryActivity";
    DiscoveryMvpContract.Presenter presenter;
    private ConnectionDetector cd;
    private DiscoveryAdapter discoveryAdapter;

    private ActionBar mActionBar;
    private String mOrderType;
    private List<Result> mResult;

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
        mActionBar = getSupportActionBar();


        // creating connection detector class instance
        cd = new ConnectionDetector(this);
        mResult = new ArrayList<>();
        mOrderType = "popularity";

        refreshActionBar();

        presenter = new DiscoveryPresenter();
        presenter.attachView(this);

        setupViews();

        discoveryAdapter = new DiscoveryAdapter(this);
        rView.setAdapter(discoveryAdapter);

        refreshText.setVisibility(View.GONE);


        if (cd.isConnectingToInternet()) {

            presenter.getPage(mOrderType);

        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            showError(getString(R.string.error_no_internet));
        }


    }

    private  void setupViews () {

        //LinearLayoutManager lLayout = new LinearLayoutManager(this);
        rView.addItemDecoration(new SpacesItemDecoration(10));
        GridLayoutManager gLayout = new GridLayoutManager(this, 2);
        rView.setLayoutManager(gLayout);
        //rView.setHasFixedSize(true);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (cd.isConnectingToInternet()) {

                    presenter.getPage(mOrderType);

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
        mResult = results;
        discoveryAdapter.setResults(mResult);
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


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mOrderType != null) {
            outState.putString(ORDER_TYPE_KEY, mOrderType);
        }
        if (mResult != null) {
           // outState.putParcelable(RESULT_TEMP_KEY, mResult);
        }
        super.onSaveInstanceState(outState);
    }



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!mOrderType.equals("popularity")) {
            menu.findItem(R.id.menu_toggle).setTitle(R.string.menuTogglePopularity);
        } else {
            menu.findItem(R.id.menu_toggle).setTitle(R.string.menuToggleRating);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_discovery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_toggle:
                toggleOrderType(item);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void refreshActionBar() {
        if (mActionBar != null) {
            mActionBar.setSubtitle(getString(R.string.subtitleOrderedPrefix) +
                    (mOrderType.equals("popularity")
                            ? getString(R.string.subtitlePopularity)
                            : getString(R.string.subtitleRating)));
            mActionBar.invalidateOptionsMenu();
        }
    }

    private void toggleOrderType(MenuItem item) {

        if (cd.isConnectingToInternet()) {

            if (item.getTitle().equals(getString(R.string.menuTogglePopularity))) {
                item.setTitle(getString(R.string.menuToggleRating));
                mOrderType = "popularity";
            } else {
                item.setTitle(getString(R.string.menuTogglePopularity));
                mOrderType = "top_rating";
            }

            presenter.getPage(mOrderType);

            refreshActionBar();

        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            showError(getString(R.string.error_no_internet));
        }
    }
}
