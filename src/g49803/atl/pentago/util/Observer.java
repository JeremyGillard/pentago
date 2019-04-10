package g49803.atl.pentago.util;

/**
 * A class can implement the Observer interface when it wants to be informed of changes in observable objects.
 * 
 * @author Jeremy Gillard
 */
public interface  Observer {
    
    /**
     * This method is called whenever the observed object is changed.
     */
    void update();
    
}
