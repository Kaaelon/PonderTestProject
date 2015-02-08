package testproject.halfmoonstudios.com.ponder;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
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

    //Variables to be used in the centerText animation, declared at class level scope due to being needed in an inner class
    private String mCharText = "";
    private int mCountIndex = 0;



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
        final Animation in = new AlphaAnimation(1.0f,0.0f);

        for(int i = 0;i < 5;i++){
            cd = new CountDownTimer(300,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                viewVisibility().startAnimation(in);
                }

                @Override
                public void onFinish() {


                }
            };
        }

    }

   private ImageView viewVisibility(){

       if(mGriefView.isShown()){
           return mWellbeingView;
       }else if(mWellbeingView.isShown()){
           return mHealthView;
       }else if (mHealthView.isShown()){
           return mIdeasView;
       }else if (mIdeasView.isShown()){
           return mMotivationView;
       }else if (mMotivationView.isShown()){
           return null;
       }
    return mGriefView;
   }

}
