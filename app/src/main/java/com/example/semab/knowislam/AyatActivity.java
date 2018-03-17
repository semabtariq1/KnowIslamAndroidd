package com.example.semab.knowislam;

/**
 * Created by semab on 14/03/2018.
 */
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class AyatActivity extends ListActivity  {
    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jsonParser = new JSONParser();

    ArrayList<HashMap<String, String>> inboxList;

    // products JSONArray
    JSONArray inbox = null;

    // Inbox JSON url
    //private static final String INBOX_URL = "http://192.168.1.4/knowIslam/getAyat.php";
    private static final String INBOX_URL = "http://tekroutesolutions.com/getAyat.php";

    // ALL JSON node names
    private static final String TAG_MESSAGES = "myarray";
    private static final String TAG_arabic = "ayat";
    private static final String TAG_urdu = "translation";
    private static final String TAG_refernce = "refrence";
    public static String arabicForNextActivity = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ayat_list);

        // Hashmap for ListView
        inboxList = new ArrayList<HashMap<String, String>>();

        // Loading INBOX in Background Thread
        new LoadInbox().execute();
    }

    /**
     * Background Async Task to Load all INBOX messages by making HTTP Request
     * */
    class LoadInbox extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AyatActivity.this);
            pDialog.setMessage("Loading Inbox ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting Inbox JSON
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            // getting JSON string from URL
            JSONObject json = jsonParser.makeHttpRequest(INBOX_URL, "GET",
                    params);

            // Check your log cat for JSON reponse
//            Log.d("Inbox JSON: ", json.toString());

            try {
                inbox = json.getJSONArray(TAG_MESSAGES);

                // looping through All messages
                for (int i = 0; i < inbox.length(); i++) {
                    JSONObject c = inbox.getJSONObject(i);

                    // Storing each json item in variable

                    String arabic = c.getString(TAG_arabic);
                    String urdu = c.getString(TAG_urdu);
                    String refernce = c.getString(TAG_refernce);
                   // String arabicc = "";
                    try {
                         //arabicc = URLDecoder.decode(arabic, "utf-8");
                        //arabicForNextActivity = arabic;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if(arabic.length() > 23){
                        arabic = arabic.substring(0, 22) + "..";
                    }
                    // creating new HashMap
                    HashMap<String, String> map = new HashMap<String, String>();

                    // adding each child node to HashMap key => value

                    map.put(TAG_arabic, arabic);
                    map.put(TAG_urdu, urdu);
                    map.put(TAG_refernce, refernce);

                    // adding HashList to ArrayList
                    inboxList.add(map);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            AyatActivity.this, inboxList,
                            R.layout.ayat_list_item, new String[] { TAG_arabic, TAG_urdu, TAG_refernce },
                            new int[] { R.id.arabic, R.id.urdu});
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show();
    }
}
