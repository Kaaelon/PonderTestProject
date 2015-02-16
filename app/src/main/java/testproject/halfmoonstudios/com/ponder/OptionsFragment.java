package testproject.halfmoonstudios.com.ponder;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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
                FragmentManager fm = getFragmentManager();
                Fragment curFragment = fm.findFragmentById(R.id.fragmentContainer);

                if (curFragment.getClass().getName() != MenuFragment.class.getName()) {
                    ((MainActivity) getActivity()).replaceMenuFragment();
                }else{

                    Toast.makeText(getActivity(),"You're already on the menu screen!",Toast.LENGTH_SHORT).show();

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
              FragmentManager fm = getFragmentManager();
              Fragment curFragment = fm.findFragmentById(R.id.fragmentContainer);

              if (curFragment.getClass().getName() != InfoFragment.class.getName()) {
                  ((MainActivity) getActivity()).replaceInfoFragment();
              }else{

                  Toast.makeText(getActivity(),"You're already on the info screen!",Toast.LENGTH_SHORT).show();
              }
          }
      });
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

