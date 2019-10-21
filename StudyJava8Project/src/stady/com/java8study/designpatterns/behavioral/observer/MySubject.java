package stady.com.java8study.designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public interface MySubject {

    List<Observer> observers = new ArrayList<Observer>();

    default public void addObserver(Observer o){
        observers.add(o);
    }

    default public void notifyAllObservers(){
        for (Observer o: observers){
            o.notify__();
        }
    }
}
