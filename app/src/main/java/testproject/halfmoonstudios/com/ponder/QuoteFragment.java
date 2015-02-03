package testproject.halfmoonstudios.com.ponder;

import android.app.Fragment;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quote,parent,false);

    //Assign values to views
        mQuoteView = (TextView)v.findViewById(R.id.quote_text);
        mAuthorView = (TextView)v.findViewById(R.id.quote_author);
        mCategoryView = (ImageView)v.findViewById(R.id.categoryView);
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

    public void assignCategory(){
        /*Assigns category icon in relation to menu item chosen, through casting the main activity
         * we are able to access the member variable  */
       mCategoryView.setImageResource(((MainActivity)getActivity()).getSelection());
    }

    public void setUpAnimation(View v){

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
                mAuthorView.setText((generateAuthor()));
                mQuoteView.startAnimation(in);
                mAuthorView.startAnimation(in);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public int generateAuthor(){
        return mQuoteList.get(curNumber).getmAuthor();
    }

    public int generateQuote(){
        int randomNum = 0;
        Random random = new Random();
        while (randomNum == curNumber) {
            randomNum = random.nextInt(mQuoteList.size());
        }
        curNumber = randomNum;
        return mQuoteList.get(randomNum).getmQoute();
    }

  public void populateList(){
      //Populates mQuoeList with quotes that match menu/category choice
      QuoteList.get(((MainActivity)getActivity()));
      Log.v("Main",""+((MainActivity)getActivity()).getSelection());
      mQuoteList = QuoteList.getArray(((MainActivity)getActivity()).getSelection());
     Log.v("HERE","" + mQuoteList.size());
  }

}
