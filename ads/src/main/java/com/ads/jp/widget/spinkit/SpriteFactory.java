package com.ads.jp.widget.spinkit;


import com.ads.jp.widget.spinkit.sprite.Sprite;
import com.ads.jp.widget.spinkit.style.ChasingDots;
import com.ads.jp.widget.spinkit.style.Circle;
import com.ads.jp.widget.spinkit.style.CubeGrid;
import com.ads.jp.widget.spinkit.style.DoubleBounce;
import com.ads.jp.widget.spinkit.style.FadingCircle;
import com.ads.jp.widget.spinkit.style.FoldingCube;
import com.ads.jp.widget.spinkit.style.MultiplePulse;
import com.ads.jp.widget.spinkit.style.MultiplePulseRing;
import com.ads.jp.widget.spinkit.style.Pulse;
import com.ads.jp.widget.spinkit.style.PulseRing;
import com.ads.jp.widget.spinkit.style.RotatingCircle;
import com.ads.jp.widget.spinkit.style.RotatingPlane;
import com.ads.jp.widget.spinkit.style.ThreeBounce;
import com.ads.jp.widget.spinkit.style.WanderingCubes;
import com.ads.jp.widget.spinkit.style.Wave;

/**
 * Created by ybq.
 */
public class SpriteFactory {

    public static Sprite create(Style style) {
        Sprite sprite = null;
        switch (style) {
            case ROTATING_PLANE:
                sprite = new RotatingPlane();
                break;
            case DOUBLE_BOUNCE:
                sprite = new DoubleBounce();
                break;
            case WAVE:
                sprite = new Wave();
                break;
            case WANDERING_CUBES:
                sprite = new WanderingCubes();
                break;
            case PULSE:
                sprite = new Pulse();
                break;
            case CHASING_DOTS:
                sprite = new ChasingDots();
                break;
            case THREE_BOUNCE:
                sprite = new ThreeBounce();
                break;
            case CIRCLE:
                sprite = new Circle();
                break;
            case CUBE_GRID:
                sprite = new CubeGrid();
                break;
            case FADING_CIRCLE:
                sprite = new FadingCircle();
                break;
            case FOLDING_CUBE:
                sprite = new FoldingCube();
                break;
            case ROTATING_CIRCLE:
                sprite = new RotatingCircle();
                break;
            case MULTIPLE_PULSE:
                sprite = new MultiplePulse();
                break;
            case PULSE_RING:
                sprite = new PulseRing();
                break;
            case MULTIPLE_PULSE_RING:
                sprite = new MultiplePulseRing();
                break;
            default:
                break;
        }
        return sprite;
    }
}
