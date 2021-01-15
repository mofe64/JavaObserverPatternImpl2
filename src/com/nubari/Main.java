package com.nubari;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataSource.populateMockDataSource(1_000, 100, 2);
        List<Integer> dataSource1 = DataSource.getMockDataSource();
        DataSource.populateMockDataSource(1_000, 100, 3);
        List<Integer> dataSource2 = DataSource.getMockDataSource();
        DataSource.populateMockDataSource(1_000, 100, 4);
        List<Integer> dataSource3 = DataSource.getMockDataSource();
        dataSource3.add(3);
        Integer searchQuery = 3;
        SearchObject searchObject1 = new SearchObject();
        SearchObject searchObject2 = new SearchObject();
        SearchObject searchObject3 = new SearchObject();
        SearchObject searchObject4 = new SearchObject();
        SearchManager searchManager = new SearchManager(searchObject1, "Jiggy Manager");
        searchManager.addSearchObject(searchObject1);
        searchManager.addSearchObject(searchObject2);
        searchManager.addSearchObject(searchObject3);
        searchManager.addSearchObject(searchObject4);
        searchManager.initiateSearch(dataSource1, searchQuery);
        searchManager.initiateSearch(dataSource2, searchQuery);
        searchManager.initiateSearch(dataSource3, searchQuery);

    }
}
