package testproject.halfmoonstudios.com.ponder;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuFragment extends Fragment {
    //Declares imageviews
    private ImageView mWellbeingView;
    private ImageView mGriefView;
    private ImageView mHealthView;
    private ImageView mIdeasView;
    private ImageView mMotivationView;
    //Declares textviews
    private TextView mWellbeingText;
    private TextView mGriefText;
    private TextView mHealthText;
    private TextView mIdeasText;
    private TextView mMotivationText;
    private TextView mCenterText;

    //Textview object for animation
    private ImageView curView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, parent, false);
        //Could be a problem moving into seperate method

        //Assigns values to ImageViews
        mWellbeingView = (ImageView) v.findViewById(R.id.wellbeingView);
        mGriefView = (ImageView) v.findViewById(R.id.griefView);
        mHealthView = (ImageView) v.findViewById(R.id.healthView);
        mIdeasView = (ImageView) v.findViewById(R.id.ideasView);
        mMotivationView = (ImageView) v.findViewById(R.id.motivationView);

        //Assigns values to TextViews
        mWellbeingText = (TextView) v.findViewById(R.id.wellbeingText);
        mGriefText = (TextView) v.findViewById(R.id.griefText);
        mHealthText = (TextView) v.findViewById(R.id.healthText);
        mIdeasText = (TextView) v.findViewById(R.id.ideasText);
        mMotivationText = (TextView) v.findViewById(R.id.motivationText);
        mCenterText = (TextView) v.findViewById(R.id.centerText);

        //Create TypeFaceObject and assign font
        Typeface mtypeFace = Typeface.createFromAsset(getActivity().getResources().getAssets(), "futuralight.ttf");

        //Sets text of TextViews
        mWellbeingText.setTypeface(mtypeFace);
        mGriefText.setTypeface(mtypeFace);
        mHealthText.setTypeface(mtypeFace);
        mIdeasText.setTypeface(mtypeFace);
        mMotivationText.setTypeface(mtypeFace);
        mCenterText.setTypeface(mtypeFace);

        //Animate centerText

        animateTextView();
        animateViews();

        //Set onclick listeners for textviews
        setListeners();
        return v;
    }

    public void setListeners() {
        mWellbeingView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Passes menu choice id to menuSelection variable in main activity
                ((MainActivity) getActivity()).setSelection(R.drawable.wellbeing_white);
                //Calls fragmentReplace() method from mainActivity to transition fragments
                ((MainActivity) getActivity()).replaceQuoteFragment();

            }
        });

        mGriefView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Passes menu choice id to menuSelection variable in main activity
                ((MainActivity) getActivity()).setSelection(R.drawable.grief_white);
                //Calls fragmentReplace() method from mainActivity to transition fragments
                ((MainActivity) getActivity()).replaceQuoteFragment();

            }
        });

        mHealthView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Passes menu choice id to menuSelection variable in main activity
                ((MainActivity) getActivity()).setSelection(R.drawable.health_white);
                //Calls fragmentReplace() method from mainActivity to transition fragments
                ((MainActivity) getActivity()).replaceQuoteFragment();
            }
        });

        mIdeasView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Passes menu choice id to menuSelection variable in main activity
                ((MainActivity) getActivity()).setSelection(R.drawable.ideas_white);
                //Calls fragmentReplace() method from mainActivity to transition fragments
                ((MainActivity) getActivity()).replaceQuoteFragment();
            }
        });
        mMotivationView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Passes menu choice id to menuSelection variable in main activity
                ((MainActivity) getActivity()).setSelection(R.drawable.motivation_white);
                //Calls fragmentReplace() method from mainActivity to transition fragments
                ((MainActivity) getActivity()).replaceQuoteFragment();
            }
        });
    }

    public void animateTextView() {

        ValueAnimator centreText = ObjectAnimator.ofFloat(mCenterText, "alpha", 0.0f, 1.0f);
        centreText.setDuration(1000);
        centreText.start();
        ValueAnimator centreSlide = ObjectAnimator.ofFloat(mCenterText,"x",0,100);
        centreSlide.setDuration(1000);
        centreSlide.start();

        //setCharacterDelay(40);
        //animateText(mCenterText.getText());

    }


    //The following code declares a handler object that adds and displays chars one at at a time to the textview
    private Handler mHandler = new Handler();
    int mIndex = 0;
    CharSequence mText;
    long mDelay;


    private Runnable characterAdder = new Runnable() {
        View v = getView();

        @Override
        public void run() {


            mCenterText.setText(mText.subSequence(0, mIndex++));
            if (mIndex <= mText.length()) {
                mHandler.postDelayed(characterAdder, mDelay);

            }
        }
    };

    //animateText, setsUp mHandler through assign required values to mText and mIndex, also flushed mHandler through the use of removeCallBacks
    public void animateText(CharSequence text) {

        mText = text;
        mIndex = 0;

        mCenterText.setText("");
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);
    }

    //Sets delay between each characters appearance
    public void setCharacterDelay(long millis) {
        mDelay = millis;
    }


    public void animateViews() {

        //Using new valueAnimator for fade in effects
        ValueAnimator fadeGrief = ObjectAnimator.ofFloat(mGriefView, "alpha", 0.0f, 1.0f);
        fadeGrief.setDuration(750);
        ValueAnimator fadeGriefTitle = ObjectAnimator.ofFloat(mGriefText, "alpha", 0.0f, 1.0f);
        fadeGriefTitle.setDuration(750);

        ValueAnimator fadeWellbeing = ObjectAnimator.ofFloat(mWellbeingView, "alpha", 0.0f, 1.0f);
        fadeWellbeing.setDuration(750);
        ValueAnimator fadeWellbeingTitle = ObjectAnimator.ofFloat(mWellbeingText, "alpha", 0.0f, 1.0f);
        fadeWellbeingTitle.setDuration(750);

        ValueAnimator fadeHealth = ObjectAnimator.ofFloat(mHealthView, "alpha", 0.0f, 1.0f);
        fadeHealth.setDuration(750);
        ValueAnimator fadeHealthTitle = ObjectAnimator.ofFloat(mHealthText, "alpha", 0.0f, 1.0f);
        fadeHealthTitle.setDuration(750);

        ValueAnimator fadeIdeas = ObjectAnimator.ofFloat(mIdeasView, "alpha", 0.0f, 1.0f);
        fadeIdeas.setDuration(750);
        ValueAnimator fadeIdeasTitle = ObjectAnimator.ofFloat(mIdeasText, "alpha", 0.0f, 1.0f);
        fadeIdeasTitle.setDuration(750);

        ValueAnimator fadeMotivation = ObjectAnimator.ofFloat(mMotivationView, "alpha", 0.0f, 1.0f);
        fadeMotivation.setDuration(750);
        ValueAnimator fadeMotivationTitle = ObjectAnimator.ofFloat(mMotivationText, "alpha", 0.0f, 1.0f);
        fadeMotivationTitle.setDuration(750);

        //Using new AnimatorSet for choreographing the order of fading
        AnimatorSet fadeGroup = new AnimatorSet();
        fadeGroup.play(fadeGrief).after(1500);
        fadeGroup.play(fadeGriefTitle).after(1500);

        fadeGroup.play(fadeWellbeing).after(fadeGrief);
        fadeGroup.play(fadeWellbeingTitle).after(fadeGrief);
        ;

        fadeGroup.play(fadeHealth).after(fadeWellbeing);
        fadeGroup.play(fadeHealthTitle).after(fadeWellbeing);

        fadeGroup.play(fadeMotivation).after(fadeHealth);
        fadeGroup.play(fadeMotivationTitle).after(fadeHealth);

        fadeGroup.play(fadeIdeas).after(fadeMotivation);
        fadeGroup.play(fadeIdeasTitle).after(fadeMotivation);


        fadeGroup.start();


    }
}
