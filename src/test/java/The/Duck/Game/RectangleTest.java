package The.Duck.Game;

import The.Duck.Game.BoardObjects.Utility.Rectangle;
import org.junit.Assert;
import org.junit.Test;

public class RectangleTest {

    @Test
    public void initTest() {
        Rectangle rectangle = new Rectangle(0, 0, 20, 40);

        Assert.assertEquals(0, rectangle.getLayoutX(), 0);
        Assert.assertEquals(0, rectangle.getLayoutY(), 0);
        Assert.assertEquals(20, rectangle.getWidth(), 0);
        Assert.assertEquals(40, rectangle.getHeight(), 0);
        Assert.assertEquals(20, rectangle.getSecondX(), 0);
        Assert.assertEquals(40, rectangle.getSecondY(), 0);
    }

    @Test
    public void intersectionTest() {

        Rectangle r1 = new Rectangle(0, 0, 20, 40);

        Rectangle r2 = new Rectangle(0, 0, 10, 30);     // Intersects
        Rectangle r3 = new Rectangle(-20, -20, 10, 10);
        Rectangle r4 = new Rectangle(80, 0, 5, 5);
        Rectangle r5 = new Rectangle(0, 80, 20, 30);
        Rectangle r6 = new Rectangle(0, 39, 20, 20); // Intersects
        Rectangle r7 = new Rectangle(0, 40, 20, 20); // Intersects

        Assert.assertTrue(r1.intersects(r2));
        Assert.assertFalse(r1.intersects(r3));
        Assert.assertFalse(r1.intersects(r4));
        Assert.assertFalse(r1.intersects(r5));
        Assert.assertTrue(r1.intersects(r6));
        Assert.assertTrue(r1.intersects(r7));
    }


}
