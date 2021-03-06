package bangtanrut.songklod.bookgazebo;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by Administrator on 20/8/2560.
 */

public class PostProcess1 extends AsyncTask<String,Void,String> {
    private Context context;

    public PostProcess1(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("Name", strings[0])
                    .add("Pavilion", strings[1])
                    .add("Date", strings[2])
                    .add("Time", strings[3])
                    .add("Timework", strings[4])
                    .add("Bodywere", strings[5])
                    .add("Deadcard", strings[6])
                    .add("Timewashbody", strings[7])
                    .add("Buenbody", strings[8])
                    .add("Movebody", strings[9])
                    .add("Placrecive", strings[10])
                    .add("Carrecive", strings[11])
                    .add("Packagebody", strings[12])
                    .add("Flower", strings[13])
                    .add("Cinamal", strings[14])
                    .add("Thaitum", strings[15])
                    .add("Waterdrink", strings[16])
                    .add("Ice1", strings[17])
                    .add("Ice2", strings[18])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[19]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}//Main Class
