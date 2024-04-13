package nus.trackme.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extract the date and time from various formats like d/M/yyyy HHmm or dd/MM/yyyy H:mm
 * and convert them to the standard date and time format: MMM dd yyyy.
 * Additionally, compare the date/times to ensure the 'from' and 'to' date/times are in the correct timeline.
 */
public class ParseDateTime {

    private static final String[] DATE_FORMATS = {"d/M/yyyy", "d/M/yy", "dd/MM/yyyy", "dd/MM/yy"};
    private static final String[] TIME_FORMATS = {"HHmm", "H:mm"};

    protected String dateTime, parsedDate, parsedTime;


    public ParseDateTime(String dateTime){
        this.dateTime = dateTime;
    }


    public boolean isCorrect(){

        //Split the date and time
        Pattern pattern = Pattern.compile("(\\d{1,2}/\\d{1,2}/\\d{2,4}) (\\d{4})");
        Matcher matcher = pattern.matcher(dateTime);

        if (matcher.find()) {
            parsedDate = matcher.group(1).trim();
            parsedTime = matcher.group(2).trim();
        }

        LocalDate deadlineDate = null;
        LocalTime deadlineTime = null;

        for(String dateFormat: DATE_FORMATS){
            for (String timeFormat : TIME_FORMATS) {
                try {
                    // Parse the deadline string into LocalDate and LocalTime
                    deadlineDate = LocalDate.parse(parsedDate, DateTimeFormatter.ofPattern(dateFormat));
                    deadlineTime = LocalTime.parse(parsedTime, DateTimeFormatter.ofPattern(timeFormat));

                    LocalDateTime dt = deadlineDate.atTime(deadlineTime);

                    if(dt.isAfter(LocalDateTime.now())){
                        //Reformat the date and time
                        this.dateTime = deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " + deadlineTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
                        return true;
                    }
                    else{
                        System.out.println("Invalid given date and time! Please ensure the date and time provided is not in past.");
                        return false;
                    }

                } catch (Exception e) {
                    continue;
                }
            }
        }

        System.out.println("Invalid datetime format! Please ensure the datetime is provided in the format dd/MM/yyyy HHmm or d/M/yy HHmm.");
        return false;
    }

    public boolean isCompare(String endDate, String endTime){
        for (String dateFormat : DATE_FORMATS) {
            for (String timeFormat : TIME_FORMATS) {
                try {
                    LocalDateTime startDateTime = LocalDateTime.parse(this.parsedDate + " " + this.parsedTime, DateTimeFormatter.ofPattern(dateFormat + " " + timeFormat));
                    LocalDateTime endDateTime = LocalDateTime.parse(endDate + " " + endTime, DateTimeFormatter.ofPattern(dateFormat + " " + timeFormat));

                    if(!endDateTime.isAfter(startDateTime)){
                        System.out.println("Invalid timeline! Please adjust your timeline accordingly");
                        return endDateTime.isAfter(startDateTime);
                    }

                    return true;
                } catch (Exception ignored) {
                }
            }
        }
        return false;
    }

    public LocalDateTime deadlineDT(String dateTime){
        String[] parts = dateTime.split(" ");
        parsedDate = parts[0] + " " + parts[1] + " " + parts[2] + " " + parts[3];
        LocalDateTime dt = LocalDateTime.parse(parsedDate, DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return dt;
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
