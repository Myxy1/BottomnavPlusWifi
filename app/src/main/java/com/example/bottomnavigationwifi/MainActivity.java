package com.example.bottomnavigationwifi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private WifiManager wifiManager;
    private WifiInfo wifiInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.wifiOn:
                        Toast.makeText(MainActivity.this, "Wifi bekapcsolva", Toast.LENGTH_SHORT).show();
                        wifiManager.setWifiEnabled(true);
                        break;

                    case R.id.wifiOff:
                        Toast.makeText(MainActivity.this, "Wifi kikapcsolva", Toast.LENGTH_SHORT).show();
                        wifiManager.setWifiEnabled(false);
                        break;

                    case R.id.wifiInfo:
                        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                        if (mWifi.isConnected()) {
                            int ip = wifiInfo.getIpAddress();
                            String converted_ip = Formatter.formatIpAddress(ip);
                            Toast.makeText(MainActivity.this, "IP: " + converted_ip, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Nem csatlakoztál wifi hálózatra!", Toast.LENGTH_SHORT).show();
                        }

                }
                return true;
            }
        });
    }

    public void init() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();
    }
}
