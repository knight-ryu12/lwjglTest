package ml.chromaryu.TransparentMonolith;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;
/**
 * Created by chroma on 16/06/02.
 */
public class TransparentMonolith {
    private int         width = 300;
    private int         height = 200;
    private int      depth = 200;

    private float           xAngle = 0;
    private float           yAngle = 0;
    private Direction       input = null;

    public void start() {
        try {
            //  ウインドウを生成する
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle("Transparent Monolith");
            Display.create();
        } catch(LWJGLException e) {
            e.printStackTrace();
            return;
        }

        try {
            //  初期設定

            //  ポリゴンの片面（表 or 裏）表示を有効にする
            glEnable(GL_CULL_FACE);
            //  ポリゴンの表示面を表（裏を表示しない）のみに設定する
            glCullFace(GL_BACK);

            //  アルファブレンドを有効化する
            glEnable(GL_BLEND);
            //  アルファブレンドの係数を設定する
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

            //  カメラ用行列の設定変更を指示する
            glMatrixMode(GL_PROJECTION);
            //  今まで設定してきた行列を単位行列に置き換え、初期化する
            glLoadIdentity();
            //  視体積（目に見える範囲）を定義する
            glOrtho(0, width, 0, height, 0, depth);

            //  物体用行列の設定変更を指示する
            glMatrixMode(GL_MODELVIEW);

            while (!Display.isCloseRequested()) {
                //  オフスクリーンを初期化する
                glClear(GL_COLOR_BUFFER_BIT);

                //  キー入力を処理する
                update();

                //  オフスクリーンに描画する
                render();

                //  オフスクリーンをスクリーンに反映する
                Display.update();
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            Display.destroy();
        }
    }

    private void update() {
        input = Direction.getPressing();

        if (input != null) {
            xAngle = input.rotateXAngle(xAngle);
            yAngle = input.rotateYAngle(yAngle);
        }
    }

    private void render() {
        //  現在設定されている行列を単位行列に置き換え、初期化する
        glLoadIdentity();

        //  画面中央の座標を (0, 0, 0) とするよう座標系を移動する
        glTranslatef(width / 2f, height / 2f, depth / -2f);

        int count = 7;
        for (int i = 0; i < count; i++) {
            //  現在設定されている行列のコピーを行列スタックにプッシュする。
            glPushMatrix();

            //  モデルの位置を少しずつ X 軸方向にずらす
            float   diffX = (i - (count / 2f)) * 30f;
            glTranslatef(diffX, 0, 0);

            //  モデルのスケール、角度、透過率を少しずつずらす
            float   scale = 20f - (count - 1 - i) * 2f;
            float   angle = (count - 1 - i) * 10f;
            float   alpha = 1f / (count - 1) * i;
            renderMonolith(scale, angle, alpha);

            //  行列スタックから行列をポップし、現在設定されている行列に置き換える
            glPopMatrix();
        }
    }

    private void renderMonolith(float scale, float angle, float alpha) {
        //  回転する角度を引数 angle 値で補正する
        float   xAngle = this.xAngle + angle;
        float   yAngle = this.yAngle + angle;

        if (360f < xAngle) xAngle %= 360f;
        if (360f < yAngle) yAngle %= 360f;

        glRotatef(xAngle, 0, 1, 0); //  y 軸を中心に xAngle 度回転させる
        glRotatef(yAngle, 1, 0, 0); //  x 軸を中心に yAngle 度回転させる

        //  座標のスケールを指定する
        //  ここで指定した x, y, z 毎の係数が、次に指定する座標にそれぞれ掛けられる
        glScalef(scale, scale, scale);

        glBegin(GL_QUADS);

        for (Face face: Face.values()) {
            face.draw(alpha);
        }

        glEnd();
    }

    public static void main(String[] args) {
        new TransparentMonolith().start();
    }
}
