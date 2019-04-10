package g49803.atl.pentago.util;


/**
 * This class represents an observable object, or "data" in the model-view paradigm. 
 * It can be subclassed to represent an object that the application wants to have observed. 
 * An observable object can have one or more observers. 
 * An observer may be any object that implements interface Observer. 
 * Sources: Java API Observable class
 * 
 * @author Jeremy Gillard
 */
public interface Observable {
    
    /**
     * Adds an observer to the set of observers for this object, provided that it is not the same as some observer already in the set.
     * 
     * @param obs the observable object.
     */
    void addObserver(Observer obs);
    
    /**
     * Remove an observer from the set of observers of this object.
     * 
     * @param obs the observable object. 
     */
    void removeObserver(Observer obs);
    
    /**
     * If this object has changed, as indicated by the hasChanged method, 
     * then notify all of its observers and then call the clearChanged method 
     * to indicate that this object has no longer changed.
     */
    void notifyObservers();
}

