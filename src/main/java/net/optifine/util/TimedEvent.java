package net.optifine.util;

import java.util.HashMap;
import java.util.Map;

public class TimedEvent {
    private static Map<String, Long> mapEventTimes = new HashMap<String, Long>();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean isActive(String name, long timeIntervalMs) {
        Map<String, Long> map = mapEventTimes;
        synchronized (map) {
            long j;
            long i = System.currentTimeMillis();
            Long olong = mapEventTimes.get(name);
            if (olong == null) {
                olong =(i);
                mapEventTimes.put(name, olong);
            }
            if (i < (j = olong.longValue()) + timeIntervalMs) {
                return false;
            }
            mapEventTimes.put(name, (i));
            return true;
        }
    }
}

