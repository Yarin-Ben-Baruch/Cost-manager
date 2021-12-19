package il.ac.hit.View;

import il.ac.hit.ViewModel.IViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportView {

    private String[] days;
    private String[] months;
    private String[] years;

    private JFrame m_ReportFrame;
    private JButton m_ShowReportActionButton;
    private JLabel m_ShowReportStartDateDayLabel, m_ShowReportStartDateMonthLabel;
    private JLabel m_ShowReportEndDateDayLabel, m_ShowReportEndDateMonthLabel;
    private JComboBox m_ShowReportStartDateDayTextField, m_ShowReportStartDateMonthTextField,m_ShowReportStartDateYearTextField;
    private JComboBox m_ShowReportEndDateDayTextField, m_ShowReportEndDateMonthTextField,m_ShowReportEndDateYearTextField;

    private IViewModel m_ViewModel;

    public ReportView(IViewModel i_Vm) {
        m_ViewModel = i_Vm;
        showReportInit();
        showReportStart();
    }

    private void showReportInit() {
        // Creating the detailed report Action.
        days = new String[31];
        for (int i =0 ; i<31 ; i++) {
            days[i] = String.valueOf(i+1);
        }
        months = new String[12];
        for (int i =0 ; i<12 ; i++) {
            months[i] = String.valueOf(i+1);
        }
        years = new String[15];
        for (int i =0 ; i <15 ; i++) {
            years[i] = String.valueOf(i+2010);
        }

        m_ReportFrame = new JFrame();
        m_ShowReportStartDateDayLabel = new JLabel("Start date:");
        m_ShowReportStartDateDayTextField = new JComboBox(days);
        m_ShowReportStartDateMonthTextField = new JComboBox(months);
        m_ShowReportStartDateYearTextField = new JComboBox(years);

//        m_ShowReportStartDateMonthLabel = new JLabel("Start month:");
//        m_ShowReportEndDateMonthLabel = new JLabel("End Month");

        m_ShowReportEndDateDayLabel = new JLabel("End date:");
        m_ShowReportEndDateDayTextField = new JComboBox(days);
        m_ShowReportEndDateMonthTextField = new JComboBox(months);
        m_ShowReportEndDateYearTextField = new JComboBox(years);

        m_ShowReportActionButton = new JButton("Show the report");
    }

    private void showReportStart() {
        // Creating the detailed report Panel.

        m_ReportFrame.setLayout(null);
        m_ReportFrame.add(m_ShowReportStartDateDayLabel);
        m_ReportFrame.add(m_ShowReportStartDateDayTextField);
        m_ReportFrame.add(m_ShowReportStartDateMonthTextField);
        m_ReportFrame.add(m_ShowReportStartDateYearTextField);

        m_ReportFrame.add(m_ShowReportEndDateDayLabel);
        m_ReportFrame.add(m_ShowReportEndDateDayTextField);
        m_ReportFrame.add(m_ShowReportEndDateMonthTextField);
        m_ReportFrame.add(m_ShowReportEndDateYearTextField);

        m_ReportFrame.add(m_ShowReportActionButton);

//        m_ReportFrame.add(m_ShowReportStartDateMonthLabel);
// m_ReportFrame.add(m_ShowReportEndDateMonthLabel);

        m_ReportFrame.setTitle("Detailed Report");
        m_ReportFrame.setSize(400,350);

        m_ShowReportStartDateDayLabel.setBounds(50, 30, 150, 30);
        m_ShowReportStartDateMonthLabel.setBounds(m_ShowReportStartDateDayLabel.getX(),m_ShowReportStartDateDayLabel.getY()+50, 100, 30);
        m_ShowReportEndDateDayLabel.setBounds(m_ShowReportStartDateDayLabel.getX(),m_ShowReportStartDateMonthLabel.getY()+50, 100, 30);
        m_ShowReportEndDateMonthLabel.setBounds(m_ShowReportStartDateDayLabel.getX(),m_ShowReportEndDateDayLabel.getY() + 50, 100, 30);

        m_ShowReportStartDateDayTextField.setBounds(m_ShowReportStartDateDayLabel.getWidth() + m_ShowReportStartDateDayLabel.getX(), m_ShowReportStartDateDayLabel.getY(), 150, 30);
        m_ShowReportStartDateMonthTextField.setBounds(m_ShowReportStartDateDayTextField.getX(), m_ShowReportStartDateMonthLabel.getY(), 150, 30);
        m_ShowReportEndDateDayTextField.setBounds(m_ShowReportStartDateDayTextField.getX(), m_ShowReportEndDateDayLabel.getY(), 150, 30);
        m_ShowReportEndDateMonthTextField.setBounds(m_ShowReportStartDateDayTextField.getX(), m_ShowReportEndDateMonthLabel.getY(), 150, 30);
        m_ShowReportActionButton.setSize(150,30);
        m_ShowReportActionButton.setLocation((m_ReportFrame.getWidth()-m_ShowReportActionButton.getWidth())/2 , m_ShowReportEndDateMonthTextField.getY() + m_ShowReportEndDateMonthTextField.getHeight()+30);

        m_ReportFrame.setVisible(true);

        m_ShowReportActionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_ViewModel.getDetailedReport(
                        java.sql.Date.valueOf("2021-" + m_ShowReportStartDateMonthTextField.getSelectedItem() +
                                "-" + m_ShowReportStartDateDayTextField.getSelectedItem()),
                        java.sql.Date.valueOf("2021-" + m_ShowReportEndDateMonthTextField.getSelectedItem() +
                                "-" + m_ShowReportEndDateDayTextField.getSelectedItem()));

                m_ReportFrame.dispose();
            }
        });
    }

}
