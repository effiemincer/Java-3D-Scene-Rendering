package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;
import scene.Scene;

public class ImageWriterTest {

    /**
     * Testing ImageWriter
     */
    @Test
    void writeImageTest() {
//        Scene s = new Scene("Testing");
//        Background colors and bg color for grid
        Color lines = new Color(0, 0, 225);
        Color bg = new Color(0,225, 0);
        ImageWriter iw = new ImageWriter("Test image", 800, 500);

        //background colouring
        for (int i = 0; i < iw.getNx(); i++) {
            for (int j = 0; j < iw.getNy(); j++) {
                iw.writePixel(i, j, bg);
            }
        }
        //vertical line colouring
        for (int i = 0; i < iw.getNx(); i += 50) {
            for (int j = 0; j < iw.getNy(); j++) {
                iw.writePixel(i, j, lines);
            }
        }
        //horizontal line colouring
        for (int i = 0; i < iw.getNx(); i++) {
            for (int j = 0; j < iw.getNy(); j += 50) {
                iw.writePixel(i, j, lines);
            }
        }
        iw.writeToImage();
    }
}
