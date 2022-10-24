import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TourPanel {

    private static JFrame frameTourPanel;
    private JPanel bottomPanel;
    private Button ButtonAdd;
    private Button ButtonBack;
    static public JTextField durationField;
    static public JTextField budgetField;

    static public JComboBox seasonList;
    static public JComboBox resting_placeList;

    static public boolean visibleTour = false;

    public static void main(String[] args) {
        TourPanel tourPanel = new TourPanel();
        tourPanel.isVisibleTourPanel();
    }

    public TourPanel() {

        frameTourPanel = new JFrame("Заполнение информации о туре");
        frameTourPanel.setSize(450, 300);
        frameTourPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTourPanel.setLocationRelativeTo(null);
        frameTourPanel.setLayout(new GridBagLayout());

        Container mainContainer = frameTourPanel.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        //Создание текстовых полей
        durationField = new JTextField(15);
        durationField.setToolTipText("Продолжительность отдыха в неделях");
        budgetField = new JTextField(15);
        budgetField.setToolTipText("Бюджет");

        seasonList = new JComboBox(new String[]{"Зима", "Весна", "Лето", "Осень"});
        seasonList.setSelectedIndex(0);

        resting_placeList = new JComboBox(new String[]{"Санаторий", "База отдыха", "Дом отдыха", "Дача"});
        resting_placeList.setSelectedIndex(0);

        JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contents.add(new JLabel("Продолжительность отдыха в неделях :"));
        contents.add(durationField);
        contents.add(new JLabel("Бюджет :"));
        contents.add(budgetField);
        contents.add(new JLabel("                                                                 "));
        contents.add(new JLabel("Сезон :"));
        contents.add(seasonList);
        contents.add(new JLabel("                                                                              "));
        contents.add(new JLabel("Место отдыха :"));
        contents.add(resting_placeList);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.setSize(20, 20);
        frameTourPanel.add(bottomPanel, BorderLayout.SOUTH);

        ButtonAdd = new Button("Добавить");
        ButtonAdd.addActionListener(e -> {
            try {
                Client.addInfoTour();
                finish();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        ButtonBack = new Button("Назад");
        ButtonBack.addActionListener(e -> backPanel());

        bottomPanel.add(ButtonBack, BorderLayout.SOUTH);
        bottomPanel.add(ButtonAdd, BorderLayout.SOUTH);

        //setContentPane(contents);
        mainContainer.add(contents, BorderLayout.CENTER);

        backPanel();
    }

    public static void isVisibleTourPanel(){
        visibleTour = !visibleTour;
        frameTourPanel.setVisible(visibleTour);
    }

    public static void finish(){
        visibleTour = !visibleTour;
        frameTourPanel.setVisible(visibleTour);

        StartApp.start();
    }

    public static void backPanel(){
        visibleTour = !visibleTour;
        frameTourPanel.setVisible(visibleTour);

        AddPanel.isVisibleAddPanel();
    }
}
