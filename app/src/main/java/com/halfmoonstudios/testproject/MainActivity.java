package com.halfmoonstudios.testproject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {


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
        mPonderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
                if(fragment == null){
                    fragment = new QuoteFragment();
                    fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
                }
            }
        });

        //set clickListener for button via anonymous action listener
        //Toast example can remove later (just for interest!)
        Toast.makeText(
                MainActivity.this,
                "Press the button for another message.",
                Toast.LENGTH_SHORT
        ).show();


    }
  private void setupLogo(){
      FragmentManager fm = getFragmentManager();
      Fragment fragmentLogo = fm.findFragmentById(R.id.fragmentContainer);
      if(fragmentLogo == null){
          fragmentLogo = new LogoFragment();
          fm.beginTransaction().add(R.id.fragmentContainer, fragmentLogo).commit();

      }
  }



}
