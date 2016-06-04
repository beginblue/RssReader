package blue.exercises.rssreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayInputStream;

import InternetIssues.WebUtils;
import XmlAnal.Channel;
import XmlAnal.Item;
import XmlAnal.myXmlUtils;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RequestQueue requestQueue = Volley.newRequestQueue(WelcomeActivity.this);
                    StringRequest request = new StringRequest("https://bbs.sjtu.edu.cn/bbsrss?board=JobInfo", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            ByteArrayInputStream stream = new ByteArrayInputStream(response.getBytes());
                            try {
                                Channel channel = myXmlUtils.parserXml(stream);
                                System.out.println(channel.getItems().size());
                                for(Item item:channel.getItems()){
                                    System.out.println(item.getTitle());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    requestQueue.add(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).run();
    }
}
