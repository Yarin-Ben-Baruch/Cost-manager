package il.ac.hit.View;

import il.ac.hit.ViewModel.IViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportView {
    private IViewModel m_ViewModel;
    private String m_Username;
    private String[] days;
    private String[] months;
    private String[] years;
    private JFrame m_ReportFrame;
    private JButton m_ShowReportActionButton;
    private JLabel m_ShowReportStartDateLabel;
    private JLabel m_ShowReportEndDateLabel;
    private JComboBox m_ShowReportStartDateDayComboBox, m_ShowReportStartDateMonthComboBox, m_ShowReportStartDateYearComboBox;
    private JComboBox m_ShowReportEndDateDayComboBox, m_ShowReportEndDateMonthComboBox, m_ShowReportEndDateYearComboBox;

    /**
     * The ctor call the init and start methods to start and initialized the report view.
     * @param i_Vm
     */
    public ReportView(IViewModel i_Vm, String i_Username) {
        m_ViewModel = i_Vm;
        m_Username = i_Username;
        showReportInit();
        showReportStart();
    }

    private void showReportInit() {
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

        m_ReportFrame = new JFrame();
        m_ShowReportStartDateLabel = new JLabel("Start date:");
        m_ShowReportStartDateDayComboBox = new JComboBox(days);
        m_ShowReportStartDateMonthComboBox = new JComboBox(months);
        m_ShowReportStartDateYearComboBox = new JComboBox(years);

        m_ShowReportEndDateLabel = new JLabel("End date:");
        m_ShowReportEndDateDayComboBox = new JComboBox(days);
        m_ShowReportEndDateMonthComboBox = new JComboBox(months);
        m_ShowReportEndDateYearComboBox = new JComboBox(years);

        m_ShowReportActionButton = new JButton("Show the report");
    }

    private void showReportStart() {
        m_ReportFrame.setLayout(null);
        m_ReportFrame.add(m_ShowReportStartDateLabel);
        m_ReportFrame.add(m_ShowReportStartDateDayComboBox);
        m_ReportFrame.add(m_ShowReportStartDateMonthComboBox);
        m_ReportFrame.add(m_ShowReportStartDateYearComboBox);
        m_ReportFrame.add(m_ShowReportEndDateLabel);
        m_ReportFrame.add(m_ShowReportEndDateDayComboBox);
        m_ReportFrame.add(m_ShowReportEndDateMonthComboBox);
        m_ReportFrame.add(m_ShowReportEndDateYearComboBox);
        m_ReportFrame.add(m_ShowReportActionButton);

        m_ReportFrame.setTitle("Detailed Report");
        setComponentsSizeAndLocation();
        m_ReportFrame.setVisible(true);

        m_ShowReportActionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_ViewModel.getDetailedReport(
                        java.sql.Date.valueOf(m_ShowReportStartDateYearComboBox.getSelectedItem()+ "-" +
                                m_ShowReportStartDateMonthComboBox.getSelectedItem() +
                                "-" + m_ShowReportStartDateDayComboBox.getSelectedItem()),
                        java.sql.Date.valueOf(m_ShowReportEndDateYearComboBox.getSelectedItem() + "-" +
                                m_ShowReportEndDateMonthComboBox.getSelectedItem() +
                                "-" + m_ShowReportEndDateDayComboBox.getSelectedItem()),
                        m_Username);

                m_ReportFrame.dispose();
            }
        });
    }

    private void setComponentsSizeAndLocation() {
        m_ReportFrame.setSize(480,220);
        m_ReportFrame.setResizable(false);

        m_ShowReportStartDateLabel.setBounds(50, 30, 80, 30);
        m_ShowReportEndDateLabel.setBounds(m_ShowReportStartDateLabel.getX(), m_ShowReportStartDateLabel.getY()+50, 80, 30);

        m_ShowReportStartDateDayComboBox.setBounds(m_ShowReportStartDateLabel.getWidth() + m_ShowReportStartDateLabel.getX(), m_ShowReportStartDateLabel.getY(), 100, 30);
        m_ShowReportStartDateMonthComboBox.setBounds(m_ShowReportStartDateDayComboBox.getX()+ m_ShowReportStartDateDayComboBox.getWidth() , m_ShowReportStartDateDayComboBox.getY(), 100, 30);
        m_ShowReportStartDateYearComboBox.setBounds(m_ShowReportStartDateMonthComboBox.getX() + m_ShowReportStartDateMonthComboBox.getWidth(), m_ShowReportStartDateDayComboBox.getY(),100,30);

        m_ShowReportEndDateDayComboBox.setBounds(m_ShowReportStartDateDayComboBox.getX(), m_ShowReportEndDateLabel.getY(), 100, 30);
        m_ShowReportEndDateMonthComboBox.setBounds(m_ShowReportStartDateMonthComboBox.getX(), m_ShowReportEndDateLabel.getY(), 100, 30);
        m_ShowReportEndDateYearComboBox.setBounds(m_ShowReportStartDateYearComboBox.getX(), m_ShowReportEndDateLabel.getY(),100,30);

        m_ShowReportActionButton.setSize(150,30);
        m_ShowReportActionButton.setLocation((m_ReportFrame.getWidth()-m_ShowReportActionButton.getWidth())/2 , m_ShowReportEndDateMonthComboBox.getY() + m_ShowReportEndDateMonthComboBox.getHeight()+30);

    }

}
