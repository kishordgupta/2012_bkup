package ie.landmarkdigital.shared.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.actionbarsherlock.view.Menu;

import java.util.Locale;

import ie.irishexaminer.mobile.fragments.HomeFragment;
import ie.landmarkdigital.shared.R;
import ie.landmarkdigital.shared.TypefaceProvider;
import ie.landmarkdigital.shared.events.SetTitleEvent;
import ie.landmarkdigital.shared.models.Category;
import ie.landmarkdigital.shared.text.TextDrawable;

/**
 * Created by adel on 2/24/14
 */
public class ArticleListActivity extends BaseArticleListActivity {

    TextView title;
    TextView subTitle;

    @Override
    protected void configureArticleListActionBar(boolean large) {
        if (!large) {
            TextDrawable logo = new TextDrawable(this);
            logo.setText(getString(R.string.menu).toUpperCase(Locale.ENGLISH));
            logo.setTypeface(TypefaceProvider.getBold());
            logo.setTextColor(Color.WHITE);
            logo.setTextSize(20);

            getSupportActionBar().setLogo(logo);

            getSupportActionBar().setCustomView(R.layout.ie_actionbar);
            getSupportActionBar().getCustomView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Category.getRoot() != null) {
                        onCategoryClick(Category.getRoot().getSubCategories()[0].getSubCategories()[0]);
                    }
                }
            });
            getSupportActionBar().setDisplayShowCustomEnabled(true);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(!large);
    }

    @Override
    protected Fragment newHomeFragment(Category category) {
        return HomeFragment.newInstance(category);
    }


    @Override
    protected void setTitle(SetTitleEvent event) {
        if (slidingMenu == null) {
            setTitle(event.title.toUpperCase());
            getSupportActionBar().setSubtitle(event.subTitle);
        } else {
            if (event == null || event.title == null) {
                return;
            }

            if (title != null) {
                title.setText(event.title.toUpperCase());
                subTitle.setText(event.subTitle);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (slidingMenu != null) {
            getSupportMenuInflater().inflate(R.menu.home, menu);
            View titles = menu.findItem(R.id.itemLatest).getActionView();

            title = (TextView) titles.findViewById(R.id.textTitle);
            title.setTypeface(TypefaceProvider.getBold());

            subTitle = (TextView) titles.findViewById(R.id.textSubtitle);
            subTitle.setTypeface(TypefaceProvider.getNormal());

            if (category != null) {
                title.setText(category.getParent().name);
                subTitle.setText(category.name);
            }

            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }
}
