package com.nubari;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SearchObject implements Subject {
    private String searchObjectName;
    private List<Observer> observers;
    private Integer searchQuery;
    private Integer searchValue;
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
    public void notifyObservers(Integer searchValue) {
        for (Observer searchManager : observers) {
            searchManager.update(searchValue);
        }
    }

    public Number getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(Integer number) {
        this.searchQuery = number;
    }

    public void carryOutSearch(List<Integer> source, Integer searchQuery) {
        this.searchQuery = searchQuery;
        busy = true;
        System.out.println("Search Object " + getSearchObjectName() + " pinged");
        System.out.println("Search Object " + getSearchObjectName() + " can carry out operations : " + canCarryOutOperation);
        while (canCarryOutOperation) {
            boolean valueFound = false;
//            for (Number value : source) {
//                System.out.println("current value is " + value.toString() + " from " + this.getSearchObjectName());
//                System.out.println();
//                System.out.println("Search Object " + this + " Carrying out search");
//                System.out.println();
//                if (value.equals(searchQuery)) {
//                    this.searchValue = value;
//                    valueFound = true;
//                    notifyObservers(searchValue);
//                    busy = false;
//                    break;
//                } else {
//                    System.out.println("No match found checking next value" + this.getSearchObjectName());
//                    System.out.println();
//                }
//            }
//
            Collections.sort(source);
            int high = source.size() - 1;
            int low = 0;
            while (low <= high) {
                int mid = (low + high) / 2;
                Integer value = source.get(mid);
                System.out.println("current value is " + value + " from " + this.getSearchObjectName());
                System.out.println();
                if (value.equals(searchQuery)) {
                    this.searchValue = value;
                    valueFound = true;
                    notifyObservers(searchValue);
                    busy = false;
                    break;
                } else if (value > searchQuery) {
                    System.out.println("current  value greater moving to next iteration" + this.getSearchObjectName());
                    System.out.println();
                    high = mid - 1;
                } else {
                    System.out.println("current  value lesser moving to next iteration" + this.getSearchObjectName());
                    System.out.println();
                    low = mid + 1;
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
