package InternetIssues;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by getbl on 2016/6/2.
 */
public class WebUtilsVolley {
    private static final String TAG = "WebUtilsVolley";
    private RequestQueue mRequestQueue;
    private String mURL;
    private Context mContext;
    private boolean isFinished = false;
    private String outputString="";
    private OutputStream mOutputStream=null;
    StringRequest request;
    static int requestCount = 0;

    public WebUtilsVolley(String url, Context context) {
        mURL = url;
        mContext = context;
    }

    private void initial(@Nullable final TextView textView) {
        mRequestQueue = Volley.newRequestQueue(mContext);

        request = new StringRequest(Request.Method.GET, mURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //outputString = response;
                System.out.println(response);
                if(textView!=null){
                    textView.setText(response);
                }
//                try {
//                    new OutputStreamWriter(mOutputStream).write(response);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                isFinished = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

    }

    public WebUtilsVolley execute(@Nullable TextView textView) {
        initial(textView);

        mRequestQueue.add(request);
        return this;
    }

    public String getOutputString() throws InterruptedException {
        requestCount++;
        if (isFinished) {
            requestCount = 0;
            return outputString;
        } else {
            if (requestCount > 3) {
                Log.i(TAG, "getOutputString: retrying" + requestCount);
                requestCount = 0;
                throw new InterruptedException("timed out");
            }
            Thread.sleep(3000);
            return getOutputString();
        }
    }

    public OutputStream getOutputStream() throws InterruptedException {
        requestCount++;
        if (isFinished) {
            requestCount = 0;
            return mOutputStream;
        } else {
            if (requestCount > 3) {
                requestCount = 0;
                throw new InterruptedException("timed out");
            }
            Thread.sleep(3000);
            return getOutputStream();
        }
    }

}
