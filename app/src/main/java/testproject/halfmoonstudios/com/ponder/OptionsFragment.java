package testproject.halfmoonstudios.com.ponder;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Fragment holds the menu button and the info button to be displayed on menu, quote and info pages
 *
 * TODO:
 * Fixing error menu button not working
 * Fixing error when on same page as what is selected (ie clicking info button while on info page
 */
public class OptionsFragment extends Fragment {
    private ImageView mMenuView;
    private ImageView mInfoView;
    private String type;
    private onInfoClickedListener mCallback;

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
        setupInfoButton();
        return view;
    }

    public interface onInfoClickedListener{

        public void onInfoSelected(boolean selected);

    }

    public void setupMenuView(){
        /*Sets onClickListener to menuView and calls replaceMenuFragment() from host activity */
        mMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adjust type to check current fragment
                type = "MenuFragment";
                if (!isCurrentFragment(type)) {
                    ((MainActivity) getActivity()).replaceMenuFragment();
                }
            }
        });
    }
  public void setupInfoButton(){
      //Sets onclicklistener for infoButton
      mInfoView.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
              //Callback to interface
              mCallback.onInfoSelected(true);
              //adjust type to check current fragment
              type = "InfoFragment";
              //check if already on InfoFragment
              if (!isCurrentFragment(type)) {
                  ((MainActivity) getActivity()).replaceInfoFragment();
              }
          }
      });
  }

  public boolean isCurrentFragment(String type) {
      boolean isCurrent = false;
      Fragment f = getActivity().getFragmentManager().findFragmentByTag(type);
      if (f instanceof InfoFragment && type.equals("InfoFragment")) {
         isCurrent = true;
      }
      if (f instanceof MenuFragment && type.equals("MenuFragment")) {
          isCurrent = true;
      }
      return isCurrent;
  }

 public void onAttach(Activity activity){
     super.onAttach(activity);

     //Makes sure that the container activity has implemented the callback interface
     try{
         mCallback = (onInfoClickedListener) activity;

     }catch(ClassCastException e){
         throw new ClassCastException(activity.toString()+ " must implement onInfoClickedListener");

     }

 }
}

