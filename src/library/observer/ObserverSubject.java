// Observer subject class

package library.observer;

public class ObserverSubject {
    // Observer object - single observer only for now
    // Will change to Observer list in the future
    Observer observer;

    // Method to register the method
    public void registerObserver(Observer observer) {
        this.observer = observer;
    }

    // Method to remove the observer (unused)
    public void removeObserver() {
        observer = null;
    }

    // Method to notify the observer's update method with a message
    public void notifyObserver(String notification) {
        if (observer != null) {
            observer.update(notification);
        }
    }

}
