package com.example.ilabafrica.first;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Details extends AppCompatActivity {

    String sno;
    String sno_text,name,status;
    TextView sno_view,nameView,statusView;
    static String URL = "http://172.16.40.128/volley/items.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle bundle = getIntent().getExtras();
        sno = bundle.getString("sno");

        if(sno == "12"){
            sno = "Samsung Tablet";
        }

        sno_view = (TextView) findViewById(R.id.textView2);
        nameView = (TextView) findViewById(R.id.textView);
        statusView = (TextView) findViewById(R.id.textView3);

        findItem();
    }

    public void findItem(){
        //Toast.makeText(Details.this,"qwertyuiop",Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success){
                                Toast.makeText(Details.this,sno,Toast.LENGTH_SHORT).show();
                            } else{
                                Toast.makeText(Details.this,"qwerty",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },null
        ){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("sno", sno);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
