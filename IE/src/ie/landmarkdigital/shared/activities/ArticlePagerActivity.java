package ie.landmarkdigital.shared.activities;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;

import com.actionbarsherlock.view.MenuItem;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;

import java.util.Locale;

import ie.landmarkdigital.shared.Preferences;
import ie.landmarkdigital.shared.R;
import ie.landmarkdigital.shared.TypefaceProvider;
import ie.landmarkdigital.shared.models.Article;
import ie.landmarkdigital.shared.models.Category;
import ie.landmarkdigital.shared.text.TextDrawable;

public class ArticlePagerActivity extends BaseArticlePagerActivity {

    public void setUpActionBar() {
        if (category != null) {
            Category parent = category.getParent();
            setTitle(parent.name.toUpperCase(Locale.ENGLISH));
            getSupportActionBar().setSubtitle(category.name);
        } else {
            if (articles != null && articles.size() > 0) {
                String ctgr = articles.get(0).category;
                if (ctgr != null) {
                    setTitle(ctgr.toUpperCase(Locale.ENGLISH));
                } else {
                    setTitle(R.string.app_name);
                }
            }
        }

        getSupportActionBar().setDisplayUseLogoEnabled(false);
        setRequestedOrientation(Preferences.getOrientation());
    }

    public int getIndicatorFooterColor() {
        return getResources().getColor(R.color.orange);
    }

    public void setBnTitle(Article article) { }

    public void onPageSelected(Article article) { }

    public void configureShareItem(MenuItem item) {
        TextDrawable logo = new TextDrawable(this);
        logo.setText(getString(R.string.share).toUpperCase(Locale.ENGLISH));
        logo.setTypeface(TypefaceProvider.getBold());
        logo.setTextColor(Color.WHITE);
        logo.setTextSize(38);

        item.setIcon(logo);
        item.getActionView().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Article article = articles.get(pager.getCurrentItem());
                startActivity(Intent.createChooser(generateShareIntent(article), "Share with.."));
                EasyTracker.getInstance(ArticlePagerActivity.this).send(MapBuilder.createEvent("interaction", "story share", article.headline, null).build());
            }
        });
    }
}
