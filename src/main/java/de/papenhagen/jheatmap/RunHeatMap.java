package de.papenhagen.jheatmap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * RUN THE DEMO
 */
public class RunHeatMap {

    public static void main(final String[] args) {

        // TODO: fixing Image paths
        final String originalImage = "C:\\Users\\mesutpiskin\\Desktop\\background.png";
        final String outputImage = "C:\\Users\\mesutpiskin\\Desktop\\output.png";

        //Generate heatmap 2d point (x,y) data
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int x = (int) (Math.random() * 200);
            int y = (int) (Math.random() * 200);
            Point p = new Point(x, y);
            points.add(p);
        }

        //import heatmap background image
        BufferedImage input;
        try {
            input = ImageIO.read(new File(originalImage));
        } catch (final IOException e) {
            throw new RuntimeException("error loading the image:" + originalImage + " : " + e);
        }

        //Create a heatmap from the data and add it to the background
        final JHeatMap myMap = new JHeatMap(input);
        final Map<Integer, List<Point>> integerListMap = myMap.initMap(points);
        final BufferedImage bufferedImage = myMap.createHeatMap(0.3f, integerListMap, 0.4f);

        //Export heatmap image
        try {
            final File outputfile = new File(outputImage);
            ImageIO.write(bufferedImage, "png", outputfile);
        } catch (final IOException e) {
            throw new RuntimeException("error saving the image:" + outputImage + " : " + e);
        }
    }

}
