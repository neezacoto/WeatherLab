import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WeatherSearcher {

    private final ArrayList<WeatherReport> reports;
    private final ArrayList<WeatherReport> reportsByTemp;
    private final ArrayList<WeatherReport> reportsByVari;

    /**
     *
     * @param reports takes in an array list of weather reports most likely from WeatherLoader to be stored and stored
     */
    public WeatherSearcher(ArrayList<WeatherReport> reports) {

        this.reports = new ArrayList<WeatherReport>(reports);
        this.reportsByTemp = new ArrayList<WeatherReport>(reports);
        this.reportsByVari = new ArrayList<WeatherReport>(reports);
        dateSort(this.reports, this.reports.size());
        tempSort(this.reportsByTemp, this.reportsByTemp.size());
        variSort(this.reportsByVari,this.reportsByVari.size());
    }

    /**
     *
     * @param arr takes in an array
     * @param n the size of the array
     *          this sorts the array into order by pushing the largest variable it's looking for to the back through each pass through using recursion.
     *          This goes for dateSort, tempSort, and variSort.
     */
    private void dateSort(ArrayList<WeatherReport> arr, int n) {
        if(n<=1)
            return;
        for(int i = 0; i < n-1; i++){
            if( (arr.get(i).getDate().compareTo(arr.get(i+1).getDate())) > 0)
            {
                WeatherReport temp = arr.get(i);
                arr.set(i, arr.get(i+1));
                arr.set(i+1, temp);
            }
        }
        dateSort(arr, n-1);
    }
    /**
     *
     * @param arr takes in an array
     * @param n the size of the array
     *          this sorts the array into order by pushing the largest variable it's looking for to the back through each pass through using recursion.
     *          This goes for dateSort, tempSort, and variSort.
     */
    private void tempSort(ArrayList<WeatherReport> arr, int n) {
        if(n<=1)
            return;
        for(int i = 0; i < n-1; i++){
            if( arr.get(i).getTMax() > arr.get(i+1).getTMax())
            {
                WeatherReport temp = arr.get(i);
                arr.set(i, arr.get(i+1));
                arr.set(i+1, temp);
            }
        }
        tempSort(arr, n-1);
    }
    /**
     *
     * @param arr takes in an array
     * @param n the size of the array
     *          this sorts the array into order by pushing the largest variable it's looking for to the back through each pass through using recursion.
     *          This goes for dateSort, tempSort, and variSort.
     */
    private void variSort(ArrayList<WeatherReport> arr, int n) {
        if(n<=1)
            return;
        for(int i = 0; i < n-1; i++){
            if( arr.get(i).getVari() > arr.get(i+1).getVari())
            {
                WeatherReport temp = arr.get(i);
                arr.set(i, arr.get(i+1));
                arr.set(i+1, temp);
            }
        }
        variSort(arr, n-1);
    }

    /**
     *
     * @return returns the sorted reports from this object
     */

    public ArrayList<WeatherReport> getReports()
    {
        return reports;
    }

    /**
     *
     * @param date takes in a date for dateSearch to use
     * @return returns the weather report of that date as a string
     */
    public String weatherOf(String date) {

        return dateSearch(reports,0,reports.size(), date).toString();
    }

    /**
     *
     * @param reports all the sorted weather data
     * @param leftBound starting index for binary sort
     * @param rightBound ending index for binary sort
     * @param date the date that is being searched for
     * @return returns the weather report from that date using recursion
     */
    public WeatherReport dateSearch(ArrayList<WeatherReport> reports, int leftBound, int rightBound, String date)
    {
        if (rightBound >= leftBound) {
            int mid = leftBound + (rightBound - leftBound) / 2;

            //if in the middle
            if (reports.get(mid).getDate().compareTo(date)==0) {
                return reports.get(mid);
            }
            //left bound
            if (reports.get(mid).getDate().compareTo(date)>0) {
                return dateSearch(reports, leftBound, mid - 1, date);
            }
            //right bound
            return dateSearch(reports, mid + 1, rightBound, date);
        }
        //not found
        return null;
    }

    /**
     *
     * @param startDate takes in starting date
     * @param endDate uses ending date
     * @return the sum of precipitation between the two dates
     */
    public double precipSum(String startDate, String endDate)
    {
        int indexStart = reports.indexOf(dateSearch(reports,0,reports.size(),startDate));
        int indexEnd = reports.indexOf(dateSearch(reports,0,reports.size(),endDate));
        return precipSumAdd(indexStart,indexEnd+1);
    }

    /**
     *
     * @param pos starting position
     * @param end ending position for the case to bounce back
     * @return the sum between these two dates using recursion
     */
    private double precipSumAdd(int pos, int end)
    {
        if(pos>end) {
            System.out.print("start date not valid");
            return 0.00;
        }
        if(pos!=end)
        return reports.get(pos).getPercip() + precipSumAdd(pos+1,end);

        else return 0.00;
    }

    /**
     *
     * @param n this variable is used to back track the sorted list of Temps
     * @return a list of n max temps
     */
    public ArrayList<WeatherReport> maxTemps(int n)
    {
        ArrayList<WeatherReport> maxReports = new ArrayList<WeatherReport>();

        int smallestIndex = Math.max(reportsByTemp.size()-1-n, 0);
        for(int i = reportsByTemp.size()-1; i > smallestIndex ; i--)
        {
            if(n==0)
                break;
            maxReports.add(reportsByTemp.get(i));
        }
        return maxReports;
    }

    /**
     *
     * @param startDate takes in the starting position for range
     * @param endDate takes in the ending position for range
     * @param n how many wanted from this range starting from its max
     * @return a list of from this range with the n max temps numbers
     */
    public ArrayList<WeatherReport> maxTempsByDate(String startDate, String endDate, int n)
    {
        int indexStart = reports.indexOf(dateSearch(reports,0,reports.size(),startDate));
        int indexEnd = reports.indexOf(dateSearch(reports,0,reports.size(),endDate));

        ArrayList<WeatherReport> maxReports = new ArrayList<WeatherReport>();
        for(int i = indexEnd; i > indexStart; i--)
        {
            if(n==0)
                break;
            maxReports.add(reportsByTemp.get(i));
            n--;
        }
        return maxReports;
    }
    /**
     *
     * @param n this variable is used to back track the sorted list of Temps
     * @return a list of n max temps
     */
    public ArrayList<WeatherReport> variTemps(int n)
    {
        ArrayList<WeatherReport> variReports = new ArrayList<WeatherReport>();
        int smallestIndex = Math.max(reportsByTemp.size()-1-n, 0);
        for(int i = reportsByVari.size()-1; i > smallestIndex ; i--)
        {
            if(n==0)
                break;
            variReports.add(reportsByVari.get(i));
        }
        return variReports;
    }

    /**
     *
     * @param startDate takes in the starting position for range
     * @param endDate takes in the ending position for range
     * @param n how many wanted from this range starting from its max
     * @return a list of from this range with the n high vari numbers
     */
    public ArrayList<WeatherReport> variTempsByDate(String startDate, String endDate, int n)
    {
        int indexStart = reports.indexOf(dateSearch(reports,0,reports.size(),startDate));
        int indexEnd = reports.indexOf(dateSearch(reports,0,reports.size(),endDate));

        ArrayList<WeatherReport> variReports = new ArrayList<WeatherReport>();
        for(int i = indexEnd; i > indexStart; i--)
        {
            if(n==0)
                break;
            variReports.add(reportsByVari.get(i));
            n--;
        }
        return variReports;
    }

    /**
     *
     * @return returns the amount of storms that happened in each month in map format
     * This should work assuming that the data set is from one year.
     */
    public Map<String, Integer> thunderStorms()
    {
        String monthsNames="January,February,March,April,May,June,July,August,September,October,November,December";
        String[] months = monthsNames.split(",");
        int[] storms = {0,0,0,0,0,0,0,0,0,0,0,0};

        for(int i =0;i < reports.size();i++)
        {
            if(reports.get(i).thunderStorm())
            storms[reports.get(i).getMonth()-1] += 1;
        }

        Map<String, Integer> stormMap = new LinkedHashMap<>();
        int j = 0;
        for(int i = 0;i<months.length;i++) {
            stormMap.put(months[j], storms[i]);
            j++;
        }
        return stormMap;
    }

    /**
     *
     * @return returns the object's list sorted by temp
     */
    public ArrayList<WeatherReport> getReportsByTemp()
    {
        return reportsByTemp;
    }

    public void week(String date)
    {
        int startIndex = reports.indexOf(dateSearch(reports,0,reports.size(),date));
        for(int i=startIndex ; i<reports.size() && i<startIndex+7;i++)
        {
            reports.get(i).compactPrint();
            System.out.println("------------------------");
        }
    }

    /**
     * This should return thunderStorms and create a map with precipitation sum from each month.
     * Also this should work assuming that the data set is from one year.
     */
    public void storms()
    {
        String monthsNames="January,February,March,April,May,June,July,August,September,October,November,December";
        String[] months = monthsNames.split(",");
        int[] daysInMonth = {0,0,0,0,0,0,0,0,0,0,0,0};
        Map<String, Double> monthPercip = new LinkedHashMap<>();
        Map<String, Integer> monthStorms = thunderStorms();

        //checking all the days that are in that month with the data that is given
        for(int i =0;i < reports.size();i++)
        {
            daysInMonth[reports.get(i).getMonth()-1] += 1;
        }
        int currentMonth = 1;
        String startingDay = reports.get(0).getDate();
        for(int i=0 ; i<reports.size(); i++)
        {
            if(reports.get(i).getMonth()!=currentMonth)
            {
                monthPercip.put(months[currentMonth-1],Math.floor( precipSum(startingDay,reports.get(i-1).getDate()) * 100) / 100);
                startingDay = reports.get(i-1).getDate();
                currentMonth++;
            }

        }
        System.out.println("Storm Data:\n"+monthStorms+"\n \n"+"Precipitation Data:\n"+monthPercip);
    }

}
