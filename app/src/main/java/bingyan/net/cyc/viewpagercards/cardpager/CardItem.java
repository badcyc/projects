package bingyan.net.cyc.viewpagercards.cardpager;

public class CardItem {
    private int mTextResources;
    private int mTitleResources;
    public CardItem(int title,int text){
        mTextResources=text;
        mTitleResources=title;
    }
    public int getText(){
        return mTextResources;
    }
    public int getTitle(){
        return mTitleResources;
    }
}
