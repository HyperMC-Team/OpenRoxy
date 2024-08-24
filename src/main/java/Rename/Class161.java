package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;

public class Class161 {
    private static final int MAX_RETRY_COUNT = 3;

    public static void download(String fileUrl, File outputFile) {
        try {
            int retryCount = 0;
            boolean success = false;

            while(retryCount < 3 && !success) {
                try {
                    URL url = new URL(fileUrl);
                    HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
                    httpConn.setConnectTimeout(5000);
                    httpConn.setReadTimeout(5000);
                    httpConn.setRequestProperty(
                            "Class57-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"
                    );
                    int responseCode = httpConn.getResponseCode();
                    if (responseCode == 200) {
                        File folder = new File(outputFile.getParent());
                        folder.mkdirs();
                        File newfile = new File(outputFile.getPath());
                        newfile.createNewFile();
                        InputStream inputStream = httpConn.getInputStream();
                        FileOutputStream outputStream = new FileOutputStream(outputFile);
                        byte[] buffer = new byte[4096];

                        int bytesRead;
                        while((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }

                        outputStream.close();
                        inputStream.close();
                        success = true;
                    } else {
                        System.out.println("Download Error：" + responseCode);
                    }

                    httpConn.disconnect();
                } catch (UnknownHostException | SocketException var13) {
                    System.out.println("Connection reset, retrying... (" + ++retryCount + "/3)");
                    if (retryCount >= 3) {
                        throw var13;
                    }

                    Thread.sleep(2000L);
                } catch (IOException var14) {
                    System.out.println("IOException occurred, retrying... (" + ++retryCount + "/3)");
                    if (retryCount >= 3) {
                        throw var14;
                    }

                    Thread.sleep(2000L);
                }
            }
        } catch (Throwable var15) {
            try {
                throw var15;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
