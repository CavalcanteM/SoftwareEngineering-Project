package IsaacMain;

import Upgrades.UpgradeDecorator;
import Upgrades.UpgradeComponent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import static java.lang.Math.signum;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class Player implements UpgradeComponent {
    private static Player playerInstance = null;
    private final int LEFT = -1, RIGHT = 1;
    private float gravity = 0.3f;
    private float speed = 5;
    private float iterations = 20;
    private int dashValue = 10;
    private Shape hitbox;
    private float vX = 0;
    private float vY = 0;
    private float speedUp = 1;
    private UpgradeDecorator speedUpDecorator;
    private UpgradeDecorator shieldDecorator;
    private boolean shield = false;
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
    private HashMap<String, Integer> commands;
    private Sound gravityfx;
    private Sound deathfx;
    private Sound hurtfx;
    private Animations animations;

    private Player() {
        try {
            // Initialization of the sounds for change gravity, death and hurt
            this.gravityfx = new Sound("./src/sound/change_gravity.wav");
            this.deathfx = new Sound("./src/sound/death.wav");
            this.hurtfx = new Sound("./src/sound/hurt.wav");
        } catch (SlickException ex){}
        
    }

    public static Player getPlayerInstance() {
        if (playerInstance == null) {
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

    @Override
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

    public boolean isAppear() {
        return appear;
    }

    public long getLastHitTime() {
        return lastHitTime;
    }


    @Override
    public int getNumHearts() {
        return numHearts;
    }

    @Override
    public int getNumVoidHearts() {
        return numVoidHearts;
    }

    public HashMap<String, Integer> getCommands() {
        return commands;
    }

    public Animations getAnimations(){
        return animations;
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

    @Override
    public void setSpeedUpDecorator(UpgradeDecorator speedUpDecorator) {
        this.speedUpDecorator = speedUpDecorator;
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
    public void setShield(boolean shield) {
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

    public void setCollisionManager(CollisionManager collision) {
        this.collision = collision;
    }

    @Override
    public void setShieldDecorator(UpgradeDecorator shieldDecorator) {
        this.shieldDecorator = shieldDecorator;
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
         * I used a shrinkage of 1 pixel in both dimentions to avoid that the
         * Hitbox is unable to pass through slight parts of the map.
         * i.e. passing through a 5x2 tile space could have caused problems.
         * The 2 values 30, 720-90, are the spawn point of the character.
         */
        hitbox = new Rectangle(31, gc.getHeight() - 90, 29, 59);


        // Loads the current set of commands from a file
        this.initCommandList();

        // Initialization of the animations (has to be changed)
        this.selectAnimations();
        //this.animations = new SantaAnimations(11, 16, 17);
        this.animations.createAnimations();

        resetStats();

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
        if(gc.isPaused()){
            if (gc.getInput().isKeyPressed((commands.get("gravity"))) || (gc.getInput().isKeyPressed((commands.get("dash")))));
        }

        // y acceleration
        vY += gravity;

        // Y movement collisions
        moveAlongY();

        //X movement
        set_speedx(gc.getInput());

        //X collisions
        moveWithCollisionsX();

        //if the speedUpDecorator is active, control if the the activation time is ended.
        if (this.speedUpDecorator != null && this.speedUpDecorator.isUpgradeActive()) {
            this.speedUpDecorator.updateActive();
        }

        /* Temporary code: used only for graphically testing the damage
         */
        if (gc.getInput().isKeyPressed(Input.KEY_R)) {
            this.resetStats();
        }

    }

    /**
     * Renders the animation and the rotation of the character
     *
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

        //Rendering of the shield when it's active
        if (this.shield) {
            this.shieldDecorator.render(gc, g);
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
     *
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
     *
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
     *
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
     *
     * @param damage The number of mid hearts to subtract
     */
    @Override
    synchronized public void getDamaged(int damage) {
        if ((System.currentTimeMillis() - this.lastHitTime) > 1000) {

            this.lastHitTime = System.currentTimeMillis();
            System.out.println(System.currentTimeMillis());
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
    public void resetStats() {
        this.numVoidHearts = 6;
        this.numHearts = this.numVoidHearts;
        this.gravity = 0.5f;
        this.rotated = false;
        this.rotate(30);
        this.vY = 0;
        this.vX = 0;
        this.isDead = false;
    }

    public void initCommandList() {
        this.commands = this.loadCommands();
        if (this.commands == null) {
            commands = new HashMap<>();
            commands.put("right", Input.KEY_D);
            commands.put("left", Input.KEY_A);
            commands.put("dash", Input.KEY_LSHIFT);
            commands.put("gravity", Input.KEY_SPACE);
            commands.put("skinIndex", 0);
        }
    }

    /**
     * Load the tree level from the file
     *
     * @return galaxy if the load operation works successfully, else it returns
     * null
     */
    public HashMap<String, Integer> loadCommands() {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream("options");
            in = new ObjectInputStream(fis);
            HashMap<String, Integer> map = (HashMap<String, Integer>) in.readObject();
            in.close();
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void setAnimations(Animations animations) {
        try {
            animations.createAnimations();
        } catch (SlickException ex) {
            ex.printStackTrace();
        }

    }

    public void selectAnimations(){
        switch(this.commands.get("skinIndex")){
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

    public void blink(){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        final ScheduledFuture<?> blinkHandle;
        blinkHandle = scheduler.scheduleAtFixedRate(() -> {this.appear=!this.appear;}, 0, 100, TimeUnit.MILLISECONDS);
        scheduler.schedule(() -> {blinkHandle.cancel(true); this.appear=true;}, 1000, TimeUnit.MILLISECONDS);
    }

}
