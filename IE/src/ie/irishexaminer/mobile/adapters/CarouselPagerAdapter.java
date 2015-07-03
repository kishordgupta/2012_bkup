package ie.irishexaminer.mobile.adapters;

import ie.irishexaminer.mobile.fragments.CarouselItemFragment;
import ie.landmarkdigital.shared.models.Article;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CarouselPagerAdapter extends FragmentStatePagerAdapter {

	private List<Article> articles;

	public CarouselPagerAdapter(FragmentManager fm, List<Article> articles) {
		super(fm);
		this.articles = articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
		notifyDataSetChanged();
	}

	@Override
	public Fragment getItem(int arg0) {
		return CarouselItemFragment.newInstance(arg0, articles.get(arg0));
	}

	@Override
	public int getCount() {
		return articles != null ? articles.size() : 0;
	}
}
