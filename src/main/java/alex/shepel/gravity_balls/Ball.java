package alex.shepel.gravity_balls;

import acm.graphics.GOval;

/*
 * File: Ball.java
 * ---------------------
 * Describes the ball that can be added to the window.
 */
public class Ball extends GOval {

    /**
     * The class constructor.
     *
     * @param x The x-coordinate of the ball.
     * @param y The y-coordinate of the ball.
     * @param size The size of the ball.
     */
    public Ball(double x, double y, double size) {
        super(x, y, size, size);
        super.setFilled(true);
    }

    /**
     * Moves the ball in the vertical direction.
     *
     * @param v The vertical velocity of the ball.
     */
    public void move(double v) {
        super.move(0, v);
    }

    /**
     * Sets the new size of the ball. Fixes its position.
     *
     * @param size The new size of the ball.
     */
    public void setSize(double size) {
        this.move((this.getWidth() - size) / 2, (this.getHeight() - size) / 2);
        this.setSize(size, size);
    }

}
