package il.ac.hit.view;

import il.ac.hit.viewmodel.IViewModel;
import javax.swing.*;
import java.awt.*;


public class DetailedReportView {
    private final IViewModel viewModel;
    private final String userName;
    private JFrame reportFrame;
    private JButton showReportActionButton;
    private JLabel showReportStartDateLabel;
    private JLabel showReportEndDateLabel;
    private JComboBox showReportStartDateDayComboBox, showReportStartDateMonthComboBox, showReportStartDateYearComboBox;
    private JComboBox showReportEndDateDayComboBox, showReportEndDateMonthComboBox, showReportEndDateYearComboBox;

    /**
     * The ctor call the init and start methods to start and initialized the report view.
     * @param vm
     */
    public DetailedReportView(IViewModel vm, String userName) {
        viewModel = vm;
        this.userName = userName;
        showReportInit();
        showReportStart();
    }

    private void showReportInit() {
        String[] days;
        String[] months;
        String[] years;

        days = new String[31];
        for (int i =0 ; i<31 ; i++) {
            days[i] = String.valueOf(i+1);
        }
        months = new String[12];
        for (int i =0 ; i<12 ; i++) {
            months[i] = String.valueOf(i+1);
        }
        years = new String[16];
        for (int i =0 ; i <16 ; i++) {
            years[i] = String.valueOf(i+2010);
        }

        reportFrame = new JFrame();
        showReportStartDateLabel = new JLabel("Start date:");
        showReportStartDateDayComboBox = new JComboBox(days);
        showReportStartDateMonthComboBox = new JComboBox(months);
        showReportStartDateYearComboBox = new JComboBox(years);

        showReportEndDateLabel = new JLabel("End date:");
        showReportEndDateDayComboBox = new JComboBox(days);
        showReportEndDateMonthComboBox = new JComboBox(months);
        showReportEndDateYearComboBox = new JComboBox(years);

        showReportActionButton = new JButton("Show the report");
    }

    private void showReportStart() {
        reportFrame.setLayout(new FlowLayout());
        reportFrame.add(showReportStartDateLabel);
        reportFrame.add(showReportStartDateDayComboBox);
        reportFrame.add(showReportStartDateMonthComboBox);
        reportFrame.add(showReportStartDateYearComboBox);
        reportFrame.add(showReportEndDateLabel);
        reportFrame.add(showReportEndDateDayComboBox);
        reportFrame.add(showReportEndDateMonthComboBox);
        reportFrame.add(showReportEndDateYearComboBox);
        reportFrame.add(showReportActionButton);

        reportFrame.setTitle("Detailed Report");
        setComponentsSizeAndLocation();
        reportFrame.setVisible(true);

        showReportActionButton.addActionListener((e -> {
            viewModel.getDetailedReport(
                    java.sql.Date.valueOf(showReportStartDateYearComboBox.getSelectedItem()+ "-" +
                            showReportStartDateMonthComboBox.getSelectedItem() +
                            "-" + showReportStartDateDayComboBox.getSelectedItem()),
                    java.sql.Date.valueOf(showReportEndDateYearComboBox.getSelectedItem() + "-" +
                            showReportEndDateMonthComboBox.getSelectedItem() +
                            "-" + showReportEndDateDayComboBox.getSelectedItem()),
                    userName);

            reportFrame.dispose();
        }));

//        m_ShowReportActionButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                m_ViewModel.getDetailedReport(
//                        java.sql.Date.valueOf(m_ShowReportStartDateYearComboBox.getSelectedItem()+ "-" +
//                                m_ShowReportStartDateMonthComboBox.getSelectedItem() +
//                                "-" + m_ShowReportStartDateDayComboBox.getSelectedItem()),
//                        java.sql.Date.valueOf(m_ShowReportEndDateYearComboBox.getSelectedItem() + "-" +
//                                m_ShowReportEndDateMonthComboBox.getSelectedItem() +
//                                "-" + m_ShowReportEndDateDayComboBox.getSelectedItem()),
//                        m_Username);
//
//                m_ReportFrame.dispose();
//            }
//        });
    }

    private void setComponentsSizeAndLocation() {
        reportFrame.setSize(480,220);
        reportFrame.setResizable(false);

        showReportStartDateLabel.setBounds(50, 30, 80, 30);
        showReportEndDateLabel.setBounds(showReportStartDateLabel.getX(), showReportStartDateLabel.getY()+50, 80, 30);

        showReportStartDateDayComboBox.setBounds(showReportStartDateLabel.getWidth() + showReportStartDateLabel.getX(), showReportStartDateLabel.getY(), 100, 30);
        showReportStartDateMonthComboBox.setBounds(showReportStartDateDayComboBox.getX()+ showReportStartDateDayComboBox.getWidth() , showReportStartDateDayComboBox.getY(), 100, 30);
        showReportStartDateYearComboBox.setBounds(showReportStartDateMonthComboBox.getX() + showReportStartDateMonthComboBox.getWidth(), showReportStartDateDayComboBox.getY(),100,30);

        showReportEndDateDayComboBox.setBounds(showReportStartDateDayComboBox.getX(), showReportEndDateLabel.getY(), 100, 30);
        showReportEndDateMonthComboBox.setBounds(showReportStartDateMonthComboBox.getX(), showReportEndDateLabel.getY(), 100, 30);
        showReportEndDateYearComboBox.setBounds(showReportStartDateYearComboBox.getX(), showReportEndDateLabel.getY(),100,30);

        showReportActionButton.setSize(150,30);
        showReportActionButton.setLocation((reportFrame.getWidth()- showReportActionButton.getWidth())/2 , showReportEndDateMonthComboBox.getY() + showReportEndDateMonthComboBox.getHeight()+30);

    }
}
