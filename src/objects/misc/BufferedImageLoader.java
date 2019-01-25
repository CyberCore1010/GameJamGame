package objects.misc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BufferedImageLoader {
    private BufferedImage image; //Variable is used to temporarily hold the image

    /**This method simply searches for an image and then returns the image at the file location specified.
     *
     * @param path - takes a string parameter as the file path name to search for the image
     * @return - Returns a buffered Image of the image at the specified path
     */
    public BufferedImage loadImage(String path) {
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}