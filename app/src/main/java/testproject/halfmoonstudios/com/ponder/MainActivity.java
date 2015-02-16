package testproject.halfmoonstudios.com.ponder;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 * MainActivity - default activity containing setup information for the app, plus management of fragments
 *
 * TODO
 * Might be a better way of managing fragments - lots of repeated code
 */

public class MainActivity extends Activity implements FragmentActionBar.onQuoteClickedListener {

    private CountDownTimer cd;
    private FragmentManager fm = getFragmentManager();
    //String to communicate what selections have been made from the menuFragment to the quote fragment
    private String menuSelection;
    //Boolean to signify whether it is the first time the user has accessed menuFragment previously
    private boolean firstAccess = true;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //remove title bar from app
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //flag as fullscreen for layout parameters
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupLogo();

    }

    private void setupLogo(){
        FragmentManager fm = getFragmentManager();
        Fragment fragmentLogo = fm.findFragmentById(R.id.fragmentContainer);
        if(fragmentLogo == null){
            fragmentLogo = new LogoFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragmentLogo).commit();

        cd = new CountDownTimer(2000,10000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            replaceMenuFragment();
            replaceOptionsFragment();


            }

        };
        cd.start();
        }
    }

    public void replaceQuoteFragment(){
        //Allows fragment replacements calls from within other fragments (must cast activity object)
        Fragment curFragment = fm.findFragmentById(R.id.fragmentContainer);
        FragmentManager fm = getFragmentManager();
        Fragment newFragment = fm.findFragmentById(R.id.fragmentContainer);
        newFragment = new QuoteFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.animator.fade_in,R.animator.fade_out);
        transaction.replace(R.id.fragmentContainer,newFragment);

        if(curFragment.getClass().getName() == MenuFragment.class.getName()){
            transaction.addToBackStack(null);
        }

        transaction.commit();

        replaceOptionsFragment();
    }
    public void replaceMenuFragment(){
        //Allows fragment replacements calls from within other fragments (must cast activity object)
        FragmentManager fm = getFragmentManager();
        Fragment newFragment = fm.findFragmentById(R.id.fragmentContainer);
        newFragment = new MenuFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.animator.fade_in,R.animator.fade_out);
        transaction.replace(R.id.fragmentContainer,newFragment);
        transaction.commit();}

    public void replaceInfoFragment(){
        FragmentManager fm = getFragmentManager();
        Fragment newFragment = fm.findFragmentById(R.id.fragmentContainer);
        newFragment = new InfoFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.animator.fade_in,R.animator.fade_out);
        transaction.replace(R.id.fragmentContainer, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void replaceOptionsFragment() {

        //Using countdown timer for OptionsFragment for propper allignment with menu icon animation
        FragmentManager fm = getFragmentManager();
        Fragment curFrag = fm.findFragmentById(R.id.fragmentContainer);


        if(curFrag.getClass().getName() == LogoFragment.class.getName()) {

            CountDownTimer cd = new CountDownTimer(4400, 1) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {

                    FragmentManager fm = getFragmentManager();
                    Fragment newFragment = fm.findFragmentById(R.id.optionsContainer);
                    newFragment = new FragmentActionBar();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.setCustomAnimations(R.animator.slide_up, R.animator.slide_down);
                    transaction.replace(R.id.optionsContainer, newFragment);
                    transaction.commit();
                }
            };
            cd.start();
        }else{

           FragmentActionBar actionFrag =(FragmentActionBar) fm.findFragmentById(R.id.optionsContainer);
           actionFrag.getCategory(true);



        }
    }



   public void setSelection(String selection){
       //Sets menuSelection variables (must reference int id of menu view) this allows for flexible manipulation
       this.menuSelection = selection;
   }

    public String getSelection(){
     //Allows for the retrieval of the current menuSelection
      return this.menuSelection;
    }

    public boolean getFirstAccess(){
        return this.firstAccess;
    }

    public void setFirstAccess(boolean firstAccess){
        this.firstAccess = firstAccess;
    }

    public void onQuoteSelected(boolean selected){
    //Method from implemented interface to provide communication between FragmentActionBar and Quote fragment, to trigger quote changes

    if(selected){

        Log.v("HERE","ERE");
        FragmentManager fm = getFragmentManager();
        QuoteFragment quoteFrag = (QuoteFragment)fm.findFragmentById(R.id.fragmentContainer);
        quoteFrag.setQuoteText();



     }




    }

}