package testproject.halfmoonstudios.com.ponder;

import android.app.Fragment;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
* Quote Fragment
 * Populates an arrayList of quotes through accessing QuoteList singleton and cycles through quotes
 * TODO
 * generate author
 * onclick listener to change quote
 * */

public class QuoteFragment extends Fragment {
    //Create Views for quote fragment
    private TextView mQuoteView;
    private TextView mAuthorView;
    private ImageView mCategoryView;
    private ArrayList<Quote> mQuoteList = new ArrayList<>();
    private int curNumber = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quote, parent, false);

        //Assign values to views
        mQuoteView = (TextView) v.findViewById(R.id.quote_text);
        mAuthorView = (TextView) v.findViewById(R.id.quote_author);
        mCategoryView = (ImageView) v.findViewById(R.id.categoryView);
        //Call to assign category method
        assignCategory();
        //Call to populateList() method
        populateList();
        //Call to setUpAnimation() method
        setUpAnimation(v);
        //Sets initial value of quoteFragment
        mQuoteView.setText(generateQuote());
        mAuthorView.setText(generateAuthor());


        return v;
    }

    public void assignCategory() {
        /*Assigns category icon in relation to menu item chosen, through casting the main activity
         * we are able to access the member variable  */
        mCategoryView.setImageResource(((MainActivity) getActivity()).getSelection());
    }

    public void setUpAnimation(View v) {

        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(3000);

        final Animation out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(3000);

        mCategoryView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mQuoteView.startAnimation(out);
                mAuthorView.startAnimation(out);
            }
        });

        out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mQuoteView.setText(generateQuote());
                mQuoteView.setTextSize(formatTextSize());

                mAuthorView.setText((generateAuthor()));
                mQuoteView.startAnimation(in);
                mAuthorView.startAnimation(in);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public int generateAuthor() {
        return mQuoteList.get(curNumber).getmAuthor();
    }

    public int generateQuote() {
        int randomNum = 0;
        Random random = new Random();
        while (randomNum == curNumber) {
            randomNum = random.nextInt(mQuoteList.size());
        }
        curNumber = randomNum;
        return mQuoteList.get(randomNum).getmQoute();
    }

    public void populateList() {
        //Populates mQuoeList with quotes that match menu/category choice
        QuoteList.get(((MainActivity) getActivity()));
        Log.v("Main", "" + ((MainActivity) getActivity()).getSelection());
        mQuoteList = QuoteList.getArray(((MainActivity) getActivity()).getSelection());
    }

    public int formatTextSize(){
        mQuoteView.setTextSize(25);
        //Sets initial text size
        int textSize = (int)mQuoteView.getTextSize();
        //Creates rectangle object to be used in getTextBounds, which essentially takes a string, the starting index and ending index and returns the smallest
        Rect textBounds = new Rect();
        //Creates Rect() object that covers the bounds of the mQuoteView, this code works 100% perfectly, essentially passing the preset bounds of the TextView and creating a rectangle from it
        Rect viewBounds= new Rect(mQuoteView.getLeft(),mQuoteView.getTop(),mQuoteView.getRight(),mQuoteView.getBottom());
        //I added trueBounds when I started running into trouble and realizing that it was hard to measure the rectangles against eachother, my idea for trubounds is that we create it with the Top,Left and Right of the TextView but the bottom of the textBounds Rect()
        Rect trueBounds;
        //Gets the text from the current quote and changes it to a string (originally a charset)
        String text = mQuoteView.getText().toString();
        //TextPaint object instantiated so we can use getTextBounds() method mentioned earlier
        TextPaint paint = new TextPaint();
        //getTextBounds() returns the smallest fitting Rect() object, is passed the string, the starting index (always 0 in this case) and the end index (string.length() so it covers the whole string) and the rect to be created
        paint.getTextBounds(text,0,text.length(),textBounds);
        //TrueBounds is a rect I thought we could use that takes all dimensions of the quote object minus the bottom which is from the textbounds object
        trueBounds = new Rect(mQuoteView.getLeft(),mQuoteView.getTop(),mQuoteView.getRight(),textBounds.bottom);
        //The idea for this loop is that while the height of viewBounds is greater than the height of truebounds, the textsize is decreased, the textpaint object has it's textsize set to the new textsize and we recall getTextBounds as to create the smaller rect object 
        while(viewBounds.height() > trueBounds.height()){
            textSize--;
            paint.setTextSize(textSize);
            paint.getTextBounds(text,0,text.length(),textBounds);
            trueBounds = new Rect(mQuoteView.getLeft(),mQuoteView.getTop(),mQuoteView.getRight(),textBounds.bottom);
            Log.v("textBottom", "" + textBounds.bottom);
        }


        return textSize;
    }
}