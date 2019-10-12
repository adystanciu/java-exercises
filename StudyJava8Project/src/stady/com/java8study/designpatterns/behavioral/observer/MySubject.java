package stady.com.java8study.designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class MySubject {

    List<Observer> observers = new ArrayList<Observer>();

    public void addObserver(Observer o){
        observers.add(o);
    }

    public void notifyAllObservers(){
        for (Observer o: observers){
            o.notify__();
        }
    }
}
