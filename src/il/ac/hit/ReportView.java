package il.ac.hit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportView {

    private String[] days;
    private String[] months;

    private JFrame ReportFrame;
    private JButton m_ShowReportActionButton;
    private JLabel m_ShowReportStartDateDayLabel, m_ShowReportStartDateMonthLabel;
    private JLabel m_ShowReportEndDateDayLabel, m_ShowReportEndDateMonthLabel;
    private JComboBox m_ShowReportStartDateDayTextField, m_ShowReportStartDateMonthTextField;
    private JComboBox m_ShowReportEndDateDayTextField, m_ShowReportEndDateMonthTextField;
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
            days[i] = (i+1) +"";
        }
        months = new String[12];
        for (int i =0 ; i<12 ; i++) {
            months[i] = (i+1) +"";
        }

        ReportFrame = new JFrame();
        m_ShowReportStartDateDayLabel = new JLabel("Start day:");
        m_ShowReportStartDateDayTextField = new JComboBox(days);
        m_ShowReportStartDateMonthLabel = new JLabel("Start month:");
        m_ShowReportStartDateMonthTextField = new JComboBox(months);
        m_ShowReportEndDateDayLabel = new JLabel("End day:");
        m_ShowReportEndDateDayTextField = new JComboBox(days);
        m_ShowReportEndDateMonthLabel = new JLabel("End Month");
        m_ShowReportEndDateMonthTextField = new JComboBox(months);
        m_ShowReportActionButton = new JButton("Show the report");
    }

    private void showReportStart() {
        // Creating the detailed report Panel.
        ReportFrame.setLayout(new GridLayout(5,2));
        ReportFrame.add(m_ShowReportStartDateDayLabel);
        ReportFrame.add(m_ShowReportStartDateDayTextField);
        ReportFrame.add(m_ShowReportStartDateMonthLabel);
        ReportFrame.add(m_ShowReportStartDateMonthTextField);
        ReportFrame.add(m_ShowReportEndDateDayLabel);
        ReportFrame.add(m_ShowReportEndDateDayTextField);
        ReportFrame.add(m_ShowReportEndDateMonthLabel);
        ReportFrame.add(m_ShowReportEndDateMonthTextField);
        ReportFrame.add(m_ShowReportActionButton);

        ReportFrame.setSize(1000,700);
        ReportFrame.setVisible(true);

        m_ShowReportActionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_ViewModel.getDetailedReport(
                        java.sql.Date.valueOf("2021-" + m_ShowReportStartDateMonthTextField.getSelectedItem() + "-" + m_ShowReportStartDateDayTextField.getSelectedItem()),
                        java.sql.Date.valueOf("2021-" + m_ShowReportEndDateMonthTextField.getSelectedItem() + "-" + m_ShowReportEndDateDayTextField.getSelectedItem()));
            }
        });
    }

}
