package duke.datetime;

/**
 * Stores Date, Month, Year and Time of an associated Task.
 */
public class DateTime {
    private final int day;
    private final int month;
    private final int year;
    private final int time;
    private final String dateTimeString;
    private String dateTimePrintFormat;

    /**
     * Represents the Date and Time of an associated Task object.
     * @param day day of the Task
     * @param month month of the Task
     * @param year year of the Task
     * @param time time of the Task(24 hr format)
     * @param dateTimeString unprocessed String representing Date and Time
     */
    public DateTime(int day, int month, int year, int time, String dateTimeString) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.time = time;
        this.dateTimeString = dateTimeString;
    }

    /**
     * Converts DateTime object to a String representing the format for the Date and Time to be stored
     * in a save file.
     * @return DateTime object in save format
     */
    public String toSaveFormat() {
        return dateTimeString;
    }

    @Override
    public String toString() {
        if (dateTimePrintFormat != null) {
            return dateTimePrintFormat;
        } else {
            String formattedDay = "" + day;
            String formattedMonth = " of ";
            int rem = day % 10;

            switch (rem) {
            case 1:
                formattedDay += "st";
                break;
            case 2:
                formattedDay += "nd";
                break;
            case 3:
                formattedDay += "rd";
                break;
            default:
                formattedDay += "th";
                break;
            }

            switch (month) {
            case 1:
                formattedMonth += "January";
                break;
            case 2:
                formattedMonth += "February";
                break;
            case 3:
                formattedMonth += "March";
                break;
            case 4:
                formattedMonth += "April";
                break;
            case 5:
                formattedMonth += "May";
                break;
            case 6:
                formattedMonth += "June";
                break;
            case 7:
                formattedMonth += "July";
                break;
            case 8:
                formattedMonth += "August";
                break;
            case 9:
                formattedMonth += "September";
                break;
            case 10:
                formattedMonth += "October";
                break;
            case 11:
                formattedMonth += "November";
                break;
            case 12:
                formattedMonth += "December";
                break;
            default:
                //Add error handling
                break;
            }

            int minutes = time % 100;
            boolean isMorning = time < 1200;
            int hours = (time - minutes) / 100;
            if (hours > 12) {
                hours -= 12;
            }

            String formattedTime = "";
            formattedTime += hours;
            if (minutes != 0) {
                formattedTime += "." + minutes;
            }

            boolean isMorning = time >= 1200
                    ? false
                    : true;
            if (isMorning) {
                formattedTime += "am";
            } else {
                formattedTime += "pm";
            }

            String formattedYear = " " + 20 + year + ", ";
            dateTimePrintFormat = formattedDay + formattedMonth + formattedYear + formattedTime;
            return dateTimePrintFormat;
        }
    }
}