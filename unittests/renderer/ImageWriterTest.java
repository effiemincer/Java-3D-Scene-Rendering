package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

public class ImageWriterTest {

    /**
     * Testing ImageWriter
     */
    @Test
    void writeImageTest() {
        int interval = 50;
        Color lines = new Color(0, 0, 225);
        Color bg = new Color(0, 225, 0);
        ImageWriter iw = new ImageWriter("Test image", 800, 500);

        for (int i = 0; i < iw.getNx(); i++) {
            for (int j = 0; j < iw.getNy(); j++) {
                // Background coloring unless its in the interval line
                if (i % interval == 0 || j % interval == 0)
                    iw.writePixel(i, j, lines);
                else
                    iw.writePixel(i, j, bg);
            }
        }
        iw.writeToImage();
    }
}
