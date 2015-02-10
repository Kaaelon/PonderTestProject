package testproject.halfmoonstudios.com.ponder;

import java.util.ArrayList;

/**
 * Quote class
 * Class that contains a quote object, it has three variables
 */
public class Quote {

    private String mQuote;
    private String mAuthor;
    private ArrayList<String> mCategory = new ArrayList<>();

    public Quote(String mQuote,String mAuthor,String mCategory) {
        this.mQuote = mQuote;
        this.mAuthor = mAuthor;
        addCategory(mCategory);
    }

    public String getQuote() {
        return mQuote;
    }

    public String getAuthor(){ return mAuthor;}

    public ArrayList<String> getCategory(){
        return mCategory;
    }

    public void addCategory(String mCategory){
        if(!this.mCategory.contains(mCategory)){
        this.mCategory.add(mCategory);}
    }

}
