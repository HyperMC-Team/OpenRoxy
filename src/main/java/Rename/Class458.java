package Rename;

import java.io.IOException;
import Rename.Class446;
import Rename.Class445;

public class Class458 {
    public static boolean loadedImage;

    public static void loadImage() throws IOException {
        if (!loadedImage) {
            for (Class446 element : Class445.INSTANCE.getElements()) {
                element.loadTexture();
                element.loadHoverTexture();
            }
            loadedImage = true;
        }
    }
}

