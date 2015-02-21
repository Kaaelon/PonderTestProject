package testproject.halfmoonstudios.com.ponder;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * InfoFragment - displays information about app/studio
 */
public class InfoFragment extends BaseFragment {
    //Create textView variables
    private TextView infoBody;
    private TextView infoHeading;
    private TextView infoBodyEnd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Call to method to set appropriate item selected in the action bar
        actionBarItemHighlighted();
        //Call to method to set mCategoryViews alpha visibility to 0
        setCategoryVisibilty(0);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info, parent, false);

     //Assign textviews resources
       assignVariables(v);
     //Create string to append to textview
       formatString();
     //Animate textView objects
        animateInfo();

        return v;
    }
    @Override
    public void assignVariables(View v){

        infoBody = (TextView)v.findViewById(R.id.info_body);
        infoHeading = (TextView)v.findViewById(R.id.info_title);
        infoBodyEnd = (TextView)v.findViewById(R.id.info_Body_End);

    }

    public void formatString(){

        //Create string objects to concatenate string in infoBody
        String infoPt1 = getResources().getString(R.string.ponderInfoPt1);
        //Sets "Halfmoon studios to required color"
        String colorStudioName = "<font color='#fb911e'>" +getResources().getString(R.string.halfmoonStudios) + "</font>";
        //Creates final string object for infoPt2
        String infoPt2 = getResources().getString(R.string.ponderInfoPt2);

        //Sets info body to the concatenated string, use of HTML object to allow colorStudioName to be colored using HTML notation
        infoBody.setText(Html.fromHtml(infoPt1 + " " + colorStudioName + " " + infoPt2));

        //Create typeface object for info page
        Typeface mTypeFace = Typeface.createFromAsset(getActivity().getResources().getAssets(),"futuralight.ttf");
        //Set typeFace of infoBody
        infoBody.setTypeface(mTypeFace);
        infoHeading.setTypeface(mTypeFace);
        infoBodyEnd.setTypeface(mTypeFace);
    }

    public void animateInfo(){

        //Set Y co-ordinates of heading for transition
        infoHeading.setY(-1000f);
        infoHeading.setVisibility(View.VISIBLE);



        ValueAnimator mTitleSlide = ObjectAnimator.ofFloat(infoHeading, "y", 350f);
        mTitleSlide.setDuration(1500);
        mTitleSlide.start();

        ValueAnimator  mBodyFade = ObjectAnimator.ofFloat(infoBody,"alpha",0.0f,1.0f);
        mBodyFade.setDuration(1500);
        mBodyFade.setStartDelay(1500);
        mBodyFade.start();

        infoBodyEnd.setY(1800);

        ValueAnimator mBottomSlide = ObjectAnimator.ofFloat(infoBodyEnd,"y",1300f);
        mBottomSlide.setDuration(1500);
        mBottomSlide.setStartDelay(1100);
        mBottomSlide.start();

    }

    public void onResume(){
        super.onResume();
        //Call to main Activity to set appropriate item selected in the action bar
        actionBarItemHighlighted();

    }



}
