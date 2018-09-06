package ibrahim.faiyaz.isdl;

import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView temp,description,city,country;
    JSONObject jsonObject;
    String url="https://api.openweathermap.org/data/2.5/weather?q=dhaka,bd&appid=6c963b3199a046f26971bbd3f0cc6de8&units=metric";
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialization
        temp = findViewById(R.id.temp);
        description= findViewById(R.id.description);
        city=findViewById(R.id.city);
        country=findViewById(R.id.country);

        //request
        requestQueue = Volley.newRequestQueue(this);

        //request for jsonobject
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    jsonObject= (JSONObject) response.getJSONArray("weather").get(0);
                    description.setText(jsonObject.getString("description"));
                    temp.setText(response.getJSONObject("main").getString("temp"));
                    city.setText(response.getString("name"));
                    country.setText(response.getJSONObject("sys").getString("country"));

               }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "error!", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
