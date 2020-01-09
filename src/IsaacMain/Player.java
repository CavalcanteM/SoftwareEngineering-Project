package IsaacMain;

import Upgrades.UpgradeComponent;
import static java.lang.Math.signum;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import menu.Mapping;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import skins.*;

/**
 * Class representing the character of the game
 * @author Isaac
 */
public class Player implements UpgradeComponent {
    private static UpgradeComponent playerInstance = null;
    private final int LEFT = -1, RIGHT = 1;
    private float gravity = 0.3f;
    private float speed = 5;
    private float iterations = 20;
    private int dashValue = 10;
    private Shape hitbox;
    private float vX = 0;
    private float vY = 0;
    private float speedUp = 1;
    private boolean isChangingGravity;
    private boolean rotated = false;
    private boolean isPaused;
    private boolean isMovingRight = true;
    private boolean isDead;
    private boolean appear=true;
    private int numHearts = 6; // Measured in mid hearts
    private int numVoidHearts = 6; // Measured in mid hearts
    private CollisionManager collision;
    private long lastHitTime = System.currentTimeMillis() - 3000;
    private Mapping commands;
    private Sound gravityfx;
    private Sound deathfx;
    private Sound hurtfx;
    private Animations animations;

    /**
     * Constructor of the class Player
     * The constructor is private because the player has been implemented with the Singleton Behavioral Design Pattern
     */
    private Player() {
        try {
            // Initialization of the sounds for change gravity, death and hurt
            this.gravityfx = new Sound("./src/sound/change_gravity.wav");
            this.deathfx = new Sound("./src/sound/death.wav");
            this.hurtfx = new Sound("./src/sound/hurt.wav");
        } catch (SlickException ex){}

    }


    public static UpgradeComponent getPlayerInstance() {
        if (playerInstance == null) {
            Player.playerInstance = new Player();
        }
        return Player.playerInstance;
    }

    public static void setPlayerInstance(UpgradeComponent newInstance){
        playerInstance = newInstance;
    }

    /*--------------------
     * Getter methods
     *--------------------*/
    
    /**
     * Getter method for the parameter hitbox
     * @return 
     */

    @Override
    public Shape getPlayer() {
        return hitbox;
    }
    
    /**
     * @return the boolean variable this.appear
     */
    @Override
    public boolean isAppear(){
        return this.appear;
    }

    /**
     * Getter method for the parameter numHearts
     * Method inherited from the interface UpgradeComponent
     * @return 
     */
    @Override
    public int getNumHearts() {
        return numHearts;
    }

    /**
     * Getter method for the parameter numVoidHearts
     * Method inherited from the interface UpgradeComponent
     * @return
     */
    @Override
    public int getNumVoidHearts() {
        return numVoidHearts;
    }
    
    /**
     * Getter method for the parameter animations
     * @return 
     */
    @Override
    public Animations getAnimations(){
        return animations;
    }

    /*--------------------
     * Setter methods
     *--------------------*/

    /**
     * Setter method for the parameter commands
     * @param commands 
     */
    @Override
    public void setCommands(Mapping commands){
        this.commands = commands;
    }
    
    /**
     * Setter method for the parameter speedUp
     * @param speedUp
     */
    @Override
    public void setSpeedUp(float speedUp) {
        this.speedUp = speedUp;
    }

    /**
     * Setter method for the parameter numHearts
     * @param numHearts
     */
    @Override
    public void setNumHearts(int numHearts) {
        this.numHearts = numHearts;
    }

    /**
     * Setter method for the parameter numVoidHearts
     * @param numVoidHearts
     */
    @Override
    public void setNumVoidHearts(int numVoidHearts) {
        this.numVoidHearts = numVoidHearts;
    }

    /**
     * Setter method for the parameter collision
     * @param collision
     */
    public void setCollisionManager(CollisionManager collision) {
        this.collision = collision;
    }

    /*--------------------
     * Other methods
     *--------------------*/
    
    /**
     * This method is necessary in the decorators.
     */
    @Override
    public void execute(){
    }

    /**
     * Implementation of the method "update" of the interface UpgradeComponent
     * This method is a sort of costructor.
     * @param gc
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gc) throws SlickException {

        /*
         * I used a shrinkage of 1 pixel in both dimentions to avoid that the
         * Hitbox is unable to pass through slight parts of the map.
         * i.e. passing through a 5x2 tile space could have caused problems.
         * The 2 values 30, 720-90, are the spawn point of the character.
         */
        hitbox = new Rectangle(31, gc.getHeight() - 90, 29, 59);

        // Initialization of the animations (has to be changed)
        this.selectAnimations();
        //this.animations = new SantaAnimations(11, 16, 17);
        this.animations.createAnimations();

        resetStats();

    }

    /**
     * Implementation of the method "update" of the interface UpgradeComponent
     * @param gc
     * @param delta
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {


        // Gravity check and change
        if (gc.getInput().isKeyPressed((commands.getCommandMap().get("gravity"))))
            gravity = changeGravity(signum(gravity));

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
     * Implementation of the method "update" of the interface UpgradeComponent
     * Renders the animation and the rotation of the character
     * @param gc
     * @param g
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        if(gc.isPaused()){
            if (gc.getInput().isKeyPressed((commands.getCommandMap().get("gravity"))) || (gc.getInput().isKeyPressed((commands.getCommandMap().get("dash")))));
        }
        //used to hyde the hitbox
        hideHitbox(g);
        /*
        Take coordinates have an offset value to make the sprite perfectly
        match the Shape rectangle, that aclually is the hitbox of the Hitbox.
         */
        float X = this.hitbox.getMinX() - 14;
        float Y = this.hitbox.getMinY() - 5;
        if (this.vX == 0) {//The character doesn't move
            if ((isMovingRight && !rotated) || (!isMovingRight && rotated)) {
                if (isPaused) {
                    this.animations.getIdleAnimationRight().draw(X, Y);
                    this.animations.getIdleAnimationRight().stop();
                } else {
                    if (isDead && !this.animations.getDeathAnimationRight().isStopped()) { // You can't die when the game is in pause
                        this.animations.getDeathAnimationRight().draw(X, Y);
                        this.animations.getDeathAnimationRight().stopAt(this.animations.getDeathAnimationRight().getFrameCount() - 1);
                        this.animations.getDeathAnimationRight().start();
                    } else {
                        /* The +2 and -2 are used to make the idle in left and
                           right position match the position of the center axis
                           if the character. That's caused by a misallignement of
                           the character in the sprite.*/
                        if (isMovingRight) {
                            this.animations.getIdleAnimationRight().draw(X + 2, Y);
                        } else {
                            this.animations.getIdleAnimationRight().draw(X - 2, Y);
                        }
                        this.animations.getIdleAnimationRight().start();
                    }
                }
            } else {
                if (isPaused) {
                    this.animations.getIdleAnimationLeft().draw(X, Y);
                    this.animations.getIdleAnimationLeft().stop();
                } else {
                    if (isDead && !this.animations.getIdleAnimationLeft().isStopped()) { // You can't die when the game is in pause
                        this.animations.getDeathAnimationLeft().draw(X, Y);
                        this.animations.getDeathAnimationLeft().stopAt(this.animations.getIdleAnimationLeft().getFrameCount() - 1);
                        this.animations.getDeathAnimationLeft().start();
                    } else {
                        if (isMovingRight) {
                            this.animations.getIdleAnimationLeft().draw(X + 2, Y);
                        } else {
                            this.animations.getIdleAnimationLeft().draw(X - 2, Y);
                        }
                        this.animations.getIdleAnimationLeft().start();
                    }
                }
            }
        }

        if ((this.vX > 0 && !rotated) || (this.vX < 0 && rotated)) {
            this.animations.getRightAnimation().draw(X, Y);
            if (!isPaused) {
                this.animations.getRightAnimation().start();
            } else {
                this.animations.getRightAnimation().stop();
            }
        }

        if ((this.vX > 0 && rotated) || (this.vX < 0 && !rotated)) {
            this.animations.getLeftAnimation().draw(X, Y);
            if (!isPaused) {
                this.animations.getLeftAnimation().start();
            } else {
                this.animations.getLeftAnimation().stop();
            }
        }

        if (this.isChangingGravity) {
            this.rotate(30);
        }

        this.drawHearts(35, 30);
    }

    /**
     * Changes the gravity and checks if there is an obstacle during the
     * transition If there is an obstacle the transition stops
     *
     * @param sign
     * @return
     */
    public float changeGravity(float sign) {
        this.gravityfx.play(1f, 0.05f);
        hitbox.setY(hitbox.getY() + sign * 0.5f);
        this.isChangingGravity = true;
        this.rotated = !this.rotated;
        hitbox.setY(hitbox.getY() - sign * 0.5f);
        return -gravity;
    }

    /**
     * Allows movements on the X axis checking the collisions
     */
    private void moveWithCollisionsX() {
        // X movement collisions

        float vXtemp = this.speedUp * (vX / iterations);

        for (int t = 0; t < iterations; t++) {
            hitbox.setX(hitbox.getX() + vXtemp);
            if (collision.collidesWith()) {
                hitbox.setX(hitbox.getX() - vXtemp);
                vX = 0;
            }
        }
    }

    /**
     * Manages the movement on the Y axis. Called only when the gravity gets
     * changed.
     */
    private void moveAlongY() {
        float vYtemp = speedUp * (vY / iterations);

        for (int t = 0; t < iterations; t++) {
            hitbox.setY(hitbox.getY() + vYtemp);
            if (collision.collidesWith()) {
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
        if (in.isKeyDown(commands.getCommandMap().get("left"))) {
            vX = -speed;
            this.isMovingRight = false;
            if (in.isKeyPressed(commands.getCommandMap().get("dash"))) {
                dash(LEFT);
            }
            // If we hold down D, the character will move to the right
        } else if (in.isKeyDown(commands.getCommandMap().get("right"))) {
            vX = speed;
            this.isMovingRight = true;
            if (in.isKeyPressed(commands.getCommandMap().get("dash"))) {
                dash(RIGHT);
            }
            // The character doesn't move
        } else {
            vX = 0;
        }
    }

    /**
     * The character executes a dash in the specified direction with a certain
     * speed
     *
     * @param dir the direction of the dash (1 for right, -1 for left)
     */
    public void dash(int dir) {
        vX = dir * dashValue * speed;
    }

    /**
     * Manages the rotation of the character.
     *
     * @param angle the rotation angle
     */
    public void rotate(int angle) {

        // Rotate the run animations
        for (int i = 0; i < animations.getRunAnimationLength(); i++) {
            Image currentImage = this.animations.getRightAnimation().getImage(i);
            if ((rotated && currentImage.getRotation() != 180) || (!rotated && currentImage.getRotation() != 0)) {
                this.animations.getRightAnimation().getImage(i).rotate(angle);
                this.animations.getLeftAnimation().getImage(i).rotate(angle);
            }
        }

        // Rotate the idle animations
        for (int i = 0; i < animations.getIdleAnimationLength(); i++) {
            Image currentImage = this.animations.getIdleAnimationRight().getImage(i);
            if ((rotated && currentImage.getRotation() != 180) || (!rotated && currentImage.getRotation() != 0)) {
                this.animations.getIdleAnimationRight().getImage(i).rotate(angle);
                this.animations.getIdleAnimationLeft().getImage(i).rotate(angle);
            }
        }

        // Rotate the death animations
        for (int i = 0; i < animations.getDeathAnimationLength(); i++) {
            Image currentImage = this.animations.getDeathAnimationRight().getImage(i);
            if ((rotated && currentImage.getRotation() != 180) || (!rotated && currentImage.getRotation() != 0)) {
                this.animations.getDeathAnimationRight().getImage(i).rotate(angle);
                this.animations.getDeathAnimationLeft().getImage(i).rotate(angle);
            }
        }
    }

    /**
     * Draw the hearts that represent the current life of the character
     * @param dim1 the width of the hearts in pixels
     * @param dim2 the height of the hearts in pixels
     * @throws SlickException
     */
    public void drawHearts(int dim1, int dim2) throws SlickException {
        SpriteSheet hearts = new SpriteSheet("./graphics/png/hearts.png", 265, 231); // Must be modified after the image is changed
        hearts.startUse();
        int i;

        // Draws the void hearts
        for (i = 0; i < this.numVoidHearts / 2; i++) {
            hearts.getSprite(2, 0).getScaledCopy(dim1, dim2).draw((dim1 + 2) * i, 0);
        }
        // Draws the full hearts
        for (i = 0; i < this.numHearts / 2; i++) {
            hearts.getSprite(0, 0).getScaledCopy(dim1, dim2).draw((dim1 + 2) * i, 0);
        }
        // Draws the mid hearts
        if (this.numHearts - 2 * i > 0) {
            hearts.getSprite(1, 0).getScaledCopy(dim1, dim2).draw((dim1 + 2) * i, 0);
        }
        hearts.endUse();
    }

    /**
     * Manages the damage on the character
     * @param damage The number of mid hearts to subtract
     */
    @Override
    public void getDamaged(int damage) {
        if ((System.currentTimeMillis() - this.lastHitTime) > 1000) {

            this.lastHitTime = System.currentTimeMillis();
            this.numHearts -= damage;
            if (this.numHearts <= 0) {
                this.isDead = true;
                this.vX = 0;
                if (!this.deathfx.playing()) {
                    this.deathfx.play(1f, 0.2f);
                }
            }
            else{
                this.hurtfx.play(1, 0.1f);
                this.blink();
            }
        }
    }

    void hideHitbox(Graphics g) {
        g.setColor(new Color(0, 0, 0, 0));
        g.fill(this.hitbox);
    }

    /**
     * Resets the number of hearts and the speed of the character
     */
    @Override
    public void resetStats() {
        this.numVoidHearts = 6;
        this.numHearts = this.numVoidHearts;
        this.gravity = 0.5f;
        this.rotated = false;
        this.rotate(30);
        this.vY = 0;
        this.vX = 0;
        this.isDead = false;
        this.speedUp = 1;
    }
    
    /**
     * Load from commands the "skinIndex" and set the attribute this.animations
     * with the correct animation.
     */
    public void selectAnimations(){
        switch(this.commands.getCommandMap().get("skinIndex")){
            case 0: {
               this.animations =  new IsaacAnimations(58, 70, 8, 10, 10);
               break;
            }
            case 1: {
                this.animations = new AdventurerAnimations(58, 70, 10, 10, 10);
                break;
            }
            case 2: {
                this.animations = new JackLanternAnimations(58, 70, 8, 10, 10);
                break;
            }
            case 3: {
                this.animations = new NinjaAnimations(58, 70, 10, 10, 10);
                break;
            }
            case 4: {
                this.animations = new SantaAnimations(58, 70, 11, 16, 17);
                break;
            }
        }
    }

    /**
     * This method has to change the player's appear attribute in order to let
     * the player image appears and disappears.
     */
    public void blink(){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        final ScheduledFuture<?> blinkHandle;
        blinkHandle = scheduler.scheduleAtFixedRate(() -> {this.appear=!this.appear;}, 0, 100, TimeUnit.MILLISECONDS);
        scheduler.schedule(() -> {blinkHandle.cancel(true); this.appear=true;}, 1000, TimeUnit.MILLISECONDS);
    }

}
