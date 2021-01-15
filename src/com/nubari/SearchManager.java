package com.nubari;

import java.util.ArrayList;
import java.util.List;

public class SearchManager implements Observer {
    private final String mangerName;
    private List<Subject> searchObjects;
    Integer foundValue;
    Integer searchQuery;
    int numberOfOwnedSearchObjects;

    public SearchManager(Subject searchObject, String mangerName) {
        searchObjects = new ArrayList<>();
        this.mangerName = mangerName;
        searchObject.registerObserver(this);
        searchObjects.add(searchObject);
        numberOfOwnedSearchObjects++;
    }

    @Override
    public void update(Integer searchValue) {
        int numberOfNullsAllowed = searchObjects.size();
        int nullsReceived = 0;
        if (searchValue == null) {
            nullsReceived++;
        } else {
            this.foundValue = searchValue;
            System.out.println("Value found : " + foundValue);
            cancelSubjectOperations();
        }
        if (nullsReceived == numberOfNullsAllowed) {
            System.out.println(searchQuery + " could not be found");
        }


    }

    public void addSearchObject(Subject searchObject) {
        searchObject.registerObserver(this);
        searchObjects.add(searchObject);
        numberOfOwnedSearchObjects++;
    }

    public void removeSearchObject(Subject searchObject) {
        searchObject.removeObserver(this);
        searchObjects.remove(searchObject);
        numberOfOwnedSearchObjects--;
    }

    public List<Subject> getSearchObjects() {
        return this.searchObjects;
    }

    public String getMangerName() {
        return this.mangerName;
    }

    public void cancelSubjectOperations() {

        for (Subject searchObject : searchObjects) {
            SearchObject currentSearchObject = (SearchObject) searchObject;
            System.out.println("Turning off operations for " + currentSearchObject.getSearchObjectName());
            currentSearchObject.turnOffOperations();
        }
        //resetSubjectOperations();
    }

    public void resetSubjectOperations() {
        for (Subject searchObject : searchObjects) {
            SearchObject currentSearchObject = (SearchObject) searchObject;
            currentSearchObject.turnOnOperations();
        }
    }

    public void initiateSearch(List<Integer> source, Integer searchQuery) {
        for (Subject searchObject : searchObjects) {
            SearchObject currentSearchObject = (SearchObject) searchObject;
            if (currentSearchObject.isBusy()) {
                continue;
            }
            currentSearchObject.carryOutSearch(source, searchQuery);
        }
    }
}
