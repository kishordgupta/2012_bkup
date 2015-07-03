package ie.irishexaminer.mobile.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.commonsware.cwac.merge.MergeAdapter;
import com.handmark.pulltorefresh.extras.listfragment.PullToRefreshListFragment;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.stiggpwnz.asynchttpclient.AsyncHttpClient;
import com.stiggpwnz.asynchttpclient.ErrorListener;
import com.stiggpwnz.asynchttpclient.MultiResponseListener;
import com.stiggpwnz.asynchttpclient.ResponseListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ie.irishexaminer.mobile.HomeRequest;
import ie.irishexaminer.mobile.adapters.CarouselListAdapter;
import ie.irishexaminer.mobile.events.CarouselClickEvent;
import ie.landmarkdigital.shared.ArticleListAdapter;
import ie.landmarkdigital.shared.BusProvider;
import ie.landmarkdigital.shared.Log;
import ie.landmarkdigital.shared.R;
import ie.landmarkdigital.shared.TypefaceProvider;
import ie.landmarkdigital.shared.activities.ArticlePagerActivity;
import ie.landmarkdigital.shared.events.SetTitleEvent;
import ie.landmarkdigital.shared.fragments.ArticleListFragment;
import ie.landmarkdigital.shared.models.Article;
import ie.landmarkdigital.shared.models.Category;
import ie.landmarkdigital.shared.requests.ArticleRequest;

import static ie.landmarkdigital.shared.fragments.ArticleListFragment.FIFTEEN_MINUTES;
import static ie.landmarkdigital.shared.fragments.ArticleListFragment.onInternetProblem;

public class HomeFragment extends PullToRefreshListFragment {

    private static final String POSITION = "position";
    private static final String CATEGORY = "category";

    public static HomeFragment newInstance(Category category) {
        Bundle args = new Bundle();
        args.putSerializable(CATEGORY, category);

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private Category category;

    private CarouselListAdapter carouselListAdapter;
    private ArticleListAdapter  articleListAdapter;

    private final OnRefreshListener<ListView> onRefreshListener = new OnRefreshListener<ListView>() {

        @Override
        public void onRefresh(PullToRefreshBase<ListView> refreshView) {
            HomeRequest request = createRequest();
            request.setCacheAge(1);
            AsyncHttpClient.getInstance().execute(request);
        }
    };

    private long lastUpdated;

    private void setLastUpdated(long when) {
        if (getSherlockActivity() != null) {
            String label;
            if (when > 0) {
                label = getString(R.string.last_updated) + " " + DateUtils.getRelativeTimeSpanString(when);
                lastUpdated = when;
            } else {
                label = getString(R.string.never_updated);
            }
            getPullToRefreshListView().getLoadingLayoutProxy().setLastUpdatedLabel(label);
        }
    }

    private OnPullEventListener<ListView> onPullEventListener = new OnPullEventListener<ListView>() {

        @Override
        public void onPullEvent(PullToRefreshBase<ListView> refreshView, State state, Mode direction) {
            if (state == State.PULL_TO_REFRESH) {
                setLastUpdated(lastUpdated);
            }
        }
    };

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        category = (Category) getArguments().getSerializable(CATEGORY);

        getPullToRefreshListView().setOnItemClickListener(onItemClickListener);
        getPullToRefreshListView().setOnRefreshListener(onRefreshListener);
        getPullToRefreshListView().setOnPullEventListener(onPullEventListener);
        getListView().setDivider(null);

        ILoadingLayout loadingLayoutProxy = getPullToRefreshListView().getLoadingLayoutProxy();
        loadingLayoutProxy.setTextTypeface(TypefaceProvider.getBold());
        loadingLayoutProxy.setSubTextTypeface(TypefaceProvider.getNormal());

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("carousel") && savedInstanceState.containsKey("list")) {
                List<List<Article>> responses = new ArrayList<List<Article>>(2);
                responses.add((List<Article>) savedInstanceState.getSerializable("carousel"));
                responses.add((List<Article>) savedInstanceState.getSerializable("list"));
                setAdapter(responses);
                lastUpdated = savedInstanceState.getLong("last updated");
                return;
            }
        }

        HomeRequest request = createRequest();
        request.setCacheAge(FIFTEEN_MINUTES);
        AsyncHttpClient.getInstance().execute(request);
    }


    private HomeRequest createRequest() {
        List<String> urls = new ArrayList<String>(category.getSubCategories().length);
        for (Category cat : category.categories) {
            urls.add(cat.url);
        }

        return new HomeRequest(urls, new MultiResponseListener<List<Article>>() {

            @Override
            public void onResponse(List<List<Article>> responses, long updated) {
                setAdapter(responses);
                setLastUpdated(updated);
                if (Category.getRoot().show_sponsored) {
                    ArticleRequest request = new ArticleRequest(Category.getRoot().sponsored_url,
                            new ResponseListener<List<Article>>() {

                                @Override
                                public void onResponse(List<Article> response, long updated) {
                                    for (Article article : response) {
                                        if (article.category.contains(category.name) || article.category.contains(category.adsCategory)) {
                                            article.sponsored = true;
                                            articleListAdapter.getArticles().add(2, article);
                                            articleListAdapter.notifyDataSetChanged();
                                            return;
                                        }
                                    }
                                }
                            }, new ErrorListener() {

                        @Override
                        public void onError(Exception e) {
                            Log.e(e);
                        }
                    }, ArticleListFragment.SPONSORED
                    );
                    request.setCacheAge(FIFTEEN_MINUTES);
                    AsyncHttpClient.getInstance().execute(request);
                }
            }
        }, new ErrorListener() {

            @Override
            public void onError(Exception e) {
                onInternetProblem(getSherlockActivity());
                setAdapter(null);
            }
        }, HomeFragment.class
        );
    }

    ;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("last updated", lastUpdated);
        if (carouselListAdapter != null && articleListAdapter != null) {
            outState.putSerializable("carousel", (Serializable) carouselListAdapter.getArticles());
            outState.putSerializable("list", (Serializable) articleListAdapter.getArticles());
        }
    }

    private void setAdapter(List<List<Article>> responses) {
        if (getSherlockActivity() == null) {
            return;
        }

        MergeAdapter mergeAdapter = new MergeAdapter();

        carouselListAdapter = new CarouselListAdapter(getActivity(), responses != null ? responses.get(0) : null, getChildFragmentManager());
        mergeAdapter.addAdapter(carouselListAdapter);

        articleListAdapter = new ArticleListAdapter(getActivity(), responses != null ? responses.get(1) : null, false);
        mergeAdapter.addAdapter(articleListAdapter);

        setListAdapter(mergeAdapter);

        if (getPullToRefreshListView().isRefreshing()) {
            getPullToRefreshListView().onRefreshComplete();
        }
    }

    @Override
    public MergeAdapter getListAdapter() {
        return (MergeAdapter) super.getListAdapter();
    }

    private final OnItemClickListener onItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
            Intent intent = new Intent(getSherlockActivity(), ArticlePagerActivity.class).putExtra("articles", (Serializable) articleListAdapter.getArticles())
                    .putExtra(POSITION, position - 2).putExtra(CATEGORY, category.getSubCategories()[1]);
            startActivity(intent);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
        BusProvider.getInstance().post(getTitle());

        if (System.currentTimeMillis() - lastUpdated > FIFTEEN_MINUTES) {
            getPullToRefreshListView().setRefreshing();
        }

        if (getListAdapter() != null) {
            getListAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    public SetTitleEvent getTitle() {
        Category parent = category.getParent();
        return new SetTitleEvent(category.name, parent.name);
    }

    public void onEvent(CarouselClickEvent event) {
        Intent intent = new Intent(getSherlockActivity(), ArticlePagerActivity.class).putExtra("articles", (Serializable) carouselListAdapter.getArticles())
                .putExtra(POSITION, event.position).putExtra(CATEGORY, category.getSubCategories()[0]);
        startActivity(intent);
    }

}
