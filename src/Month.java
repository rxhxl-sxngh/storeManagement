import java.util.HashMap;
import java.util.Map;

public class Month {
    private int monthValue;
    private String monthName;

    private static final Map<Integer, String> monthMap = createMonthMap();

    public Month(int monthValue) {
        if (monthValue < 1 || monthValue > 12) {
            throw new IllegalArgumentException("Month value must be between 1 and 12");
        }
        this.monthValue = monthValue;
        this.monthName = monthMap.get(monthValue);
    }

    public Month(int monthValue, String monthName) {
        this.monthValue = monthValue;
        this.monthName = monthName;
    }

    public int getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(int monthValue) {
        if (monthValue < 1 || monthValue > 12) {
            throw new IllegalArgumentException("Month value must be between 1 and 12");
        }
        this.monthValue = monthValue;
        this.monthName = monthMap.get(monthValue);
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    @Override
    public String toString() {
        return monthName;
    }

    private static Map<Integer, String> createMonthMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "January");
        map.put(2, "February");
        map.put(3, "March");
        map.put(4, "April");
        map.put(5, "May");
        map.put(6, "June");
        map.put(7, "July");
        map.put(8, "August");
        map.put(9, "September");
        map.put(10, "October");
        map.put(11, "November");
        map.put(12, "December");
        return map;
    }
}
