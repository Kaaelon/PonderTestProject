package testproject.halfmoonstudios.com.ponder;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Fragment holds the menu button and the info button to be displayed on both the menu and quote pages
 *
 * TODO:
 * Onclicklistener for information button
 */
public class OptionsFragment extends Fragment {
    private ImageView rMenuView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_options, parent, false);
    //Assign value to rMenuView
        rMenuView = (ImageView)view.findViewById(R.id.menuView);
        setupMenuView();
        return view;
    }

    public void setupMenuView(){
        /*Sets onClickListener to menuView and calls replaceMenuFragment() from host activity */
        rMenuView.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
                ((MainActivity)getActivity()).replaceMenuFragment();
            }


        });


    }
}

