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
        if (MapFragment.first) {
            return;
        }

            List<ScanResult> res = MainActivity.wifi.getScanResults();
            Iterator<ScanResult> iterator = res.iterator();
            //Toast.makeText(c, "Total APs found: "
                    //+ res.size(), Toast.LENGTH_SHORT).show();

            APView.list_a.clear();
            while (iterator.hasNext()) {
                ScanResult next = iterator.next();
                String type = "";
                boolean wps = false;
                Log.i("CAP", "" + next.capabilities);
                if (next.capabilities.contains("WEP")) {
                    type = "WEP";
                }
                else if (next.capabilities.contains("WPA2")) {
                    type = "WPA2";
                }
                else if (next.capabilities.contains("WPA")) {
                    type = "WPA";
                }
                else {
                    type = "NO-ENCRYPTION";
                }
                if (next.capabilities.contains("WPA")) {
                    wps = true;
                }
                AP tmp = new AP(next.BSSID, next.SSID, next.level, type, wps, MapFragment.mLatitude, MapFragment.mLongtitude);
                if (tmp == null) {
                    Toast.makeText(c, "tmp null "
                            + res.size(), Toast.LENGTH_SHORT).show();
                }
                if (!MyUtils.isInGlobal(tmp)) {
                    APGlobal.global_list.add(tmp);
                }
                else {
                    MyUtils.compare(tmp);
                }

                APView.list_a.add(tmp);
                    //compare with b;
                    //add map ou fix pos
                    //clear B
                    APView.textView.setText("AAAAAA");
                APView.list_a_adapt.notifyDataSetChanged();
                APGlobal.listAdapter.notifyDataSetChanged();

            }

    }

}