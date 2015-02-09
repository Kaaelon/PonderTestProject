package testproject.halfmoonstudios.com.ponder;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
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
        View v = inflater.inflate(R.layout.fragment_menu,parent,false);
        //Could be a problem moving into seperate method

        //Assigns values to ImageViews
       mWellbeingView = (ImageView)v.findViewById(R.id.wellbeingView);
       mGriefView = (ImageView)v.findViewById(R.id.griefView);
       mHealthView = (ImageView)v.findViewById(R.id.healthView);
       mIdeasView = (ImageView)v.findViewById(R.id.ideasView);
       mMotivationView = (ImageView)v.findViewById(R.id.motivationView);

        //Assigns values to TextViews
        mWellbeingText = (TextView)v.findViewById(R.id.wellbeingText);
        mGriefText = (TextView)v.findViewById(R.id.griefText);
        mHealthText = (TextView)v.findViewById(R.id.healthText);
        mIdeasText = (TextView)v.findViewById(R.id.ideasText);
        mMotivationText = (TextView)v.findViewById(R.id.motivationText);
        mCenterText = (TextView)v.findViewById(R.id.centerText);

        //Create TypeFaceObject and assign font
        Typeface mtypeFace = Typeface.createFromAsset(getActivity().getResources().getAssets(),"futuralight.ttf");

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

    public void setListeners(){
     mWellbeingView.setOnClickListener(new View.OnClickListener(){

      @Override
      public void onClick(View v){
          //Passes menu choice id to menuSelection variable in main activity
          ((MainActivity)getActivity()).setSelection(R.drawable.wellbeing_white);
          //Calls fragmentReplace() method from mainActivity to transition fragments
          ((MainActivity)getActivity()).replaceQuoteFragment();

            }
        });

    mGriefView.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View v){
            //Passes menu choice id to menuSelection variable in main activity
            ((MainActivity)getActivity()).setSelection(R.drawable.grief_white);
           //Calls fragmentReplace() method from mainActivity to transition fragments
            ((MainActivity)getActivity()).replaceQuoteFragment();

        }
    });

     mHealthView.setOnClickListener(new View.OnClickListener(){

       @Override
       public void onClick(View v){
           //Passes menu choice id to menuSelection variable in main activity
           ((MainActivity)getActivity()).setSelection(R.drawable.health_white);
           //Calls fragmentReplace() method from mainActivity to transition fragments
           ((MainActivity)getActivity()).replaceQuoteFragment();
            }
        });

     mIdeasView.setOnClickListener(new View.OnClickListener(){

       @Override
       public void onClick(View v){
           //Passes menu choice id to menuSelection variable in main activity
           ((MainActivity)getActivity()).setSelection(R.drawable.ideas_white);
           //Calls fragmentReplace() method from mainActivity to transition fragments
           ((MainActivity)getActivity()).replaceQuoteFragment();
            }
        });
     mMotivationView.setOnClickListener(new View.OnClickListener(){

     @Override
     public void onClick(View v){
         //Passes menu choice id to menuSelection variable in main activity
         ((MainActivity)getActivity()).setSelection(R.drawable.motivation_white);
         //Calls fragmentReplace() method from mainActivity to transition fragments
         ((MainActivity)getActivity()).replaceQuoteFragment();
            }
        });
    }

    public void animateTextView(){

       setCharacterDelay(40);
       animateText(mCenterText.getText());

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
            if(mIndex <= mText.length()) {
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


    public void animateViews(){

        CountDownTimer cd;

            final Animation in = new AlphaAnimation(0.0f,1.0f);
            in.setDuration(1000);

            cd = new CountDownTimer(1500,100) {
                @Override
                public void onTick(long millisUntilFinished) {



                }

                @Override
                public void onFinish() {
                    if(viewVisibility() != null){
                    viewVisibility().startAnimation(in);}
                    Log.v("TIMER ON FINISH", "ON FINISH");
                    in.reset();
                    this.start();



                    in.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            Log.v("ANIMATION START","START");
                            if(curView != null){
                            curView.setVisibility(View.VISIBLE);
                            if(!getTitle().isShown()){
                                getTitle().setVisibility(View.VISIBLE);
                                getTitle().startAnimation(in);
                            }}





                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {




                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {


                        }
                    });


                }
            };
                cd.start();

        }



   private ImageView viewVisibility(){

       if(mGriefView.getVisibility() == View.VISIBLE && mWellbeingView.getVisibility() == View.INVISIBLE){
            curView = mWellbeingView;
           return mWellbeingView;}

       if(mGriefView.getVisibility() == View.VISIBLE && mWellbeingView.getVisibility() == View.VISIBLE && mHealthView.getVisibility() == View.INVISIBLE){
           Log.v("WELL","RETURNING HEALTH");
           curView = mHealthView;
           return mHealthView;}

       if (mGriefView.getVisibility() == View.VISIBLE && mHealthView.getVisibility() == View.VISIBLE){
           Log.v("WELL","RETURNING IDEAS");
           curView = mIdeasView;
           return mIdeasView;}

       if (mGriefView.getVisibility() == View.VISIBLE && mIdeasView.getVisibility() == View.VISIBLE){
           Log.v("WELL","RETURNING MOTIVATION");
           curView = mMotivationView;
           return mMotivationView;}

        if (mGriefView.getVisibility() == View.VISIBLE && mMotivationView.getVisibility() == View.VISIBLE){
           Log.v("NULL","JERE");
           return null;
       }
       Log.v("GREIF","JERE");
       curView = mGriefView;
    return mGriefView;
   }

   private TextView getTitle(){
       if(curView == mGriefView){
           return mGriefText;
       }else if(curView == mWellbeingView){
           return mWellbeingText;
       }else if(curView == mHealthView){
           return mHealthText;
       }else if (curView == mIdeasView){
           return mIdeasText;
       }else if (curView == mMotivationView){
           return mMotivationText;
       }
      return null;

   };


}
