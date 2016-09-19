package streetmapper.lokoum.me;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lokoum on 9/19/16.
 */
public class APView extends Fragment {

    static WifiManager wifi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.acces_point, container, false);

        if (MainActivity.wifi.isWifiEnabled()) {
            Toast.makeText(getActivity(), "WIFI ON",
                    Toast.LENGTH_LONG).show();
        }

        TextView tv = (TextView) v.findViewById(R.id.tvFragSecond);
        tv.setText(getArguments().getString("msg"));

        return v;
    }

    public static APView newInstance(String text) {

        APView f = new APView();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}