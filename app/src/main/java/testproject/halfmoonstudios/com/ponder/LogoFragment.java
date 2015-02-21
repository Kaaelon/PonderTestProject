package testproject.halfmoonstudios.com.ponder;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class LogoFragment extends BaseFragment {

    private TextView mPonderText;
    private TextView mInspText;
    private TextView mLogoTop;
    private TextView mLogoBottom;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_logo,parent,false);

        //Assigns member variables
        assignVariables(v);

        //Format body text typeFace
        formatTextTypeFace();


        return v;
    }

    @Override
    public void assignVariables(View v){

        //Assign value to text variables
        mPonderText = (TextView)v.findViewById(R.id.ponderText);
        mInspText = (TextView)v.findViewById(R.id.inspText);
        mLogoTop =(TextView)v.findViewById(R.id.logoTop);
        mLogoBottom = (TextView)v.findViewById(R.id.logoBottom);

    }

    public void formatTextTypeFace(){

        //Set typeface for ponder logo
        Typeface mTypeFace = Typeface.createFromAsset(getActivity().getResources().getAssets(),"sweetlybroken.ttf");
        mPonderText.setTypeface(mTypeFace);
        //Set typeface for body
        mTypeFace = Typeface.createFromAsset(getActivity().getResources().getAssets(),"futuralight.ttf");
        mInspText.setTypeface(mTypeFace);
        //Set typeface for halfmoon logo first top text and then bottom
        mTypeFace = Typeface.createFromAsset(getActivity().getResources().getAssets(),"myriadproregular.otf");
        mLogoTop.setTypeface(mTypeFace);

        mTypeFace = Typeface.createFromAsset(getActivity().getResources().getAssets(),"dosisregular.otf");
        mLogoBottom.setTypeface(mTypeFace);


    }
}
