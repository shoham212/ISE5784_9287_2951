package renderer;

import org.junit.jupiter.api.Test;
import java.io.File;

/**
 * A test class for generating an initial image with a grid.
 */
public class ImageWriterTest {

    /**
     * Test method to generate an initial image with a grid.
     */
    @Test
    public void testInitialImageWithGrid() {
        // Image dimensions
        int width = 800;
        int height = 500;

        // Background and line colors
        primitives.Color backgroundColor = new primitives.Color(255, 255, 0); // Bright yellow background color
        primitives.Color lineColor = new primitives.Color(255, 0, 0); // Red line color

        // Grid parameters
        int rows = 10;
        int cols = 16;

        // Image name
        String imageName = "test_construction_initial_image";

        // Creating an ImageWriter object
        ImageWriter writer = new ImageWriter(imageName, width, height);

        // Filling the image with the background color
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                writer.writePixel(x, y, backgroundColor);
            }
        }

        // Drawing grid lines
        int rowHeight = height / rows;
        int colWidth = width / cols;

        // Drawing horizontal grid lines
        for (int row = 0; row <= rows; row++) {
            int y = row * rowHeight;
            if (y >= height) continue; // Boundary check
            for (int x = 0; x < width; x++) {
                writer.writePixel(x, y, lineColor);
            }
        }

        // Drawing vertical grid lines
        for (int col = 0; col <= cols; col++) {
            int x = col * colWidth;
            if (x >= width) continue; // Boundary check
            for (int y = 0; y < height; y++) {
                writer.writePixel(x, y, lineColor);
            }
        }

        // Saving the image to a file
        writer.writeToImage();

        // Checking if the file was created
        File file = new File(System.getProperty("user.dir") + "/images/" + imageName + ".png");
        if (!file.exists()) {
            throw new IllegalArgumentException("Image file was not created.");
        }
    }
}
