package com.nubari;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private static final SecureRandom secureRandom = new SecureRandom();

    private static final List<Integer> mockDataSource = new ArrayList<>();

    public static void populateMockDataSource(int count, int maxValue, int line) {
        for (int i = 0; i < count; i++) {
            mockDataSource.add(line * secureRandom.nextInt(maxValue));
        }
    }

    public static List<Integer> getMockDataSource() {
        return mockDataSource;
    }
}
