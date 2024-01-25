package renderer;

import org.junit.jupiter.api.Test;
import scene.Scene;

public class ImageWriterTest {
    @Test
    void testImage() {
//        Scene s = new Scene("Testing");
//        Background colors and bg color for grid
        ImageWriter iw = new ImageWriter("Test image", 500, 800);

            for (int i = 0; i < iw.getNx(); i++) {
                for (int j = 0; j < iw.getNy(); j++) {
                    iw.writePixel(i, j, );
                }

        }
    }
}
