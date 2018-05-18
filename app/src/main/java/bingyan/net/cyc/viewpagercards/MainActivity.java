package bingyan.net.cyc.viewpagercards;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import bingyan.net.cyc.viewpagercards.cardpager.CardItem;
import bingyan.net.cyc.viewpagercards.cardpager.CardPagerAdapter;
import bingyan.net.cyc.viewpagercards.cardpager.ShadowTransformer;

public class MainActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private CardPagerAdapter cardPagerAdapter;
    private ShadowTransformer shadowTransformer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        cardPagerAdapter=new CardPagerAdapter();
        cardPagerAdapter.addCardItem(new CardItem(R.string.title_1,R.string.content_text_1));
        cardPagerAdapter.addCardItem(new CardItem(R.string.title_2,R.string.content_text_2));
        cardPagerAdapter.addCardItem(new CardItem(R.string.title_3,R.string.content_text_3));
        cardPagerAdapter.addCardItem(new CardItem(R.string.title_4,R.string.content_text_4));
        cardPagerAdapter.addCardItem(new CardItem(R.string.title_5,R.string.content_text_5));
        cardPagerAdapter.addCardItem(new CardItem(R.string.title_6,R.string.content_text_6));
        cardPagerAdapter.setOnClickCallback(new CardPagerAdapter.OnClickCallback() {
            @Override
            public void onClick(int position) {

            }
        });
        shadowTransformer=new ShadowTransformer(viewPager,cardPagerAdapter);
        viewPager.setAdapter(cardPagerAdapter);
        viewPager.setPageTransformer(false,shadowTransformer);
        viewPager.setOffscreenPageLimit(4);
        shadowTransformer.enableScaling(true);
    }
}
