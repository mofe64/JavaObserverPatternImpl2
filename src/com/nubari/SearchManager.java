package com.nubari;

import java.util.ArrayList;
import java.util.List;

public class SearchManager implements Observer {
    private final String mangerName;
    private List<Subject> searchObjects;
    Number foundValue;
    Number searchQuery;

    public SearchManager(Subject searchObject, String mangerName) {
        searchObjects = new ArrayList<>();
        this.mangerName = mangerName;
        searchObject.registerObserver(this);
        searchObjects.add(searchObject);
    }

    @Override
    public void update(Number searchValue) {
        if (searchValue != null) {
            this.foundValue = searchValue;
            System.out.println("Value found : " + foundValue);
            cancelSubjectOperations();
        }

    }

    public void addSearchObject(Subject searchObject) {
        searchObject.registerObserver(this);
        searchObjects.add(searchObject);
    }

    public void removeSearchObject(Subject searchObject) {
        searchObject.removeObserver(this);
        searchObjects.remove(searchObject);
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

    private void resetSubjectOperations() {
        for (Subject searchObject : searchObjects) {
            SearchObject currentSearchObject = (SearchObject) searchObject;
            currentSearchObject.turnOnOperations();
        }
    }

    public void initiateSearch(List<Number> source, Number searchQuery) {
        for (Subject searchObject : searchObjects) {
            SearchObject currentSearchObject = (SearchObject) searchObject;
            if (currentSearchObject.isBusy()) {
                continue;
            }
            currentSearchObject.carryOutSearch(source, searchQuery);
        }
    }
}
