package bingyan.net.cyc.viewpagercards.cardpager;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

import bingyan.net.cyc.viewpagercards.R;

public class ShadowTransformer implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

    private ViewPager viewPager;
    private CardAdapter cardAdapter;
    private float lastOffset;
    private boolean scalingEnable;

    public ShadowTransformer(ViewPager viewPager, CardAdapter cardAdapter) {
        this.viewPager = viewPager;
        viewPager.setOnPageChangeListener(this);
        this.cardAdapter = cardAdapter;
    }

    public void enableScaling(boolean enable) {
        if (scalingEnable && !enable) {
            CardView currentCard = cardAdapter.getCardViewAt(viewPager.getCurrentItem());
            if (currentCard != null) {
                currentCard.animate().scaleX(1);
                currentCard.animate().scaleY(1);
            }
        } else if (!scalingEnable && enable) {
            CardView currentCard = cardAdapter.getCardViewAt(viewPager.getCurrentItem());
            if (currentCard != null) {
                currentCard.animate().scaleY(1.1f);
                currentCard.animate().scaleX(1.1f);
            }
        }
        scalingEnable = enable;
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int realCurrentPostition;
        int nextPostition;
        float baseElevation = cardAdapter.getBaseElevation();
        float realOffset;
        boolean goingLeft = lastOffset > positionOffset;
        Log.d("postion",Integer.toString(position));
        Log.d("lastOffset",Float.toString(lastOffset));
        Log.d("postionOffset",Float.toString(positionOffset));
        if (goingLeft) {
            realCurrentPostition = position + 1;
            nextPostition = position;
            realOffset = 1 - positionOffset;
            Log.d("scale","left");
        } else {
            nextPostition = position + 1;
            realCurrentPostition = position;
            realOffset = positionOffset;
        }
        if (nextPostition > cardAdapter.getCount() - 1 || realCurrentPostition > cardAdapter.getCount() - 1) {
            return;
        }
        CardView currentCard = cardAdapter.getCardViewAt(realCurrentPostition);
        if (currentCard != null) {
            if (scalingEnable) {
                currentCard.setScaleX((float) (1 + 0.1 * (1 - realOffset)));
                currentCard.setScaleY((float) (1 + 0.1 * (1 - realOffset)));
            }
            currentCard.setCardElevation(baseElevation + baseElevation * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset));
        }
        CardView nextCard = cardAdapter.getCardViewAt(nextPostition);
        if (nextCard != null) {
            if (scalingEnable) {
                nextCard.setScaleX((float) (1 + 0.1 * (realOffset)));
                nextCard.setScaleY((float) (1 + 0.1 * (realOffset)));
            }
            nextCard.setCardElevation(baseElevation + baseElevation * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (realOffset));
        }
        lastOffset = positionOffset;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void transformPage(@NonNull View page, float position) {
        Log.d("postion",Float.toString(position));
       // int pagePosition=(int)page.getTag();
        int pageWidth=page.getWidth();
        float pageWidthTimePostion=pageWidth*position;
        float absPosition=Math.abs(position);

        if (position<=-1f||position>=1.0f){

        }
        else if (position==0.0f){

        }
        else {
            View title=page.findViewById(R.id.titleTextView);
            title.setAlpha(1.0f-absPosition);
            View content=page.findViewById(R.id.contentTextView);
            content.setTranslationY(-pageWidthTimePostion/2f);
            content.setAlpha(1.0f-absPosition);
        }
    }
}
