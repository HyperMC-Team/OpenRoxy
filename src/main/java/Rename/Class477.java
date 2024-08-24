package Rename;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class Class477 {
    public static String get(String url) {
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)urlObj.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Class57-Agent", "Mozilla/5.0");
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                String line;
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder responseBuilder = new StringBuilder();
                boolean isFirstLine = true;
                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        responseBuilder.append(line);
                        isFirstLine = false;
                        continue;
                    }
                    responseBuilder.append("\n").append(line);
                }
                reader.close();
                return responseBuilder.toString();
            }
            throw new IOException("HTTP request failed with response code: " + responseCode);
        }
        catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }
}

