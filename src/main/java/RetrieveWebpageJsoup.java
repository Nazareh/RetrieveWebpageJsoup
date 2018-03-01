import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * By using JSoup, retrieve the following page and parse useful information about Western Australia’s
 * public holiday for 2018 2019 and 2020:
 *  https://www.commerce.wa.gov.au/labour-relations/public-holidays-western-australia
 It should include at least the following fields for each holiday
 -  Year
 -  Start Date
 -  End Date
 -  Holiday Name
 Print the parsed information in pretty format in console.
 */

public class RetrieveWebpageJsoup {

    public static void main(String[] args) {
        String webpage = "https://www.commerce.wa.gov.au/labour-relations/public-holidays-western-australia";
        RetrieveWebpageJsoup lp = new RetrieveWebpageJsoup();

        List<Holiday> holidaysList = lp.fetchHolidays(webpage);

        for (Holiday h : holidaysList) {
            h.printHoliday();
            System.out.println();
        }
    }

    public List<Holiday> fetchHolidays(String webpage) {
        List<Holiday> holidaysList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(webpage).get();

            List<String> years = new ArrayList<>();
            List<String> holidayNames = new ArrayList<>();

            Element table = doc.select("table").get(2); //select the holiday table.
            Elements rows = table.select("tr"); //select the holiday rows.

            for (int rowId = 0; rowId < rows.size(); rowId++) {
                Element row = rows.get(rowId);
                if (rowId == 0) {
                    // Array list containing each year;
                    Elements elements = row.select("th");
                    for (Element e : elements) {
                        if (e.text().length() > 0)
                            years.add(e.text());
                    }
                } else {
                    // Array list containing each holiday;
                    Elements elements = row.select("th");
                    for (Element e : elements) {
                        holidayNames.add(e.text());
                    }
                    //holiday dates
                    Elements cols = row.select("td");
                    for (int colId = 0; colId < cols.size(); colId++) {
                        holidaysList.add(new Holiday(
                                holidayNames.get(rowId - 1),
                                years.get(colId),
                                cols.get(colId).text()));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return holidaysList;
    }
}
