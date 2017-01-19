package ir.coursio.imageslider;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    final int CHANGE_AFTER = 2;
    final int CHANGING_SPEED = 4;
    private String[] urls = {"http://bit.ly/2icWu7C",
            "http://bit.ly/2jvguyp",
            "http://bit.ly/2ifY2bW",
            "http://bit.ly/2jvgxu5",};


    private ImageSliderAdapter imageSliderAdapter;
    private ViewPagerCustomDuration viewPager;
    private Timer timer;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] permissions = {Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        new PermissionHandler().checkPermission(MainActivity.this, permissions, new PermissionHandler.OnPermissionResponse() {
            @Override
            public void onPermissionGranted() {
                imageSliderAdapter = new ImageSliderAdapter(getSupportFragmentManager(), urls);
                setupViewpager();
            }

            @Override
            public void onPermissionDenied() {
            }
        });

    }


    public void pageSwitcher(int seconds) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000); // delay
    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (page < imageSliderAdapter.getCount())
                        viewPager.setCurrentItem(page++);
                    else {
                        page = 0;
                        viewPager.setCurrentItem(page++);
                    }
                }
            });

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Intent intent = new Intent("PERMISSION_RECEIVER");
        intent.putExtra("requestCode", requestCode);
        intent.putExtra("permissions", permissions);
        intent.putExtra("grantResults", grantResults);
        sendBroadcast(intent);
    }

    private void setupViewpager() {
        viewPager = (ViewPagerCustomDuration) findViewById(R.id.pager);
        viewPager.setScrollDurationFactor(CHANGING_SPEED);
        viewPager.setAdapter(imageSliderAdapter);
        pageSwitcher(CHANGE_AFTER);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                page = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
