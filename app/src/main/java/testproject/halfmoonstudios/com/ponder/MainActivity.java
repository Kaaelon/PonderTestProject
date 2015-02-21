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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * MainActivity - default activity containing setup information for the app, plus management of fragments
 *
 * TODO
 * Might be a better way of managing fragments - lots of repeated code
 */

public class MainActivity extends Activity {

    //Countdown timer to be used for the appropriate timing of multiple fragment transitions
    private CountDownTimer cd;
    private FragmentManager fm;
    //String to communicate what selections have been made from the menuFragment to the quote fragment
    private String menuSelection;
    //Boolean to signify whether it is the first time the user has accessed menuFragment previously
    private boolean firstAccess;
    public static final String TAG = MainActivity.class.getSimpleName();
    protected JSONObject mQuotesData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //remove title bar from app
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //flag as fullscreen for layout parameters
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Assign variable base values
        assignVariables();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupLogo();

        if (isNetworkAvailable()) {
            GetQuotesTask getQuotesTask = new GetQuotesTask();
            getQuotesTask.execute();
        }
        else {
            Toast.makeText(this, "Network is unavailable", Toast.LENGTH_LONG).show();
            mQuotesData = null;
        }

    }

    public void assignVariables(){

        //Assigns base value of variables
        fm  = getFragmentManager();

        firstAccess = true;

    }

    private void setupLogo(){
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
        Fragment newFragment = fm.findFragmentById(R.id.fragmentContainer);
        newFragment = new QuoteFragment();

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragmentContainer,newFragment);

        if(curFragment.getClass().getName() == MenuFragment.class.getName()){
            transaction.addToBackStack(null);
        }

        transaction.commit();
    //Calls replace option Fragment to update mCategoryView with quoteFragment
        replaceOptionsFragment();
    }

    public void replaceMenuFragment(){
        //Allows fragment replacements calls from within other fragments (must cast activity object)
        Fragment newFragment = fm.findFragmentById(R.id.fragmentContainer);
        newFragment = new MenuFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.animator.fade_in,R.animator.fade_out);
        transaction.replace(R.id.fragmentContainer,newFragment);
        transaction.commit();}

    public void replaceInfoFragment(){
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

        Fragment curFrag = fm.findFragmentById(R.id.fragmentContainer);

        //If the current fragment is the logoFragment class then actionBar is created after timer
        if(curFrag.getClass().getName() == LogoFragment.class.getName()) {

            cd = new CountDownTimer(4400, 1) {
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

    public void onQuoteSelected(boolean selected) {
        //Method from implemented interface to provide communication between FragmentActionBar and Quote fragment, to trigger quote changes

        if (selected) {

            QuoteFragment quoteFrag = (QuoteFragment) fm.findFragmentById(R.id.fragmentContainer);
            quoteFrag.setQuoteText();


        }
    }

    public void setCategoryClickable(boolean clickable){
        //Allows communication between quoteFragment and FragmentActionBar call actions fragments setCategoryClickable to clickable


        FragmentActionBar actionFragment = (FragmentActionBar)fm.findFragmentById(R.id.optionsContainer);

        if(actionFragment != null){
            actionFragment.setCategoryClickable(clickable);
        }

    }

    public void actionBarItemSelected(String selected) {
        //Method passes value to ActionBars setSelectedItem() method, to highlight current menu Icon
        FragmentActionBar actionFragment = (FragmentActionBar) fm.findFragmentById(R.id.optionsContainer);

        if (actionFragment != null) {
            actionFragment.setSelectedItem(selected);
        }
    }

    public void setCategoryVisibility(float alpha){
    //Method sets alpha visibility of mCategoryView in FragmentActionBar
       FragmentActionBar actionFrag = (FragmentActionBar)fm.findFragmentById(R.id.optionsContainer);
        if(actionFrag != null){
       actionFrag.setCategoryViewVisible(alpha);
    }}

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

    public JSONObject getQuotesData() {
        return mQuotesData;
    }

    private class GetQuotesTask extends AsyncTask<Object, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(Object... params) {

            int responseCode = -1;
            JSONObject jsonResponse = null;
            StringBuilder builder = new StringBuilder();
            HttpClient client = new DefaultHttpClient();
            HttpGet httpget = new HttpGet("http://halfmoonstudios.com.au/ponder/quotes.json");

            try {
                HttpResponse response = client.execute(httpget);
                StatusLine statusLine = response.getStatusLine();
                responseCode = statusLine.getStatusCode();
                Log.v(TAG, "Code: " + responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                    String line;
                    while((line = reader.readLine()) != null){
                        builder.append(line);
                    }
                    jsonResponse = new JSONObject(builder.toString());
                }
                else {
                    Log.i(TAG, "Unsuccessful Http Request Code: " + responseCode);
                }
            } catch (IOException e) {
                Log.e(TAG, "IO Exception caught: ", e);
            } catch (JSONException e) {
                Log.e(TAG, "JSON Exception caught: ", e);
            }
            return jsonResponse;
        }

        @Override
        protected void onPostExecute (JSONObject result) {
            mQuotesData = result;
            Log.v(TAG, "Got data from internet successfully!");
        }
    }
}






