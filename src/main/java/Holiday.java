public class Holiday {
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getYear() {
        return year;
    }

    public Holiday(String name, String year, String date) {
        Name = name;
        this.year = year;
        this.date = date;
    }

    public void setYear(String year) {

        this.year = year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String year;
    private String date;

    public void printHoliday() {
        System.out.println("Holiday year:  "+ getYear());
        System.out.println("Holiday name:  "+ getName());
        System.out.println("Holiday date:  "+ getDate());
    }

}
