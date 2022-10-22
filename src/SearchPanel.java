import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SearchPanel {

    private JFrame frameSearchPanel;
    private JPanel bottomPanel;
    private Button ButtonSearch;
    private Button ButtonBack;
    static public JTextField nameField;
    static public JTextField ageField;
    static public JTextField incomeField;
    static public JComboBox educationList;
    static public JComboBox socialStatusList;

    static public boolean visibleSearch = false;

    public SearchPanel(){
        frameSearchPanel = new JFrame("Поиск клиента");
        frameSearchPanel.setSize(450, 300);
        frameSearchPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSearchPanel.setLocationRelativeTo(null);
        frameSearchPanel.setLayout(new GridBagLayout());

        Container mainContainer = frameSearchPanel.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        //Создание текстовых полей
        nameField = new JTextField(30);
        nameField.setToolTipText("ФИО");

        JPanel contents = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contents.add(new JLabel("ФИО :"));
        contents.add(nameField);

       // setContentPane(contents);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.setSize(20, 20);
        frameSearchPanel.add(bottomPanel, BorderLayout.SOUTH);

        ButtonSearch = new Button("Поиск");
        ButtonSearch.addActionListener(e -> {
            try {
                Client.searchClient();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            nameField.setText("");
        });

        ButtonBack = new Button("Назад");
        ButtonBack.addActionListener(e -> isVisibleSearchPanel());

        bottomPanel.add(ButtonBack, BorderLayout.SOUTH);
        bottomPanel.add(ButtonSearch, BorderLayout.SOUTH);

     //   setContentPane(contents);
        mainContainer.add(contents, BorderLayout.CENTER);

        isVisibleSearchPanel();
    }
    public void isVisibleSearchPanel(){
        visibleSearch = !visibleSearch;
        frameSearchPanel.setVisible(visibleSearch);

        StartApp.start();
    }








}
