package ml.chromaryu.lwjglTest;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by chroma on 16/06/02.
 */
public class DrawQuad {
    private int     width = 300;
    private int     height = 200;
    private int     depth = 100;

    public void start() {
        try {
            //  ウインドウを生成する
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle("hello world!");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            return;
        }

        try {
            //  OpenGL の初期設定

            //  ポリゴンの片面（表 or 裏）表示を有効にする
            glEnable(GL_CULL_FACE);
            //  ポリゴンの表示面を表（裏を表示しない）のみに設定する
            glCullFace(GL_BACK);

            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            //  視体積（描画する領域）を定義する
            glOrtho(0, width, 0, height, 0, depth);
            glMatrixMode(GL_MODELVIEW);

            while (!Display.isCloseRequested()) {
                //  オフスクリーンを初期化する
                glClear(GL_COLOR_BUFFER_BIT);

                //  オフスクリーンに描画する
                render();

                //  オフスクリーンをスクリーンに反映する
                Display.update();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Display.destroy();
        }
    }
    private void render() {
        //  次に指定する４つの座標を、描く四角形の頂点として認識させる
        glBegin(GL_QUADS);

        //  OpenGL では頂点が左回りになっているのがポリゴンの表となる
        //  今は表のみ表示する設定にしているので、頂点の方向を反対にすると裏側となり、表示されなくなる

        glColor3f(1.0f, 0.5f, 0.5f);            //  次に指定する座標に RGB で色を設定する
        glVertex3f(width - 50, height - 50, 0);  //  1 つめの座標を指定する

        glColor3f(0.5f, 1.0f, 0.5f);
        glVertex3f(50, height - 50, 0);      // 2 つめの座標を指定する

        glColor3f(0.5f, 0.5f, 1.0f);
        glVertex3f(50, 50, 0);                //    3 つめの座標を指定する

        glColor3f(1.0f, 1.0f, 1.0f);
        glVertex3f(width - 50, 50, 0);        //    4 つめの座標を指定する

        glEnd();
    }
}
