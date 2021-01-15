package com.nubari;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

public class SearchObject implements Subject {
    private String searchObjectName;
    private List<Observer> observers;
    private Number searchQuery;
    private Number searchValue;
    private boolean canCarryOutOperation;
    private boolean busy = false;
    private static int counter;

    public SearchObject(int searchQuery) {
        this.searchQuery = searchQuery;
        observers = new ArrayList<>();
        canCarryOutOperation = true;
        counter++;
        this.searchObjectName = "Search Object " + counter;

    }

    public SearchObject() {
        counter++;
        this.searchObjectName = "Search Object " + counter;

        observers = new ArrayList<>();
        canCarryOutOperation = true;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int indexOfObserver = observers.indexOf(observer);
        if (indexOfObserver >= 0) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers(Number searchValue) {
        for (Observer searchManager : observers) {
            searchManager.update(searchValue);
        }
    }

    public Number getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(Number number) {
        this.searchQuery = number;
    }

    public void carryOutSearch(List<Number> source, Number searchQuery) {
        this.searchQuery = searchQuery;
        busy = true;
        System.out.println("Search Object " + getSearchObjectName() + " pinged");
        while (canCarryOutOperation) {
            boolean valueFound = false;
            for (Number value : source) {

                System.out.println("current value is " + value.toString() + " from " + this.getSearchObjectName());
                System.out.println();
                System.out.println("Search Object " + this + " Carrying out search");
                System.out.println();
                if (value.equals(searchQuery)) {
                    this.searchValue = value;
                    valueFound = true;
                    notifyObservers(searchValue);
                    busy = false;
                    break;
                } else {
                    System.out.println("No match found checking next value" + this.getSearchObjectName());
                    System.out.println();
                }
            }
            if (!valueFound) {
                notifyObservers(null);
                canCarryOutOperation = false;
                busy = false;
            }
        }
    }

    public List<Observer> getObservers() {
        return this.observers;
    }

    public void turnOffOperations() {
        canCarryOutOperation = false;
    }

    public void turnOnOperations() {
        canCarryOutOperation = true;
    }

    public String getSearchObjectName() {
        return this.searchObjectName;
    }

    public boolean isBusy() {
        return busy;
    }

    public String toString() {
        return getSearchObjectName();
    }
}
