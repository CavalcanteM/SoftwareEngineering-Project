package IsaacMain;

import IsaacMain.Upgrades.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import static java.lang.Math.signum;
import java.util.HashMap;
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

public class Player implements UpgradeComponent{
    
    private static Player playerInstance = null;
    private final int LEFT = -1, RIGHT = 1;
    private float gravity = 0.5f;
    private float speed = 5;
    private float iterations = 20;
    private int dashValue = 10;
    private Shape hitbox;
    private float vX = 0;
    private float vY = 0;
    private float speedUp = 2;
    private boolean shield = false;
    private Animation rightAnimation;
    private Animation leftAnimation;
    private Animation idleAnimationRight;
    private Animation idleAnimationLeft;
    private Animation deathAnimationRight;
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
    private CollisionManager collision;
    private long lastHitTime = System.currentTimeMillis() - 3000;
    private HashMap<String, Integer> commands;
    
    private Player() {
    }
    
    public static Player getPlayerInstance() {
        if(playerInstance == null) {
            Player.playerInstance = new Player();
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

    public Shape getPlayer() {
        return hitbox;
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

    @Override
    public int getNumHearts() {
        return numHearts;
    }

    public int getNumVoidHearts() {
        return numVoidHearts;
    }

    public HashMap<String, Integer> getCommands() {
        return commands;
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
    
    public void setPlayer(Shape hitbox) {
        this.hitbox = hitbox;
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

    @Override
    public void setSpeedUp(float speedUp) {
        this.speedUp = speedUp;
    }
    
    @Override
    public void setShield(boolean shield){
        this.shield = shield;
    }
    
    public void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }
    
    @Override
    public void setNumHearts(int numHearts) {
        this.numHearts = numHearts;
    }
    
    @Override
    public void setNumVoidHearts(int numVoidHearts) {
        this.numVoidHearts = numVoidHearts;
    }
    
    public void setCollisionManager(CollisionManager collision){
        this.collision=collision;
    }
    /*--------------------
     * Other methods 
     *--------------------*/
    
    /**
     * 
     * @param gc
     * @throws SlickException 
     */
    @Override
    public void init(GameContainer gc) throws SlickException {
        /*
        I used a shrinkage of 1 pixel in both dimentions to avoid that the
        Hitbox is unable to pass through slight parts of the map.
        i.e. passing through a 5x2 tile space could have caused problems.
        The 2 values 30, 720-90, are the spawn point of the character.
        */
        
        hitbox = new Rectangle(31, gc.getHeight()-90, 29, 59);
        
        // Create the animations for character moving on both the right and the left
        Image[] frames = new Image[8];
        this.rightAnimation = new Animation(); 
        this.leftAnimation =  new Animation();
        for(int i=0; i<frames.length; i++)
        {
            // Adding current image to animation for moving to the right
            frames[i] = new Image("./graphics/png/Run (" + (i+1) + ").png").getScaledCopy(WIDTH, HEIGHT);  
            this.rightAnimation.addFrame(frames[i], 60);
            // Flip and add current image to animation for moving to the left
            frames[i] = frames[i].getFlippedCopy(true, false);
            this.leftAnimation.addFrame(frames[i], 60);
        }
        
        // Create the animations for character not moving (idle animation)
        frames = new Image[10];
        this.idleAnimationRight = new Animation();
        this.idleAnimationLeft =  new Animation();
        for(int i=0; i<frames.length; i++) 
        {
            // Adding current image to animation idle looking to the right
            frames[i] = new Image("./graphics/png/Idle (" + (i+1) + ").png").getScaledCopy(WIDTH, HEIGHT);
            this.idleAnimationRight.addFrame(frames[i], 60);
            // Flip and add current image to the animation idle looking to the left
            frames[i] = frames[i].getFlippedCopy(true, false);
            this.idleAnimationLeft.addFrame(frames[i], 60);
        }
        
        // Create the animation for the character dying
        this.deathAnimationRight = new Animation();
        this.deathAnimationLeft =  new Animation();
        for(int i = 0; i < frames.length; i++)
        {
            // Adding current image to animation death falling to the right
            frames[i] = new Image("./graphics/png/Dead (" + (i+1) + ").png").getScaledCopy(WIDTH, HEIGHT);
            this.deathAnimationRight.addFrame(frames[i], 60);
            // Flip and add current image to the animation death falling to the left
            frames[i] = frames[i].getFlippedCopy(true, false);
            this.deathAnimationLeft.addFrame(frames[i], 60);
        }
        
        // Loads the current set of commands from a file
        this.initCommandList();
    }
    
    /**
     * 
     * @param gc
     * @param delta
     * @throws SlickException 
     */
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
       
         // Gravity check and change
        if (gc.getInput().isKeyPressed(commands.get("gravity"))) {
            gravity = changeGravity(signum(gravity));
        }        

        // y acceleration 
        vY += gravity;
        
        // Y movement collisions
        moveAlongY();
        
        //X movement
        set_speedx(gc.getInput());

        //X collisions
        moveWithCollisionsX();
        
        
        
        
        
        /* Temporary code: used only for graphically testing the damage
        */
        if (gc.getInput().isKeyPressed(Input.KEY_R)) {
            this.resetStats();
        }
        
    }
    
    /**
     * Renders the animation and the rotation of the character
     * @param gc
     * @param g
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        
        //used to hyde the hitbox
        hideHitbox(g);
        /* 
        Take coordinates have an offset value to make the sprite perfectly
        match the Shape rectangle, that aclually is the hitbox of the Hitbox. 
        */
        float X = this.hitbox.getMinX()-14;
        float Y = this.hitbox.getMinY()-5;
        
        if(this.vX ==0){//The character doesn't move
            if((isMovingRight && !rotated) || (!isMovingRight && rotated)){
                if(isPaused){
                    this.idleAnimationRight.draw(X,Y);
                    this.idleAnimationRight.stop();
                }else{
                    if(isDead && !this.deathAnimationRight.isStopped()){ // You can't die when the game is in pause
                        this.deathAnimationRight.draw(X, Y);
                        this.deathAnimationRight.stopAt(this.deathAnimationRight.getFrameCount()-1);
                        this.deathAnimationRight.start();
                    } else {
                        /* The +2 and -2 are used to make the idle in left and 
                           right position match the position of the center axis
                           if the character. That's caused by a misallignement of
                           the character in the sprite.*/
                        if(isMovingRight){
                            this.idleAnimationRight.draw(X+2,Y);
                        }else{
                            this.idleAnimationRight.draw(X-2,Y);
                        }
                        this.idleAnimationRight.start();
                    }    
                }
            }else{
                if(isPaused){
                    this.idleAnimationLeft.draw(X,Y);
                    this.idleAnimationLeft.stop();
                }else{
                    if(isDead && !this.deathAnimationLeft.isStopped()){ // You can't die when the game is in pause
                        this.deathAnimationLeft.draw(X, Y);
                        this.deathAnimationLeft.stopAt(this.deathAnimationLeft.getFrameCount()-1);
                        this.deathAnimationLeft.start();
                    } else {
                        if(isMovingRight){
                            this.idleAnimationLeft.draw(X+2,Y);
                        }else{
                            this.idleAnimationLeft.draw(X-2,Y);
                        }
                        this.idleAnimationLeft.start();
                    }   
                }
            }
        }
        
        if((this.vX > 0 && !rotated) || (this.vX < 0 && rotated)) {
            this.rightAnimation.draw(X, Y);
            if(!isPaused){ 
                this.rightAnimation.start(); 
            } else {
                this.rightAnimation.stop();
            }
        }
            
        if((this.vX > 0 && rotated) || (this.vX < 0 && !rotated)) {
            this.leftAnimation.draw(X, Y);
            if(!isPaused){
                this.leftAnimation.start();
            } else {
                this.leftAnimation.stop();
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

        hitbox.setY(hitbox.getY() + sign * 0.5f);
        this.isChangingGravity = true;
        this.rotated = !this.rotated;
        hitbox.setY(hitbox.getY() - sign * 0.5f);
        return -gravity;
    }
    
    /**
     * 
     */
    private void moveWithCollisionsX() {
        // X movement collisions

        float vXtemp = this.speedUp * (vX / iterations);

        for (int t = 0; t < iterations; t++) {
            hitbox.setX(hitbox.getX() + vXtemp);
            if(collision.collidesWith()){
                hitbox.setX(hitbox.getX() - vXtemp);
                vX = 0;
            }
        }
    }
    
    /**
     * Manages the movement on the Y axis.
     * Called only when the gravity gets changed.
     */
    private void moveAlongY() {
        float vYtemp = speedUp * (vY / iterations);
        
        for (int t = 0; t < iterations; t++) {
            hitbox.setY(hitbox.getY() + vYtemp);
            if(collision.collidesWith()){
                hitbox.setY(hitbox.getY() - vYtemp);
                vY = 0;
            }
        }
    }
    
    /**
     * Manages the movement on the X axis. 
     * @param in the key button we have pressed
     */
    private void set_speedx(Input in) {
        
        // If we hold down A, the character will move to the left
        if (in.isKeyDown(commands.get("left"))) {
            vX = -speed;
            this.isMovingRight = false;
            if (in.isKeyPressed(commands.get("dash"))) {
                dash(LEFT);
            }
        // If we hold down D, the character will move to the right
        } else if (in.isKeyDown(commands.get("right"))) {
            vX = speed;
            this.isMovingRight = true;
            if (in.isKeyPressed(commands.get("dash"))) {
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
     * Manages the rotation of the character. (Has to be refactored)
     * @param angle the rotation angle
     */
    public void rotate(int angle){
        
       // idleAnimationRight.getCurrentFrame().setRotation(180);
        
        for(int i=0; i<10; i++){
            Image currentImage = this.idleAnimationRight.getImage(i);
            /* The character rotates if the gravity is changed but it's 180 rotation
             * is not yet completed
            */
            if((rotated && currentImage.getRotation() != 180) || (!rotated && currentImage.getRotation() != 0)){
                this.idleAnimationRight.getImage(i).rotate(angle);
                this.idleAnimationLeft.getImage(i).rotate(angle);
                this.deathAnimationRight.getImage(i).rotate(angle);
                this.deathAnimationLeft.getImage(i).rotate(angle);
                if(i<8){
                    this.rightAnimation.getImage(i).rotate(angle);
                    this.leftAnimation.getImage(i).rotate(angle);
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
     * @param damage The number of mid hearts to subtract
     */
    synchronized public void getDamaged(int damage){
        if((System.currentTimeMillis()-this.lastHitTime)> 3000 ){
            this.lastHitTime=System.currentTimeMillis();
            
            System.out.println(System.currentTimeMillis());
            
            this.numHearts -= damage;
            if(this.numHearts <= 0) {
                this.isDead = true;
                this.vX = 0;
                
            }    
        }
    }

   void hideHitbox(Graphics g)
   {
        g.setColor(new Color(0,0,0,0));
        g.fill(this.hitbox);
   }
    
    /**
     * Resets the number of hearts and the speed of the character
     */
    public void resetStats(){
        this.numHearts = this.numVoidHearts;
        this.gravity = 0.5f;
        this.rotated=false;
        this.rotate(30);
        this.vY=0;
        this.vX=0;
        this.isDead=false;
    }
    
    
    public void initCommandList(){
        this.commands = this.loadCommands();
        if(this.commands == null){
            commands.put("right", Input.KEY_D);
            commands.put("left", Input.KEY_A);
            commands.put("dash", Input.KEY_LSHIFT);
            commands.put("gravity", Input.KEY_SPACE);
        }
    }
    
    
    /**
     * Load the tree level from the file
     * @return galaxy if the load operation works successfully,
     * else it returns null
     */
    public HashMap<String, Integer> loadCommands(){
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try{
            fis = new FileInputStream("options");
            in = new ObjectInputStream(fis);
            HashMap<String, Integer> map = (HashMap<String,Integer>) in.readObject();
            in.close();
            return map;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
            return null;
        }
    }
}
