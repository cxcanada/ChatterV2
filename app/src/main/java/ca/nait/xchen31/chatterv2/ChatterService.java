package ca.nait.xchen31.chatterv2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class ChatterService extends Service
{
    static final String TAG = "ChatterService";
    static final int DELAY = 5000;
    private boolean bRun = false;
    private MyThread myThread = null;


    @Override
    public void onCreate()
    {
        super.onCreate();
        myThread = new MyThread();
        Log.d(TAG, "on Create()");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        bRun = false;
        myThread.interrupt();
        myThread = null;
        Log.d(TAG, "on Destroy()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        bRun = true;
        myThread.start();
        Log.d(TAG, "service started");
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
    private class MyThread extends Thread
    {
        public MyThread()
        {
            super("ChatterV2-Thread");
        }
        public void run()
        {

            BufferedReader in = null;
            while(bRun == true)
            {
                try
                {
                    Log.d(TAG, "one Thread cycle()");

                    HttpClient client = new DefaultHttpClient();
                    HttpGet request = new HttpGet();
                    request.setURI(new URI("http://www.youcode.ca/JSONServlet"));
                    HttpResponse response= client.execute(request);
                    in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    String line = "";

                    while((line = in.readLine()) != null){
                        Log.d(TAG,line);
                    }
                    in.close();

                    Thread.sleep(DELAY);
                }
                catch(InterruptedException e)
                {
                    bRun = false;
                }
                catch (URISyntaxException e){
                    Log.d(TAG, "Invalid URI");
                    bRun = false;
                }
                catch (Exception e){
                    Log.d(TAG, "Java I/O exception");
                    bRun = false;
                }
            }
        }
    }
}
