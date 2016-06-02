package ml.chromaryu.DrawMonolith;

import static org.lwjgl.opengl.GL11.glColor3f;
/**
 * Created by chroma on 16/06/02.
 */
public enum Color {
    RED(1f, 0.6f, 0.6f),
    DARK_RED(1f, 0.5f, 0.5f),
    BLUE(0.6f, 0.6f, 1f),
    DARK_BLUE(0.5f, 0.5f, 1f),
    GREEN(0.6f, 1f, 0.6f),
    DARK_GREEN(0.5f, 1f, 0.5f),
    YELLOW(1f, 1f, 0.6f),
    DARK_YELLOW(1f, 1f, 0.5f);

    private final float r;
    private final float g;
    private final float b;

    private Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void set() {
        glColor3f(r, g, b);
    }
}
