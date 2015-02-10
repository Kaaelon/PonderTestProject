package testproject.halfmoonstudios.com.ponder;

import java.util.ArrayList;

/**
 * Quote class
 * Class that contains a quote object, it has three variables
 */
public class Quote {

    private int mQuote;
    private int mAuthor;
    private ArrayList<Integer> mCategory = new ArrayList<>();

    public Quote(int mQuote,int mAuthor,int mCategory) {
        this.mQuote = mQuote;
        this.mAuthor = mAuthor;
        addCategory(mCategory);
    }

    public int getQuote() {
        return mQuote;
    }

    public int getAuthor(){ return mAuthor;}

    public ArrayList<Integer> getCategory(){
        return mCategory;
    }

    public void addCategory(int mCategory){
        if(this.mCategory.contains(mCategory) == false){
        this.mCategory.add(mCategory);}
    }

    public void setQuote(int mQuote) {
        this.mQuote = mQuote;
    }

    public void setAuthor(int mAuthor){
        this.mAuthor = mAuthor;
    }


}
