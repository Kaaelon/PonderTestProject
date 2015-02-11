package testproject.halfmoonstudios.com.ponder;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * MainActivity - default activity containing setup information for the app, plus management of fragments
 *
 * TODO
 * Might be a better way of managing fragments - lots of repeated code
 */

public class MainActivity extends Activity {

    private CountDownTimer cd;
    private FragmentManager fm = getFragmentManager();
    private String menuSelection;
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

        // check if network is available on user's phone and if it is run Async task
        if (isNetworkAvailable()) {
            GetQuotesTask getQuotesTask = new GetQuotesTask();
            getQuotesTask.execute();
        }
        else {
            Toast.makeText(this, "Network is unavailable", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNetworkAvailable() {
        boolean isAvailable = false;

        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }

        return isAvailable;
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
                replaceOptionsFragment();


            }

        };
        cd.start();
        }
    }

    public void replaceQuoteFragment(){
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
    public void replaceMenuFragment(){
        //Allows fragment replacements calls from within other fragments (must cast activity object)
        FragmentManager fm = getFragmentManager();
        Fragment newFragment = fm.findFragmentById(R.id.fragmentContainer);
        newFragment = new MenuFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.animator.fade_in,R.animator.fade_out);
        transaction.replace(R.id.fragmentContainer,newFragment);
        transaction.addToBackStack(null);
        transaction.commit();}

    public void replaceInfoFragment(){
        FragmentManager fm = getFragmentManager();
        Fragment newFragment = fm.findFragmentById(R.id.fragmentContainer);
        newFragment = new InfoFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.animator.fade_in,R.animator.fade_out);
        transaction.replace(R.id.fragmentContainer,newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void replaceOptionsFragment() {

        //Using countdown timer for OptionsFragment for propper allignment with menu icon animation
        CountDownTimer cd = new CountDownTimer(4400, 1) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                FragmentManager fm = getFragmentManager();
                Fragment newFragment = fm.findFragmentById(R.id.optionsContainer);
                newFragment = new OptionsFragment();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.setCustomAnimations(R.animator.slide_up, R.animator.slide_down);
                transaction.replace(R.id.optionsContainer, newFragment);
                transaction.commit();
            }
        };
        cd.start();
    }

   public void setSelection(String selection){
       //Sets menuSelection variables (must reference int id of menu view) this allows for flexible manipulation
       this.menuSelection = selection;
   }

    public String getSelection(){
     //Allows for the retrieval of the current menuSelection
      return this.menuSelection;
    }

    private class GetQuotesTask extends AsyncTask<Object, Void, String> {

        @Override
        protected String doInBackground(Object... params) {
            int responseCode = -1;
            JSONObject jsonResponse;
            StringBuilder builder = new StringBuilder();
            HttpClient client = new DefaultHttpClient();
            HttpGet httpget = new HttpGet("http://halfmoonstudios.com.au/ponder/quotes.json");

            try {
                HttpResponse response = client.execute(httpget);
                StatusLine statusLine = response.getStatusLine();
                responseCode = statusLine.getStatusCode();
                Log.v(TAG, "Code: " + responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    Log.v(TAG, "Success!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Code: " + responseCode;
        }
    }

}