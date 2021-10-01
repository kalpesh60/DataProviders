package Testng.DataProvider;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	@DataProvider(name="dP")
    public static Object[][] getDataFromDataprovider(){
        return new Object[][] {
            { "Guru", "Indian" },
            { "Krishna", "UK" },
            { "Bhupesh", "USA" }
        };  
	}
}
