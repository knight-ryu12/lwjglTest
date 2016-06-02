package ml.chromaryu.DrawMonolith;
import org.lwjgl.input.Keyboard;
/**
 * Created by chroma on 16/06/02.
 */
public enum Direction {
    UP {
        @Override
        public float rotateYAngle(float angle) {
            angle -= UNIT;

            if (angle < 0) {
                angle = 360;
            }

            return angle;
        }
    },
    DOWN {
        @Override
        public float rotateYAngle(float angle) {
            angle %= 360;
            angle += UNIT;

            return angle;
        }
    },
    RIGHT {
        @Override
        public float rotateXAngle(float angle) {
            angle %= 360;
            angle += UNIT;

            return angle;
        }
    },
    LEFT {
        @Override
        public float rotateXAngle(float angle) {
            angle -= UNIT;

            if (angle < 0) {
                angle = 360;
            }

            return angle;
        }
    };

    private static final float  UNIT = 0.1f;

    public float rotateXAngle(float xAngle) {
        return xAngle;
    }

    public float rotateYAngle(float yAngle) {
        return yAngle;
    }

    public static Direction getPressing() {
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            return UP;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            return DOWN;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            return RIGHT;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            return LEFT;
        }

        return null;
    }
}
