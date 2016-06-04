package InternetIssues;

import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Web Utils.
 * Using Retrofit and RxJava
 * Connot fit all kind of URLs.
 * Created by getbl on 2016/6/2.
 */
public class WebUtils {


    private static final String TAG = "WebUtils";
    private String baseURL; //请求的网址
//    private String completeURL;
//    private String suffix;
    Retrofit mRetrofit;
    iUtils mUtils;
    private boolean complete = false; //完成标志位
    private String result; //结果字符串
    private TextView mTextView;


    private interface iUtils {
        @GET("/feed")
        Observable<ResponseBody> getResult();
    }


    public void setTextView(TextView tv){
        mTextView=tv;
    }

    public WebUtils(String baseURL) {
        this.baseURL = baseURL;
    }

    private void initUtils() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mUtils = mRetrofit.create(iUtils.class);
    }

    public void execute() throws Exception {
        initUtils();
        mUtils.getResult()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("completed");
                        System.out.println(result);
                        if(mTextView!=null){
                            mTextView.setText(result);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            throw e;
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                    @Override
                    public void onNext(ResponseBody body) {
                        try {
                            result = body.string();
                            complete = true;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public boolean checkFinish(){
        return complete;
    }

    public String getResult() throws IOException {
        if (complete) return result;
        else throw new IOException("It's not finished ");
    }

    public InputStream getInputstream() {
        ByteArrayInputStream bai = new ByteArrayInputStream(result.getBytes());
        return bai;
    }
}
