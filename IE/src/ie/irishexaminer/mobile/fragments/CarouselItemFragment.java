package ie.irishexaminer.mobile.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

import ie.irishexaminer.mobile.events.CarouselClickEvent;
import ie.landmarkdigital.shared.BusProvider;
import ie.landmarkdigital.shared.R;
import ie.landmarkdigital.shared.TypefaceProvider;
import ie.landmarkdigital.shared.models.Article;

public class CarouselItemFragment extends SherlockFragment {

    private static final String ARTICLE = "article";

    public static CarouselItemFragment newInstance(int position, Article article) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putSerializable(ARTICLE, article);

        CarouselItemFragment fragment = new CarouselItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static final Handler UI_HANDLER = new Handler();

    private final OnClickListener onCarouselClick = new OnClickListener() {

        @Override
        public void onClick(View v) {
            int position = getArguments().getInt("position");
            BusProvider.getInstance().post(new CarouselClickEvent(position));
        }
    };
    private TextView  textHeadline;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.carousel_pager_item, container, false);
    }

    public Article getArticle() {
        return (Article) getArguments().getSerializable(ARTICLE);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.viewClick).setOnClickListener(onCarouselClick);

        Article article = getArticle();

        textHeadline = (TextView) view.findViewById(R.id.textGallery);
        String headline = article.category.toUpperCase() + ": " + article.headline.toUpperCase();
        textHeadline.setText(headline);
        textHeadline.setTypeface(TypefaceProvider.getBold());

        imageView = (ImageView) view.findViewById(R.id.imageArticle);
        if (article.images.length > 0) {
            String image = article.images[0];
            ImageLoader.getInstance().displayImage(image, imageView);
        } else {
            ImageLoader.getInstance().displayImage(null, imageView);
        }

        tryToSetImageViewMargin.run();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        tryToSetImageViewMargin.run();
    }

    private final Runnable tryToSetImageViewMargin = new Runnable() {

        @Override
        public void run() {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
            switch (textHeadline.getLineCount()) {
                case 0:
                    UI_HANDLER.postDelayed(tryToSetImageViewMargin, 50);
                    return;

                case 1:
                    params.topMargin = spToPx(getActivity(), 14);
                    break;

                case 2:
                    params.topMargin = spToPx(getActivity(), 32);
                    break;

                case 3:
                    params.topMargin = spToPx(getActivity(), 52);
                    break;
            }
            imageView.setLayoutParams(params);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        UI_HANDLER.removeCallbacks(tryToSetImageViewMargin);
    }

    ;

    public static int spToPx(Context context, float sp) {
        if (context != null) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
        } else {
            return (int) sp;
        }
    }

}
