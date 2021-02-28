package alex.shepel.gravity_balls;

import com.shpp.cs.a.simple.SimpleProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * File: BallInteractor.java
 * ---------------------
 * Allows user to create balls on the program window and interacts with them.
 */
public class BallInteractor extends SimpleProgram implements Constants, ActionListener {

    /* The canvas where program is visualized. */
    InteractiveCanvas canvas;

    /**
     * Initialize the program.
     *  -- Sets the size of the window.
     *  -- Adds mouse and action listeners.
     */
    public void init() {
        canvas = new InteractiveCanvas();
        add(canvas, CENTER);
        add(new JButton ("Clear"), SOUTH);
        addActionListeners();
    }

    /**
     * Clears the window when "Clear" button is pressed.
     *
     * @param ae The action event object.
     *           Uses to identify the pressing of "Clear" button.
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Clear")) {
            canvas.removeAll();
        }
    }

}
