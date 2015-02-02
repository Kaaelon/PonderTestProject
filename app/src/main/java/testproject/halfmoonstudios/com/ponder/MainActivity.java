package testproject.halfmoonstudios.com.ponder;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity {

    private CountDownTimer cd;
    private FragmentManager fm = getFragmentManager();
    private int menuSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

            FragmentManager fm = getFragmentManager();
                Fragment menuFragment = fm.findFragmentById(R.id.fragmentContainer);
                menuFragment = new MenuFragment();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.setCustomAnimations(R.animator.fade_in,R.animator.fade_out);
                transaction.replace(R.id.fragmentContainer,menuFragment);
                transaction.commit();


            }

        };
        cd.start();
        }
    }

    public void replaceFragment(){
        //Allows fragment replacements calls from within other fragments (must cast activity object)
        FragmentManager fm = getFragmentManager();
        Fragment newFragment = fm.findFragmentById(R.id.fragmentContainer);
        newFragment = new QuoteFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.animator.fade_in,R.animator.fade_out);
        transaction.replace(R.id.fragmentContainer,newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
   public void setSelection(int selection){
       //Sets menuSelection variables (must reference int id of menu view) this allows for flexible manipulation
       this.menuSelection = selection;
   }

  public int getSelection(){
     //Allows for the retrieval of the current menuSelection
      return this.menuSelection;
  }



}