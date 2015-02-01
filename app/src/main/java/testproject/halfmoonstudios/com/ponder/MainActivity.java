package testproject.halfmoonstudios.com.ponder;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

import testproject.halfmoonstudios.com.ponder.R;


public class MainActivity extends ActionBarActivity {

    private CountDownTimer cd;
    private FragmentManager fm = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupLogo();
        setupButton();

    }

    private void setupButton() {
        //get reference to button
        ImageView mPonderView = (ImageView) findViewById(R.id.ponder_img);
       /* mPonderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
                if(fragment == null){
                    fragment = new QuoteFragment();
                    fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
                }
            }
        });*/

    }
    private void setupLogo(){
        FragmentManager fm = getFragmentManager();
        Fragment fragmentLogo = fm.findFragmentById(R.id.fragmentContainer);
        if(fragmentLogo == null){
            fragmentLogo = new LogoFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragmentLogo).commit();

        cd = new CountDownTimer(100000,10000) {
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



}