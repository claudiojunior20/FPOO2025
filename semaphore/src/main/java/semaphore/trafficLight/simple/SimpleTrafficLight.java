package semaphore.trafficLight.simple;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import semaphore.light.e27.E27LightBulb;
import semaphore.spot.SpotLight;
import semaphore.util.gui.Paintable;

import java.awt.Image;

public class SimpleTrafficLight implements Paintable{
    private Point position = new Point(0,0);
    private Dimension dimension = new Dimension(70,180);
    private Image mask;
    private SpotLight yellow;
    private SpotLight green;
    private SpotLight red;
    
    private String currentRelativePath() {
    	
    	return "/" +
    			this.getClass()
    			.getPackageName()
    			.toString()
    			.replace('.', '/') + 
    			"/";
    }
    
    private SpotLight createSpot( String color) throws IOException {
    	
    	final String path = currentRelativePath() +"img/";
    	URL url;
    	
    	url = getClass().getResource(path + color +"On.png");
    	Image maskOn = ImageIO.read(url);
    	
    	url = getClass().getResource(path + color +"Off.png");
    	Image mask0ff = ImageIO.read( url);;
    	
		SpotLight spot = new SpotLight(maskOn, mask0ff);
    	
    	return spot;
    }
    
    private void create() throws IOException{
    	this.green = createSpot("green");
    	this.green.setLight(new E27LightBulb());
    	
    	this.yellow = createSpot("yellow");
    	this.yellow.setLight(new E27LightBulb());
    	
    	this.red = createSpot("red");
    	this.red.setLight (new E27LightBulb());
    	
    	String path = this.currentRelativePath();
    	URL url = this.getClass().getResource(path + "img/trafficLight.png");
    	this.mask = ImageIO.read(url);
    }
    
    public SimpleTrafficLight() throws IOException {
    	
       this.create();
       this.configurePositions();

    }
    
    private void configurePositions() {
    	final int WIDTH = 50;
    	final int HEIGHT = 180;
    	final Dimension dimension = new Dimension(WIDTH, HEIGHT);
    	
    	int xLeft = this.position.x + 10;
    	int yTop = this.position.y + 10;
    	this.green.setPosition(xLeft, yTop);
    	this.green.setDimension(dimension);
    	
    	yTop = ( yTop + 5 + HEIGHT );
    	this.yellow.setPosition(xLeft, yTop);
    	this.yellow.setDimension(dimension);
    	
    	yTop = ( yTop + 5 + HEIGHT );
    	this.red.setPosition(xLeft, yTop);
    	this.red.setDimension(dimension);
    }
    
    public SimpleTrafficLight(Point position, Dimension dimension) throws IOException {
        this.position = position;
        this.dimension = dimension;
        
        this.create();
        this.configurePositions();

    }
    
    
    public void setPosition(Point position) {
    	this.position = new Point(position);
    }
    
    public Point getPosition() {
    	return new Point(this.position);

    }
    
    public void setDimension(Dimension dimension) {
    	this.dimension = new Dimension(dimension);
    }
    
    public Dimension getDimension() {
    	return new Dimension(this.dimension);
    }

	@Override
	public void paint(Graphics g) {
		
		synchronized(g) {
			int xLeft = this .position.x;
			int yTop = this .position.y;
			int width = this.dimension.width;
			int height = this.dimension.height;
			
			g.drawImage(mask, xLeft, yTop, width, height, null);
			
			this.green.paint(g);
			this.yellow.paint(g);
			this.red.paint(g);
		}
		
	}
    
}