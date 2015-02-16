package testproject.halfmoonstudios.com.ponder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class FragmentActionBar extends Fragment {

    private ImageView mCategoryView;


    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_action_bar, parent, false);

        //Call to assign variables
        mCategoryView = (ImageView)v.findViewById(R.id.categoryView);




        return v;}

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void getCategory(boolean onQuote){

        String id = ((MainActivity)getActivity()).getSelection();

        FragmentManager fm = getFragmentManager();
        Fragment curFragment = fm.findFragmentById(R.id.fragmentContainer);

        if(onQuote){
            Log.v("HERE", "HERE");
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


}
