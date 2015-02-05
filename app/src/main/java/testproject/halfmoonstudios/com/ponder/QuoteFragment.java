package testproject.halfmoonstudios.com.ponder;

import android.app.Fragment;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.util.TypedValue;
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

        setFont();
        mQuoteView.setText(generateQuote());
        clunkyTextFormat();
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
                setFont();
                mQuoteView.setText(generateQuote());
                mQuoteView.setTextSize(clunkyTextFormat());

                //commented out auto increasing size when less text appears for the moment
                //mQuoteView.setTextSize(formatTextSize());

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
        //Populates mQuoteList with quotes that match menu/category choice
        QuoteList.get(((MainActivity) getActivity()));
        Log.v("Main", "" + ((MainActivity) getActivity()).getSelection());
        mQuoteList = QuoteList.getArray(((MainActivity) getActivity()).getSelection());
    }



    public float formatTextSize(){

        //Sets initial text size, higher to test scale down working
        mQuoteView.setTextSize(TypedValue.COMPLEX_UNIT_SP,40);
        //saving textSize as sp to variable, getTextSize() returns dp not sp
        float textSize = 40; //(int)mQuoteView.getTextSize();
        //Creates rectangle object to be used in getTextBounds, which essentially takes a string, the starting index and ending index and returns the smallest
        Rect textBounds = new Rect();
        //I added trueBounds when I started running into trouble and realizing that it was hard to measure the rectangles against eachother, my idea for trubounds is that we create it with the Top,Left and Right of the TextView but the bottom of the textBounds Rect()
        Rect trueBounds;
        //Creates Rect() object that covers the bounds of the mQuoteView, this code works 100% perfectly, essentially passing the preset bounds of the TextView and creating a rectangle from it
        Rect viewBounds= new Rect(mQuoteView.getLeft(),mQuoteView.getTop(),mQuoteView.getRight(),mQuoteView.getBottom());
        //TrueBounds is a rect I thought we could use that takes all dimensions of the quote object minus the bottom which is from the textbounds object
        trueBounds = new Rect(mQuoteView.getLeft(),mQuoteView.getTop(),mQuoteView.getRight(),textBounds.bottom);
        //I added trueBounds when I started running into trouble and realizing that it was hard to measure the rectangles against eachother, my idea for trubounds is that we create it with the Top,Left and Right of the TextView but the bottom of the textBounds Rect()
        Log.v("getX and Y",": " + mQuoteView.getX() + " " + mQuoteView.getY() + " and maxHeight: " + mQuoteView.getMaxHeight());

        //Rect trueBounds;
        //TrueBounds is a rect I thought we could use that takes all dimensions of the quote object minus the bottom which is from the textbounds object
        //trueBounds = new Rect(mQuoteView.getLeft(),mQuoteView.getTop(),mQuoteView.getRight(),textBounds.bottom);

        //Gets the text from the current quote and changes it to a string (originally a charset)
        String text = mQuoteView.getText().toString();
        //TextPaint object instantiated so we can use getTextBounds() method mentioned earlier
        TextPaint paint = new TextPaint();
        //TrueBounds is a rect I thought we could use that takes all dimensions of the quote object minus the bottom which is from the textbounds object
        paint.getTextBounds(text,0,text.length(),textBounds);
        //TrueBounds is a rect I thought we could use that takes all dimensions of the quote object minus the bottom which is from the textbounds object

        float targetSize = paint.getTextSize();

        int textHeight = getTextHeight(text,paint,mQuoteView.getWidth(),targetSize);

        /*while (textHeight > mQuoteView.getHeight() && targetSize > 25) {
            targetSize = Math.max(targetSize - 1, 25);
            textHeight = getTextHeight(text,paint,mQuoteView.getWidth(),targetSize);
        }*/

        while (textHeight > mQuoteView.getHeight() && targetSize > 25) {
            targetSize = Math.max(targetSize - 1, 25);
            textHeight = getTextHeight(text,paint,mQuoteView.getWidth(),targetSize);
        }


        //getTextBounds() returns the smallest fitting Rect() object, is passed the string, the starting index (always 0 in this case) and the end index (string.length() so it covers the whole string) and the rect to be created

        //paint.getTextBounds(text,0,text.length(),textBounds);

        /*while ((viewBounds.bottom > textBounds.bottom) && textSize > 20) {
           textSize--;
          mQuoteView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
            Log.v("new textSize :","" + mQuoteView.getTextSize());
           Log.v("viewBounds bottom",": " + viewBounds.bottom);
           Log.v("textbounds bottom ", ": " + textBounds.bottom);

       }*/



        //The idea for this loop is that while the height of viewBounds is greater than the height of truebounds, the textsize is decreased, the textpaint object has it's textsize set to the new textsize and we recall getTextBounds as to create the smaller rect object
        while((viewBounds.centerY() > trueBounds.centerY()) && textSize > 0){
            textSize--;
            paint.setTextSize(textSize);
            paint.getTextBounds(text,0,text.length(),textBounds);
            trueBounds = new Rect(mQuoteView.getLeft(),mQuoteView.getTop(),mQuoteView.getRight(),textBounds.bottom);
           Log.v("textBottom", "" + textBounds.bottom + "textSize " + textSize + " viewBounds height " + viewBounds.height() + " truebounds height " + trueBounds.height());
       }

        //paint.getTextBounds(text,0,text.length(),textBounds);

//        while ((viewBounds.bottom > textBounds.bottom) && textSize > 20) {
//            textSize--;
//            mQuoteView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
//            Log.v("new textSize :","" + mQuoteView.getTextSize());
//            Log.v("viewBounds bottom",": " + viewBounds.bottom);
//            Log.v("textbounds bottom ", ": " + textBounds.bottom);
//
//        }



//        //The idea for this loop is that while the height of viewBounds is greater than the height of truebounds, the textsize is decreased, the textpaint object has it's textsize set to the new textsize and we recall getTextBounds as to create the smaller rect object
//        while((viewBounds.height() > trueBounds.height()) && textSize > 0){
//            textSize--;
//            paint.setTextSize(textSize);
//            paint.getTextBounds(text,0,text.length(),textBounds);
//            trueBounds = new Rect(mQuoteView.getLeft(),mQuoteView.getTop(),mQuoteView.getRight(),textBounds.bottom);
//            Log.v("textBottom", "" + textBounds.bottom + "textSize " + textSize + " viewBounds height " + viewBounds.height() + " truebounds height " + trueBounds.height());
//        }

        paint.getTextBounds(text,0,text.length(),textBounds);
        //TrueBounds is a rect I thought we could use that takes all dimensions of the quote object minus the bottom which is from the textbounds object
        trueBounds = new Rect(mQuoteView.getLeft(),mQuoteView.getTop(),mQuoteView.getRight(),textBounds.bottom);
        //The idea for this loop is that while the height of viewBounds is greater than the height of truebounds, the textsize is decreased, the textpaint object has it's textsize set to the new textsize and we recall getTextBounds as to create the smaller rect object
        while((viewBounds.height() > trueBounds.height()) && textSize > 0){
            textSize--;
            paint.setTextSize(textSize);
            paint.getTextBounds(text,0,text.length(),textBounds);
            trueBounds = new Rect(mQuoteView.getLeft(),mQuoteView.getTop(),mQuoteView.getRight(),textBounds.bottom);
            Log.v("textBottom", "" + textBounds.bottom + "textSize " + textSize + " viewBounds height " + viewBounds.height() + " truebounds height " + trueBounds.height());
        }

        return textSize;
    }

    //separate function for getTextHeight
   private int getTextHeight(CharSequence source, TextPaint paint, int width, float textSize) {
        paint.setTextSize(textSize);
        StaticLayout layout = new StaticLayout(source, paint, width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
        return layout.getHeight();
    }
    public float clunkyTextFormat(){
        String text = mQuoteView.getText().toString();

        if(text.length() < 30){

            return 28;

        }else if(text.length() < 60){

            return 26;

        }else if(text.length() < 90){

            return 25;

        }else if(text.length() < 120){

            return 23;

        }else if (text.length() < 150){

            return 21;

        }else if (text.length() < 180){

            return 20;

        }else if (text.length() < 210){

            return 18;

        }else if (text.length() < 240){

            return 17;

        }else if (text.length() > 270){

            return 15;

        }
        return 20;
        }

    public void setFont(){
        //Sets font for mQuoteView and mAuthorView

        Typeface mtypeFace = Typeface.createFromAsset(getActivity().getResources().getAssets(),"futuralight.ttf");
        mQuoteView.setTypeface(mtypeFace);

        mtypeFace = Typeface.createFromAsset(getActivity().getResources().getAssets(),"myriadproregular.otf");
        mAuthorView.setTypeface(mtypeFace);
    }
}