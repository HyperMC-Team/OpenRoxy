package Rename;

import java.io.*;
import java.util.Objects;
import org.apache.commons.io.IOUtils;

public class Class462 {
    public static String readFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String readInputStream(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void unpackFile(File file, String name) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(file);
        try {
            IOUtils.copy(Objects.requireNonNull(Class462.class.getClassLoader().getResourceAsStream(name)), fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

