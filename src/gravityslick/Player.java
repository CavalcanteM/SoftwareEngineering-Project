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
    
    private Shape player;
    private Shape rwd;
    private StaticLevel level;

    private float vX = 0;
    private float vY = 0;
    
    private Animation forwardAnimation;
    private Animation backwardAnimation;
    private Animation idleAnimation;
    private Animation idleAnimationLeft;
    private Animation deathAnimation;
    private Animation deathAnimationLeft;
    private boolean isChangingGravity;
    private boolean rotated = false;
    private static final int WIDTH = 58;
    private static final int HEIGHT = 70;
    private boolean isPaused;
    private boolean isMovingRight = true;
    private boolean isDead;
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

    public int getNumVoidHearts() {
        return numVoidHearts;
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
    
    public void setNumVoidHearts(int numVoidHearts) {
        this.numVoidHearts = numVoidHearts;
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
        
        // Create the animations for character moving on both the right and the left
        Image[] frames = new Image[8];
        this.forwardAnimation = new Animation(); 
        this.backwardAnimation = new Animation();
        for(int i=0; i<frames.length; i++) {
            frames[i] = new Image("./graphics/png/Run (" + (i+1) + ").png").getScaledCopy(WIDTH, HEIGHT);  
            // Adding current image to animation for moving to the right
            this.forwardAnimation.addFrame(frames[i], 60);
            // Flip and add current image to animation for moving to the left
            frames[i] = frames[i].getFlippedCopy(true, false);
            this.backwardAnimation.addFrame(frames[i], 60);
        }
        
        // Create the animations for character not moving (idle animation)
        frames = new Image[10];
        this.idleAnimation = new Animation();
        this.idleAnimationLeft = new Animation();
        for(int i=0; i<frames.length; i++) {
            frames[i] = new Image("./graphics/png/Idle (" + (i+1) + ").png").getScaledCopy(WIDTH, HEIGHT);
            // Adding current image to animation idle looking to the right
            this.idleAnimation.addFrame(frames[i], 60);
            // Flip and add current image to the animation idle looking to the left
            frames[i] = frames[i].getFlippedCopy(true, false);
            this.idleAnimationLeft.addFrame(frames[i], 60);
        }
        
        // Create the animation for the character dying
        this.deathAnimation = new Animation();
        this.deathAnimationLeft = new Animation();
        for(int i = 0; i < frames.length; i++) {
            frames[i] = new Image("./graphics/png/Dead (" + (i+1) + ").png").getScaledCopy(WIDTH, HEIGHT);
            // Adding current image to animation death falling to the right
            this.deathAnimation.addFrame(frames[i], 60);
            // Flip and add current image to the animation death falling to the left
            frames[i] = frames[i].getFlippedCopy(true, false);
            this.deathAnimationLeft.addFrame(frames[i], 60);
        }
        
        this.rwd = level.getPts().iterator().next();
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
        
        getReward();
        
        // Temporary code: used only for graphically testing the damage
//        if(isDead) {
//            System.out.println("You are dead!");
//            this.isDead = false;
//        }
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
//        if(this.vX > 0){ // The character is moving on the right
//            if(!isPaused){
//                if(rotated){
//                    this.backwardAnimation.draw(X, Y);
//                    this.backwardAnimation.start();
//                } else {
//                    this.forwardAnimation.draw(X, Y);
//                    this.forwardAnimation.start();
//                }   
//            } else {
//                if(rotated){
//                    this.backwardAnimation.draw(X, Y);
//                    this.backwardAnimation.stop();
//                } else {
//                    this.forwardAnimation.draw(X, Y);
//                    this.forwardAnimation.stop();
//                }
//            }
//             
//        }
//        else if(this.vX < 0){ // The character is moving on the left
//            if(!isPaused){
//                if(rotated){
//                    this.forwardAnimation.draw(X, Y);
//                    this.forwardAnimation.start();
//                } else {
//                    this.backwardAnimation.draw(X, Y);
//                    this.backwardAnimation.start(); 
//                }
//            } else {
//                if(rotated){
//                    this.forwardAnimation.draw(X, Y);
//                    this.forwardAnimation.stop();
//                } else {
//                    this.backwardAnimation.draw(X, Y);
//                    this.backwardAnimation.stop(); 
//                }
//            }
//        }
//        else { 
        if(this.vX == 0) {// The character doesn't move
            if(!isPaused){             
                if(isMovingRight){
                    if(rotated){
                        if(isDead && !this.deathAnimationLeft.isStopped()){ // You can't die when the game is in pause
                            this.deathAnimationLeft.draw(X, Y);
                            this.deathAnimationLeft.stopAt(this.deathAnimationLeft.getFrameCount()-1);
                            this.deathAnimationLeft.start();
                        } else {
                            /*
                            The +2 and -2 are used to make the idle in left and 
                            right position match the position of the center axis
                            if the character. That's caused by a misallignement of
                            the character in the sprite.
                            */
                            this.idleAnimationLeft.draw(X+2,Y);
                            this.idleAnimationLeft.start();
                        }
                        
                    } else {
                        if(isDead && !this.deathAnimation.isStopped()){ // You can't die when the game is in pause
                            this.deathAnimation.draw(X, Y);
                            this.deathAnimation.stopAt(this.deathAnimation.getFrameCount()-1);
                            this.deathAnimation.start();
                        } else {
                            this.idleAnimation.draw(X+2,Y);
                            this.idleAnimation.start();
                        }
                    }
                } else {
                    if(rotated){
                        if(isDead && !this.deathAnimation.isStopped()){ // You can't die when the game is in pause
                            this.deathAnimation.draw(X, Y);
                            this.deathAnimation.stopAt(this.deathAnimation.getFrameCount()-1);
                            this.deathAnimation.start();
                        } else {
                            this.idleAnimation.draw(X-2,Y);
                            this.idleAnimation.start();
                        }    
                    } else {
                        if(isDead && !this.deathAnimationLeft.isStopped()){ // You can't die when the game is in pause
                            this.deathAnimationLeft.draw(X, Y);
                            this.deathAnimationLeft.stopAt(this.deathAnimationLeft.getFrameCount()-1);
                            this.deathAnimationLeft.start();
                        } else {
                            this.idleAnimationLeft.draw(X-2,Y);
                            this.idleAnimationLeft.start();
                        }   
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
        
        if((this.vX > 0 && !rotated) || (this.vX < 0 && rotated)) {
            this.forwardAnimation.draw(X, Y);
            if(!isPaused){ 
                this.forwardAnimation.start(); 
            } else {
                this.forwardAnimation.stop();
            }
        }
            
        if((this.vX > 0 && rotated) || (this.vX < 0 && !rotated)) {
            this.backwardAnimation.draw(X, Y);
            if(!isPaused){
                this.backwardAnimation.start();
            } else {
                this.backwardAnimation.stop();
            }
        }
        
        
        if(this.isChangingGravity) {
            this.rotate(30);
        }
        
        this.drawHearts(35, 30);
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
                return true;
            }
        }
        return false;
    }
    
    public void getReward(){
        if(this.player.intersects(this.rwd)){
            //this.score++;
            this.rwd = level.getPts().iterator().next();
        }
    }
    
    /**
     * Manages the rotation of the character. (Has to be refactored)
     * @param angle the rotation angle
     */
    public void rotate(int angle){
        for(int i=0; i<10; i++){
            Image currentImage = this.idleAnimation.getImage(i);
            /* The character rotates if the gravity is changed but it's 180 rotation
             * is not yet completed
            */
            if((rotated && currentImage.getRotation() != 180) || (!rotated && currentImage.getRotation() != 0)){
                this.idleAnimation.getImage(i).rotate(angle);
                this.idleAnimationLeft.getImage(i).rotate(angle);
                this.deathAnimation.getImage(i).rotate(angle);
                this.deathAnimationLeft.getImage(i).rotate(angle);
                if(i<8){
                    this.forwardAnimation.getImage(i).rotate(angle);
                    this.backwardAnimation.getImage(i).rotate(angle);
                }
            }
        }
    }
    
    /**
     * Draw the hearts that represent the current life of the character
     * @param dim1 the width of the hearts in pixels
     * @param dim2 the height of the hearts in pixels
     * @throws SlickException 
     */
    public void drawHearts(int dim1, int dim2) throws SlickException{
        SpriteSheet hearts = new SpriteSheet("./graphics/png/hearts.png", 265, 231); // Must be modified after the image is changed        
        hearts.startUse();
        int i;
        
        // Draws the void hearts
        for(i = 0; i<this.numVoidHearts/2; i++){
            hearts.getSprite(2, 0).getScaledCopy(dim1, dim2).draw((dim1+2)*i, 0);
        }
        // Draws the full hearts
        for(i = 0; i<this.numHearts/2 ; i++){
            hearts.getSprite(0, 0).getScaledCopy(dim1, dim2).draw((dim1+2)*i, 0);
        }
        // Draws the mid hearts
        if(this.numHearts - 2*i > 0){
            hearts.getSprite(1,0).getScaledCopy(dim1, dim2).draw((dim1+2)*i, 0);
        }
        hearts.endUse();
    }
    
    /**
     * Manages the damage on the character
     * @param points The number of mid hearts to subtract
     */
    public void getDamaged(int points){
        this.numHearts -= points;
        if(this.numHearts <= 0) {
            this.isDead = true;
            this.vX = 0;
        }
    }
}
