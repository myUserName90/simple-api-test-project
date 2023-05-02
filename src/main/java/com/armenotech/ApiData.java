package com.armenotech;

import org.testng.annotations.DataProvider;

public class ApiData {
    @DataProvider(name = "Assets")
    public static Object[][] assetData() {
        return new Object[][]{
                {"ATUSD","ATUSD"},
                {"atbrl","ATBRL"}

        };
    }

}
