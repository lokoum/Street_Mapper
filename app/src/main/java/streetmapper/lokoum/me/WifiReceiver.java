package streetmapper.lokoum.me;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.util.Log;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

/**
 * Created by lokoum on 9/20/16.
 */
public class WifiReceiver extends BroadcastReceiver {

    public void onReceive(Context c, Intent intent) {

            List<ScanResult> res = MainActivity.wifi.getScanResults();
            Iterator<ScanResult> iterator = res.iterator();
            Toast.makeText(c, "Total APs found: "
                    + res.size(), Toast.LENGTH_SHORT).show();

            while (iterator.hasNext()) {
                ScanResult next = iterator.next();
                String type = "";
                Log.i("CAP", "" + next.capabilities);
                if (next.capabilities.contains("WEP")) {
                    type = "WEP";
                }
                else if (next.capabilities.contains("WPA2")) {
                    type = "WPA2";
                }
                else {
                    type = "WPA";
                }
                APView.values.add(new AP(next.BSSID, next.SSID, next.level, type, true, 0, 0));
                APView.listAdapter.notifyDataSetChanged();
            }

    }

}