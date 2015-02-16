package testproject.halfmoonstudios.com.ponder;

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
        //Call to setUpAnimation() method
        setUpAnimation(v);
        //Sets initial value of quoteFragment
        mQuoteView.setText(generateQuote());
        mAuthorView.setText(generateAuthor());
        setFont();


        return v;
    }
    public void setQuoteText(){
        clunkyTextFormat();
        mQuoteView.startAnimation(out);
        mAuthorView.startAnimation(out);

    }

    public void setUpAnimation(View v) {

        in.setDuration(2250);

        //Sets duration of
        out.setDuration(2250);

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

            return 28;

        }else if(text.length() < 90){

            return 26;

        }else if (text.length() < 150){

            return 24;


        }else if (text.length() < 240){

            return 21;

        }
        return 20;
        }

    public void setFont(){
        //Sets font for mQuoteView and mAuthorView

        Typeface mTypeFace = Typeface.createFromAsset(getActivity().getResources().getAssets(),"futuralight.ttf");
        mQuoteView.setTypeface(mTypeFace);

        mTypeFace = Typeface.createFromAsset(getActivity().getResources().getAssets(),"futuralight.ttf");
        mAuthorView.setTypeface(mTypeFace);
    }




}