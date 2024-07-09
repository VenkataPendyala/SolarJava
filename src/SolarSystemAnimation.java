import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolarSystemAnimation extends JPanel {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private static final int SUN_RADIUS = 20;
    private static final int PLANET_RADIUS = 10;

    // Define planet properties (arbitrary units)
	private static final double MERCURY_ORBITAL_RADIUS = 100;
	private static final double MERCURY_ANGULAR_VELOCITY = 0.4127; // Adjust as needed
	private static final double VENUS_ORBITAL_RADIUS = 150;
	private static final double VENUS_ANGULAR_VELOCITY = 0.162; // Adjust as needed
    private static final double EARTH_ORBITAL_RADIUS = 200;
    private static final double EARTH_ANGULAR_VELOCITY = 0.1; // Adjust as needed
    private static final double MARS_ORBITAL_RADIUS = 270;
    private static final double MARS_ANGULAR_VELOCITY = 0.08; // Adjust as needed

    private double earthAngle = 0;
    private double marsAngle = 0;
	private double venusAngle = 0;
	private double mercuryAngle = 0;

    public SolarSystemAnimation() {
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update planet positions
                earthAngle += EARTH_ANGULAR_VELOCITY;
                marsAngle += MARS_ANGULAR_VELOCITY;
				venusAngle += VENUS_ANGULAR_VELOCITY;
				mercuryAngle += MERCURY_ANGULAR_VELOCITY;
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw Sun
        g.setColor(Color.YELLOW);
        g.fillOval(WIDTH / 2 - SUN_RADIUS, HEIGHT / 2 - SUN_RADIUS, 2 * SUN_RADIUS, 2 * SUN_RADIUS);

        // Draw Earth
        drawPlanet(g, EARTH_ORBITAL_RADIUS, earthAngle, Color.BLUE);

        // Draw Mars
        drawPlanet(g, MARS_ORBITAL_RADIUS, marsAngle, Color.RED);
		
		// Draw Venus
		drawPlanet(g, VENUS_ORBITAL_RADIUS, venusAngle, Color.RED);
		
		// Draw Mercury
		drawPlanet(g, MERCURY_ORBITAL_RADIUS, mercuryAngle, Color.GRAY);
    }

    private void drawPlanet(Graphics g, double orbitalRadius, double angle, Color color) {
        int x = (int) (WIDTH / 2 + orbitalRadius * Math.cos(angle));
        int y = (int) (HEIGHT / 2 + orbitalRadius * Math.sin(angle));
        g.setColor(color);
        g.fillOval(x - PLANET_RADIUS, y - PLANET_RADIUS, 2 * PLANET_RADIUS, 2 * PLANET_RADIUS);
    }
	

    public static void main(String[] args) {
        JFrame frame = new JFrame("Solar System Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(new SolarSystemAnimation());
        frame.setVisible(true);
    }
}
