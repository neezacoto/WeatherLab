import java.util.Scanner;

public class main {
    public static void main(String[] args)
    {
    //    WeatherLoader test = new WeatherLoader();
    //    test.weatherReader();
        //System.out.println(test.getWeatherObjectData());
   //     WeatherSearcher testSearch = new WeatherSearcher(test.getWeatherObjectData());
       // System.out.println("weather of date");
       // System.out.println(testSearch.weatherOf("2020-03-14"));
        //System.out.println(testSearch.getReports());
       // System.out.println("precip sum");
       // System.out.println(testSearch.precipSum("2020-01-05","2020-01-08"));
//        System.out.println("max temps");
//        System.out.println(testSearch.maxTemps(3));
//        System.out.println("max temps by date");
//        System.out.println(testSearch.maxTempsByDate("2020-01-05","2020-01-20",5));
//        System.out.println("vari temps");
//        System.out.println(testSearch.variTemps(5));
//        System.out.println("vari temps by date");
//        System.out.println(testSearch.variTempsByDate("2020-01-05","2020-01-20",5));
//        System.out.println("storms in each month");
  //     System.out.println(testSearch.thunderStorms());
        setup();

    }

    public static void setup()
    {
        WeatherLoader weather= new WeatherLoader("");
        while(!(weather.doesExist())) {
            try {
                System.out.println("Please insert which weather file you will be using");
                Scanner search = new Scanner(System.in);
                weather = new WeatherLoader(search.nextLine());
            }
            catch(Throwable t){
            }
        }
        interact(weather);
    }

    public static void interact(WeatherLoader report_data)
    {
        boolean run = true;
        while(run) {
            try {
                WeatherSearcher reports = new WeatherSearcher(report_data.getWeatherObjectData());

                System.out.println("\n Please Select an Option:\n -highest\n -week\n -storms\n -quit");
                Scanner in = new Scanner(System.in);
                String ansr = in.nextLine();
                switch (ansr.toLowerCase()) {
                    case "highest":
                        System.out.println("How many days would you like to rank in temperature?");
                        Scanner days = new Scanner(System.in);
                        for(WeatherReport i: reports.maxTemps(days.nextInt())) {
                            i.compactPrint();
                            System.out.println("------------------------");
                        }

                        break;
                    case "week":
                        System.out.println("Please insert a date");
                        Scanner date = new Scanner(System.in);
                        reports.week(date.nextLine());
                        break;
                    case "storms":
                        reports.storms();
                        break;
                    case "quit":
                        run=false;
                        break;
                }
            }
            catch(Throwable t)
            {
                System.out.println("Wrong input");
            }
        }

    }

}
