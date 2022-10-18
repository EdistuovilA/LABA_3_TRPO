/*
Разработать ПО по автоматизации работы туристической фирмы.
ПС имеет информацию об отдыхающих:
•	ФИО, возраст, образование, социальное положение,
•	доход, место отдыха( санаторий, база  отдыха, дом отдыха, дача и т. д.);
•	время сезонное отдыха,
•	продолжительность отдыха,
•	сумма затраченная на отдых.
Выявить места отдыха, предпочитаемые различными слоями населения;
выяснить тенденции к увеличению/уменьшению количества отдыхающих в зависимости от сезона.
*/

import javax.swing.*;
import java.awt.*;

public class StartApp extends JFrame{
    private JFrame frame;
    private JPanel bottomPanel;
    private Button ButtonAdd;
    private Button ButtonSearch;
    JTextField bigField;

    public static void main(String[] args) {
        StartApp app = new StartApp();
        app.start();
    }
    public StartApp()
    {
        frame = new JFrame("LAB...");
        frame.setSize(450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.setSize(900, 100);

        mainContainer.add(bottomPanel, BorderLayout.SOUTH);

        ButtonSearch = new Button("Найти");
        ButtonSearch.addActionListener(e -> searchClient());
        bottomPanel.add(ButtonSearch);

        ButtonAdd = new Button("Добавить");
        ButtonAdd.addActionListener(e -> addClient());
        bottomPanel.add(ButtonAdd);

        // Создание текстовых полей
        bigField = new JTextField(30);
        bigField.setToolTipText("ФИО");

        JPanel contents = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contents.add(new JLabel("ФИО :"));
        contents.add(bigField  );
        setContentPane(contents);

        mainContainer.add(contents, BorderLayout.CENTER);
    }

    private void searchClient() {


    }

    private void addClient() {
        Client client = new Client(bigField.getText());
        Client.print();
    }

    public void start() {
        frame.setVisible(true);
    }
}
