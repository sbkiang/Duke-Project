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

    private final String[] DATE_FORMATS = {"d/M/yyyy", "d/M/yy", "dd/MM/yyyy", "dd/MM/yy"};
    private final String[] TIME_FORMATS = {"HHmm", "H:mm", ""};

    private String dateTime, parsedDate = "", parsedTime = "";


    public ParseDateTime(String dateTime){
        this.dateTime = dateTime;
    }


    public boolean isCorrect(){

        //Split the date and time
        Pattern pattern = Pattern.compile("(\\d{1,2}/\\d{1,2}/\\d{2,4})(?:\\s(\\d{1,4}))?");
        Matcher matcher = pattern.matcher(dateTime);

        if (matcher.find()) {
            parsedDate = matcher.group(1).trim();
            if (matcher.group(2) != null) {
                parsedTime = matcher.group(2).trim();
            } else {
                parsedTime = "";
            }
        }

        LocalDate deadlineDate = null;
        LocalTime deadlineTime = null;

        for(String dateFormat: DATE_FORMATS){
            for (String timeFormat : TIME_FORMATS) {
                try {
                    if(!parsedDate.isBlank() && !parsedTime.isBlank()){
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
                    }
                    else if(!parsedDate.isBlank() && parsedTime.isBlank()){

                        deadlineDate = LocalDate.parse(parsedDate, DateTimeFormatter.ofPattern(dateFormat));
                        LocalDate dt = deadlineDate;

                        if(dt.isAfter(LocalDate.now()) || dt.equals(LocalDate.now())) {
                            this.dateTime = deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                            return true;
                        }
                        else{
                            System.out.println("Invalid given date and time! Please ensure the date and time provided is not in past.");
                            return false;
                        }
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

                    if(parsedTime.isBlank()){
                        LocalDate fromDate = LocalDate.parse(this.parsedDate, DateTimeFormatter.ofPattern(dateFormat));
                        LocalDate toDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern(dateFormat));

                        if(!toDate.isAfter(fromDate)){
                            System.out.println("Invalid timeline! Please adjust your timeline accordingly");
                            return toDate.isAfter(fromDate);
                        }

                    }
                    else{
                        LocalDateTime startDateTime = LocalDateTime.parse(this.parsedDate + " " + this.parsedTime, DateTimeFormatter.ofPattern(dateFormat + " " + timeFormat));
                        LocalDateTime endDateTime = LocalDateTime.parse(endDate + " " + endTime, DateTimeFormatter.ofPattern(dateFormat + " " + timeFormat));

                        if(!endDateTime.isAfter(startDateTime)){
                            System.out.println("Invalid timeline! Please adjust your timeline accordingly");
                            return endDateTime.isAfter(startDateTime);
                        }
                    }

                    return true;

                } catch (Exception e) {
                    continue;
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

    public LocalDate deadlineD(String dateTime){
        String[] parts = dateTime.split(" ");
        parsedDate = parts[0] + " " + parts[1] + " " + parts[2];
        LocalDate dt = LocalDate.parse(parsedDate, DateTimeFormatter.ofPattern("MMM dd yyyy"));
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
