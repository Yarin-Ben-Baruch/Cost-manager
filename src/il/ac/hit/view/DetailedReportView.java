package il.ac.hit.view;

import il.ac.hit.viewmodel.IViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class representing receive Information button in the app.
 * Within the department there is a view of selecting dates for the display from a specific date to an end date.
 */
public class DetailedReportView {

    //Fields.
    private final IViewModel viewModel;
    private final String userName;
    private JFrame reportFrame;
    private JButton showReportActionButton;
    private JLabel showReportStartDateLabel;
    private JLabel showReportEndDateLabel;
    private JComboBox<String> showReportStartDateDayComboBox;
    private JComboBox<String> showReportStartDateMonthComboBox;
    private JComboBox<String> showReportStartDateYearComboBox;
    private JComboBox<String> showReportEndDateDayComboBox;
    private JComboBox<String> showReportEndDateMonthComboBox;
    private JComboBox<String> showReportEndDateYearComboBox;

    /**
     * The ctor call the init and start methods to start and initialized the report view.
     * @param vm  An object that holds the link to the viewModel class
     */
    public DetailedReportView(IViewModel vm, String userName) {
        viewModel = vm;
        this.userName = userName;
        showReportInit();
        showReportStart();
    }

    private void showReportInit() {
        // Creating Arrays of the days months and years we support.
        String[] days;
        String[] months;
        String[] years;

        //Initializes the days.
        days = new String[31];
        for (int i =0 ; i<31 ; i++) {
            days[i] = String.valueOf(i+1);
        }

        //Initializes the months.
        months = new String[12];
        for (int i =0 ; i<12 ; i++) {
            months[i] = String.valueOf(i+1);
        }

        //Initializes the years.
        years = new String[16];
        for (int i =0 ; i <16 ; i++) {
            years[i] = String.valueOf(i+2010);
        }

        // Creates new fields.
        reportFrame = new JFrame();
        showReportStartDateLabel = new JLabel("Start date:");
        showReportStartDateDayComboBox = new JComboBox<>(days);
        showReportStartDateMonthComboBox = new JComboBox<>(months);
        showReportStartDateYearComboBox = new JComboBox<>(years);
        showReportEndDateLabel = new JLabel("End date:");
        showReportEndDateDayComboBox = new JComboBox<>(days);
        showReportEndDateMonthComboBox = new JComboBox<>(months);
        showReportEndDateYearComboBox = new JComboBox<>(years);
        showReportActionButton = new JButton("Show the report");
    }

    private void showReportStart() {
        setColorToLabel();

        // this will center the frame
        reportFrame.setLocationRelativeTo(null);

        reportFrame.setContentPane(new JLabel(new ImageIcon("src/images/General background.jpg")));

        // Add to the frame.
        reportFrame.add(showReportStartDateLabel);
        reportFrame.add(showReportStartDateDayComboBox);
        reportFrame.add(showReportStartDateMonthComboBox);
        reportFrame.add(showReportStartDateYearComboBox);
        reportFrame.add(showReportEndDateLabel);
        reportFrame.add(showReportEndDateDayComboBox);
        reportFrame.add(showReportEndDateMonthComboBox);
        reportFrame.add(showReportEndDateYearComboBox);
        reportFrame.add(showReportActionButton);

        reportFrame.setLayout(new BorderLayout());
        reportFrame.setTitle("Detailed Report");
        reportFrame.setSize(480,220);
        reportFrame.setResizable(false);
        setComponentsSizeAndLocation();
        reportFrame.setVisible(true);

        // Action listener.
        showReportActionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // show the report.
                viewModel.getDetailedReport(
                        java.sql.Date.valueOf(showReportStartDateYearComboBox.getSelectedItem()+ "-" +
                                showReportStartDateMonthComboBox.getSelectedItem() +
                                "-" + showReportStartDateDayComboBox.getSelectedItem()),
                        java.sql.Date.valueOf(showReportEndDateYearComboBox.getSelectedItem() + "-" +
                                showReportEndDateMonthComboBox.getSelectedItem() +
                                "-" + showReportEndDateDayComboBox.getSelectedItem()),
                        userName);

                reportFrame.dispose();
            }
        });
    }

    // Set bounds and sizes.
    private void setComponentsSizeAndLocation() {

        showReportStartDateLabel.setBounds(50, 30, 80, 30);
        showReportEndDateLabel.setBounds(showReportStartDateLabel.getX(), showReportStartDateLabel.getY()+50,
                80, 30);

        showReportStartDateDayComboBox.setBounds(showReportStartDateLabel.getWidth() + showReportStartDateLabel.getX(),
                showReportStartDateLabel.getY(), 100, 30);
        showReportStartDateMonthComboBox.setBounds(showReportStartDateDayComboBox.getX() +
                showReportStartDateDayComboBox.getWidth() , showReportStartDateDayComboBox.getY(), 100, 30);
        showReportStartDateYearComboBox.setBounds(showReportStartDateMonthComboBox.getX() +
                showReportStartDateMonthComboBox.getWidth(), showReportStartDateDayComboBox.getY(),100,30);

        showReportEndDateDayComboBox.setBounds(showReportStartDateDayComboBox.getX(),
                showReportEndDateLabel.getY(), 100, 30);
        showReportEndDateMonthComboBox.setBounds(showReportStartDateMonthComboBox.getX(),
                showReportEndDateLabel.getY(), 100, 30);
        showReportEndDateYearComboBox.setBounds(showReportStartDateYearComboBox.getX(),
                showReportEndDateLabel.getY(),100,30);

        showReportActionButton.setSize(150,30);
        showReportActionButton.setLocation((reportFrame.getWidth()- showReportActionButton.getWidth())/2 ,
                showReportEndDateMonthComboBox.getY() + showReportEndDateMonthComboBox.getHeight()+30);

    }

    // Set colors.
    private void setColorToLabel(){
        showReportStartDateLabel.setForeground(Color.WHITE);
        showReportEndDateLabel.setForeground(Color.WHITE);
    }
}
