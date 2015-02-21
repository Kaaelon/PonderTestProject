package testproject.halfmoonstudios.com.ponder;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
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
import java.util.ArrayList;

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

    private QuoteList(Context mAppContext, JSONObject quotesData){
        this.mAppContext = mAppContext;
        this.mQuoteArray = populateList(quotesData);
    }

    public static QuoteList get(Context c, JSONObject quotesData){
        if(mQuoteList == null){
            mQuoteList = new QuoteList(c, quotesData); //create list with JSON data if available
        }
        return mQuoteList;
    }

    //Method that will parse the JSON file and will return a JSONObject
    //Used if data wasn't available over internet, using local source then
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

    private ArrayList<Quote> populateList(JSONObject jsonData){
        ArrayList<Quote> mRetList = new ArrayList<>();

        // check if JSON data is available, if not - retrieve data from local source
        if (jsonData == null) {
            jsonData = parseJSONData();
            Log.v(TAG, "Data fetched from local source");
        }
        else {
            Log.v(TAG, "Data fetched from internet");
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


}
