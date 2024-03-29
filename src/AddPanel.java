import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddPanel extends JFrame {

    private static JFrame frameAddPanel;
    private JPanel bottomPanel;
    private Button ButtonAdd;
    private Button ButtonBack;
    static public JTextField nameField;
    static public JTextField ageField;
    static public JTextField incomeField;
    static public JComboBox educationList;
    static public JComboBox socialStatusList;

    static public boolean visibleAdd = false;

    public AddPanel() {

        frameAddPanel = new JFrame("Добавление нового клиента");
        frameAddPanel.setSize(450, 300);
        frameAddPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameAddPanel.setLocationRelativeTo(null);
        frameAddPanel.setLayout(new GridBagLayout());

        Container mainContainer = frameAddPanel.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        //Создание текстовых полей
        nameField = new JTextField(30);
        nameField.setToolTipText("ФИО");
        ageField = new JTextField(10);
        ageField.setToolTipText("Возраст");
        incomeField = new JTextField(10);
        incomeField.setToolTipText("Доход");

        educationList = new JComboBox(new String[]{"Среднее профессиональное образование", "Бакалавриат", "Магистратура"});
        educationList.setSelectedIndex(0);

        socialStatusList = new JComboBox(new String[]{"Выживающий", "Средний класс", "Состоятельный"});
        socialStatusList.setSelectedIndex(0);

        JPanel contents = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contents.add(new JLabel("ФИО :"));
        contents.add(nameField);
        contents.add(new JLabel("Возраст :"));
        contents.add(ageField);
        contents.add(new JLabel("Доход :"));
        contents.add(incomeField);
        contents.add(new JLabel(" Образование :"));
        contents.add(educationList);
        contents.add(new JLabel("Социальный статус :"));
        contents.add(socialStatusList);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.setSize(20, 20);
        frameAddPanel.add(bottomPanel, BorderLayout.SOUTH);

        ButtonAdd = new Button("Далее");
        ButtonAdd.addActionListener(e -> {
            try {
                Client.addClient();
                addTourPanel();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        ButtonBack = new Button("Назад");
        ButtonBack.addActionListener(e -> backPanel());

        bottomPanel.add(ButtonBack, BorderLayout.SOUTH);
        bottomPanel.add(ButtonAdd, BorderLayout.SOUTH);

        mainContainer.add(contents, BorderLayout.CENTER);

        backPanel();
    }

    private void addTourPanel() {
        TourPanel tourPanel = new TourPanel();
        frameAddPanel.setVisible(false);
    }
    public static void isVisibleAddPanel(){
        visibleAdd = !visibleAdd;
        frameAddPanel.setVisible(visibleAdd);

    }


    public static void backPanel(){
        visibleAdd = !visibleAdd;
        frameAddPanel.setVisible(visibleAdd);

        StartApp.start();
    }
}
