import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WeatherSearcherTest {


    @org.junit.jupiter.api.Test
    void maxTempsTestnull() {
        //list of weather reports
        ArrayList<WeatherReport> test = new ArrayList<>();
        WeatherSearcher testTemps = new WeatherSearcher(test);
        ArrayList<WeatherReport> maxTemps = testTemps.maxTemps(5);


        assertEquals(0,maxTemps.size());
    }
    @org.junit.jupiter.api.Test
    void maxTemps() {
        String line1 = "USW00014739,BOSTON MA US,42.3606,-71.0097,3.7,2020-07-19,11.63,,0.00,0.0,,82,95,70,  220,  220,21.0,29.1,,,,,,,";
        String line2 ="USW00014739,BOSTON MA US,42.3606,-71.0097,3.7,2020-07-20,12.97,,0.00,0.0,,82,93,75,  270,  260,21.0,25.9,,,,,,,";
        String line3 ="USW00014739,BOSTON MA US,42.3606,-71.0097,3.7,2020-07-21,10.29,,0.00,0.0,,83,90,74,  280,  290,18.1,25.1,,,,,,,";
        String nameString = "STATION,NAME,LATITUDE,LONGITUDE,ELEVATION,DATE,AWND,PGTM,PRCP,SNOW,SNWD,TAVG,TMAX,TMIN,WDF2,WDF5,WSF2,WSF5,WT01,WT02,WT03,WT04,WT05,WT06,WT08";
        WeatherReport report1 = new WeatherReport(line1.split(","),nameString.split(","));
        WeatherReport report2 = new WeatherReport(line2.split(","),nameString.split(","));
        WeatherReport report3 = new WeatherReport(line3.split(","),nameString.split(","));
        ArrayList<WeatherReport> test = new ArrayList<>();
        test.add(report1);
        test.add(report2);
        test.add(report3);
        WeatherSearcher testTemps = new WeatherSearcher(test);
        ArrayList<WeatherReport> maxTemps = testTemps.maxTemps(2);


        assertEquals(95.0,maxTemps.get(maxTemps.size()-1).getTMax());
    }
    @org.junit.jupiter.api.Test
    void sorted() {
        String line1 = "USW00014739,BOSTON MA US,42.3606,-71.0097,3.7,2020-07-19,11.63,,0.00,0.0,,82,95,70,  220,  220,21.0,29.1,,,,,,,";
        String line2 ="USW00014739,BOSTON MA US,42.3606,-71.0097,3.7,2020-07-20,12.97,,0.00,0.0,,82,93,75,  270,  260,21.0,25.9,,,,,,,";
        String line3 ="USW00014739,BOSTON MA US,42.3606,-71.0097,3.7,2020-07-21,10.29,,0.00,0.0,,83,90,74,  280,  290,18.1,25.1,,,,,,,";
        String nameString = "STATION,NAME,LATITUDE,LONGITUDE,ELEVATION,DATE,AWND,PGTM,PRCP,SNOW,SNWD,TAVG,TMAX,TMIN,WDF2,WDF5,WSF2,WSF5,WT01,WT02,WT03,WT04,WT05,WT06,WT08";
        WeatherReport report1 = new WeatherReport(line1.split(","),nameString.split(","));
        WeatherReport report2 = new WeatherReport(line2.split(","),nameString.split(","));
        WeatherReport report3 = new WeatherReport(line3.split(","),nameString.split(","));
        ArrayList<WeatherReport> test = new ArrayList<>();
        test.add(report1);
        test.add(report2);
        test.add(report3);
        WeatherSearcher testTemps = new WeatherSearcher(test);


// for some reason this works in main but not here
        assertEquals(95.0,testTemps.getReportsByTemp().get(2));
    }

    @org.junit.jupiter.api.Test
    void maxTempsByDate() {
    }

    @org.junit.jupiter.api.Test
    void variTemps() {
    }

}