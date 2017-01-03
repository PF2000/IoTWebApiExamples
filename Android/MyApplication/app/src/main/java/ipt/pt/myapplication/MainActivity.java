package ipt.pt.myapplication;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    TextView tvid;
    TextView tvname;
    TextView tvcountry;
    TextView tvdistrict;
    TextView tvcity;
    TextView tvcounty;
    TextView tvpostCode;
    TextView tvadressDetails;

    EditText edId;
    EditText edName;
    EditText edCountry;
    EditText edDistric;
    EditText edCity;
    EditText edCounty;
    EditText edPostCode;
    EditText edAdressDetails;


    Button btnGet;
    Button btnPost;
    Button btnPATCH;
    Button btnDelte;

    RequestQueue queue;
    String token;
    String baseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvid = (TextView) findViewById(R.id.tvId);
        tvname = (TextView) findViewById(R.id.tvname);
        tvcountry = (TextView) findViewById(R.id.tvCountry);
        tvdistrict = (TextView) findViewById(R.id.tvdistrict);
        tvcity = (TextView) findViewById(R.id.tvCity);
        tvcounty = (TextView) findViewById(R.id.tvCounty);
        tvpostCode = (TextView) findViewById(R.id.tvPostCode);
        tvadressDetails = (TextView) findViewById(R.id.tvadressDetails);

        edId = (EditText) findViewById(R.id.editId);
        edName = (EditText) findViewById(R.id.editname);
        edCountry = (EditText) findViewById(R.id.editCountry);
        edDistric = (EditText) findViewById(R.id.editdistrict);
        edCity = (EditText) findViewById(R.id.editCity);
        edCounty = (EditText) findViewById(R.id.editCounty);
        edPostCode = (EditText) findViewById(R.id.editPostCode);
        edAdressDetails = (EditText) findViewById(R.id.editAdressDetails);

        btnGet = (Button) findViewById(R.id.btnGet);
        btnPost = (Button) findViewById(R.id.btnPost);
        btnPATCH = (Button) findViewById(R.id.btnPatch);
        btnDelte = (Button) findViewById(R.id.btnDelete);

        queue = Volley.newRequestQueue(this);

        baseUrl = "http://192.168.173.1:8000/v1/schools";
        token = "Token token=9ZQrhUSg3rTIPla7cyHGGAtt";

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edId.getText().toString().equals("")) {
                    makeRequest(baseUrl + "/" + edId.getText().toString(),Request.Method.GET,null);
                }else{
                    sendToast(getResources().getString(R.string.schoolIdMissing));
                }
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setDataToSend()!=null){
                    makeRequest(baseUrl,Request.Method.POST,setDataToSend());
                }else{
                    sendToast(getResources().getString(R.string.ErrorParsingDataToSend));
                }
            }
        });

        btnPATCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setDataToSend()!=null){
                    makeRequest(baseUrl+ "/" + edId.getText().toString(),Request.Method.PATCH,setDataToSend());
                }else{
                    sendToast(getResources().getString(R.string.ErrorParsingDataToSend));
                }

            }
        });

        btnDelte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edId.getText().toString().equals("")) {
                    makeRequest(baseUrl + "/" + edId.getText().toString(),Request.Method.DELETE,null);
                }else{
                    sendToast(getResources().getString(R.string.schoolIdMissing));
                }
            }
        });
    }

    private void makeRequest(String url, final int method, JSONObject reqObj){

                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (method, url , reqObj, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                if (response.length()>0) {
                                    if(method == 0 || method == 1 || method == 7) {
                                        if (!setDataFromJson(response)) {
                                            sendToast(getResources().getString(R.string.ErrorParsingResponse));
                                        }
                                    }else{
                                        sendToast("success");
                                    }
                                } else {
                                    sendToast(getResources().getString(R.string.noResponseFromAPI));
                                }
                            }

                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                sendToast(error.toString());

                            }
                        }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        headers.put("Authorization",token);
                        return headers;
                    }
                };
                queue.add(jsObjRequest);
    }

    @Nullable
    private JSONObject setDataToSend(){
        JSONObject  params = new JSONObject();
        try {
            if(!edId.getText().toString().equals("")) {
                params.put("id", edId.getText().toString());
            }
            if(!edName.getText().toString().equals("")) {
                params.put("name", edName.getText().toString());
            }
            if(!edCountry.getText().toString().equals("")) {
                params.put("country", edCountry.getText().toString());
            }
            if(!edDistric.getText().toString().equals("")) {
                params.put("distric", edDistric.getText().toString());
            }
            if(!edCity.getText().toString().equals("")) {
                params.put("city", edCity.getText().toString());
            }
            if(!edCounty.getText().toString().equals("")) {
                params.put("county", edCounty.getText().toString());
            }
            if(!edPostCode.getText().toString().equals("")) {
                params.put("postCode", edPostCode.getText().toString());
            }
            if(!edAdressDetails.getText().toString().equals("")) {
                params.put("addressDetails", edAdressDetails.getText().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return params;
    }

    private boolean setDataFromJson(JSONObject obj){
      try {

          tvid.setText(obj.getString("id"));
          tvname.setText(obj.getString("name"));
          tvcountry.setText(obj.getString("country"));
          tvdistrict.setText(obj.getString("distric"));
          tvcity.setText(obj.getString("city"));
          tvcounty.setText(obj.getString("county"));
          tvpostCode.setText(obj.getString("postCode"));
          tvadressDetails.setText(obj.getString("addressDetails"));
          return true;
      }catch (Exception ex){
          return false;
      }
    }

    private void sendToast(String text){
        Toast.makeText(MainActivity.this,text,Toast.LENGTH_LONG).show();
    }
}
