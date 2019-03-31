package g49803.atl.pentago.util;


/**
 *
 * @author g49803
 */
public interface Observable {
    
    void addObserver(Observer obs);
    
    void removeObserver(Observer obs);
    
    void notifyObservers();
}

