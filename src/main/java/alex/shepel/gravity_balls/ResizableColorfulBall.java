package alex.shepel.gravity_balls;

import acm.util.RandomGenerator;

import java.util.concurrent.atomic.AtomicBoolean;

/*
 * File: ResizableColorfulBall.java
 * ---------------------
 * Describes the ball that can increase itself size
 * and change itself color.
 */
public class ResizableColorfulBall extends Ball implements Runnable, Constants {

    /* The size of the ball. */
    private double size;

    /* The flag of simulation start. */
    private final AtomicBoolean isStarted;

    /**
     * The class constructor.
     *
     * @param x The x-coordinate of the ball.
     * @param y The y-coordinate of the ball.
     * @param size The size of the ball.
     */
    public ResizableColorfulBall(double x, double y, double size) {
        super(x, y, size);
        this.size = size;
        this.isStarted = new AtomicBoolean(false);
    }

    /**
     * Starts increasing and coloring of the ball.
     */
    public void run() {
        /* The flag of simulation start. */
        isStarted.set(true);

        /* Random generator instance. */
        /* It is needed to randomize a ball's color. */
        RandomGenerator rg = new RandomGenerator();

        /* Starts the simulation. */
        while (isStarted.get()) {
            size = size * 1.1;
            setSize(size);
            setColor(rg.nextColor());
            pause(100);
        }
    }

    /**
     * Stops the simulation.
     */
    public void stop() {
        isStarted.set(false);
    }

}
