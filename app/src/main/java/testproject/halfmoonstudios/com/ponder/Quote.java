package testproject.halfmoonstudios.com.ponder;

import java.util.ArrayList;

/**
 * Quote class
 * Class that contains a quote object, it has three variables
 */
public class Quote {

    private int mQoute;
    private int mAuthor;
    private ArrayList<Integer> mCategory = new ArrayList<>();

    public Quote(int mQoute,int mAuthor,int mCategory) {
        this.mQoute = mQoute;
        this.mAuthor = mAuthor;
        addmCategory(mCategory);
    }

    public int getmQoute() {
        return mQoute;
    }

    public int getmAuthor(){ return mAuthor;}

    public ArrayList<Integer> getmCategory(){
        return mCategory;
    }

    public void addmCategory(int mCategory){
        if(this.mCategory.contains(mCategory) == false){
        this.mCategory.add(mCategory);}
    }

    public void setmQoute(int mQoute) {
        this.mQoute = mQoute;
    }

    public void setmAuthor(int mAuthor){
        this.mAuthor = mAuthor;
    }


}
