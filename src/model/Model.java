package model;
import java.util.TreeMap;
import java.awt.Dimension;


/**
 * Evaluate an expression for each pixel in a image.
 * 
 * @author Robert C Duvall
 */
public class Model
{
    public static final double DOMAIN_MIN = -1;
    public static final double DOMAIN_MAX = 1;
    public static TreeMap<String, Expression> letVariables;
    public static final int NUM_FRAMES = 50;
    private double myCurrentTime = 0;

    /**
     * Reset the animation.
     */
    public void reset () {
        myCurrentTime = 0;
    }
    
    /**
     * Advance to the next frame in the animation.
     */
    public void nextFrame () {
        myCurrentTime += 1.0 / NUM_FRAMES;
    }

    /**
     * Evaluate an expression for each point in the image.
     */
    public Pixmap evaluate (String input, Dimension size)
    {
        Pixmap result = new Pixmap(size);
        // create expression to evaluate just once
        Parser parser = new Parser();
        Expression toEval = parser.makeExpression(input, parser);
        // evaluate at each pixel
        for (int imageY = 0; imageY < size.height; imageY++) {
            double evalY = imageToDomainScale(imageY, size.height);
            for (int imageX = 0; imageX < size.width; imageX++) {
                double evalX = imageToDomainScale(imageX, size.width);
                letVariables = new TreeMap<String, Expression>();
                result.setColor(imageX, imageY, toEval.evaluate(evalX, evalY, letVariables).toJavaColor());
            }
        }
        return result;
    }


    /**
     * Convert from image space to domain space.
     */
    protected double imageToDomainScale (int value, int bounds)
    {
        double range = DOMAIN_MAX - DOMAIN_MIN;
        return ((double) value / bounds) * range + DOMAIN_MIN;
    }
}
