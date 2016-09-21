package streetmapper.lokoum.me;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lokoum on 9/21/16.
 */
public class APGlobal extends Fragment {

    public static List<AP> global_list;
    public static ListView ap_list;
    public static ArrayAdapter<AP> listAdapter;
    public static TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.global_list, container, false);

        ap_list = (ListView) v.findViewById(R.id.ap_list);
        global_list = new ArrayList<>();
        listAdapter = new MySimpleArrayAdapter(getActivity(),
                R.layout.list_layout, R.id.ssid , global_list);


        textView = (TextView) v.findViewById(R.id.debug);
        textView.setText("in progress");

        ap_list.setAdapter(listAdapter);

        return v;
    }
}
