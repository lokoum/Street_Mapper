package streetmapper.lokoum.me;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lokoum on 9/20/16.
 */
public class MySimpleArrayAdapter extends ArrayAdapter<AP> {
    private final Context context;
    private List<AP> values;

    public MySimpleArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    public MySimpleArrayAdapter(Context context, int resource, List<AP> values) {
        super(context, resource, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_layout, parent, false);
        TextView ssid = (TextView) rowView.findViewById(R.id.ssid);
        TextView info = (TextView) rowView.findViewById(R.id.info);
        ssid.setText(values.get(position).getSSID());
        info.setText(values.get(position).getType() + "  Strength: " + values.get(position).getLevel() + " WPS:" + values.get(position).getWPS());
        if (values.get(position).getType().equalsIgnoreCase("wep")) {
            rowView.setBackgroundColor(Color.GREEN);
        }
        else if (values.get(position).getType().equalsIgnoreCase("wpa2")) {
            rowView.setBackgroundColor(Color.RED);
        }
        else {
            rowView.setBackgroundColor(Color.YELLOW);
        }
        return rowView;
    }
}