package testproject.halfmoonstudios.com.ponder;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
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
 * */

public class QuoteFragment extends Fragment {
    //Create Views for quote fragment
    private TextView mQuoteView;
    private TextView mAuthorView;
    private ImageView mCategoryView;
    private ArrayList<Quote> mQuoteList = new ArrayList<>();
    private int curNumber = 0;
    private boolean mIsCreated = false;
    final Animation out = new AlphaAnimation(1.0f, 0.0f);
    final Animation in = new AlphaAnimation(0.0f, 1.0f);

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
;
        //Call to populateList() method
        populateList();
        //Sets initial value of quoteFragment
        mQuoteView.setText(generateQuote());
        mAuthorView.setText(generateAuthor());
        setFont();


        return v;
    }
    public void setQuoteText(){
        clunkyTextFormat();
        playOutAnimation();

    }

    public String getQuoteText(){
        return mQuoteList.get(curNumber).getQuote();
    }

    public String getQuoteAuthor(){
        return mQuoteList.get(curNumber).getAuthor();
    }

    public void playOutAnimation(){

        //Creates animation object and playset for out animation
        ValueAnimator textOut = ObjectAnimator.ofFloat(mQuoteView, "alpha", 0.0f);
        textOut.setDuration(1500);
        ValueAnimator authorOut = ObjectAnimator.ofFloat(mAuthorView,"alpha",0.0f);
        authorOut.setDuration(1500);

        AnimatorSet outSet = new AnimatorSet();
        outSet.play(textOut);
        outSet.play(authorOut).with(textOut);

        //Creates AnimatorListener to allow for event manipulation, this will be primarily used to set
        //the clickickable of the ActionBars categoryView to false or true to limit the users ability to spam animations
        outSet.addListener( new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //Calls setCategoryClickable and sets it to false to disable the CategoryView on the action bar (stops animation spam)
                Log.v("IN","FALSE");
                ((MainActivity)getActivity()).setCategoryClickable(false);

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setFont();
                mQuoteView.setText(generateQuote());
                mQuoteView.setTextSize(clunkyTextFormat());

                //commented out auto increasing size when less text appears for the moment
                //mQuoteView.setTextSize(formatTextSize());

                mAuthorView.setText((generateAuthor()));
                playInAnimation();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        outSet.start();



    }

    public void playInAnimation(){

        ValueAnimator quoteIn = ObjectAnimator.ofFloat(mQuoteView,"alpha",1.0f);
        quoteIn.setDuration(1500);

        ValueAnimator authorIn = ObjectAnimator.ofFloat(mAuthorView,"alpha",1.0f);
        authorIn.setDuration(1500);

        AnimatorSet inSet = new AnimatorSet();
        inSet.play(quoteIn);
        inSet.play(authorIn).with(quoteIn);

        inSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //Calls setCategoryClickable from mainActivity to set clickable of actionBars mCategory to true
                Log.v("OUT", "ANIM");
                ((MainActivity)getActivity()).setCategoryClickable(true);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        inSet.start();


    }

    public String generateAuthor() {
        return mQuoteList.get(curNumber).getAuthor();
    }

    public String generateQuote() {
        int randomNum = 0;
        Random random = new Random();
        while (randomNum == curNumber) {
            randomNum = random.nextInt(mQuoteList.size());
        }
        curNumber = randomNum;
        return mQuoteList.get(randomNum).getQuote();
    }

    public void populateList() {
        //Populates mQuoteList with quotes that match menu/category choice
        QuoteList.get(getActivity());
        Log.v("Main", "" + ((MainActivity) getActivity()).getSelection());
        mQuoteList = QuoteList.getQuotesByType(((MainActivity) getActivity()).getSelection());
    }




    public float clunkyTextFormat(){
        String text = mQuoteView.getText().toString();

        if(text.length() < 30){

            return 25;

        }else if(text.length() < 90){

            return 24;

        }else if (text.length() < 150){

            return 23;


        }else if (text.length() < 240){

            return 22;

        }
        return 21;
        }

    public void setFont(){
        //Sets font for mQuoteView and mAuthorView

        Typeface mTypeFace = Typeface.createFromAsset(getActivity().getResources().getAssets(),"futuralight.ttf");
        mQuoteView.setTypeface(mTypeFace);

        mTypeFace = Typeface.createFromAsset(getActivity().getResources().getAssets(),"futuralight.ttf");
        mAuthorView.setTypeface(mTypeFace);
    }




}