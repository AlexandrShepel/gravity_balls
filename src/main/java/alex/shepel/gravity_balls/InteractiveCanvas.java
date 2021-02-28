package alex.shepel.gravity_balls;

import acm.graphics.GCanvas;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/*
 * File: InteractiveCanvas.java
 * ---------------------
 * Creates balls on the program window and interacts with them.
 */
public class InteractiveCanvas extends GCanvas implements MouseListener, MouseMotionListener {

    /* The ball that can increase itself size and have randomized color. */
    private ResizableColorfulBall rcBall;

    /* The flag that stands for creating ResizableColorfulBall object. */
    private boolean rcBallCreated;

    /* The balls that feels gravity attraction. */
    private final ArrayList<GravityBall> gBalls;

    /**
     * The class constructor.
     */
    public InteractiveCanvas() {
        addMouseListener(this);
        addMouseMotionListener(this);
        gBalls = new ArrayList<>();
    }

    /**
     * -- Removes all objects from canvas.
     * -- Stops all threads.
     */
    public void removeAll() {
        for (GravityBall gb : gBalls) {
            gb.stop();
        }
        gBalls.clear();
        super.removeAll();
    }

    /**
     * Starts to resize the created ball,
     * where mouse is pressed.
     *
     * @param mpe The mouse pressed event.
     */
    private void startResizing(MouseEvent mpe) {
        if (this.getElementAt(mpe.getX(), mpe.getY()) == null) {
            rcBall = new ResizableColorfulBall(mpe.getX(), mpe.getY(), 10);
            add(rcBall);
            Thread t = new Thread(rcBall);
            t.start();
            rcBallCreated = true;
        } else {
            rcBallCreated = false;
        }
    }

    /**
     * -- Stops to resize the created ball.
     * -- Removes it from canvas.
     *    It will be replaced with the GravityBall object.
     */
    private void stopResizing() {
        rcBall.stop();
        remove(rcBall);
    }

    /**
     * Creates a ball that feels gravity attraction.
     *
     * @param mre The mouse released event.
     */
    private void startGravity(MouseEvent mre) {
        GravityBall gb = new GravityBall(
                mre.getX() - rcBall.getWidth() / 2,
                mre.getY() - rcBall.getHeight() / 2,
                rcBall.getWidth(),
                rcBall.getColor(),
                getHeight());
        add(gb);
        gb.setGravityDown(true);
        Thread t = new Thread(gb);
        t.start();
        gBalls.add(gb);
    }

    /**
     * Inverses the gravity for the specified ball.
     * Ball specifies by clicking on it.
     *
     * @param mce The mouse clicked event.
     */
    private void inverseBallGravity(MouseEvent mce) {
        if (!rcBallCreated) {
            GravityBall gb = (GravityBall) this.getElementAt(mce.getX(), mce.getY());
            gb.setGravityDown(!gb.isGravityDown());
        }
    }

    /* The implementation of the MouseListener and MouseMotionListener interfaces. */
    public void mouseClicked(MouseEvent mce) {
        inverseBallGravity(mce);
    }
    public void mousePressed(MouseEvent mpe) {
        startResizing(mpe);
    }
    public void mouseReleased (MouseEvent mre) {
        if (rcBallCreated) {
            stopResizing();
            startGravity(mre);
        }
    }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseDragged(MouseEvent mde) {
        rcBall.setLocation(mde.getX(), mde.getY());
    }
    public void mouseMoved(MouseEvent mme) { }

}
