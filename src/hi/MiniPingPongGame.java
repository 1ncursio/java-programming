package hi;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.*;
import java.awt.event.*;

public class MiniPingPongGame extends JPanel implements Runnable {
    private static final long serialVersionUID = 1L;

    private Score score;
    private Ball ball;
    private GamePaddle paddle1;
    private GamePaddle paddle2;

    public MiniPingPongGame() {
        ball = new Ball(this, Color.red);
        score = new Score(getWidth(), getHeight());
        this.setBackground(Color.black);
        paddle1 = new GamePaddle(10, 150, 10, 80, 1);
        paddle2 = new GamePaddle(560, 150, 10, 80, 2);
        this.setFocusable(true);
    }

    void move() {
        ball.move();
        paddle1.move();
        paddle2.move();
    }

    void checkCollision() {
        if (ball.y + ball.ySpeed < 0) {
            ball.ySpeed = 3;
            System.out.println("위 닿음");
        }
        if (ball.y + ball.ySpeed > this.getHeight() - 2 * Ball.RADIUS) {
            System.out.println("밑 닿음");
            ball.ySpeed = -3;
        }
        if (ball.x + ball.xSpeed > this.getWidth() - 2 * Ball.RADIUS) {
            System.out.println("오른쪽 닿음");
            // score
            score.player2 += 1;
            ball.xSpeed = -3;
        }
        if (ball.x + ball.xSpeed < 0) {
            score.player1 += 1;
            System.out.println("왼쪽 닿음");
            ball.xSpeed = 3;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        ball.draw(g2d);
        paddle1.draw(g2d);
        paddle2.draw(g2d);
        score.draw(g2d);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            try {
                Thread.sleep(10);
                move();
                checkCollision();
                repaint();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PingPong Game");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MiniPingPongGame game = new MiniPingPongGame();
        frame.add(game);
        frame.setVisible(true);
        Thread thread = new Thread(game);
        thread.start();
    }

    class Ball {
        private static final int RADIUS = 20;
        private int x = 0, y = 0, xSpeed = 3, ySpeed = 3;
        private MiniPingPongGame game;
        private Color color;

        public Ball(MiniPingPongGame game, Color color) {
            this.game = game;
            this.color = color;
        }

        void move() {
            if (x + xSpeed < 0)
                xSpeed = 3;
            if (x + xSpeed > game.getWidth() - 2 * RADIUS)
                xSpeed = -3;
            if (y + ySpeed < 0)
                ySpeed = 3;
            if (y + ySpeed > game.getHeight() - 2 * RADIUS)
                ySpeed = -3;
            if (collision()) {
                xSpeed = -xSpeed;
                System.out.println("collision");

            }
            x += xSpeed;
            y += ySpeed;
        }

        private boolean collision() {
            return game.paddle1.getBounds().intersects(getBounds()) || game.paddle2.getBounds().intersects(getBounds());
        }

        public void draw(Graphics2D g) {
            g.setColor(color);
            g.fillOval(x, y, 2 * RADIUS, 2 * RADIUS);
        }

        public Rectangle getBounds() {
            return new Rectangle(x, y, 2 * RADIUS, 2 * RADIUS);
        }
    }

    public class Score {
        private int GAME_WIDTH;
        private int GAME_HEIGHT;
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

        private MiniPingPongGame game;
        private int x, y, width, height;
        private int ySpeed = 0;
        private int id;

        GamePaddle(int x, int y, int width, int height, int id) {
            super(x, y, width, height);
            this.x = x;
            this.y = y;
            this.id = id;
            this.width = width;
            this.height = height;
            addKeyListener(this);
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (this.id == 1) {
                if (e.getKeyCode() == KeyEvent.VK_W)
                    ySpeed = -3;
                else if (e.getKeyCode() == KeyEvent.VK_S)
                    ySpeed = 3;

            } else if (this.id == 2) {
                if (e.getKeyCode() == KeyEvent.VK_UP)
                    ySpeed = -3;
                else if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    ySpeed = 3;

            }
            System.out.println(e.getKeyCode());
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
            if (y + ySpeed > 0 && y + ySpeed < 400 - HEIGHT)
                y += ySpeed;
        }

        void draw(Graphics2D g) {
            if (this.id == 1)
                g.setColor(Color.blue);
            else if (this.id == 2)
                g.setColor(Color.red);
            g.fillRect(this.x, this.y, this.width, this.height);

            // id가 1이면 파란색 지정
            // id가 2이면 빨간색 지정
            // x, y 좌표에 w, h 만큼 사각형을 채운다.
        }

        public Rectangle getBounds() {
            return new Rectangle(x, y, WIDTH, HEIGHT);
        }
    }

}
