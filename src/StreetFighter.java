import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class StreetFighter {
    private static Boolean endgame;
    private static BufferedImage background;
    private static Vector<BufferedImage> player1 = new Vector<>();
    private static Vector<BufferedImage> player2 = new Vector<>();
    private static BufferedImage gameCover;
    private static Boolean iPressed;
    private static Boolean kPressed;
    private static Boolean jPressed;
    private static Boolean lPressed;
    private static Boolean uPressed;
    private static Boolean hPressed;
    private static Boolean wPressed;
    private static Boolean sPressed;
    private static Boolean aPressed;
    private static Boolean dPressed;
    private static Boolean ePressed;
    private static Boolean fPressed;
    private static ImageObject p1;
    private static ImageObject p2;
    private static double pWidth;
    private static double pHeight;
    private static double p1OriginalX;
    private static double p1OriginalY;
    private static double p1Velocity;
    private static double p1Yvelocity;
    private static double p2Width;
    private static double p2Height;
    private static double p2OriginalX;
    private static double p2OriginalY;
    private static double p2Velocity;
    private static double p2Yvelocity;
    private static double maxSpeed;
    private static int xOffset;
    private static int yOffset;
    private static double pi;
    private static double twoPi;
    private static double piOvertwo;
    private static double threePiOverTwo;
    private static long start = System.currentTimeMillis();
    private static long startPlayer1 = System.currentTimeMillis();
    private static long startPlayer2 = System.currentTimeMillis();
    private static long bestTimePlayer1 = Integer.MAX_VALUE;
    private static long bestTimePlayer2 = Integer.MAX_VALUE;
    private static JFrame appFrame;
    private static JPanel titleScreen;
    private static JPanel menuPanel;
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    private static int maxLaps;
    private static int currentLap;
    private static JComboBox lapList;
    private static JComboBox vehicleList;
    private static JComboBox vehicleList2;
    private static int p1CurrentLap = 1;
    private static int p2CurrentLap = 1;
    private static BufferedImage Porshe;
    private static BufferedImage BMW;
    private static BufferedImage Supra;
    private static BufferedImage Toyota;
    private static BufferedImage[] barriers;
    private static AudioInputStream ais;
    private static Clip wav1;
    private static AudioInputStream ais2;
    private static Clip wav2;
    private static Image image;
    private static Vector<String> moveSet;
    private static Vector<Integer> moveSteps;

    public StreetFighter() {
        setup();
    }

    public static void setup() {
        appFrame = new JFrame("Street Fighter");
        xOffset = 0;
        yOffset = 0;
        pi = Math.PI;
        twoPi = 2 * pi;
        piOvertwo = pi / 2;
        threePiOverTwo = (3 * pi) / 2;
        endgame = false;

        maxSpeed = 4;
        maxLaps = 5;
        currentLap = 1;

        //vehicle dimensions
        pHeight = 50;
        pWidth = 50;

        //initial coords for the players
        p1OriginalX = 295; //(double) xOffset + ((double) winWidth / 2.0) - (pWidth / 2.0) + 400;
        p1OriginalY = 316; //(double) yOffset + ((double) winHeight / 2.0) - (pHeight / 2.0) + 50;

        p2OriginalX = 295; //(double) xOffset + ((double) winWidth / 2.0) - (pWidth / 2.0) + 400;
        p2OriginalY = 332; //(double) yOffset + ((double) winHeight / 2.0) - (pHeight / 2.0) + 100;

        System.out.println("P1 x: " + p1OriginalX + ", P1 y: " + p1OriginalY);
        System.out.println("P2 x: " + p2OriginalX + ", P2 y: " + p2OriginalY);

        moveSet = new Vector<>();
        moveSteps = new Vector<>();
        moveSet.add("Idle");
        moveSteps.add(4);
        moveSet.add("Walk");
        moveSteps.add(5);
        moveSet.add("Jump");
        moveSteps.add(7);
        moveSet.add("Crouch");
        moveSteps.add(3);
        moveSet.add("LightPunch");
        moveSteps.add(3);

        try {

            //default images for the game
            gameCover = ImageIO.read(new File("src/images/street fighter logo.png"));
            background = ImageIO.read(new File("src/images/SF2bg1.jpg"));

            //Player 1
            player1.add(ImageIO.read(new File("src/images/ryuidleOne.png")));
            player1.add(ImageIO.read(new File("src/images/ryuidleTwo.png")));
            player1.add(ImageIO.read(new File("src/images/ryuidleThree.png")));
            player1.add(ImageIO.read(new File("src/images/ryuidleFour.png")));
            player1.add(ImageIO.read(new File("src/images/ryuwalkOne.png")));
            player1.add(ImageIO.read(new File("src/images/ryuwalkTwo.png")));
            player1.add(ImageIO.read(new File("src/images/ryuwalkThree.png")));
            player1.add(ImageIO.read(new File("src/images/ryuwalkFour.png")));
            player1.add(ImageIO.read(new File("src/images/ryuwalkFive.png")));
            player1.add(ImageIO.read(new File("src/images/ryujumpOne.png")));
            player1.add(ImageIO.read(new File("src/images/ryujumpTwo.png")));
            player1.add(ImageIO.read(new File("src/images/ryujumpThree.png")));
            player1.add(ImageIO.read(new File("src/images/ryujumpFour.png")));
            player1.add(ImageIO.read(new File("src/images/ryujumpFive.png")));
            player1.add(ImageIO.read(new File("src/images/ryujumpSix.png")));
            player1.add(ImageIO.read(new File("src/images/ryujumpSeven.png")));
            player1.add(ImageIO.read(new File("src/images/ryucrouch.png")));
            player1.add(ImageIO.read(new File("src/images/ryucrouch.png")));
            player1.add(ImageIO.read(new File("src/images/ryucrouch.png")));
            player1.add(ImageIO.read(new File("src/images/ryulightPunchOne.png")));
            player1.add(ImageIO.read(new File("src/images/ryulightPunchTwo.png")));
            player1.add(ImageIO.read(new File("src/images/ryulightPunchThree.png")));


            //Player 2
            player2.add(ImageIO.read(new File("src/images/kenidleOne.png")));
            player2.add(ImageIO.read(new File("src/images/kenidleTwo.png")));
            player2.add(ImageIO.read(new File("src/images/kenidleThree.png")));
            player2.add(ImageIO.read(new File("src/images/kenidleFour.png")));
            player2.add(ImageIO.read(new File("src/images/kenwalkOne.png")));
            player2.add(ImageIO.read(new File("src/images/kenwalkTwo.png")));
            player2.add(ImageIO.read(new File("src/images/kenwalkThree.png")));
            player2.add(ImageIO.read(new File("src/images/kenwalkFour.png")));
            player2.add(ImageIO.read(new File("src/images/kenwalkFive.png")));
            player2.add(ImageIO.read(new File("src/images/kenjumpOne.png")));
            player2.add(ImageIO.read(new File("src/images/kenjumpTwo.png")));
            player2.add(ImageIO.read(new File("src/images/kenjumpThree.png")));
            player2.add(ImageIO.read(new File("src/images/kenjumpFour.png")));
            player2.add(ImageIO.read(new File("src/images/kenjumpFive.png")));
            player2.add(ImageIO.read(new File("src/images/kenjumpSix.png")));
            player2.add(ImageIO.read(new File("src/images/kenjumpSeven.png")));
            player2.add(ImageIO.read(new File("src/images/kencrouch.png")));
            player2.add(ImageIO.read(new File("src/images/kencrouch.png")));
            player2.add(ImageIO.read(new File("src/images/kencrouch.png")));
            player2.add(ImageIO.read(new File("src/images/kenlightPunchOne.png")));
            player2.add(ImageIO.read(new File("src/images/kenlightPunchTwo.png")));
            player2.add(ImageIO.read(new File("src/images/kenlightPunchThree.png")));


            for (int i = 0; i < player2.size(); i++) {
                player2.set(i, flipImage(player2.get(i)));
            }

        } catch (IOException ioe) {

        }
        System.out.println(player1.size());
        System.out.println(player2.size());

    }

    /**
     * Main method for initializing the game, adding buttons, adding drop down menus,
     * binding keyboard keys, creating the main app frame
     */
    public static void main(String[] args) {
        setup();
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setSize(1000, 600);
        appFrame.setResizable(false);

        menuPanel = new JPanel();
        Cover(titleScreen);

        //start game button
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new StartGame());
        menuPanel.add(newGameButton);

        //select vehicle player 1 drop down
        String[] vehicles = new String[]{"Ryu", "Ken"};
        JLabel vehicleText1 = new JLabel("Player 1");
        vehicleList = new JComboBox(vehicles);
        menuPanel.add(vehicleText1);
        menuPanel.add(vehicleList);
        //vehicleList.addActionListener(new vehicleListener1());

        //select vehicle player 2 drop down
        JLabel vehicleText2 = new JLabel("Player 2");
        vehicleList2 = new JComboBox(vehicles);
        menuPanel.add(vehicleText2);
        menuPanel.add(vehicleList2);
        //vehicleList2.addActionListener(new vehicleListener2());

        //quit game button
        JButton quitButton = new JButton("Quit Game");
        quitButton.addActionListener(new QuitGame());
        menuPanel.add(quitButton);

        //controls for player 1
        bindKey(menuPanel, "I");
        bindKey(menuPanel, "K");
        bindKey(menuPanel, "J");
        bindKey(menuPanel, "L");
        bindKey(menuPanel, "U");
        bindKey(menuPanel, "H");


        //controls for player 2
        bindKey(menuPanel, "W");
        bindKey(menuPanel, "S");
        bindKey(menuPanel, "A");
        bindKey(menuPanel, "D");
        bindKey(menuPanel, "E");
        bindKey(menuPanel, "F");

        appFrame.getContentPane().add(menuPanel, "South");
        appFrame.setVisible(true);
    }

    public static void Cover(JPanel container) {
        image = new ImageIcon("src/images/street fighter logo.png").getImage();

        container = new MyBackground();
        appFrame.add(container);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setSize(1200, 600);
        appFrame.setVisible(true);
        try {
            //play music
            ais = AudioSystem.getAudioInputStream(new File(""));
            wav1 = AudioSystem.getClip();
            wav1.open(ais);
            wav1.loop(0);
        } catch (UnsupportedAudioFileException uafe) {
        } catch (IOException ioe) {
        } catch (LineUnavailableException lue) {
        }
    }

    public static class MyBackground extends JPanel {

        public MyBackground() {
            setBackground(new Color(0, true));
        }

        @Override
        public void paintComponent(Graphics g) {
            //Paint background first
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            //Paint the rest of the component. Children and self etc.
            super.paintComponent(g);
        }
    }

    private static class Animate implements Runnable {

        Graphics g = appFrame.getGraphics();
        Graphics2D g2d = (Graphics2D) g;

        public void run() {
            while (!endgame) {
                drawBackground();
                drawPlayer();
                CountdownTimer timer = new CountdownTimer(99);
                timer.start();

                if (p1CurrentLap >= maxLaps + 1) {
                    drawWinner("Player 1");
                    drawWinner("Player 1");
                    g2d.setFont(new Font("TimesRoman", Font.BOLD, 40));
                    g2d.setColor(Color.GREEN);
                    endgame = true;
                    wav1.close();
                    wav2.close();
                } else if (p2CurrentLap >= maxLaps + 1) {
                    drawWinner("Player 2");
                    g2d.setFont(new Font("TimesRoman", Font.BOLD, 40));
                    g2d.setColor(Color.red);
                    endgame = true;
                    wav1.close();
                    wav2.close();
                }
                drawPlayer();

                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    System.out.println("Exception caught in Animate");
                }

            }
        }
    }

    private static void drawWinner(String winningPlayer) {
        Graphics g = appFrame.getGraphics();
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.red);
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 120));
        g2d.drawString(winningPlayer + " won!", 400, 400);
    }
//
//    private static class LapListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            maxLaps = lapList.getSelectedIndex() + 1;
//        }
//    }

//    private static class vehicleListener1 implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            int n = vehicleList.getSelectedIndex();
//            //player = vehiclePick(player.get(PlayerMover.getAnimState()), n);
//        }
//    }
//
//    private static class vehicleListener2 implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            int n = vehicleList2.getSelectedIndex();
//            player2 = vehiclePick(player2, n);
//        }
//    }

//    public static BufferedImage vehiclePick(BufferedImage playervehicle, int n) {
//        if (n == 0)
//            playervehicle = Porshe;
//        else if (n == 1)
//            playervehicle = BMW;
//        else if (n == 2)
//            playervehicle = Supra;
//        else
//            playervehicle = Toyota;
//        return playervehicle;
//    }


    private static class StartGame implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            endgame = true;
            start = System.currentTimeMillis();
            iPressed = false;
            kPressed = false;
            jPressed = false;
            lPressed = false;
            uPressed = false;
            hPressed = false;

            wPressed = false;
            sPressed = false;
            aPressed = false;
            dPressed = false;
            ePressed = false;
            fPressed = false;

            //instantiate the ImageObjects for the player vehicles
            p1 = new ImageObject(p1OriginalX, p1OriginalY, pWidth, pHeight, -Math.PI / 2);
            p2 = new ImageObject(p2OriginalX, p2OriginalY, pWidth, pHeight, -Math.PI / 2);

            p1Velocity = 0.0;
            p2Velocity = 0.0;
            p1Yvelocity = 0.0;
            p2Yvelocity = 0.0;

            try {
                Thread.sleep(50);
            } catch (InterruptedException ie) {
                System.out.println("Caught the exception in start game");
            }

            try {
                //play music
                ais = AudioSystem.getAudioInputStream(new File("src/sounds/yt5s.io_-_NFS_CARBON_BELT_TUNER_THEMEPHONK_REMIX_320_kbps.mp3"));
                wav1 = AudioSystem.getClip();
                wav1.open(ais);
                wav1.loop(Clip.LOOP_CONTINUOUSLY);
                wav1.start();

            } catch (UnsupportedAudioFileException uafe) {
            } catch (IOException ioe) {
            } catch (LineUnavailableException lue) {
            }

            endgame = false;
            Thread t1 = new Thread(new Animate());
            Thread t2 = new Thread(new PlayerMover());
            Thread t3 = new Thread(new CollisionChecker());
            //Thread t5 = new Thread(new WinChecker());
            t1.start();
            t2.start();
            t3.start();
        }
    }
    private static class QuitGame implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            endgame = true;
            wav1.stop();
        }
    }

    private static class WinChecker implements Runnable {
        public void run() {
            if (p1CurrentLap >= maxLaps + 1) {
                drawWinner("Player 1");
            } else if (p2CurrentLap >= maxLaps + 1) {
                drawWinner("Player 2");
            }
            else if (bestTimePlayer1 == bestTimePlayer2) {
                drawWinner("Player 1");
            }
        }

        private static void drawWinner(String winningPlayer) {
            Graphics g = appFrame.getGraphics();
            Graphics2D g2d = (Graphics2D) g;

            g.setColor(Color.red);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 60));
            g2d.drawString(winningPlayer + " won!", 450, 450);

            g2d.setFont(new Font("TimesRoman", Font.BOLD, 40));
            g2d.drawString("Player 1 Best Lap: " + bestTimePlayer1, 550, 550);
            g2d.drawString("Player 2 Best Lap: " + bestTimePlayer2, 550, 450);
        }
    }

    private static class PlayerMover implements Runnable {

        private boolean p1AnimationFinished;
        private boolean p1NewAnimation = true;
        private String p1CurrentAnimation = "Idle";
        private boolean p2AnimationFinished;
        private boolean p2NewAnimation = true;
        private String p2CurrentAnimation = "Idle";
        private static int p1AnimState = 0;
        private static int p2AnimState = 0;
        private static int p1CurrentMoveEnd = 0;
        private boolean p1Jumping = false;
        private boolean p1Crouching = false;
        private static int p2CurrentMoveEnd = 0;
        private boolean p2Jumping = false;
        private boolean p2Crouching = false;
        private double crouchHeight;
        private double standingHeight;

        //level of acceleration and rotation speed
        public PlayerMover() {
            p1AnimationFinished = true;
            p2AnimationFinished = true;
            standingHeight = p1.getY();
            crouchHeight = p1.getY() + 60;
            p2.move(300, 0);
        }

        public static int getP1AnimState() {
            return p1AnimState;
        }

        public static int getP2AnimState() {
            return p2AnimState;
        }

        public void run() {

            while (!endgame) {
                if(p1NewAnimation) {
                    int tempAnimState = 0;
                    p1Yvelocity = 0;

                    findMove:
                    for(int i = 0; i < moveSet.size(); i++) {
                        if(moveSet.get(i).equalsIgnoreCase(p1CurrentAnimation)) {
                            p1AnimState = tempAnimState;
                            p1CurrentMoveEnd = tempAnimState + moveSteps.get(i) - 1;
                            p1NewAnimation = false;
                            p1AnimationFinished = false;
                            break findMove;
                        } else {
                            tempAnimState += moveSteps.get(i);
                        }
                    }
                } else {
                    if (p1CurrentMoveEnd - p1AnimState > 0) {
                        try {
                            Thread.sleep(150);
                        } catch (InterruptedException e) {
                            System.out.println("Exception caught for PlayerMover");
                        }
                        p1AnimState++;

                        if(p1Jumping) {
                            p1Yvelocity -= (((p1CurrentMoveEnd - p1AnimState) * 7) - 10);
                        }

                    } else {
                        p1CurrentAnimation = "Idle";
                        p1AnimState = 0;
                        p1CurrentMoveEnd = 0;
                        p1AnimationFinished = true;
                        p1NewAnimation = true;
                        p1Jumping = false;
                        p1Crouching = false;
                    }
                }

                if(p2NewAnimation) {
                    int tempAnimState = 0;
                    p2Yvelocity = 0;

                    findMove:
                    for(int i = 0; i < moveSet.size(); i++) {
                        if(moveSet.get(i).equalsIgnoreCase(p2CurrentAnimation)) {
                            p2AnimState = tempAnimState;
                            p2CurrentMoveEnd = tempAnimState + moveSteps.get(i) - 1;
                            p2NewAnimation = false;
                            p2AnimationFinished = false;
                            break findMove;
                        } else {
                            tempAnimState += moveSteps.get(i);
                        }
                    }
                } else {
                    if (p2CurrentMoveEnd - p2AnimState > 0) {
                        try {
                            Thread.sleep(150);
                        } catch (InterruptedException e) {
                            System.out.println("Exception caught for PlayerMover");
                        }
                        p2AnimState++;

                        if(p2Jumping) {
                            p2Yvelocity -= (((p2CurrentMoveEnd - p2AnimState) * 7) - 10);
                        }

                    } else {
                        p2CurrentAnimation = "Idle";
                        p2AnimState = 0;
                        p2CurrentMoveEnd = 0;
                        p2AnimationFinished = true;
                        p2NewAnimation = true;
                        p2Jumping = false;
                        p2Crouching = false;
                    }
                }


                //Player One
                if(p1AnimationFinished) {
                    if (sPressed) {
                        p1CurrentAnimation = "Crouch";
                        p1NewAnimation = true;
                        p1Crouching = true;
                        p1Velocity = 0;
                    } else if (wPressed) {
                        p1CurrentAnimation = "Jump";
                        p1NewAnimation = true;
                        p1Jumping = true;
                        p1Velocity = 0;

                    } else if(ePressed) {
                        p1CurrentAnimation = "LightPunch";
                        System.out.println("Punch");
                        p1Velocity = 0;
                        p1NewAnimation = true;

                    } else if (dPressed) {
                        if ((!p1CurrentAnimation.equals("Crouch") && !p1CurrentAnimation.equals("Jump") && !p1CurrentAnimation.equals("LightPunch"))) {
                            p1Velocity = 8;
                        }
                        p1NewAnimation = true;
                        p1CurrentAnimation = "Walk";
                    } else if (aPressed) {
                        if ((!p1CurrentAnimation.equals("Crouch") && !p1CurrentAnimation.equals("Jump") && !p1CurrentAnimation.equals("LightPunch"))) {
                            p1Velocity = -8;
                        }
                        p1NewAnimation = true;
                        p1CurrentAnimation = "Walk";
                    } else {
                        p1Velocity = 0;
                        p1CurrentAnimation = "Idle";
                    }
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("Exception caught for PlayerMover");
                }

                if(p1Crouching) {
                    p1.moveTo(p1.getX(), crouchHeight);
                } else {
                    p1.moveTo(p1.getX(), standingHeight);
                }

                p1.move(p1Velocity, p1Yvelocity);


                //Player Two
                if(p2AnimationFinished) {
                    if (kPressed) {
                        p2CurrentAnimation = "Crouch";
                        p2NewAnimation = true;
                        p2Crouching = true;
                        p2Velocity = 0;
                    } else if (iPressed) {
                        p2CurrentAnimation = "Jump";
                        p2NewAnimation = true;
                        p2Jumping = true;
                        p2Velocity = 0;

                    } else if(uPressed) {
                        p2CurrentAnimation = "LightPunch";
                        p2NewAnimation = true;
                        p2Velocity = 0;
                    } else if (lPressed) {
                        if ((!p2CurrentAnimation.equals("Crouch") && !p2CurrentAnimation.equals("Jump")  && !p2CurrentAnimation.equals("LightPunch"))) {
                            p2Velocity = 8;
                        }
                        p2NewAnimation = true;
                        p2CurrentAnimation = "Walk";
                    } else if (jPressed) {
                        if ((!p2CurrentAnimation.equals("Crouch") && !p2CurrentAnimation.equals("Jump")  && !p2CurrentAnimation.equals("LightPunch"))) {
                            p2Velocity = -8;
                        }
                        p2NewAnimation = true;
                        p2CurrentAnimation = "Walk";
                    } else {
                        p2Velocity = 0;
                        p2CurrentAnimation = "Idle";
                    }
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("Exception caught for PlayerMover");
                }

                if(p2Crouching) {
                    p2.moveTo(p2.getX(), crouchHeight);
                } else {
                    p2.moveTo(p2.getX(), standingHeight);
                }

                p2.move(p2Velocity, p2Yvelocity);



                //p1.move(-p1Velocity * Math.cos(p1.getAngle() - pi / 2.0), p1Velocity * Math.sin(p1.getAngle() - pi / 2.0));
//                p2.move(-p2Velocity * Math.cos(p2.getAngle() - pi / 2.0), p2Velocity * Math.sin(p2.getAngle() - pi / 2.0));

            }

        }
    }

    private static class KeyPressed extends AbstractAction {

        private String action;

        public KeyPressed() {
            action = "";
        }

        public KeyPressed(String input) {
            action = input;
        }

        public void actionPerformed(ActionEvent e) {
            if (action.equals("I")) iPressed = true;
            if (action.equals("K")) kPressed = true;
            if (action.equals("J")) jPressed = true;
            if (action.equals("L")) lPressed = true;
            if (action.equals("U")) uPressed = true;
            if (action.equals("H")) hPressed = true;

            if (action.equals("W")) wPressed = true;
            if (action.equals("S")) sPressed = true;
            if (action.equals("A")) aPressed = true;
            if (action.equals("D")) dPressed = true;
            if (action.equals("E")) ePressed = true;
            if (action.equals("F")) fPressed = true;
        }
    }

    private static class KeyReleased extends AbstractAction {

        private String action;

        public KeyReleased() {
            action = "";
        }

        public KeyReleased(String input) {
            action = input;
        }

        public void actionPerformed(ActionEvent e) {
//            System.out.println("Key released");

            if (action.equals("I")) iPressed = false;
            if (action.equals("K")) kPressed = false;
            if (action.equals("J")) jPressed = false;
            if (action.equals("L")) lPressed = false;
            if (action.equals("U")) uPressed = false;
            if (action.equals("H")) hPressed = false;

            if (action.equals("W")) wPressed = false;
            if (action.equals("S")) sPressed = false;
            if (action.equals("A")) aPressed = false;
            if (action.equals("D")) dPressed = false;
            if (action.equals("E")) ePressed = false;
            if (action.equals("F")) fPressed = false;
        }
    }

    private static void bindKey(JPanel myPanel, String input) {
        System.out.println("Key bound");

        myPanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed " + input), input + " pressed");
        myPanel.getActionMap().put(input + " pressed", new KeyPressed(input));

        myPanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("released " + input), input + " released");
        myPanel.getActionMap().put(input + " released", new KeyReleased(input));
    }
    public static class CountdownTimer {
        private int secondsLeft;
        private Timer timer;

        public CountdownTimer(int seconds) {
            this.secondsLeft = seconds;
        }

        public void start() {
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    if (secondsLeft == 0) {
                        System.out.println("Time's up!");
                        timer.cancel();
                    } else {
                        System.out.println(secondsLeft + " seconds left");
                        secondsLeft--;
                    }
                }
            }, 0, 1000);
        }
    }


    private static void drawPlayer() {

        Graphics g = appFrame.getGraphics();
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage((player1.get(PlayerMover.getP1AnimState())), (int) (p1.getX()), (int) (p1.getY()), null);
        g2d.drawImage((player2.get(PlayerMover.getP2AnimState())), (int) (p2.getX()), (int) (p2.getY()), null);
    }
    private static void drawBarriers() {
        //import graphics
        Graphics g = appFrame.getGraphics();
        Graphics2D g2d = (Graphics2D) g;
    }

    private static AffineTransformOp rotateImageObject(ImageObject obj) {
        AffineTransform at = AffineTransform.getRotateInstance(-obj.getAngle(), obj.getWidth() / 2.0, obj.getHeight() / 2.0);
        return new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
    }

    private static BufferedImage flipImage(BufferedImage image) {
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(-1, 1));
        at.concatenate(AffineTransform.getTranslateInstance(-image.getWidth(),0));
        return transformImage(image, at);
    }

    private static BufferedImage transformImage(BufferedImage image, AffineTransform at) {
        BufferedImage newImage = new BufferedImage(
                image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.transform(at);
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

    private static void drawBackground() {
        Graphics g = appFrame.getGraphics();
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, xOffset, yOffset, null);
    }

    private static class CollisionChecker implements Runnable {
        public void run() {
            while (!endgame) {
                boolean hit = false;

                //Collision between cars

                //inBounds
                //System.out.println(p2.getAngle());
                //System.out.println(hitBottomWall(p2));
                //System.out.println(p1.getY()    );

//                if(hitBottomWall(p1)) {
//                    p1Velocity /= 1.5;
//                    hit = true;
//                    if(p1.getAngle() > pi) {
//                        p1.rotate((((3 * pi) / 2) - p1.getAngle()) + p1.getAngle() - pi);
//
//                    } else {
//                        p1.rotate(-((p1.getAngle() - (pi / 2)) + (pi - p1.getAngle())));
//                    }
//                }
//
//                if(hitTopWall(p1)) {
//                    p1Velocity /= 1.5;
//                    hit = true;
//                    if(p1.getAngle() < twoPi && p1.getAngle() > threePiOverTwo) {
//                        p1.rotate(-piOvertwo);
//
//                    } else {
//                        p1.rotate(piOvertwo);
//                    }
//                }
//
//                if(hitRightWall(p1)) {
//                    p1Velocity /= 1.5;
//                    hit = true;
//                    if(p1.getAngle() > threePiOverTwo) {
//                        p1.rotate(piOvertwo);
//
//                    } else {
//                        p1.rotate(-piOvertwo);
//                    }
//                }
//                if(hitLeftWall(p1)) {
//                    p1Velocity /= 1.5;
//                    hit = true;
//                    if(p1.getAngle() > piOvertwo) {
//                        p1.rotate(piOvertwo);
//
//                    } else {
//                        p1.rotate(-piOvertwo);
//                    }
//                }
//                if(hitBottomWall(p2)) {
//                    p2Velocity /= 1.5;
//                    hit = true;
//                    if(p2.getAngle() > pi) {
//                        p2.rotate((((3 * pi) / 2) - p2.getAngle()) + p2.getAngle() - pi);
//
//                    } else {
//                        p2.rotate(-((p2.getAngle() - (pi / 2)) + (pi - p2.getAngle())));
//                    }
//                }
//                if(hitTopWall(p2)) {
//                    p2Velocity /= 1.5;
//                    hit = true;
//                    if(p2.getAngle() < twoPi && p1.getAngle() > threePiOverTwo) {
//                        p2.rotate(-piOvertwo);
//
//                    } else {
//                        p2.rotate(piOvertwo);
//                    }
//                }
//                if(hitRightWall(p2)) {
//                    p2Velocity /= 1.5;
//                    hit = true;
//                    if(p2.getAngle() > threePiOverTwo) {
//                        p2.rotate(piOvertwo);
//
//                    } else {
//                        p2.rotate(-piOvertwo);
//                    }
//                }
//                if(hitLeftWall(p2)) {
//                    p2Velocity /= 1.5;
//                    hit = true;
//                    if(p2.getAngle() > piOvertwo) {
//                        p2.rotate(piOvertwo);
//
//                    } else {
//                        p2.rotate(-piOvertwo);
//                    }
//                }

                //onTrack
//                if(onTrack(p1)) {
//                    maxSpeed = 4;
//                } else {
//                    maxSpeed = 1;
//                }
//                if(onTrack(p2)) {
//                    maxSpeed = 4;
//                } else {
//                    maxSpeed = 1;
//                }

//                if(hit) {
//                    try {
//                        Thread.sleep(500);
//                    } catch(InterruptedException e) {
//                        System.out.println("Exception caught for PlayerMover");
//                    }
//                } else {
//                    try {
//                        Thread.sleep(100);
//                    } catch(InterruptedException e) {
//                        System.out.println("Exception caught for PlayerMover");
//                    }
//                }

            }
        }

//        private boolean onTrack(ImageObject p) {
//            int offTrack = 0;
//
//            for (int i = 0; i < p.getWidth(); i++) {
//                for (int j = 0; j < p.getHeight(); j++) {
//                    int pixelColor = background.getRGB((int)Math.round(p.getX()) + i, (int)Math.round(p.getY() + j));
//                    if(pixelColor != -11711155 && pixelColor != -4118739) {
//                        offTrack++;
//                    }
//                }
//            }
//            //System.out.println(offTrack);
//            if(offTrack > 1500) {
//                return false;
//            }
//            return true;
//        }
//
//        private boolean hitLeftWall(ImageObject p) {
//            for (int i = 0; i < p.getWidth(); i++) {
//                for (int j = 0; j < p.getHeight(); j++) {
//                    if(isInside(p.getX() + i, p.getY() + j, 10, 0, -5,appFrame.getHeight())) {
//                        return true;
//                    }
//                }
//            }
//            return false;        }
//
//        private boolean hitRightWall(ImageObject p) {
//            for (int i = 0; i < p.getWidth(); i++) {
//                for (int j = 0; j < p.getHeight(); j++) {
//                    if(isInside(p.getX() + i, p.getY() + j, appFrame.getWidth() - 5, 0, appFrame.getWidth() + 5,appFrame.getHeight())) {
//                        return true;
//                    }
//                }
//            }
//            return false;        }
//
//        private boolean hitTopWall(ImageObject p) {
//            for (int i = 0; i < p.getWidth(); i++) {
//                for (int j = 0; j < p.getHeight(); j++) {
//                    if(isInside(p.getX() + i, p.getY() + j, 0, -5, appFrame.getWidth(),15)) {
//                        return true;
//                    }
//                }
//            }
//            return false;
//        }
//
//        private boolean hitBottomWall(ImageObject p) {
//            for (int i = 0; i < p.getWidth(); i++) {
//                for (int j = 0; j < p.getHeight(); j++) {
//                    if(isInside(p.getX() + i, p.getY() + j, 1, appFrame.getHeight() - 2, appFrame.getWidth() + 5,appFrame.getHeight() + 5)) {
//                        return true;
//                    }
//                }
//            }
//            return false;
//        }


        private static double changeVelocity(double playerVelocity) {
            playerVelocity -= playerVelocity * 1.2;
            return playerVelocity;
        }
    }

    private static Boolean isInside(double p1x, double p1y, double p2x1, double p2y1, double p2x2, double p2y2)
    {
        Boolean ret = false;
        if(p1x > p2x1 && p1x < p2x2)
        {
            if(p1y > p2y1 && p1y < p2y2)
            {
                ret = true;
            }
            if(p1y > p2y2 && p1y < p2y1)
            {
                ret = true;
            }
        }
        if(p1x > p2x2 && p1x < p2x1)
        {
            if(p1y > p2y1 && p1y < p2y2)
            {
                ret = true;
            }
            if(p1y > p2y2 && p1y < p2y1)
            {
                ret = true;
            }
        }
        return ret;

    }

    private static Boolean collisionOccursCoordinates(double p1x1, double p1y1, double p1x2, double p1y2, double p2x1, double p2y1, double  p2x2, double p2y2)
    {
        Boolean ret = false;
        if(isInside(p1x1, p1y1, p2x1, p2y1, p2x2, p2y2) == true)
        {
            ret = true;
        }
        if(isInside(p1x1, p1y2, p2x1, p2y1, p2x2, p2y2) == true)
        {
            ret = true;
        }
        if(isInside(p1x2, p1y1, p2x1, p2y1, p2x2, p2y2) == true)
        {
            ret = true;
        }
        if(isInside(p1x2, p1y2, p2x1, p2y1, p2x2, p2y2) == true)
        {
            ret = true;
        }
        if(isInside(p2x1, p2y1, p1x1, p1y1, p1x2, p1y2) == true)
        {
            ret = true;
        }
        if(isInside(p2x1, p2y2, p1x1, p1y1, p1x2, p1y2) == true)
        {
            ret = true;
        }
        if(isInside(p2x2, p2y1, p1x1, p1y1, p1x2, p1y2) == true)
        {
            ret = true;
        }
        if(isInside(p2x2, p2y2, p1x1, p1y1, p1x2, p1y2) == true)
        {
            ret = true;
        }
        return ret;

    }

    private static boolean collisionOccurs(ImageObject obj1, ImageObject obj2) {

        System.out.println("P1 x: " + obj1.x + ", P1 y: " + obj1.y);
        System.out.println("P2 x: " + obj2.x + ", P2 y: " + obj2.y);
        System.out.println();
        System.out.println();
        try {
            Thread.sleep(800);
        } catch (InterruptedException ie) {
            System.out.println("ie");
        }

        return collisionOccursCoordinates(obj1.getX(), obj1.getY(), obj1.getX() + obj1.getWidth(),
                obj1.getY() + obj1.getHeight(), obj2.getX(), obj2.getY(), obj2.getX() + obj2.getWidth(),
                obj2.getY() + obj2.getHeight());
    }

    private static class ImageObject {

        private double x;
        private double y;
        private double xWidth;
        private double yHeight;
        private double angle;
        private double internalAngle;
        private ArrayList<Double> coords;
        private ArrayList<Double> triangles;
        private double comX;
        private double comY;

        public ImageObject() {
        }

        public ImageObject(double xInput, double yInput, double xWidthInput, double yHeightInput, double angleInput) {
            x = xInput;
            y = yInput;
            xWidth = xWidthInput;
            yHeight = yHeightInput;
            angle = angleInput;
            internalAngle = 0.0;
            coords = new ArrayList<Double>();
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getWidth() {
            return xWidth;
        }

        public double getHeight() {
            return yHeight;
        }

        public double getAngle() {
            return angle;
        }

        public double getInternalAngle() {
            return internalAngle;
        }

        public void setAngle(double angleInput) {
            angle = angleInput;
        }

        public void setInternalAngle(double interalAngleInput) {
            internalAngle = interalAngleInput;
        }

        public ArrayList<Double> getCoords() {
            return coords;
        }

        public void setCoords(ArrayList<Double> coordsInput) {
            coords = coordsInput;
            generateTriangles();
            //printTriangles();
        }

        public void generateTriangles() {
            triangles = new ArrayList<Double>();
            comX = getComX();
            comY = getComY();

            for (int i = 0; i < coords.size(); i = i + 2) {
                triangles.add(coords.get(i));
                triangles.add(coords.get(i + 1));

                triangles.add(coords.get((i + 2) % coords.size()));
                triangles.add(coords.get((i + 3) % coords.size()));

                triangles.add(comX);
                triangles.add(comY);
            }
        }

        public void printTriangles() {
            for (int i = 0; i < triangles.size(); i = i + 6) {
                System.out.print("p0x: " + triangles.get(i) + ", p0y: " + triangles.get(i + 1));
                System.out.print("p1x: " + triangles.get(i + 2) + ", p1y: " + triangles.get(i + 3));
                System.out.print("p2x: " + triangles.get(i + 4) + ", p2y: " + triangles.get(i + 5));

            }
        }

        public double getComX() {
            double ret = 0;

            if (coords.size() > 0) {
                for (int i = 0; i < coords.size(); i = i + 2) {
                    ret += coords.get(i);
                }
                ret /= (coords.size() / 2.0);
            }
            return ret;
        }

        public double getComY() {
            double ret = 0;

            if (coords.size() > 0) {
                for (int i = 1; i < coords.size(); i = i + 2) {
                    ret += coords.get(i);
                }
                ret /= (coords.size() / 2.0);
            }
            return ret;
        }

        public void move(double xinput, double yinput) {
            x += xinput;
            y += yinput;
        }

        public void moveTo(double xinput, double yinput) {
            x = xinput;
            y = yinput;
        }
        public void screenWrap(double leftEdge, double rightEdge, double topEdge, double bottomEdge) {
            if (x > rightEdge) {
                moveTo(leftEdge, getY());
            }
            if (x < leftEdge) {
                moveTo(rightEdge, getY());
            }
            if (y > bottomEdge) {
                moveTo(getX(), topEdge);
            }
            if (y < topEdge) {
                moveTo(getX(), bottomEdge);
            }
        }

        public void rotate(double angleInput) {
            angle += angleInput;
            while (angle > twoPi) {
                angle -= twoPi;
            }
            while (angle < 0) {
                angle += twoPi;
            }
        }

        public void spin(double internalAngleInput) {
            internalAngle += internalAngleInput;
            while (internalAngle > twoPi) {
                internalAngle -= twoPi;
            }
            while (internalAngle < 0) {
                internalAngle += twoPi;
            }
        }


    }
}