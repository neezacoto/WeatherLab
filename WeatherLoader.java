import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WeatherLoader {


    private Path weatherFile = Path.of("src/2020_boston_weather.csv");
    private ArrayList<WeatherReport> weatherObjectData;
    private boolean exists = false;

    /**
     *
     * @param path_of_weather takes in a String to be converted into a path to read that file from the path
     */
    public WeatherLoader(String path_of_weather )
    {
        this.weatherFile = Path.of("src/"+path_of_weather);

        try {
            List<String> weatherLines = Files.readAllLines(weatherFile);
            ArrayList<String[]> wordsInLine = new ArrayList<>();

            String[] columns =(weatherLines.get(0).replace("\"", "").split(","));//names

            for(int i = 1 ; i < weatherLines.size(); i++) {
                wordsInLine.add(weatherLines.get(i).replace("\"", "").split(","));//data
            }
             this.weatherObjectData = fileToObjects(wordsInLine,columns);
            exists=true;
        }
        catch(IOException e){}
    }

    /**
     *
     * @return checks to see if this class is loaded properly
     */
    public boolean doesExist()
    {
        return exists;
    }

    /**
     *
     * @param linesOfData takes in all the lines from the file used above to be sent to WeatherReport to construct a full weather report
     * @param names uses the first row of the file, which are all the names to be used as a reference in WeatherReport
     * @return returns a list of WeatherReports constructed with the file used above.
     */
    public ArrayList<WeatherReport> fileToObjects(ArrayList<String[]> linesOfData,String[] names)
    {
        /**
         * takes in all the relevant rows of data and sends them to a class to be instantiated and then sent back to be
         * put in a list.
         */
        ArrayList<WeatherReport> weatherObjects = new ArrayList<>();
        for(int i = 0 ; i < linesOfData.size(); i++)
        {
            weatherObjects.add(new WeatherReport(linesOfData.get(i),names));
            //System.out.println(new WeatherReport(linesOfData.get(i),names).toString());
        }
        return weatherObjects;
    }

    /**
     *
     * @return returns the list of WeatherReports that this class has constructed
     */
    public ArrayList<WeatherReport> getWeatherObjectData()
    {
        return weatherObjectData;
    }
}