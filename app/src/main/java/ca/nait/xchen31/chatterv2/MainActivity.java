package ca.nait.xchen31.chatterv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "in onCreate() ******");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu_item_start_service:
            {
                Log.d(TAG, "start" );
                startService(new Intent(this, ChatterService.class));
                break;
            }
            case R.id.menu_item_stop_service:
            {
                Log.d(TAG, "stop" );
                stopService(new Intent(this, ChatterService.class));
                break;
            }
        }
        return true;
    }

    @Override
    protected void onPause()
    {
        Log.d(TAG, "in onPause()");
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        Log.d(TAG, "in onResume()");
        super.onResume();
    }

    @Override
    protected void onDestroy()
    {
        Log.d(TAG, "in onDestroy()");
        super.onDestroy();
    }
}
