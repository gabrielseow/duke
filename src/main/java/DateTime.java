public class DateTime {
    private final int day;
    private final int month;
    private final int year;
    private final int time;
    private final String dateTimeString;
    private String dateTimePrintFormat;

    public DateTime(int day, int month, int year, int time, String dateTimeString) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.time = time;
        this.dateTimeString = dateTimeString;
    }

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
            String formattedYear = " " + year + " ";
            String formattedTime = "";
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
            boolean isMorning = time >= 1200
                    ? false
                    : true;
            int hours = (time - minutes) / 100;
            if (hours > 12) {
                hours -= 12;
            }
            formattedTime += hours;
            if (minutes != 0) {
                formattedTime += "." + minutes;
            }
            if (isMorning) {
                formattedTime += "am";
            } else {
                formattedTime += "pm";
            }
            dateTimePrintFormat = formattedDay + formattedMonth + formattedYear + formattedTime;
            return dateTimePrintFormat;
        }
    }
}