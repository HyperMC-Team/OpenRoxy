package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Class164 {
    public static String country = "\u5988\u5988";

    public static void getAddressByIP() {
        try {
            URL url = new URL("https://ip.appworlds.cn");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                String line;
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                String jsonResponse = response.toString();
                Pattern pattern = Pattern.compile("\"province\":\"(.*?)\"");
                Matcher matcher = pattern.matcher(jsonResponse);
                if (matcher.find()) {
                    country = matcher.group(1).replace("\u0443\u044e\u0402", "");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

