package IsaacMain;

/**
 * Interface representing the interface of the Mediator design pattern
 */
public interface Mediator {
    
    /**
     * @return True if the collion of the player has to stop its movements.
     */
    public boolean collidesWith();
}