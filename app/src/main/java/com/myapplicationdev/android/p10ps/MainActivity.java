package com.myapplicationdev.android.p10ps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    int reqCode = 12345;
    ArrayList<Fragment> al;
    MyFragmentAdapter adapter;
    ViewPager vPager;

    Button btnReadLater;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vPager = findViewById(R.id.viewPager);
        btnReadLater = findViewById(R.id.btnReadLater);

        sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);

        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add(new frag1());
        al.add(new frag2());

        adapter = new MyFragmentAdapter(fm, al);

        vPager.setAdapter(adapter);

        btnReadLater.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MINUTE, 5);

                Intent intent = new Intent(MainActivity.this,
                        NotificationReceiver.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        MainActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);
            }
        });

    /*btnBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (vPager.getCurrentItem() > 0){
          int previousPage = vPager.getCurrentItem() - 1;
          vPager.setCurrentItem(previousPage, true);
        }
      }
    });
    btnNext.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        int max = vPager.getChildCount();
        if (vPager.getCurrentItem() < max-1){
          int nextPage = vPager.getCurrentItem() + 1;
          vPager.setCurrentItem(nextPage, true);
        }
      }
    });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId()){
            case R.id.action_Next:
                int max = vPager.getChildCount();
                if (vPager.getCurrentItem() < max-1){
                    int nextPage = vPager.getCurrentItem() + 1;
                    vPager.setCurrentItem(nextPage, true);
                }

                return true;
            case R.id.action_previous:
                if (vPager.getCurrentItem() > 0){
                    int previousPage = vPager.getCurrentItem() - 1;
                    vPager.setCurrentItem(previousPage, true);
                }
                return true;
            case R.id.action_random:
                Random random = new Random();
                int randomNum = random.nextInt(vPager.getChildCount());

                Log.d("Random", String.valueOf(randomNum));

                vPager.setCurrentItem(randomNum, true);
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("fragmentPage", vPager.getCurrentItem());
        editor.apply();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
        int vpage = sharedPreferences.getInt("fragmentPage", 0);

        vPager.setCurrentItem(vpage, true);
    }
}