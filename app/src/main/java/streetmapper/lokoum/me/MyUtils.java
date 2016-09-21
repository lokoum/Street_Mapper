package streetmapper.lokoum.me;

import android.widget.Toast;

import java.util.List;

/**
 * Created by lokoum on 9/21/16.
 */
public class MyUtils {

    public static void compare(AP item) {

        int i = 0;
        for (i = 0; i < APGlobal.global_list.size(); i++) {
            if (APGlobal.global_list.get(i).getBSSID().equals(item.getBSSID())) {
                if (item.getLevel() > APGlobal.global_list.get(i).getLevel()) {

                    APGlobal.global_list.get(i).setLevel(item.getLevel());
                    APGlobal.global_list.get(i).setLoc_lat(item.getLoc_lat());
                    APGlobal.global_list.get(i).setLoc_lon(item.getLoc_lon());
                }
            }
        }
    }

    public static boolean isInGlobal(AP item) {
        int i = 0;
        for (i = 0; i < APGlobal.global_list.size(); i++) {
            if (APGlobal.global_list.get(i).getBSSID().equals(item.getBSSID())) {
                return true;
            }
        }
        return false;
    }
}
