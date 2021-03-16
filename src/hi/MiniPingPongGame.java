package hi;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.*;
import java.awt.event.*;

public class MiniPingPongGame extends JPanel implements KeyListener {
    private static final long serialVersionUID = 1L;

    private Ball ball;
    private Racquet racquet1;
    private Racquet racquet2;

    public MiniPingPongGame() {
        ball = new Ball(this, Color.red);
        this.setBackground(Color.green);
        racquet1 = new Racquet(this, 10, 150, Color.blue);
        racquet2 = new Racquet(this, 560, 150, Color.yellow);
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        racquet1.keyPressed(e);
        racquet2.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        racquet1.keyReleased(e);
        racquet2.keyReleased(e);
    }

    void move() {
        ball.move();
        racquet1.move();
        racquet2.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        ball.draw(g2d);
        racquet1.draw(g2d);
        racquet2.draw(g2d);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PingPong Game");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MiniPingPongGame game = new MiniPingPongGame();
        frame.add(game);
        frame.setVisible(true);
        while (true) {
            game.move();
            game.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    class Ball {
        private static final int RADIUS = 20;
        private int x = 0, y = 0, xSpeed = 1, ySpeed = 1;
        private MiniPingPongGame game;
        private Color color;

        public Ball(MiniPingPongGame game, Color color) {
            this.game = game;
            this.color = color;
        }

        void move() {
            if (x + xSpeed < 0)
                xSpeed = 1;
            if (x + xSpeed > game.getWidth() - 2 * RADIUS)
                xSpeed = -1;
            if (y + ySpeed < 0)
                ySpeed = 1;
            if (y + ySpeed > game.getHeight() - 2 * RADIUS)
                ySpeed = -1;
            x += xSpeed;
            y += ySpeed;
        }

        private boolean collision() {
            return game.racquet1.getBounds().intersects(getBounds())
                    || game.racquet2.getBounds().intersects(getBounds());
        }

        public void draw(Graphics2D g) {
            g.setColor(color);
            g.fillOval(x, y, 2 * RADIUS, 2 * RADIUS);
        }

        public Rectangle getBounds() {
            return new Rectangle(x, y, 2 * RADIUS, 2 * RADIUS);
        }
    }

    class Racquet {
        private static final int WIDTH = 10;
        private static final int HEIGHT = 80;
        private int x = 0, y = 0;
        private int xSpeed = 0;
        private int ySpeed = 0;
        private MiniPingPongGame game;
        private Color color;

        public Racquet(MiniPingPongGame game, int x, int y, Color color) {
            this.game = game;
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public void move() {
            if (y + ySpeed > 0 && y + ySpeed < game.getHeight() - HEIGHT)
                y += ySpeed;
        }

        public void draw(Graphics2D g) {
            g.setColor(color);
            g.fillRect(x, y, WIDTH, HEIGHT);
        }

        public void keyReleased(KeyEvent e) {
            ySpeed = 0;
        }

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP)
                ySpeed = -3;
            else if (e.getKeyCode() == KeyEvent.VK_DOWN)
                ySpeed = 3;

        }

        public Rectangle getBounds() {
            return new Rectangle(x, y, WIDTH, HEIGHT);
        }
    }

    public class Score {
        private static int GAME_WIDTH;
        private static int GAME_HEIGHT;
        protected int player1;
        protected int player2;

        public Score(int gameWidth, int gameHeight) {
            GAME_WIDTH = gameWidth;
            GAME_HEIGHT = gameHeight;
        }

        public void draw(Graphics g) {
            g.setColor(Color.WHITE);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));

            g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);

            g.drawString(String.valueOf(player1 / 10) + String.valueOf(player1 % 10), GAME_WIDTH / 2 - 85, 50);
            g.drawString(String.valueOf(player2 / 10) + String.valueOf(player2 % 10), GAME_WIDTH / 2 + 20, 50);
        }
    }

    class GamePaddle extends Rectangle implements KeyListener {
        private static final long serialVersionUID = 1L;

        private int x = 0, y = 0;
        private int xSpeed = 0;
        private int ySpeed = 0;
        private int id;

        GamePaddle(int x, int y, int width, int height, int id) {
            super(x, y, width, height);
            this.id = id;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void keyPressed(KeyEvent e) {
            // id가 1이면 :
            // W, S 에 따라 위 아래로 좌표 설정
            // id가 2이면 :
            // 위화살표, 아래 화살표에 따라 위 아래로 좌표 설정
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (this.id == 1)
                this.ySpeed = 0;
            else if (this.id == 2)
                this.ySpeed = 0;
            // id가 1 이면 y방향 속도 0
            // id가 2이면 y 방향 속도 0
        }

        void move() {
            // y방향 속도만큼 y좌표 변경
        }

        void draw() {
            // id가 1이면 파란색 지정
            // id가 2이면 빨간색 지정
            // x, y 좌표에 w, h 만큼 사각형을 채운다.
        }
    }

}
