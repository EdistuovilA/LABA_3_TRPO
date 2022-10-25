import java.sql.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class Client {

    private String name;
    private int age;
    private String education;
    private String socialSstatus;
    private int income;

    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "1769";
    static Connection connection = null;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

    }

    public static void addClient() throws SQLException {

        Statement statement =
                connection.createStatement();
        ResultSet res = statement.executeQuery("SELECT COUNT(id_client) FROM CLIENTS");

        int maxId = 0;
        while (res.next()){
            maxId = res.getInt("count") + 1;
        }

        PreparedStatement addThisClient =
                connection.prepareStatement("INSERT INTO CLIENTS VALUES (?, ?, ?, ?, ?, ?)");

        addThisClient.setInt(1,  maxId);
        addThisClient.setInt(2, Integer.parseInt(AddPanel.ageField.getText()));
        addThisClient.setString(3, AddPanel.educationList.getSelectedItem().toString());
        addThisClient.setString(4, AddPanel.nameField.getText());
        addThisClient.setString(5, AddPanel.socialStatusList.getSelectedItem().toString());
        addThisClient.setInt(6, Integer.parseInt(AddPanel.incomeField.getText()));

        addThisClient.executeUpdate();
    }

    public static void addInfoTour() throws SQLException {

        Statement statement =
                connection.createStatement();
        ResultSet res = statement.executeQuery("SELECT COUNT(id_client) FROM INFO_TOUR");

        int maxId = 0;
        while (res.next()){
            maxId = res.getInt("count") + 1;
        }

        PreparedStatement addThisInfoTour =
                connection.prepareStatement("INSERT INTO INFO_TOUR VALUES (?, ?, ?, ?, ?)");

        addThisInfoTour.setInt(1,  maxId);
        addThisInfoTour.setString(2, TourPanel.seasonList.getSelectedItem().toString());
        addThisInfoTour.setString(3, TourPanel.resting_placeList.getSelectedItem().toString());
        addThisInfoTour.setInt(4, Integer.parseInt(TourPanel.durationField.getText()));
        addThisInfoTour.setInt(5, Integer.parseInt(TourPanel.budgetField.getText()));

        addThisInfoTour.executeUpdate();
    }


    public static void searchClient() throws SQLException {


        Statement statement =
                connection.createStatement();

        ResultSet res = statement.executeQuery("SELECT name, age, season, resting_place, budget FROM CLIENTS JOIN INFO_TOUR USING (id_client) WHERE NAME='" + SearchPanel.nameField.getText().toString() + "'");

        while (res.next()){
            System.out.print("Имя : " + res.getString("name") + "\n");
            System.out.print("Возраст : " + res.getInt("age") + "\n");
            System.out.print("Сезон отдыха : " + res.getString("season") + "\n");
            System.out.print("Место отдыха : " + res.getString("resting_place") + "\n");
            System.out.print("Бюджет : " + res.getInt("budget") + "\n");
        }
    }

    public static void searchPopularPlaces() throws SQLException {
        Statement statement =
                connection.createStatement();

        ResultSet res = statement.executeQuery("SELECT mode() WITHIN GROUP (ORDER BY resting_place) from info_tour join clients c on info_tour.id_client = c.id_client where social_status='Царь'");

        while (res.next())
            System.out.print("Популярное место отдыха для царей - " + res.getString("mode") + "\n");

        res = statement.executeQuery("SELECT mode() WITHIN GROUP (ORDER BY resting_place) from info_tour join clients c on info_tour.id_client = c.id_client where social_status='Крепостной'");

        while (res.next())
            System.out.print("Популярное место отдыха для крепостных - " + res.getString("mode") + "\n");

        res = statement.executeQuery("SELECT mode() WITHIN GROUP (ORDER BY resting_place) from info_tour join clients c on info_tour.id_client = c.id_client where social_status='Дворянин'");

        while (res.next())
            System.out.print("Популярное место отдыха для дворянинов - " + res.getString("mode") + "\n");

    }

    public static void findTrend() throws SQLException {
        Statement statement =
                connection.createStatement();

        ResultSet res = statement.executeQuery("select season, count(*)  from info_tour group by season;");

        HashMap<String, Integer> seasonCount = new HashMap<>();

        while (res.next()) {
            System.out.println("Сезон - " + res.getString("season") + ". Количество отдыхающих - " + res.getInt("count"));
            seasonCount.put(res.getString("season"), res.getInt("count"));
        }

        Map.Entry<String, Integer> maxEntry = seasonCount.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .orElse(null);

        Map.Entry<String, Integer> minEntry = seasonCount.entrySet().stream()
                .min(Comparator.comparing(Map.Entry::getValue))
                .orElse(null);


        assert maxEntry != null;
        System.out.println("Люди предпочитают " + maxEntry.getKey().toLowerCase(Locale.ROOT) + " для отдыха. Количество отдыхающих в это время года - " + maxEntry.getValue());
        System.out.println(minEntry.getKey() + " - самое нелюбимое время года для отдыха. Количество отдыхающих - " + minEntry.getValue());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSocialSstatus() {
        return socialSstatus;
    }

    public void setSocialSstatus(String socialSstatus) {
        this.socialSstatus = socialSstatus;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public Client() {
    }
}
