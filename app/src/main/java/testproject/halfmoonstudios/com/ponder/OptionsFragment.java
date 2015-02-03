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
 * Functionality for infoButton
 */
public class OptionsFragment extends Fragment {
    private ImageView mMenuView;
    private ImageView mInfoView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_options, parent, false);
    //Assign value to rMenuView and rInfoButton
        mMenuView = (ImageView)view.findViewById(R.id.menuView);
        mInfoView = (ImageView)view.findViewById(R.id.infoView);
        setupMenuView();
        return view;
    }

    public void setupMenuView(){
        /*Sets onClickListener to menuView and calls replaceMenuFragment() from host activity */
        mMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).replaceMenuFragment();
            }


        });


    }
  public void setupInfoButton(){
      //Sets onclicklistener to mInfoview, functionality to be added later
      mInfoView.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){

          }


      });

  }
}

