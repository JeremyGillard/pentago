package g49803.atl.pentago.util;


/**
 *
 * @author Jeremy Gillard
 */
public interface Observable {
    
    void addObserver(Observer obs);
    
    void removeObserver(Observer obs);
    
    void notifyObservers();
}

