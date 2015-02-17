package testproject.halfmoonstudios.com.ponder;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class FragmentActionBar extends Fragment {

    private ImageView mCategoryView;
    private ImageView mInfoView;
    private ImageView mProfileView;
    private ImageView mShareView;
    private ImageView mMenuView;
    private onQuoteClickedListener mCallback;


    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_action_bar, parent, false);

        //Call to assign variables
        mCategoryView = (ImageView)v.findViewById(R.id.categoryView);
        mInfoView = (ImageView)v.findViewById(R.id.infoView);
        mProfileView = (ImageView)v.findViewById(R.id.profileView);
        mShareView = (ImageView)v.findViewById(R.id.shareView);
        mMenuView = (ImageView)v.findViewById(R.id.menuView);

        //Call set listeners method to set actionListeners for views
        setListeners();


        return v;}

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void getCategory(boolean onQuote){

        String id = ((MainActivity)getActivity()).getSelection();

        FragmentManager fm = getFragmentManager();
        Fragment curFragment = fm.findFragmentById(R.id.fragmentContainer);

        //Paramater for accessing method at appropriate time
        if(onQuote){
           mCategoryView.setImageResource(getImageID(id));
        }

    }

    public int getImageID (String type) {
        int imageID = -1;
        switch (type) {
            case MenuFragment.WELLBEING:  imageID = R.drawable.wellbeing_white;
                break;
            case MenuFragment.MOTIVATION:  imageID = R.drawable.motivation_white;
                break;
            case MenuFragment.IDEA:  imageID = R.drawable.ideas_white;
                break;
            case MenuFragment.HEALTH:  imageID = R.drawable.health_white;
                break;
            case MenuFragment.GRIEF:  imageID = R.drawable.grief_white;
                break;
        }
        return imageID;
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
                // get the quote shown
                QuoteFragment quoteFrag = (QuoteFragment)fm.findFragmentById(R.id.fragmentContainer);
                String quoteText = quoteFrag.getQuoteText();
                String quoteAuthor = quoteFrag.getQuoteAuthor();
                // share the quote
                sharePost(quoteText, quoteAuthor);
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

    public interface onQuoteClickedListener{

        public void onQuoteSelected(boolean selected);

    }

    public void onAttach(Activity activity){
        super.onAttach(activity);

        //Make sure the container activity has implemented to appropriate interface, assigns mCallBack variable value of assigned interface
        try{
            Log.v("got here","got here");
           mCallback = (onQuoteClickedListener)activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implement onQuoteClickedListener");
        }
    }

    private void sharePost(String sharedQuote, String sharedAuthor) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        // TODO: replace to chosen content to share & extras
        shareIntent.setType("text/plain");
        String textToShare = "\"" + sharedQuote + "\" (" + sharedAuthor + ")";
        shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_chooser_title)));
    }


}
