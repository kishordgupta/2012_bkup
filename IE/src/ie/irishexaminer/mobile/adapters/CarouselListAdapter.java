package ie.irishexaminer.mobile.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import ie.irishexaminer.mobile.views.BeastViewPager;
import ie.landmarkdigital.shared.R;
import ie.landmarkdigital.shared.models.Article;

import static ie.irishexaminer.mobile.fragments.CarouselItemFragment.spToPx;

public class CarouselListAdapter extends BaseAdapter {

    private final LayoutInflater  inflater;
    private final FragmentManager childFragmentManager;
    private       List<Article>   articles;

    private CarouselPagerAdapter carouselPagerAdapter;
    private BeastViewPager       pager;
    private CirclePageIndicator  indicator;

    public CarouselListAdapter(Context context, List<Article> articles, FragmentManager childFragmentManager) {
        this.inflater = LayoutInflater.from(context);
        this.childFragmentManager = childFragmentManager;
        this.articles = articles;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public boolean isFullScreen() {
        if (pager != null && pager.getCurrentItem() != 0) {
            return false;
        }
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.carousel_list_item, parent, false);

            pager = (BeastViewPager) convertView.findViewById(R.id.pager);

            carouselPagerAdapter = new CarouselPagerAdapter(childFragmentManager, articles);
            pager.setAdapter(carouselPagerAdapter);

            indicator = (CirclePageIndicator) convertView.findViewById(R.id.indicator);
            indicator.setViewPager(pager);
        }

        if (articles != null && articles.size() > 0) {
            int width = Math.min(parent.getRootView().getWidth(), parent.getRootView().getHeight());
            convertView.getLayoutParams().height = width * 13 / 24 + spToPx(inflater.getContext(), 32);
        } else {
            convertView.setVisibility(View.GONE);
        }

        return convertView;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        if (carouselPagerAdapter != null && indicator != null) {
            this.articles = articles;
            carouselPagerAdapter.setArticles(articles);
            indicator.notifyDataSetChanged();
        }
    }

    public void refresh() {
        if (carouselPagerAdapter != null && indicator != null) {
            carouselPagerAdapter.notifyDataSetChanged();
            indicator.notifyDataSetChanged();
        }
    }
}
