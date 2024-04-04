package nus.trackme.parser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseDateTime {

    //Expected date and time format
    //private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    //private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

    // Define an array of possible date and time formats
    private static final String[] dateFormats = {"d/M/yyyy", "d/M/yy", "dd/MM/yyyy", "dd/MM/yy"};
    private static final String[] timeFormats = {"HHmm", "H:mm"};

    protected String dateTime, parsedDate, parsedTime;


    public ParseDateTime(String dateTime){
        this.dateTime = dateTime;
    }


    public boolean isCorrect(){

        //Split the date and time
        Pattern pattern = Pattern.compile("(\\d{1,2}/\\d{1,2}/\\d{4}) (\\d{4})");
        Matcher matcher = pattern.matcher(dateTime);

        if (matcher.find()) {
            parsedDate = matcher.group(1).trim();
            parsedTime = matcher.group(2).trim();
        }

        LocalDate deadlineDate = null;
        LocalTime deadlineTime = null;

        for(String dateFormat: dateFormats){
            for (String timeFormat : timeFormats) {
                try {
                    // Parse the deadline string into LocalDate and LocalTime
                    deadlineDate = LocalDate.parse(parsedDate, DateTimeFormatter.ofPattern(dateFormat));
                    deadlineTime = LocalTime.parse(parsedTime, DateTimeFormatter.ofPattern(timeFormat));

                    //Reformat the date and time
                    this.dateTime = deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " + deadlineTime.format(DateTimeFormatter.ofPattern("hh:mm a"));

                    return true;

                } catch (Exception e) {
                    continue;
                }
            }
        }

        System.out.println("Incorrect datetime format!! Please provide the datetime in this format dd/MM/yyyy HHmm.");
        return false;
    }

    public boolean isCompare(String endDate, String endTime){
        for (String dateFormat : dateFormats) {
            for (String timeFormat : timeFormats) {
                try {
                    LocalDateTime startDateTime = LocalDateTime.parse(this.parsedDate + " " + this.parsedTime, DateTimeFormatter.ofPattern(dateFormat + " " + timeFormat));
                    LocalDateTime endDateTime = LocalDateTime.parse(endDate + " " + endTime, DateTimeFormatter.ofPattern(dateFormat + " " + timeFormat));

                    if(!endDateTime.isAfter(startDateTime)){
                        System.out.println("Incorrect timeline!! Please correct your timeline");
                        return endDateTime.isAfter(startDateTime);
                    }

                    return true;
                } catch (Exception ignored) {
                }
            }
        }
        return false;
    }

    public LocalDate deadlineDate(String dateTime){
        String[] parts = dateTime.split(" ");
        parsedDate = parts[0] + " " + parts[1] + " " + parts[2];
        LocalDate date = LocalDate.parse(parsedDate, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return LocalDate.parse(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    public String toString(){
        return this.dateTime;
    }

    public String date(){
        return this.parsedDate;
    }

    public String time(){
        return this.parsedTime;
    }
}
