package gravityslick;

import static java.lang.Math.signum;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Player {
    
    private static Player playerInstance = null;
    
    private final int LEFT = -1, RIGHT = 1;
    private float gravity = 0.5f;
    private float speed = 5;
    private float iterations = 20;
    private int dashValue = 10;
    private int score=0;
    private int rotationAngle = 30;
    
    private Shape player;
    private StaticLevel level;

    private float vX = 0;
    private float vY = 0;
    
    private Animation forwardAnimation;
    private Animation backwardAnimation;
    private Animation idleAnimation;
    private Animation idleAnimationLeft;
    private boolean isChangingGravity;
    private boolean rotated = false;
    private static final int WIDTH = 58;
    private static final int HEIGHT = 70;
    private boolean isPaused;
    private boolean isMovingRight = true;
    private int numHearts = 6; // Measured in mid hearts
    private int numVoidHearts = 6; // Measured in mid hearts

    private Player(StaticLevel level) {
        this.level = level;
        this.score = score;
    }
    
    public static Player getPlayerInstance(StaticLevel level) {
        if(playerInstance == null) {
            Player.playerInstance = new Player(level);
            
        }
        return Player.playerInstance;
    }

    /*--------------------
     * Getter methods
     *--------------------*/
    public int getLEFT() {
        return LEFT;
    }

    public int getRIGHT() {
        return RIGHT;
    }
    
    public int getDashValue() {
        return dashValue;
    }
    
    public float getGravity() {
        return gravity;
    }
    
    public float getIterations() {
        return iterations;
    }

    public StaticLevel getLevel() {
        return level;
    }

    public Shape getPlayer() {
        return player;
    }

    public float getSpeed() {
        return speed;
    }

    public float getvX() {
        return vX;
    }

    public float getvY() {    
        return vY;
    }
    
    public boolean isPaused() {
        return isPaused;
    }

    public int getScore() {
        return score;
    }
    
    public int getNumHearts() {
        return numHearts;
    }
    
    /*--------------------
     * Setter Methods 
     *--------------------*/
    public void setDashValue(int dashValue) {
        this.dashValue = dashValue;
    }
    
    public void setGravity(float gravity) {
        this.gravity = gravity;
    }
    
    public void setIterations(float iterations) {
        this.iterations = iterations;
    }
    
    public void setLevel(StaticLevel level) {
        this.level = level;
    }
    
    public void setPlayer(Shape player) {
        this.player = player;
    }
    
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setvX(float vX) {
        this.vX = vX;
    }

    public void setvY(float vY) {    
        this.vY = vY;
    }
    
    public void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }
    
    public void setNumHearts(int numHearts) {
        this.numHearts = numHearts;
    }
    
    /*--------------------
     * Other methods 
     *--------------------*/
    
    /**
     * 
     * @param gc
     * @throws SlickException 
     */
    public void init(GameContainer gc) throws SlickException {
        /*
        I used a shrinkage of 1 pixel in both dimentions to avoid that the
        player is unable to pass through slight parts of the map.
        i.e. passing through a 5x2 tile space could have caused problems 
        */
        player = new Rectangle(200, 200, 29, 59);
        
        // Create the character animation
        Image[] frames = new Image[8];
        frames[0] = new Image("./graphics/png/Run (1).png").getScaledCopy(WIDTH, HEIGHT);
        frames[1] = new Image("./graphics/png/Run (2).png").getScaledCopy(WIDTH, HEIGHT);
        frames[2] = new Image("./graphics/png/Run (3).png").getScaledCopy(WIDTH, HEIGHT);
        frames[3] = new Image("./graphics/png/Run (4).png").getScaledCopy(WIDTH, HEIGHT);
        frames[4] = new Image("./graphics/png/Run (5).png").getScaledCopy(WIDTH, HEIGHT);
        frames[5] = new Image("./graphics/png/Run (6).png").getScaledCopy(WIDTH, HEIGHT);
        frames[6] = new Image("./graphics/png/Run (7).png").getScaledCopy(WIDTH, HEIGHT);
        frames[7] = new Image("./graphics/png/Run (8).png").getScaledCopy(WIDTH, HEIGHT);
        
        // Create the animation for the character moving on the right
        this.forwardAnimation = new Animation(frames, 60);  
        
        for(int i=0; i<frames.length; i++) {
            frames[i] = frames[i].getFlippedCopy(true, false);
        }
        
        // Create the animation for the character moving on the left
        this.backwardAnimation = new Animation(frames, 60);
        
        // Create the animation for the character not moving (idle animation)
        frames = new Image[10];
        frames[0] = new Image("./graphics/png/Idle (1).png").getScaledCopy(WIDTH, HEIGHT);
        frames[1] = new Image("./graphics/png/Idle (2).png").getScaledCopy(WIDTH, HEIGHT);
        frames[2] = new Image("./graphics/png/Idle (3).png").getScaledCopy(WIDTH, HEIGHT);
        frames[3] = new Image("./graphics/png/Idle (4).png").getScaledCopy(WIDTH, HEIGHT);
        frames[4] = new Image("./graphics/png/Idle (5).png").getScaledCopy(WIDTH, HEIGHT);
        frames[5] = new Image("./graphics/png/Idle (6).png").getScaledCopy(WIDTH, HEIGHT);
        frames[6] = new Image("./graphics/png/Idle (7).png").getScaledCopy(WIDTH, HEIGHT);
        frames[7] = new Image("./graphics/png/Idle (8).png").getScaledCopy(WIDTH, HEIGHT);
        frames[8] = new Image("./graphics/png/Idle (9).png").getScaledCopy(WIDTH, HEIGHT);
        frames[9] = new Image("./graphics/png/Idle (10).png").getScaledCopy(WIDTH, HEIGHT);
        
        this.idleAnimation = new Animation(frames, 60);
        
        for(int i=0; i<frames.length; i++) {
            frames[i] = frames[i].getFlippedCopy(true, false);
        }
        
        // Create the animation for the character moving on the left
        this.idleAnimationLeft = new Animation(frames, 60);
    }
    
    /**
     * 
     * @param gc
     * @param delta
     * @throws SlickException 
     */
    public void update(GameContainer gc, int delta) throws SlickException {
        // y acceleration 
        vY += gravity;
        
        // Y movement collisions
        moveAlongY();

        // Gravity check and change
        if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
            gravity = changeGravity(signum(gravity));
        }
        
        //X movement
        moveAlongX(gc.getInput());

        //X collisions
        moveWithCollisionsX();
        
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            gc.pause();
        }
        
        if(this.score >= this.level.getScore()){
            gc.pause();
        }
        
        // Temporary code: used only for graphically testing the damage
        if (gc.getInput().isKeyPressed(Input.KEY_T)) {
            this.getDamaged(1);
        }
        if (gc.getInput().isKeyPressed(Input.KEY_Y)) {
            this.getDamaged(2);
        }
    }
    
    /**
     * Renders the animation and the rotation of the character
     * @param gc
     * @param g
     * @throws SlickException 
     */
    public void render(GameContainer gc, Graphics g) throws SlickException {
        //used to hyde the hitbox
        g.setColor(new Color(0,0,0,0));
        g.fill(player);
        /* 
        Take coordinates have an offset value to make the sprite perfectly
        match the Shape rectangle, that aclually is the hitbox of the player. 
        */
        float X = this.player.getMinX()-14;
        float Y = this.player.getMinY()-5;

        // From this point the code has to be refactored
        if(this.vX > 0){ // The character is moving on the right
            if(!isPaused){
                if(rotated){
                    this.backwardAnimation.draw(X, Y);
                    this.backwardAnimation.start();
                } else {
                    this.forwardAnimation.draw(X, Y);
                    this.forwardAnimation.start();
                }   
            } else {
                if(rotated){
                    this.backwardAnimation.draw(X, Y);
                    this.backwardAnimation.stop();
                } else {
                    this.forwardAnimation.draw(X, Y);
                    this.forwardAnimation.stop();
                }
            }
             
        }
        else if(this.vX < 0){ // The character is moving on the left
            if(!isPaused){
                if(rotated){
                    this.forwardAnimation.draw(X, Y);
                    this.forwardAnimation.start();
                } else {
                    this.backwardAnimation.draw(X, Y);
                    this.backwardAnimation.start(); 
                }
            } else {
                if(rotated){
                    this.forwardAnimation.draw(X, Y);
                    this.forwardAnimation.stop();
                } else {
                    this.backwardAnimation.draw(X, Y);
                    this.backwardAnimation.stop(); 
                }
            }
        }
        else { // The character doesn't move
            if(!isPaused){
                if(isMovingRight){
                    if(rotated){
                        /*
                        The +2 and -2 are used to make the idle in left and 
                        right position match the position of the center axis
                        if the character. That's caused by a misallignement of
                        the character in the sprite.
                        */
                        this.idleAnimationLeft.draw(X+2,Y);
                        this.idleAnimationLeft.start();
                    } else {
                        this.idleAnimation.draw(X+2,Y);
                        this.idleAnimation.start();
                    }
                } else {
                    if(rotated){
                        this.idleAnimation.draw(X-2,Y);
                        this.idleAnimation.start();
                    } else {
                        this.idleAnimationLeft.draw(X-2,Y);
                        this.idleAnimationLeft.start();
                    }
                }
            } else {

                if(isMovingRight){
                        if(rotated){
                            this.idleAnimationLeft.draw(X,Y);
                            this.idleAnimationLeft.stop();
                        } else {
                            this.idleAnimation.draw(X,Y);
                            this.idleAnimation.stop();
                        }
                    } else {
                        if(rotated){
                            this.idleAnimation.draw(X,Y);
                            this.idleAnimation.stop();
                        } else {
                            this.idleAnimationLeft.draw(X,Y);
                            this.idleAnimationLeft.stop();
                        }
                    }
                }
        }
        
        if(this.isChangingGravity) {
            this.rotate();
        }
        
        this.drawHearts(35);
    }
    
    /**
     * Changes the gravity and checks if there is an obstacle during the transition
     * If there is an obstacle the transition stops
     * @param sign
     * @return 
     */
    public float changeGravity(float sign) {

        player.setY(player.getY() + sign * 0.5f);
        this.isChangingGravity = true;
        this.rotated = !this.rotated;
        player.setY(player.getY() - sign * 0.5f);
        return -gravity;
    }
    
    /**
     * 
     */
    private void moveWithCollisionsX() {
        // X movement collisions

        float vXtemp = vX / iterations;

        for (int t = 0; t < iterations; t++) {
            player.setX(player.getX() + vXtemp);
            if (this.collidesWith(level.getRtl())) {
                player.setX(player.getX() - vXtemp);
                vX = 0;
            }
        }
    }
    
    /**
     * Manages the movement on the Y axis.
     * Called only when the gravity gets changed.
     */
    private void moveAlongY() {
        float vYtemp = vY / iterations;
        
        for (int t = 0; t < iterations; t++) {
            player.setY(player.getY() + vYtemp);
            if (this.collidesWith(level.getRtl())) {
                player.setY(player.getY() - vYtemp);
                vY = 0;
            }
        }
    }
    
    /**
     * Manages the movement on the X axis. 
     * @param in the key button we have pressed
     */
    private void moveAlongX(Input in) {
        
        // If we hold down A, the character will move to the left
        if (in.isKeyDown(Input.KEY_A)) {
            vX = -speed;
            this.isMovingRight = false;
            if (in.isKeyPressed(Input.KEY_LSHIFT)) {
                dash(LEFT);
            }
        // If we hold down D, the character will move to the right
        } else if (in.isKeyDown(Input.KEY_D)) {
            vX = speed;
            this.isMovingRight = true;
            if (in.isKeyPressed(Input.KEY_LSHIFT)) {
                dash(RIGHT);
            }
        // The character doesn't move 
        } else {
            vX = 0;
        }
    }
    
    /**
     * The character executes a dash in the specified direction
     * with a certain speed
     * @param dir the direction of the dash (1 for right, -1 for left)
     */
    public void dash(int dir) {
        vX = dir * dashValue * speed;
    }

    /**
     * This method has to detect the collisions of the character
     * and the map's objects.
     * @param objects
     * @return 
     */
    public boolean collidesWith(ArrayList<Shape> objects){
        for(int i = 0; i < objects.size(); i++){
            if(this.player.intersects(objects.get(i))){
                if(i==objects.size()-1 && !(this.score > this.level.getScore())){
                    this.score++;
                    if(this.score < this.level.getScore()){
                        Random random = new Random();
                        objects.get(i).setCenterX((float) random.nextInt(960-300)+300);
                        objects.get(i).setCenterY((float) random.nextInt(720-300)+300);
                    }
                }
                else{
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Manages the rotation of the character. (Has to be refactored)
     */
    public void rotate(){
        for(int i=0; i<10; i++){
            Image currentImage = this.idleAnimation.getImage(i);
            if(rotated && currentImage.getRotation() != 180){
                this.idleAnimation.getImage(i).rotate(this.rotationAngle);
                this.idleAnimationLeft.getImage(i).rotate(this.rotationAngle);
                if(i<8){
                    this.forwardAnimation.getImage(i).rotate(this.rotationAngle);
                    this.backwardAnimation.getImage(i).rotate(this.rotationAngle);
                }
            } 
            if(!rotated && currentImage.getRotation() != 0){
                this.idleAnimation.getImage(i).rotate(this.rotationAngle);
                this.idleAnimationLeft.getImage(i).rotate(this.rotationAngle);
                if(i<8){
                    this.forwardAnimation.getImage(i).rotate(this.rotationAngle);
                    this.backwardAnimation.getImage(i).rotate(this.rotationAngle);
                }
            }
        }
    }
    
    /**
     * Draw the hearts that represent the current life of the character
     * @dim the dimension of the hearts in pixels
     * @throws SlickException 
     */
    public void drawHearts(int dim) throws SlickException{
        SpriteSheet hearts = new SpriteSheet("./graphics/png/hearts.png", 300, 300); // Must be modified after the image modification        
        hearts.startUse();
        int i;
        for(i = 0; i<this.numVoidHearts/2; i++){
            hearts.getSubImage(2, 0).getScaledCopy(dim, dim).draw(40*i, 0);
        }
        for(i = 0; i<this.numHearts/2 ; i++){
            hearts.getSprite(0, 0).getScaledCopy(dim, dim).draw(40*i, 0);
        }
        if(this.numHearts - 2*i > 0){
            hearts.getSubImage(1,0).getScaledCopy(dim, dim).draw(40*i, 0);
        }
        hearts.endUse();
    }
    
    /**
     * The number of mid hearts to subtract
     * @param points 
     */
    public void getDamaged(int points){
        this.numHearts -= points;
    }
}
