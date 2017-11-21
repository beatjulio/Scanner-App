package com.example.newpc.qrcode;

/**
 * Created by Julio on 21/11/2017.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class Cerrar extends AppCompatActivity {

    // Creating Volley RequestQueue.
    RequestQueue requestQueue;

    // Create string variable to hold the EditText Value.
    String id,idHolder;

    // Creating Progress dialog.
    ProgressDialog progressDialog;

    // Storing server url into String variable.
    String HttpUrl = "http://portaljulio.com/Scanner/FuncionApp/prueba.php";

    Boolean CheckEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String idHolder = getIntent().getStringExtra("id");

        id = idHolder;
        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(Cerrar.this);


        // Adding click listener to button.
        UserLogin();
    }

    // Creating user login function.
    public void UserLogin() {


        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        // Matching server responce message to our text.
                        if(ServerResponse.equalsIgnoreCase("Data Matched")) {

                            // Finish the current Login activity.
                            finish();

                            // Opening the user profile activity using intent.
                            Intent intent = new Intent(Cerrar.this, ReaderActivity.class);
                            startActivity(intent);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.

                        // Showing error message if something goes wrong.
                        //Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("id", id);

                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(Cerrar.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

    }
}