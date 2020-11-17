import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WeatherReport {
    /** "STATION","NAME","LATITUDE","LONGITUDE","ELEVATION","DATE","AWND","PGTM","PRCP","SNOW","SNWD","TAVG","TMAX","TMIN","WDF2","WDF5","WSF2","WSF5","WT01","WT02","WT03","WT04","WT05","WT06","WT08"
     */
private final Map<String, String> weatherMap;
//private String columns="STATION,NAME,LATITUDE,LONGITUDE,ELEVATION,DATE,AWND,PGTM,PRCP,SNOW,SNWD,TAVG,TMAX,TMIN,WDF2,WDF5,WSF2,WSF5,WT01,WT02,WT03,WT04,WT05,WT06,WT08";


    public WeatherReport(String[] rowOfData, String[] names)
    {

        Map<String, String> weatherMap = new LinkedHashMap<>();
        //String[] names = columns.split(",");
        int j = 0;
        for(int i = 0;i<rowOfData.length;i++) {
                weatherMap.put(names[j], rowOfData[i]);
            j++;
        }
        this.weatherMap = weatherMap;
    }
    public Map<String, String> getWeatherMap()
    {
        return weatherMap;
    }
    @Override
    public String toString()
    {
        String mapString = "";
        for(Map.Entry<String, String> data: weatherMap.entrySet())
        {
            mapString += data.getKey() + ":"+data.getValue()+"\n";
        }
        return mapString;
    }
    public String getDate()
    {
        return weatherMap.get("DATE");
    }
    public double getPercip()
    {
        return Double.parseDouble(weatherMap.get("PRCP"));
    }
    public double getTMax()
    {
        return Double.parseDouble(weatherMap.get("TMAX"));
    }
    public double getVari()
    {
        return ( (Double.parseDouble(weatherMap.get("TMAX"))) - (Double.parseDouble(weatherMap.get("TMIN"))) );
    }
    public int getMonth()
    {
        String[] date = weatherMap.get("DATE").split("-");
        return Integer.parseInt(date[1]);
    }
    public int getYear()
    {
        String[] date = weatherMap.get("DATE").split("-");
        return Integer.parseInt(date[0]);
    }

    public boolean thunderStorm()
    {
        if(weatherMap.containsKey("WT03"))
        {
            return true;
        }
        else{
            return false;
        }
    }
    public void compactPrint()
    {
        System.out.println(getDate()+" -- "+getTMax()+" degrees F.\nRain: "+getPercip()+" inches. Thunder? "+thunderStorm());
    }

}
