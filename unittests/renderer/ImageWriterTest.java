package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;
import scene.Scene;

public class ImageWriterTest {
    @Test
    void testImage() {
//        Scene s = new Scene("Testing");
//        Background colors and bg color for grid
        Color lines = new Color(100, 100, 100);
        Color bg = new Color(0, 0, 0);
        ImageWriter iw = new ImageWriter("Test image", 500, 800);

        for (int i = 0; i < iw.getNx(); i++) {
            for (int j = 0; j < iw.getNy(); j++) {
                iw.writePixel(i, j, bg);
            }

        }
        for (int i = 0; i < iw.getNx(); i += 50) {
            for (int j = 0; j < iw.getNy(); j++) {
                iw.writePixel(i, j, lines);
            }

        }
        for (int i = 0; i < iw.getNx(); i++) {
            for (int j = 0; j < iw.getNy(); j += 50) {
                iw.writePixel(i, j, lines);
            }

        }
    }
}
