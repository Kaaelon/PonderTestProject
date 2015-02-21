package testproject.halfmoonstudios.com.ponder;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class FragmentActionBar extends BaseFragment {

    private ImageView mCategoryView;
    private ImageView mInfoView;
    private ImageView mProfileView;
    private ImageView mShareView;
    private ImageView mMenuView;



    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_action_bar, parent, false);

        //Assign variables
        assignVariables(v);

        //Call set listeners method to set actionListeners for views
        setListeners();


        return v;}

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void assignVariables(View v){

        //Call to assign variables
        mCategoryView = (ImageView)v.findViewById(R.id.categoryView);
        mInfoView = (ImageView)v.findViewById(R.id.infoView);
        mProfileView = (ImageView)v.findViewById(R.id.profileView);
        mShareView = (ImageView)v.findViewById(R.id.shareView);
        mMenuView = (ImageView)v.findViewById(R.id.menuView);


    }


    public void getCategory(boolean onQuote){

        String id = ((MainActivity)getActivity()).getSelection();

        //Paramater for accessing method at appropriate time
        if(onQuote){
           mCategoryView.setImageResource(getImageID(id));
        }
    }

    public int getImageID (String type) {
        int imageID = -1;
        switch (type) {
            case MenuFragment.WELLBEING:  imageID = R.drawable.wellbeing_line;
                break;
            case MenuFragment.MOTIVATION:  imageID = R.drawable.motivation_line;
                break;
            case MenuFragment.IDEA:  imageID = R.drawable.ideas_line;
                break;
            case MenuFragment.HEALTH:  imageID = R.drawable.health_line;
                break;
            case MenuFragment.GRIEF:  imageID = R.drawable.grief_line;
                break;
        }
        return imageID;
    }

    public void setCategoryViewVisible(float alpha){
    //Creates ValueAnimator that sets the alpha value of the mCategory view with an animation, pass in 1 for visible 0 for invisible
        ValueAnimator mCategoryFade = ObjectAnimator.ofFloat(mCategoryView,"alpha",alpha);
        mCategoryFade.setDuration(1000);
        mCategoryFade.start();
    }


    public void setListeners(){

        mMenuView.setOnClickListener( new View.OnClickListener(){
            @Override
        public void onClick(View v){
                //adjust type to check current fragment
                FragmentManager fm = getFragmentManager();
                Fragment curFragment = fm.findFragmentById(R.id.fragmentContainer);

                if (curFragment.getClass().getName() != MenuFragment.class.getName()) {
                    ((MainActivity) getActivity()).replaceMenuFragment();
                }else{

                    Toast.makeText(getActivity(), "You're already on the menu screen!", Toast.LENGTH_SHORT).show();

                }

            }
        });

        mInfoView.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
                //Callback to interface
                //mCallback.onInfoSelected(true);
                FragmentManager fm = getFragmentManager();
                Fragment curFragment = fm.findFragmentById(R.id.fragmentContainer);

                if (curFragment.getClass().getName() != InfoFragment.class.getName()) {
                    ((MainActivity) getActivity()).replaceInfoFragment();
                }else{
                    Toast.makeText(getActivity(),"You're already on the info screen!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        mShareView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                FragmentManager fm = getFragmentManager();
                Fragment curFragment = fm.findFragmentById(R.id.fragmentContainer);

                if (curFragment.getClass().getName() != QuoteFragment.class.getName()) {
                    // if it's any other fragment except QuoteFragment
                    Toast.makeText(getActivity(),"Choose a quote you would like to share",Toast.LENGTH_SHORT).show();
                }else{
                    // get the quote shown
                    QuoteFragment quoteFrag = (QuoteFragment)fm.findFragmentById(R.id.fragmentContainer);
                    String quoteText = quoteFrag.getQuoteText();
                    String quoteAuthor = quoteFrag.getQuoteAuthor();
                    // share the quote
                    sharePost(quoteText, quoteAuthor);
                }
            }
        });

        mCategoryView.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){

                FragmentManager fm = getFragmentManager();
                Fragment curFragment = fm.findFragmentById(R.id.fragmentContainer);

                if(curFragment.getClass().getName() == QuoteFragment.class.getName()) {
                    ((MainActivity) getActivity()).onQuoteSelected(true);
                }
            }
        });

    }

    public void setCategoryClickable(boolean clickable){
        //Sets the clickable status of mCategoryView
        mCategoryView.setClickable(clickable);
    }

    private void sharePost(String sharedQuote, String sharedAuthor) {
        List<Intent> targetedShareIntents = new ArrayList<Intent>();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String textToShare = "\"" + sharedQuote + "\" (" + sharedAuthor + ")";
        intent.putExtra(Intent.EXTRA_TEXT, textToShare);

        List<ResolveInfo> resInfo = getActivity().getPackageManager().queryIntentActivities(intent, 0);

        for (ResolveInfo resolveInfo : resInfo) {
            String packageName = resolveInfo.activityInfo.packageName;

            Intent targetedShareIntent = new Intent(android.content.Intent.ACTION_SEND);
            targetedShareIntent.setType("text/plain");
            targetedShareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
            targetedShareIntent.setPackage(packageName);

            if (!packageName.equals("com.facebook.katana") && // Remove Facebook Intent share
                !packageName.equals("com.sec.android.app.FileShareClient") && //wi-fi
                !packageName.equals("com.android.bluetooth")) // bluetooth
            {
                targetedShareIntents.add(targetedShareIntent);
            }
        }

        Intent chooserIntent = Intent.createChooser(
                targetedShareIntents.remove(0), getString(R.string.share_chooser_title));

        chooserIntent.putExtra(
                Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[]{}));

        startActivity(chooserIntent);
    }

    public void setSelectedItem(String selected){

        if(selected == MenuFragment.class.getName()){
            mMenuView.setImageResource(R.drawable.menupressed);
            mInfoView.setImageResource(R.drawable.aboutpage);

        }else if(selected == InfoFragment.class.getName()){
            mInfoView.setImageResource(R.drawable.aboutpressed);
            mMenuView.setImageResource(R.drawable.menuicon);
        }else if(selected == QuoteFragment.class.getName()){
            mInfoView.setImageResource(R.drawable.aboutpage);
            mMenuView.setImageResource(R.drawable.menuicon);
        }


    }


}
