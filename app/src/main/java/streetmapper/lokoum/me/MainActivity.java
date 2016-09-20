package streetmapper.lokoum.me;

import android.Manifest;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public static WifiManager wifi;
    public static WifiReceiver receiverWifi;
    public static LocationManager mLocMgr;
    private MyLocationNewOverlay mLocationOverlay;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        receiverWifi = new WifiReceiver();
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifi.startScan();
        mLocMgr = (LocationManager) getSystemService(LOCATION_SERVICE);


        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION},
                123);




    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 123: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {



                    new Timer().scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            //Toast.makeText(MainActivity.this, "scanning", Toast.LENGTH_SHORT).show();
                            wifi.startScan();

                        }
                    }, 0, 1000); //put here time 1000 milliseconds=1 second

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
                    pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    protected void onPause() {
        unregisterReceiver(receiverWifi);
        super.onPause();
    }

    @Override
    protected void onResume() {
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }
}