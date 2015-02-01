package testproject.halfmoonstudios.com.ponder;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import java.util.Random;


public class QuoteFragment extends Fragment {
    //Create textview
    private TextView mQuoteView;
    private final Quote[] mQuoteList = {
            new Quote(R.string.quote_1),
            new Quote(R.string.quote_2),
            new Quote(R.string.quote_3),
            new Quote(R.string.quote_4)};
    private int curNumber = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quote,parent,false);
        setUpView(v);
        return v;
    }
    public void setUpView(View v){

        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(3000);

        final Animation out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(3000);

        mQuoteView = (TextView)v.findViewById(R.id.quote_text);
        mQuoteView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mQuoteView.startAnimation(out);

            }
        });

        out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mQuoteView.setText(generateQuote());
                mQuoteView.startAnimation(in);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public int generateQuote(){
        int randomNum = 0;
        Random random = new Random();
        while (randomNum == curNumber) {
            randomNum = random.nextInt(mQuoteList.length);
        }
        curNumber = randomNum;
        return mQuoteList[randomNum].getmQoute();

    }

}
