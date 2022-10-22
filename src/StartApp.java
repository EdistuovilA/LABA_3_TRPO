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
    private static JFrame frame;
    private JPanel bottomPanel;
    private Button ButtonAdd;
    private Button ButtonSearch;
    JTextField bigField;

    private static boolean visible = false;

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

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.setSize(100, 100);
        frame.add(bottomPanel);

        ButtonSearch = new Button("Найти");
        ButtonSearch.addActionListener(e -> searchPanel());
        bottomPanel.add(ButtonSearch);

        ButtonAdd = new Button("Добавить");
        ButtonAdd.addActionListener(e -> addPanel());
        bottomPanel.add(ButtonAdd);


    }

    private void searchPanel() {
        SearchPanel searchPanel = new SearchPanel();
        frame.setVisible(false);
    }

    private void addPanel() {
        AddPanel addPanel = new AddPanel();
        frame.setVisible(false);
    }

    public static void start() {
        visible = !visible;
        frame.setVisible(visible);
    }
}
