package testproject.halfmoonstudios.com.ponder;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Fragment;
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
public class InfoFragment extends Fragment {
    //Create textView variables
    private TextView infoBody;
    private TextView infoHeading;
    //Info body end is used due to the fact that when using the HTML object to change the words in the text you are unable to add line breaks
    private TextView infoBodyEnd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info, parent, false);

     //Assign textviews resources
       infoBody = (TextView)v.findViewById(R.id.info_body);
       infoHeading = (TextView)v.findViewById(R.id.info_title);
       infoBodyEnd = (TextView)v.findViewById(R.id.info_Body_End);
     //Create string to append to textview
       String infoPt1 = getResources().getString(R.string.ponderInfoPt1);
       String colorStudioName = "<font color='#fb911e'>" +getResources().getString(R.string.halfmoonStudios) + "</font>";
       String infoPt2 = getResources().getString(R.string.ponderInfoPt2);
    //Sets text of infoBody
        infoBody.setText(Html.fromHtml(infoPt1 + " " + colorStudioName + " " + infoPt2));
    //Create typeface object for info page
        Typeface mTypeFace = Typeface.createFromAsset(getActivity().getResources().getAssets(),"futuralight.ttf");
    //Set typeFace of infoBody
    infoBody.setTypeface(mTypeFace);
    infoHeading.setTypeface(mTypeFace);
    infoBodyEnd.setTypeface(mTypeFace);


        animateInfo();
        return v;
    }

    public void animateInfo(){

        //Set Y co-ordinates of heading for transition
        infoHeading.setY(-3000f);
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
        mBottomSlide.setStartDelay(1500);
        mBottomSlide.start();

    }

}
