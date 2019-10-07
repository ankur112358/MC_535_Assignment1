package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_2);

        Intent int1 = getIntent();
        //String[] gestures = int1.getStringArrayExtra("First Gesture:");

        Bundle bundle = getIntent().getExtras();
        String gesture = bundle.getString("gesture");
        //System.out.println(gestures.length);

        Button bt2 = (Button)findViewById(R.id.practice_button);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2 = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(int2);
            }
        });

        Button bt3 = (Button)findViewById(R.id.play_button);

        bt3.setEnabled(false);

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoView vv2 = (VideoView)findViewById(R.id.videoView);
                vv2.start();
            }
        });

        DownloadTask dw1 = new DownloadTask();
        dw1.execute(gesture);

    }


    public class DownloadTask extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            //Toast.makeText(getApplicationContext(), "Starting to execute Background Task", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {

            Map<String, String> gesture_mapping = new HashMap<>();
            gesture_mapping.put("gift", "23/23781.mp4");
            gesture_mapping.put("car", "26/26165.mp4");
            gesture_mapping.put("pay", "14/14618.mp4");
            /*
            Gift - https://www.signingsavvy.com/media/mp4-ld/23/23781.mp4
            Car - https://www.signingsavvy.com/media/mp4-ld/26/26165.mp4
            Pay - https://www.signingsavvy.com/media/mp4-ld/14/14618.mp4
            Pet - https://www.signingsavvy.com/media/mp4-ld/25/25066.mp4
            Sell - https://www.signingsavvy.com/media/mp4-ld/9/9199.mp4
            Explain - https://www.signingsavvy.com/media/mp4-ld/22/22623.mp4 That - https://www.signingsavvy.com/media/mp4-ld/14/14366.mp4 Book - https://www.signingsavvy.com/media/mp4-ld/14/14326.mp4 Now - https://www.signingsavvy.com/media/mp4-ld/7/7774.mp4 Work - https://www.signingsavvy.com/media/mp4-ld/14/14523.mp4 Total - https://www.signingsavvy.com/media/mp4-ld/26/26467.mp4 Trip - https://www.signingsavvy.com/media/mp4-ld/9/9117.mp4 Future - https://www.signingsavvy.com/media/mp4-ld/14/14736.mp4 Good - https://www.signingsavvy.com/media/mp4-ld/21/21534.mp4 Thank you - https://www.signingsavvy.com/media/mp4-ld/21/21533.mp4 Learn - https://www.signingsavvy.com/media/mp4-ld/21/21560.mp4 Agent - https://www.signingsavvy.com/media/mp4-ld/10/10000.mp4 Should - https://www.signingsavvy.com/media/mp4-ld/9/9563.mp4 Like - https://www.signingsavvy.com/media/mp4-ld/6/6394.mp4 Movie - https://www.signingsavvy.com/media/mp4-ld/8/8626.mp4
             */
            File SDCardRoot = Environment.getExternalStorageDirectory(); // location where you want to store
            File directory = new File(SDCardRoot, "/my_folder/"); //create directory to keep your downloaded file
            if (!directory.exists())
            {
                directory.mkdir();
            }
            //publishProgress();
//            Toast.makeText(getApplicationContext(),"In Background Task", Toast.LENGTH_LONG).show();
            String fileName = "Action1" + ".mp4"; //song name that will be stored in your device in case of song
            //String fileName = "myImage" + ".jpeg"; in case of image
            try
            {
                InputStream input = null;
                try{

                    URL url = new URL("https://www.signingsavvy.com/media/mp4-ld/" + gesture_mapping.get(params[0])); // link of the song which you want to download like (http://...)
                    HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setReadTimeout(95 * 1000);
                    urlConnection.setConnectTimeout(95 * 1000);
                    urlConnection.setDoInput(true);
                    urlConnection.setRequestProperty("Accept", "application/json");
                    urlConnection.setRequestProperty("X-Environment", "android");


                    urlConnection.setHostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            /** if it necessarry get url verfication */
                            //return HttpsURLConnection.getDefaultHostnameVerifier().verify("your_domain.com", session);
                            return true;
                        }
                    });
                    urlConnection.setSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());


                    urlConnection.connect();
                    input = urlConnection.getInputStream();
                    //input = url.openStream();
                    File xyz = new File(directory, fileName);
                    OutputStream output = new FileOutputStream(xyz);

                    try {
                        byte[] buffer = new byte[1024];
                        int bytesRead = 0;
                        while ((bytesRead = input.read(buffer, 0, buffer.length)) >= 0)
                        {
                            output.write(buffer, 0, bytesRead);

                        }
                        output.close();
                        //Toast.makeText(getApplicationContext(),"Read Done", Toast.LENGTH_LONG).show();
                    }
                    catch (Exception exception)
                    {


                        //Toast.makeText(getApplicationContext(),"output exception in catch....."+ exception + "", Toast.LENGTH_LONG).show();
                        Log.d("Error", String.valueOf(exception));
                        publishProgress(String.valueOf(exception));
                        output.close();

                    }
                }
                catch (Exception exception)
                {
                    Log.e("MYAPP", "exception", exception);
                    //Toast.makeText(getApplicationContext(), "input exception in catch....."+ exception + "", Toast.LENGTH_LONG).show();
                    publishProgress(String.valueOf(exception));

                }
                finally
                {
                    input.close();
                }
            }
            catch (Exception exception)
            {
                publishProgress(String.valueOf(exception));
            }

            return "true";
        }




        @Override
        protected void onProgressUpdate(String... text) {
            Toast.makeText(getApplicationContext(), "In Background Task" + text[0], Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(String text){
            VideoView vv = (VideoView) findViewById(R.id.videoView);
            vv.setVideoPath(Environment.getExternalStorageDirectory()+"/my_folder/Action1.mp4");
            vv.start();
            Button bt4 = (Button)findViewById(R.id.play_button);
            bt4.setEnabled(true);
        }
    }

}
