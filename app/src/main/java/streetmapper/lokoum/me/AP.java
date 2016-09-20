package streetmapper.lokoum.me;

/**
 * Created by lokoum on 9/20/16.
 */
public class AP {

    private String bssid;
    private String ssid;
    private int level;
    private String type;
    private boolean wps;
    private double loc_lat;
    private double loc_lon;

    public AP(String bssid, String ssid, int level, String type, boolean wps, double loc_lat, double loc_lon) {
        this.bssid = bssid;
        this.ssid = ssid;
        this.level = level;
        this.type = type;
        this.wps = wps;
        this.loc_lat = loc_lat;
        this.loc_lon = loc_lon;
    }

    //SETTERS
    public void setBSSID(String bssid) {
        this.bssid = bssid;
    }

    public void setSSID(String ssid) {
        this.ssid = ssid;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWPS(boolean wps) {
        this.wps = wps;
    }

    public void setLoc_lat(double loc_lat) {
        this.loc_lat = loc_lat;
    }

    public void setLoc_lon(double loc_lon) {
        this.loc_lon = loc_lon;
    }

    //GETTERS
    public String getBSSID() {
        return this.bssid;
    }

    public String getSSID() {
        return this.ssid;
    }

    public int getLevel() {
        return this.level;
    }

    public String getType() {
        return this.type;
    }

    public boolean getWPS() {
        return this.wps;
    }

    public double getLoc_lat() {
        return this.loc_lat;
    }

    public double getLoc_lon() {
        return this.loc_lon;
    }
}
