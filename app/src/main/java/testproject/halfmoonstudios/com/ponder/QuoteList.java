package testproject.halfmoonstudios.com.ponder;

import android.content.Context;

import java.util.ArrayList;

/**
 * Singleton object that stores quotes
 * Contains three methods:
 * Get() Checks if the object has been instantiated if not creates QuoteList object
 * getArray(int stringRes) Returns an arraylist of all quotes that have a matching category string resource
 * populateList() populates mQuoteList with quotes
 */
public class QuoteList {
    private static QuoteList mQuoteList;
    private static ArrayList<Quote> mQuoteArray = new ArrayList<>();
    private Context mAppContext;

    private QuoteList(Context mAppContext){
        this.mAppContext = mAppContext;
        this.mQuoteArray = populateList();

    }

    public static QuoteList get(Context c){
        if(mQuoteList == null){
            mQuoteList = new QuoteList(c);
        }
        return mQuoteList;
    }

    private ArrayList<Quote> populateList(){
        ArrayList<Quote> mRetList = new ArrayList<>();

        mRetList.add(new Quote(R.string.idea_quote_1,R.string.idea_author_1,R.drawable.ideas_white));
        mRetList.add(new Quote(R.string.idea_quote_2,R.string.idea_author_2,R.drawable.ideas_white));
        mRetList.add(new Quote(R.string.idea_quote_3,R.string.idea_author_3,R.drawable.ideas_white));
        mRetList.add(new Quote(R.string.idea_quote_4,R.string.idea_author_4,R.drawable.ideas_white));
        mRetList.add(new Quote(R.string.idea_quote_5,R.string.idea_author_5,R.drawable.ideas_white));
        mRetList.add(new Quote(R.string.idea_quote_6,R.string.idea_author_6,R.drawable.ideas_white));
        mRetList.add(new Quote(R.string.idea_quote_7,R.string.idea_author_7,R.drawable.ideas_white));
        mRetList.add(new Quote(R.string.idea_quote_8,R.string.idea_author_8,R.drawable.ideas_white));
        mRetList.add(new Quote(R.string.idea_quote_9,R.string.idea_author_9,R.drawable.ideas_white));
        mRetList.add(new Quote(R.string.idea_quote_10,R.string.idea_author_10,R.drawable.ideas_white));
        mRetList.add(new Quote(R.string.motivation_quote_1,R.string.motivation_author_1,R.drawable.motivation_white));
        mRetList.add(new Quote(R.string.motivation_quote_2,R.string.motivation_author_2,R.drawable.motivation_white));
        mRetList.add(new Quote(R.string.motivation_quote_3,R.string.motivation_author_3,R.drawable.motivation_white));
        mRetList.add(new Quote(R.string.motivation_quote_4,R.string.motivation_author_4,R.drawable.motivation_white));
        mRetList.add(new Quote(R.string.motivation_quote_5,R.string.motivation_author_5,R.drawable.motivation_white));
        mRetList.add(new Quote(R.string.motivation_quote_6,R.string.motivation_author_6,R.drawable.motivation_white));
        mRetList.add(new Quote(R.string.motivation_quote_7,R.string.motivation_author_7,R.drawable.motivation_white));
        mRetList.add(new Quote(R.string.motivation_quote_8,R.string.motivation_author_8,R.drawable.motivation_white));
        mRetList.add(new Quote(R.string.motivation_quote_9,R.string.motivation_author_9,R.drawable.motivation_white));
        mRetList.add(new Quote(R.string.motivation_quote_10,R.string.motivation_author_10,R.drawable.motivation_white));
        mRetList.add(new Quote(R.string.grief_quote_1,R.string.grief_author_1,R.drawable.grief_white));
        mRetList.add(new Quote(R.string.grief_quote_2,R.string.grief_author_2,R.drawable.grief_white));
        mRetList.add(new Quote(R.string.grief_quote_3,R.string.grief_author_3,R.drawable.grief_white));
        mRetList.add(new Quote(R.string.grief_quote_4,R.string.grief_author_4,R.drawable.grief_white));
        mRetList.add(new Quote(R.string.grief_quote_5,R.string.grief_author_5,R.drawable.grief_white));
        mRetList.add(new Quote(R.string.grief_quote_6,R.string.grief_author_6,R.drawable.grief_white));
        mRetList.add(new Quote(R.string.grief_quote_7,R.string.grief_author_7,R.drawable.grief_white));
        mRetList.add(new Quote(R.string.grief_quote_8,R.string.grief_author_8,R.drawable.grief_white));
        mRetList.add(new Quote(R.string.grief_quote_9,R.string.grief_author_9,R.drawable.grief_white));
        mRetList.add(new Quote(R.string.grief_quote_10,R.string.grief_author_10,R.drawable.grief_white));
        mRetList.add(new Quote(R.string.health_quote_1,R.string.health_author_1,R.drawable.health_white));
        mRetList.add(new Quote(R.string.health_quote_2,R.string.health_author_2,R.drawable.health_white));
        mRetList.add(new Quote(R.string.health_quote_3,R.string.health_author_3,R.drawable.health_white));
        mRetList.add(new Quote(R.string.health_quote_4,R.string.health_author_4,R.drawable.health_white));
        mRetList.add(new Quote(R.string.health_quote_5,R.string.health_author_5,R.drawable.health_white));
        mRetList.add(new Quote(R.string.health_quote_6,R.string.health_author_6,R.drawable.health_white));
        mRetList.add(new Quote(R.string.health_quote_7,R.string.health_author_7,R.drawable.health_white));
        mRetList.add(new Quote(R.string.health_quote_8,R.string.health_author_8,R.drawable.health_white));
        mRetList.add(new Quote(R.string.health_quote_9,R.string.health_author_9,R.drawable.health_white));
        mRetList.add(new Quote(R.string.health_quote_10,R.string.health_author_10,R.drawable.health_white));
        mRetList.add(new Quote(R.string.wellbeing_quote_1,R.string.wellbeing_author_1,R.drawable.wellbeing_white));
        mRetList.add(new Quote(R.string.wellbeing_quote_2,R.string.wellbeing_author_2,R.drawable.wellbeing_white));
        mRetList.add(new Quote(R.string.wellbeing_quote_3,R.string.wellbeing_author_3,R.drawable.wellbeing_white));
        mRetList.add(new Quote(R.string.wellbeing_quote_4,R.string.wellbeing_author_4,R.drawable.wellbeing_white));
        mRetList.add(new Quote(R.string.wellbeing_quote_5,R.string.wellbeing_author_5,R.drawable.wellbeing_white));
        mRetList.add(new Quote(R.string.wellbeing_quote_6,R.string.wellbeing_author_6,R.drawable.wellbeing_white));
        mRetList.add(new Quote(R.string.wellbeing_quote_7,R.string.wellbeing_author_7,R.drawable.wellbeing_white));
        mRetList.add(new Quote(R.string.wellbeing_quote_8,R.string.wellbeing_author_8,R.drawable.wellbeing_white));
        mRetList.add(new Quote(R.string.wellbeing_quote_9,R.string.wellbeing_author_9,R.drawable.wellbeing_white));
        mRetList.add(new Quote(R.string.wellbeing_quote_10,R.string.wellbeing_author_10,R.drawable.wellbeing_white));
        return mRetList;
    }

    public static ArrayList<Quote> getArray(int stringRes){
        ArrayList<Quote> retList = new ArrayList<>();
        for(Quote q : mQuoteArray){
            if(q.getmCategory().get(0)== stringRes){

                retList.add(q);
            }
        }
        return retList;
    }
}
