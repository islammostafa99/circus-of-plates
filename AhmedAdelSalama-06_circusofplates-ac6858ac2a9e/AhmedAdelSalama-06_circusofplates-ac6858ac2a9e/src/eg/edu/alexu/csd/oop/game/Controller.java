package eg.edu.alexu.csd.oop.game;

import com.sun.management.GcInfo;
import eg.edu.alexu.csd.oop.game.object.AddShapes;
import eg.edu.alexu.csd.oop.game.object.Gift;
import eg.edu.alexu.csd.oop.game.world.Circus;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static eg.edu.alexu.csd.oop.game.Main.logger;

public class Controller {
    private int level;
    public Controller(int level) {
        this.level = level;
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem pauseMenuItem = new JMenuItem("Pause");
        JMenuItem resumeMenuItem = new JMenuItem("Resume");
        JMenuItem addShape = new JMenuItem("Add gifts");
        menu.add(newMenuItem);
        menu.addSeparator();
        menu.add(pauseMenuItem);
        menu.add(resumeMenuItem);
        menu.add(addShape);
        menuBar.add(menu);
        Circus circus = new Circus(1100, 600 , level);
        final GameEngine.GameController gameController = GameEngine.start("Circus of plates", circus, menuBar, Color.BLACK);
        newMenuItem.addActionListener(e -> {
            logger.info("New Game!");
            new Controller(level);
        });
        pauseMenuItem.addActionListener(e -> {
            logger.info("pause game!");
            gameController.pause() ;
        });
        resumeMenuItem.addActionListener(e -> {
            logger.info("resume game!");
                gameController.resume();
        });
        addShape.addActionListener(e -> {
            List<GameObject> moving =circus.getMovableObjects();
            Class addShapes = AddShapes.class;
            try {
                logger.info("Gifts is added!");
                AddShapes addShapes1 = (AddShapes) addShapes.newInstance();
                addShapes1.addGifts(moving , level , 1100 , 600);
            } catch (InstantiationException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        });
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
