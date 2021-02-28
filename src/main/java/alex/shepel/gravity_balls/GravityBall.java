package alex.shepel.gravity_balls;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

/*
 * File: GravityBall.java
 * ---------------------
 * Describes the ball that can fall down to the ground.
 */
public class GravityBall extends Ball implements Runnable, Constants {

    /* The x-coordinate where ground is. */
    private final int ground;

    /* The direction of the gravity attraction. */
    private boolean isGravityDown;

    /* The flag of simulation start. */
    private final AtomicBoolean isStarted;

    /**
     * The class constructor.
     *
     * @param x The x-coordinate of the ball.
     * @param y The y-coordinate of the ball.
     * @param size The size of the ball.
     * @param color The color of the ball.
     * @param ground The x-coordinate where ground is.         
     */
    public GravityBall(double x, double y, double size, Color color, double ground) {
        super(x, y, size);
        super.setColor(color);
        this.ground = (int) ground;
        isStarted = new AtomicBoolean(false);
    }

    /**
     * Runs the free fall of the ball.
     */
    public void run() {
        /* The flag of simulation start. */
        isStarted.set(true);

        /* Initializes the velocity of the ball. */
        double vy = 0;

        /* Starts the simulation. */
        while (isStarted.get()) {

            /* Checks if ball is on the ground. */
            /* Reverses the velocity if true. */
            if (isGround()) {
                vy = - ELASTICITY * calculateVelocity(vy);
                while (isGround()) {
                    this.move(vy);
                }
            }

            /* Increases the velocity of the ball */
            /* according to the gravity acceleration. */
            else {
                vy = calculateVelocity(vy);
                pause(10);
                this.move(vy);
            }
        }
    }

    /**
     * Stops the simulation.
     */
    public void stop() {
        isStarted.set(false);
    }

    /**
     * Calculates the vertical velocity of the ball.
     *
     * @param v The vertical velocity of the ball.
     * @return The updated velocity.
     */
    private double calculateVelocity(double v) {
        /* If velocity equals zero, starts motion of a ball. */
        if (v == 0) {
            return 0.1;
        }

        /* Else accelerate it. */
        else {
            return (isGravityDown) ? (v + GRAVITY_ACCELERATION) : (v - GRAVITY_ACCELERATION);
        }
    }

    /**
     * Checks if ball is on the ground.
     *
     * @return Returns the "true" value if ball is on the ground.
     */
    private boolean isGround() {
        /* When gravity has normal downward direction. */
        if (isGravityDown) {
            return this.getY() > ground - this.getHeight();
        }

        /* When gravity is reversed. */
        else {
            return this.getY() < 0;
        }
    }

    /**
     * Sets the direction of the gravity attraction.
     *
     * @param isGravityDown When true -- gravity acts downwards.
     *                      When false -- vice a versa.
     */
    public void setGravityDown(boolean isGravityDown) {
        this.isGravityDown = isGravityDown;
    }

    /**
     * Returns the current direction of the gravity attraction.
     *
     * @return The boolean value.
     *         When it is true -- gravity acts downwards.
     *         When false -- vice a versa.
     */
    public boolean isGravityDown() {
        return isGravityDown;
    }

}
