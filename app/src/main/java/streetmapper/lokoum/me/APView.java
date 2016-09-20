package streetmapper.lokoum.me;

import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lokoum on 9/19/16.
 */
public class APView extends Fragment {


    public static List<AP> values;
    public static ListView ap_list ;
    public static ArrayAdapter<AP> listAdapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.acces_point, container, false);

        ap_list = (ListView) v.findViewById(R.id.ap_list);
        values = new ArrayList<>();
        listAdapter = new MySimpleArrayAdapter(getActivity(),
                R.layout.list_layout, R.id.ssid , values);


        if (MainActivity.wifi.isWifiEnabled() == false) {
            // If wifi disabled then enable it
            Toast.makeText(getActivity(), "wifi is disabled..making it enabled",
                    Toast.LENGTH_LONG).show();

            MainActivity.wifi.setWifiEnabled(true);
        }

        MainActivity.receiverWifi = new WifiReceiver();
        getActivity().registerReceiver(MainActivity.receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        MainActivity.wifi.startScan();
        Toast.makeText(getActivity(), "Starting Scan...",
                Toast.LENGTH_LONG).show();

        ap_list.setAdapter(listAdapter);

        return v;
    }

}