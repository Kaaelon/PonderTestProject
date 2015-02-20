package testproject.halfmoonstudios.com.ponder;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Singleton object that stores quotes
 * Contains three methods:
 * Get() Checks if the object has been instantiated if not creates QuoteList object
 * getQuotesByType(int stringRes) Returns an ArrayList of all quotes that have a matching category string resource
 * populateList() populates mQuoteList with quotes
 */
public class QuoteList {
    private static QuoteList mQuoteList;
    private static ArrayList<Quote> mQuoteArray = new ArrayList<>();
    private Context mAppContext;


    public static final String TAG = QuoteList.class.getSimpleName();

    private QuoteList(Context mAppContext){
        this.mAppContext = mAppContext;
        this.mQuoteArray = populateList();

    }

    public static QuoteList get(Context c){
        if(mQuoteList == null){
            mQuoteList = new QuoteList(c);
        }
        return mQuoteList;
    }

    //Method that will parse the JSON file and will return a JSONObject
    public JSONObject parseJSONData() {

        JSONObject jsonData = null;
        Resources resources = mAppContext.getResources();
        InputStream resourceReader = resources.openRawResource(R.raw.quotes);
        Writer writer = new StringWriter();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Unhandled exception while trying to parse data from JSON file", e);
        } catch (IOException e) {
            Log.e(TAG, "IO exception while trying to parse data from JSON file", e);
        } finally {
            try {
                resourceReader.close();
            } catch (Exception e) {
                Log.e(TAG, "Unhandled exception while trying to close JSON file", e);
            }
        }

        try {
            jsonData = new JSONObject(writer.toString());
        } catch (JSONException e) {
            Log.e(TAG, "Unhandled exception while trying to create JSONObject from string", e);
        }

        return jsonData;
    }

    private ArrayList<Quote> populateList(){
        ArrayList<Quote> mRetList = new ArrayList<>();
        JSONObject jsonData = null;

        // check if network is available on user's phone and if it is run Async task
        if (isNetworkAvailable()) {
            GetQuotesTask getQuotesTask = new GetQuotesTask();
            getQuotesTask.execute();

            try {
                jsonData = getQuotesTask.get();
                Log.v(TAG, "Data fetched from web");
            } catch (InterruptedException e) {
                Log.e(TAG, "Interrupted when getting JSONObject", e);
            } catch (ExecutionException e) {
                Log.e(TAG, "Something get wrong with getting JSONObject", e);
            }
        }
        else {
            jsonData = parseJSONData();
            Log.v(TAG, "Data fetched from local source");
        }

        try {
            JSONObject jsonQuote;
            String author, text, type;
            JSONArray jsonQuotes = null;
            if (jsonData != null) {
                jsonQuotes = jsonData.getJSONArray("quotes");
                Log.v(TAG, "Number of quotes in array " + jsonQuotes.length());
                for (int i=0; i<jsonQuotes.length(); i++) {
                    jsonQuote = jsonQuotes.getJSONObject(i);
                    text = jsonQuote.getString("text");
                    author = jsonQuote.getString("author");
                    type = jsonQuote.getString("type");
                    mRetList.add(new Quote(text,author,type));
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "Unhandled exception while trying to read from JSONObject", e);
        }

        return mRetList;
    }

    public static ArrayList<Quote> getQuotesByType(String typeChosen){
        ArrayList<Quote> quotesByType = new ArrayList<>();
        // TODO: make it work through all array if there is more than one category
        for(Quote q : mQuoteArray){
            if(q.getCategory().get(0).equals(typeChosen)){
                quotesByType.add(q);
            }
        }
        return quotesByType;
    }

    private boolean isNetworkAvailable() {
        boolean isAvailable = false;

        ConnectivityManager manager = (ConnectivityManager)
                mAppContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }

        return isAvailable;
    }

    //TODO: figure out where exactly should async task be called, here or in MainActivity
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
    }


}
