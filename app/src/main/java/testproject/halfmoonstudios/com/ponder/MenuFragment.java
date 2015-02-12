package testproject.halfmoonstudios.com.ponder;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuFragment extends Fragment  {
    //Declares imageviews
    private ImageView mWellbeingView;
    private ImageView mGriefView;
    private ImageView mHealthView;
    private ImageView mIdeasView;
    private ImageView mMotivationView;
    private ImageView mInfoView;
    //Declares textviews
    private TextView mWellbeingText;
    private TextView mGriefText;
    private TextView mHealthText;
    private TextView mIdeasText;
    private TextView mMotivationText;
    private TextView mCenterText;
    //Constants for categories
    public static final String WELLBEING = "wellbeing";
    public static final String MOTIVATION = "motivation";
    public static final String HEALTH = "health";
    public static final String GRIEF = "grief";
    public static final String IDEA = "idea";
    //Declare boolean to check if first access
    private boolean firstAccess;

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
        mInfoView = (ImageView)v.findViewById(R.id.infoView);

        //Assigns values to TextViews
        mWellbeingText = (TextView) v.findViewById(R.id.wellbeingText);
        mGriefText = (TextView) v.findViewById(R.id.griefText);
        mHealthText = (TextView) v.findViewById(R.id.healthText);
        mIdeasText = (TextView) v.findViewById(R.id.ideasText);
        mMotivationText = (TextView) v.findViewById(R.id.motivationText);
        mCenterText = (TextView) v.findViewById(R.id.centerText);

        //Create TypeFaceObject and assign font
        Typeface mTypeFace = Typeface.createFromAsset(getActivity().getResources().getAssets(), "futuralight.ttf");

        //Sets text of TextViews
        mWellbeingText.setTypeface(mTypeFace);
        mGriefText.setTypeface(mTypeFace);
        mHealthText.setTypeface(mTypeFace);
        mIdeasText.setTypeface(mTypeFace);
        mMotivationText.setTypeface(mTypeFace);
        mCenterText.setTypeface(mTypeFace);

        //Set value of first access through invoking getFirstAccess() from mainActivity
        firstAccess = ((MainActivity)getActivity()).getFirstAccess();

        //Animate centerText and views, checks if it is users first time accessing menu within this use
        animateTextView();
        animateViewsFirst();



        //Set onclick listeners for textviews
        setListeners();
        return v;
    }

    public void setListeners() {

        mWellbeingView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Passes menu choice id to menuSelection variable in main activity
                ((MainActivity) getActivity()).setSelection(WELLBEING);
                //Animate selection
                animateSelection(mWellbeingView,mWellbeingText);
                //Calls fragmentReplace() method from mainActivity to transition fragments
                CountDownTimer cd = new CountDownTimer(2500,1) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        ((MainActivity) getActivity()).replaceQuoteFragment();
                    }
                };
                cd.start();


            }
        });

        mGriefView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Passes menu choice id to menuSelection variable in main activity
                ((MainActivity) getActivity()).setSelection(GRIEF);
                //Animate selection
                animateSelection(mGriefView,mGriefText);
                //Calls fragmentReplace() method from mainActivity to transition fragments
                CountDownTimer cd = new CountDownTimer(2500,1) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        ((MainActivity) getActivity()).replaceQuoteFragment();
                    }
                };
                cd.start();


            }
        });

        mHealthView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Passes menu choice id to menuSelection variable in main activity
                ((MainActivity) getActivity()).setSelection(HEALTH);
                //Animate selection
                animateSelection(mHealthView,mHealthText);
                //Calls fragmentReplace() method from mainActivity to transition fragments
                CountDownTimer cd = new CountDownTimer(2500,1) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        ((MainActivity) getActivity()).replaceQuoteFragment();
                    }
                };
                cd.start();

            }
        });

        mIdeasView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Passes menu choice id to menuSelection variable in main activity
                ((MainActivity) getActivity()).setSelection(IDEA);
                //Animate selection
                animateSelection(mIdeasView,mIdeasText);
                //Calls fragmentReplace() method from mainActivity to transition fragments
                CountDownTimer cd = new CountDownTimer(2500,1) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        ((MainActivity) getActivity()).replaceQuoteFragment();
                    }
                };
                cd.start();

            }
        });
        mMotivationView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Passes menu choice id to menuSelection variable in main activity
                ((MainActivity) getActivity()).setSelection(MOTIVATION);
                //Animate selection
                animateSelection(mMotivationView,mMotivationText);
                //Calls fragmentReplace() method from mainActivity to transition fragments
                CountDownTimer cd = new CountDownTimer(2500,1) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        ((MainActivity) getActivity()).replaceQuoteFragment();
                    }
                };
                cd.start();

            }
        });
    }

    public void animateTextView() {

        CountDownTimer cd = new CountDownTimer(775,20) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                ValueAnimator centreText = ObjectAnimator.ofFloat(mCenterText, "alpha", 0.0f, 1.0f);
                centreText.setDuration(1000);
                centreText.start();
                ValueAnimator centreSlide = ObjectAnimator.ofFloat(mCenterText,"x",0,100);
                centreSlide.setDuration(1000);
                centreSlide.start();


            }
        };
        cd.start();


    }


    //The following code declares a handler object that adds and displays chars one at at a time to the textview
    private Handler mHandler = new Handler();
    int mIndex = 0;
    CharSequence mText;
    long mDelay;


    private Runnable characterAdder = new Runnable() {

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

    public void animateViewsFirst() {
        //Method animates views on the first time the user enters the menu screen
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

        if(firstAccess){

        fadeGroup.play(fadeGrief).after(1500);
        fadeGroup.play(fadeGriefTitle).after(1500);

        fadeGroup.play(fadeWellbeing).after(fadeGrief);
        fadeGroup.play(fadeWellbeingTitle).after(fadeGrief);

        fadeGroup.play(fadeHealth).after(fadeWellbeing);
        fadeGroup.play(fadeHealthTitle).after(fadeWellbeing);

        fadeGroup.play(fadeMotivation).after(fadeHealth);
        fadeGroup.play(fadeMotivationTitle).after(fadeHealth);

        fadeGroup.play(fadeIdeas).after(fadeMotivation);
        fadeGroup.play(fadeIdeasTitle).after(fadeMotivation);

            //Sets first access to false
        ((MainActivity)getActivity()).setFirstAccess(false);

        }else{

            fadeGroup.play(fadeGrief).after(1500);
            fadeGroup.play(fadeGriefTitle).after(1500);

            fadeGroup.play(fadeWellbeing).after(1500);
            fadeGroup.play(fadeWellbeingTitle).after(1500);

            fadeGroup.play(fadeHealth).after(1500);
            fadeGroup.play(fadeHealthTitle).after(1500);

            fadeGroup.play(fadeMotivation).after(1500);
            fadeGroup.play(fadeMotivationTitle).after(1500);

            fadeGroup.play(fadeIdeas).after(1500);
            fadeGroup.play(fadeIdeasTitle).after(1500);

        }

        fadeGroup.start();



    }

    public void animateSelection(ImageView imageSelect,TextView vText){

        //Populate arraylist with ImageView objects
        ArrayList<ImageView> imageViewList = new ArrayList<>();
        imageViewList.add(mGriefView);
        imageViewList.add(mMotivationView);
        imageViewList.add(mWellbeingView);
        imageViewList.add(mIdeasView);
        imageViewList.add(mHealthView);

        //Populate arrayList with textView objects
        ArrayList<TextView> textViewList = new ArrayList<>();
        textViewList.add(mGriefText);
        textViewList.add(mMotivationText);
        textViewList.add(mWellbeingText);
        textViewList.add(mIdeasText);
        textViewList.add(mHealthText);


        //Remove passed in imageView
        imageViewList.remove(imageSelect);
        textViewList.remove(vText);

        //Create animators from imageViews
        ValueAnimator fade1 = ObjectAnimator.ofFloat(imageViewList.get(0),"alpha",1.0f,0.0f);
        fade1.setDuration(1000);
        ValueAnimator fade2 = ObjectAnimator.ofFloat(imageViewList.get(1),"alpha",1.0f,0.0f);
        fade2.setDuration(1000);
        ValueAnimator fade3 = ObjectAnimator.ofFloat(imageViewList.get(2),"alpha",1.0f,0.0f);
        fade3.setDuration(1000);
        ValueAnimator fade4 = ObjectAnimator.ofFloat(imageViewList.get(3),"alpha",1.0f,0.0f);
        fade4.setDuration(1000);
        //Create animators for textViews
        ValueAnimator textFade1 = ObjectAnimator.ofFloat(textViewList.get(0),"alpha",1.0f,0.0f);
        textFade1.setDuration(1000);
        ValueAnimator textFade2 = ObjectAnimator.ofFloat(textViewList.get(1),"alpha",1.0f,0.0f);
        textFade2.setDuration(1000);
        ValueAnimator textFade3 = ObjectAnimator.ofFloat(textViewList.get(2),"alpha",1.0f,0.0f);
        textFade3.setDuration(1000);
        ValueAnimator textFade4 = ObjectAnimator.ofFloat(textViewList.get(3),"alpha",1.0f,0.0f);
        textFade4.setDuration(1000);
        AnimatorSet fadeGroup = new AnimatorSet();

        fadeGroup.play(fade1).after(100);
        fadeGroup.play(fade2).after(100);
        fadeGroup.play(fade3).after(100);
        fadeGroup.play(fade4).after(100);
        fadeGroup.play(textFade1).after(100);
        fadeGroup.play(textFade2).after(100);
        fadeGroup.play(textFade3).after(100);
        fadeGroup.play(textFade4).after(100);

        fadeGroup.start();

        //Start animation to move view to center, if anyone can think of a better way to dynamically decide which layout is selected feel free to implement
        LinearLayout mSlideLayout = null;

        if(imageSelect == getActivity().findViewById(R.id.griefView)){
           mSlideLayout = (LinearLayout)getActivity().findViewById(R.id.griefLayout);
        }else if (imageSelect == getActivity().findViewById(R.id.wellbeingView)){
           mSlideLayout = (LinearLayout)getActivity().findViewById(R.id.wellbeingLayout);
        }else if (imageSelect == getActivity().findViewById(R.id.healthView)){
           mSlideLayout = (LinearLayout)getActivity().findViewById(R.id.healthLayout);
        }else if (imageSelect == getActivity().findViewById(R.id.ideasView)){
            mSlideLayout = (LinearLayout)getActivity().findViewById(R.id.ideasLayout);
        }else if ( imageSelect == getActivity().findViewById(R.id.motivationView)){
            mSlideLayout = (LinearLayout)getActivity().findViewById(R.id.motivationLayout);
        }

        //Calculates distance to center of screen relative to where the views layout currently is
        int[] location = new int[2];
        mSlideLayout.getLocationOnScreen(location);

        DisplayMetrics display = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(display);
        int mWindowWidth = display.widthPixels/2;
        int mViewLocation = location[0];
        int mDifference = 0;
        mDifference = mWindowWidth - mViewLocation;





        if(mDifference - 73 != 0){
            ValueAnimator slideAcrossView = ObjectAnimator.ofFloat(mSlideLayout,"translationX",mDifference - 73);
            slideAcrossView.setDuration(800);
            slideAcrossView.setStartDelay(1100);
            slideAcrossView.start();}


        //Starts animation for slide down


        ValueAnimator slideImage = ObjectAnimator.ofFloat(imageSelect, "y", 2000f);
        slideImage.setDuration(900);
        ValueAnimator slideText = ObjectAnimator.ofFloat(vText,"y",2000f);
        slideText.setDuration(800);
        ValueAnimator slideCenterText = ObjectAnimator.ofFloat(mCenterText,"y",2000f);
        slideCenterText.setDuration(900);


        AnimatorSet slideSet = new AnimatorSet();
        slideSet.play(slideImage).after(1800);
        slideSet.play(slideText).after(1800);
        slideSet.play(slideCenterText).after(1800);

        slideSet.start();



    }

    public void animateInfo(){


        //Create valueAnimator objects for Imageview objects
        ValueAnimator slideViewGrief = ObjectAnimator.ofFloat(mGriefView,"y",1600f);
        slideViewGrief.setDuration(850);

        ValueAnimator slideViewWellbeing = ObjectAnimator.ofFloat(mWellbeingView, "y", 1600f);
        slideViewWellbeing.setDuration(850);

        ValueAnimator slideViewHealth = ObjectAnimator.ofFloat(mHealthView,"y",1600f);
        slideViewHealth.setDuration(850);

        ValueAnimator slideViewIdeas = ObjectAnimator.ofFloat(mIdeasView,"y",1600f);
        slideViewIdeas.setDuration(850);

        ValueAnimator slideViewMotivation = ObjectAnimator.ofFloat(mMotivationView,"y",1600f);
        slideViewMotivation.setDuration(850);

        //Create valueAnimator objects for textView objects
        ValueAnimator slideTextGrief = ObjectAnimator.ofFloat(mGriefText,"y",1600f);
        slideTextGrief.setDuration(800);

        ValueAnimator slideTextWellbeing = ObjectAnimator.ofFloat(mWellbeingText,"y",1600f);
        slideTextWellbeing.setDuration(800);

        ValueAnimator slideTextHealth = ObjectAnimator.ofFloat(mHealthText,"y",1600f);
        slideTextHealth.setDuration(800);

        ValueAnimator slideTextIdeas = ObjectAnimator.ofFloat(mIdeasText,"y",1600f);
        slideTextIdeas.setDuration(800);

        ValueAnimator slideTextMotivation = ObjectAnimator.ofFloat(mMotivationText,"y",1600f);
        slideTextMotivation.setDuration(800);

        ValueAnimator slideTextCenter = ObjectAnimator.ofFloat(mCenterText,"y",1600f);
        slideTextCenter.setDuration(825);


        //Create animation set for sliding animation

        AnimatorSet slideSet = new AnimatorSet();

        //Set animation playtimes

        slideSet.play(slideViewGrief).after(100);
        slideSet.play(slideViewHealth).after(100);
        slideSet.play(slideViewIdeas).after(100);
        slideSet.play(slideViewWellbeing).after(100);
        slideSet.play(slideViewMotivation).after(100);

        slideSet.play(slideTextGrief).after(100);
        slideSet.play(slideTextHealth).after(100);
        slideSet.play(slideTextIdeas).after(100);
        slideSet.play(slideTextMotivation).after(100);
        slideSet.play(slideTextWellbeing).after(100);

        slideSet.play(slideTextCenter).after(100);

        slideSet.start();
    }


}
