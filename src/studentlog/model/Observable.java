package studentlog.model;

import java.util.List;


public interface Observable {
	void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(List<Observer> observers);
}
