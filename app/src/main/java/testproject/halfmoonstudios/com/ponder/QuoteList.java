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
    private ArrayList<Quote> mQuoteArray = new ArrayList<>();
    private Context mAppContext;

    private QuoteList(Context mAppContext){
        this.mAppContext = mAppContext;
        this.mQuoteArray = populateList();

    }

    public static QuoteList get(Context c){
        if(mQuoteList == null){
            mQuoteList = new QuoteList(c.getApplicationContext());
        }
        return mQuoteList;
    }

    private ArrayList<Quote> populateList(){
        ArrayList<Quote> mRetList = new ArrayList<>();

        mRetList.add(new Quote(R.string.quote_1,R.string.author_1,R.id.motivationText));
        mRetList.add(new Quote(R.string.quote_2,R.string.author_2,R.id.ideasText));
        mRetList.add(new Quote(R.string.quote_3,R.string.author_3,R.id.wellbeingText));
        mRetList.add(new Quote(R.string.quote_4,R.string.author_4,R.id.motivationText));
        return mRetList;
    }

    public ArrayList<Quote> getArray(int stringRes){
        ArrayList<Quote> retList = new ArrayList<>();
        for(Quote q : mQuoteArray){
            if(q.getmCategory().equals(stringRes)){
                retList.add(q);
            }
        }
        return retList;
    }
}
