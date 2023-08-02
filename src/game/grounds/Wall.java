package game.grounds;

/**
 * Class that specify the properties of Wall which extends from HighGround
 */
public class Wall extends HighGround {

    /**
     * Constructor.
     * the Wall's properties which states the displayChar,successRate and fallDamage for a Wall
     */
    public Wall() {
        super('#', 80, 20);
    }//Wall: 80% success rate, 20 fall damage

    /**
     * Check if Ground can block thrown objects
     * @return true
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }
}
