package utils;
import android.app.ProgressDialog;
import app.MainActivity;
import utils.URLS;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.sae1.raisehand.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {
    private String TAG= LoginActivity.class.getSimpleName();
    private Button buttonLogin;
    private TextView msgResponse;
    private ProgressDialog pDialog;
    private String tag_string_req= "string_req";
    private StringRequest strReq;
    EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonLogin= (Button) findViewById(R.id.buttonLogin);
        msgResponse = (TextView) findViewById(R.id.msgResponse);

        pDialog= new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
    }

    private void showProgressDialog() {
        if(!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    private void hideProgressDialog() {
        if(pDialog.isShowing()) {
            pDialog.hide();
        }
    }

    private void userLogin() {
        //first getting the values
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();
        showProgressDialog();
        JsonArrayRequest req = new JsonArrayRequest(URLS.URL_STRING_REQ,
                    new Response.Listener<JSONArray>() { @Override
                    public void onResponse(JSONArray response) { Log.d(TAG, response.toString()); msgResponse.setText(response.toString()); hideProgressDialog();
                    }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) { VolleyLog.d(TAG, "Error: " + error.getMessage()); hideProgressDialog();
                } });
        // Adding request to request queue
        MainActivity.getInstance().addToRequestQueue(req, tag_string_req);
        // Cancelling request
// ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }
        //String urlSuffix= "?username="+username+"&password="+password;
       // String url_final= URLS.URL_STRING_REQ+urlSuffix;
        /*showProgressDialog();
        strReq= new StringRequest(Request.Method.GET, url_final, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        Log.d(TAG, response);
                        if(response=="success")
                            Toast.makeText(MainActivity.getInstance(), "Logged In Successfully", Toast.LENGTH_LONG).show();
                        else if (response=="failed")
                            Toast.makeText(MainActivity.getInstance(), "Logged In Failed", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.getInstance(), "Not Reading Correctly", Toast.LENGTH_LONG).show();
                        hideProgressDialog();

                    }}, new Response.ErrorListener(){
                        @Override
                            public void onErrorResponse(VolleyError error){
                                Log.d(TAG, "unable to read");
                                VolleyLog.d(TAG, "Error: "+ error.getMessage());
                                hideProgressDialog();
                            }
        });




        //validating inputs
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Please enter your username");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        MainActivity.getInstance().addToRequestQueue(strReq, tag_string_req);
    }*/
}