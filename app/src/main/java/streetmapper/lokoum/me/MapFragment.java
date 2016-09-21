package streetmapper.lokoum.me;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.PathOverlay;
import org.osmdroid.views.overlay.SimpleLocationOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import org.osmdroid.views.util.constants.MapViewConstants;

import java.util.ArrayList;

/**
 * Created by lokoum on 9/19/16.
 */
public class MapFragment extends Fragment implements
        LocationListener, MapViewConstants {

    private MapView map;
    private IMapController mapController;
    private LocationManager mLocMgr;
    public static int mLongtitude = 31987968;
    public static int mLatitude = 34783155;
    public static boolean first = true;
    ArrayList<OverlayItem> mItems;

    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map, container, false);

        textView = (TextView) v.findViewById(R.id.debug);
        //important! set your user agent to prevent getting banned from the osm servers
        org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants.setUserAgentValue(BuildConfig.APPLICATION_ID);

        map = (MapView) v.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        //map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        mapController = map.getController();
        mapController.setZoom(15);
        GeoPoint startPoint = new GeoPoint(44.8548407, -0.5727528);
        mapController.setCenter(startPoint);

        mItems = new ArrayList<OverlayItem>();
        // Put overlay icon a little way from map centre
        mItems.add(new OverlayItem("Here", "SampleDescription", startPoint));

        mLocMgr = MainActivity.mLocMgr;
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        mLocMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);

        return v;
    }

    @Override
    public void onLocationChanged(Location location) {
        mLatitude = (int) (location.getLatitude() * 1E6);
        mLongtitude = (int) (location.getLongitude() * 1E6);
        textView.setText("Lat:" + mLatitude + " long:" + mLongtitude);
        //Toast.makeText(getActivity(),
                //"Location changed. Lat:" + mLatitude + " long:" + mLongtitude,
                //Toast.LENGTH_LONG).show();
        GeoPoint gpt = new GeoPoint(mLatitude, mLongtitude);
        if (first) {
            mapController.setCenter(gpt);
            first = false;
        }
        ArrayList<OverlayItem> overlays = new ArrayList<OverlayItem>();
        int i = 0;
        for (i = 0; i < APGlobal.global_list.size(); i++) {
            GeoPoint point = new GeoPoint(APGlobal.global_list.get(i).getLoc_lat(), APGlobal.global_list.get(i).getLoc_lon());
            OverlayItem tmp = new OverlayItem(APGlobal.global_list.get(i).getSSID(), APGlobal.global_list.get(i).getType(), point);
            tmp.setMarker(getActivity().getResources().getDrawable(R.drawable.wifi));
            overlays.add(tmp);
        }
        overlays.add(new OverlayItem("Here", "This is your position", gpt));
        DefaultResourceProxyImpl resourceProxy = new DefaultResourceProxyImpl(getActivity());
        ItemizedIconOverlay<OverlayItem> myLocationOverlay = new ItemizedIconOverlay<OverlayItem>(overlays, null, resourceProxy);
        map.getOverlays().clear();
        map.getOverlays().add(myLocationOverlay);
        map.invalidate();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
