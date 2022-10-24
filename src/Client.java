import java.sql.*;


public class Client {

    private static int count = 5;
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
            System.out.print("Имя : " + res.getString("name") + " ");
            System.out.print("Возраст : " + res.getInt("age") + " ");
            System.out.print("Сезон отдыха : " + res.getString("season") + " ");
            System.out.print("Место отдыха : " + res.getString("resting_place") + " ");
            System.out.print("Бюджет : " + res.getInt("budget"));
        }





//        Statement searchThisClient =
//                connection.createStatement();
//
//        String sql = "SELECT* FROM CLIENTS WHERE name='" + SearchPanel.nameField.getText().toString() + "'";
//
//        ResultSet resultSet = searchThisClient.executeQuery(sql);
//
//        while (resultSet.next()){
//            System.out.println(resultSet.getString("name") + " " + resultSet.getInt("id_client"));
//        }
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

enum EDUCATION {
    BasicGeneralEducation, //(9 классов)
    SecondarySchool, //(11 классов)
    LowerVocationalEducation, //Среднее профессиональное образование
    BachelorDegree, //Бакалавриат
    MasterDegree //Магистратура
}

enum SOCIAL_STATUS {
    Peasant,
    Tradesman,
    HonorableSir,
    Nobleman
}

enum SEASONAL_VACATION_TIME {
    Winter,
    Spring,
    Summer,
    Autumn
}

enum RESTING_PLACE {

}

