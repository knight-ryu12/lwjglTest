package ml.chromaryu.TransparentMonolith;

import static ml.chromaryu.TransparentMonolith.Point.*;
import static ml.chromaryu.TransparentMonolith.Color.*;

/**
 * Created by chroma on 16/06/02.
 */
public enum Face {
    F_FRONT(new Point[] {
        P_FRONT_TOP_RIGHT,
                P_FRONT_TOP_LEFT,
                P_FRONT_BOTTOM_LEFT,
                P_FRONT_BOTTOM_RIGHT
    }, BLUE, RED),
    F_BACK(new Point[] {
        P_BACK_TOP_LEFT,
                P_BACK_TOP_RIGHT,
                P_BACK_BOTTOM_RIGHT,
                P_BACK_BOTTOM_LEFT,
    }, BLUE, RED),
    F_LEFT(new Point[] {
        P_FRONT_TOP_LEFT,
                P_BACK_TOP_LEFT,
                P_BACK_BOTTOM_LEFT,
                P_FRONT_BOTTOM_LEFT
    }, BLUE, GREEN),
    F_RIGHT(new Point[] {
        P_BACK_TOP_RIGHT,
                P_FRONT_TOP_RIGHT,
                P_FRONT_BOTTOM_RIGHT,
                P_BACK_BOTTOM_RIGHT
    }, BLUE, GREEN),
    F_TOP(new Point[] {
        P_BACK_TOP_RIGHT,
                P_BACK_TOP_LEFT,
                P_FRONT_TOP_LEFT,
                P_FRONT_TOP_RIGHT
    }, DARK_BLUE, DARK_BLUE),
    F_BOTTOM(new Point[] {
        P_FRONT_BOTTOM_RIGHT,
                P_FRONT_BOTTOM_LEFT,
                P_BACK_BOTTOM_LEFT,
                P_BACK_BOTTOM_RIGHT
    }, DARK_RED, DARK_RED);

    private final Point[]  points;
    private final Color color1;
    private final Color color2;

    private Face(Point[] points, Color color1, Color color2) {
        this.points = points;
        this.color1 = color1;
        this.color2 = color2;
    }

    public void draw() {
        color1.set();
        points[0].point();
        points[1].point();

        color2.set();
        points[2].point();
        points[3].point();
    }

    public void draw(float alpha) {
        color1.set(alpha);
        points[0].point();
        points[1].point();

        color2.set(alpha);
        points[2].point();
        points[3].point();
    }

}
