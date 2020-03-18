package com.eip.serviceHelper;

import com.eip.domain.TimeSheet;
import com.eip.domain.TimeSheetDateStatus;
import com.eip.domain.UserDetail;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import utils.Constants;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Component
public class TimeSheetServiceHelper {

    public ByteArrayInputStream timeSheetsToExcel(TimeSheet timeSheets, UserDetail detail)
        throws IOException, ParseException {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

            Sheet sheet = workbook.createSheet(Constants.TIMESHEET);
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            CellStyle style = workbook.createCellStyle();
            CellStyle style1 = workbook.createCellStyle();
            style.setFillBackgroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            style1.setFont(headerFont);
            int cols = 1;
            Row row = sheet.createRow(0);
            Row row1 = sheet.createRow(1);
            Row row2 = sheet.createRow(2);
            Row row3 = sheet.createRow(3);

            String firstName = detail.getFirstName() == null ? "" : detail.getFirstName();
            String lastName = detail.getLastName() == null ? "" : detail.getLastName();
            String empId = detail.getEmpId() == null ? "" : detail.getEmpId();

            Cell headCell1 = row.createCell(0);
            Cell headCell2 = row1.createCell(0);
            Cell headCell3 = row2.createCell(0);
            headCell1.setCellStyle(style1);
            headCell2.setCellStyle(style1);
            headCell3.setCellStyle(style1);
            headCell1.setCellValue(Constants.Emp_Name+" :-" + firstName + " " + lastName);
            headCell2.setCellValue(Constants.EMP_ID + empId);
            headCell3.setCellValue(Constants.DAYS);

            for (TimeSheetDateStatus timeSheetDateStatus : timeSheets.getTimeSheetDateStatusList()) {
                System.out.println("timesheet status" + timeSheetDateStatus.getStatus());
                Cell cell1 = row.createCell(cols);
                Cell cell2 = row2.createCell(cols);
                Cell cell3 = row3.createCell(cols);
                cell1.setCellStyle(style);
                cell2.setCellStyle(style);
                cell1.setCellValue(timeSheetDateStatus.getDate().format(DateTimeFormatter.ofPattern(Constants.dd_MM_yyyy)));
                cell2.setCellValue(timeSheetDateStatus.getDate().format(DateTimeFormatter.ofPattern(Constants.EEE)));
                if (timeSheetDateStatus.getStatus().equals(Constants.PRESENT)) {
                    cell3.setCellValue(Constants.P);
                } else if (timeSheetDateStatus.getStatus().equals(Constants.HOLIDAY)) {
                    cell3.setCellValue(Constants.H);
                } else if (timeSheetDateStatus.getStatus().equals(Constants.CL)) {
                    cell3.setCellValue("CL");
                } else if (timeSheetDateStatus.getStatus().equals(Constants.SL)) {
                    cell3.setCellValue("SL");
                } else if (timeSheetDateStatus.getStatus().equals(Constants.WFH)) {
                    cell3.setCellValue("WFH");
                } else if (timeSheetDateStatus.getStatus().equals(Constants.WCL)) {
                    cell3.setCellValue("WCL");
                } else if (timeSheetDateStatus.getStatus().equals(Constants.CO)) {
                    cell3.setCellValue("CO");
               
                } else {
                    cell3.setCellValue(timeSheetDateStatus.getStatus() != null ? timeSheetDateStatus.getStatus() : Constants.ZERO);
                }
                cols++;
            }

            Row row8 = sheet.createRow(8);
            Row row9 = sheet.createRow(9);
            Row row10 = sheet.createRow(10);
            Row row11 = sheet.createRow(11);
            Row row12 = sheet.createRow(12);
            Row row13 = sheet.createRow(13);
            Row row14 = sheet.createRow(14);
            Row row15 = sheet.createRow(15);

            Cell cell8 = row8.createCell(0);
            cell8.setCellStyle(style1);
            cell8.setCellValue(Constants.LEGEND);
            row9.createCell(0).setCellValue(Constants.PRESENT);
            row9.createCell(1).setCellValue(Constants.P);
            row10.createCell(0).setCellValue(Constants.VACATION);
            row10.createCell(1).setCellValue("CL");
            row11.createCell(0).setCellValue(Constants.SICK_LEAVE);
            row11.createCell(1).setCellValue("SL");
            row12.createCell(0).setCellValue(Constants.WEEKEND);
            row12.createCell(1).setCellValue(Constants.H);
            row13.createCell(0).setCellValue(Constants.WFH);
            row13.createCell(1).setCellValue("WFH");
            row14.createCell(0).setCellValue(Constants.WCL);
            row14.createCell(1).setCellValue("WCL");
            row15.createCell(0).setCellValue(Constants.CO);
            row15.createCell(1).setCellValue("CO");
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }


    public ByteArrayInputStream wholeDataTimeSheetsToExcel(List<TimeSheet> timesheets)
        throws Exception {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(Constants.TIMESHEET);
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            CellStyle style = workbook.createCellStyle();
            CellStyle style1 = workbook.createCellStyle();
            style.setFillBackgroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            style1.setFont(headerFont);
            int cols = 1;
            int j = 5;
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue(Constants.ID);
            header.createCell(1).setCellValue(Constants.USER_NAME);
            header.createCell(2).setCellValue(Constants.EMAIL_ID);
            header.createCell(3).setCellValue(Constants.SAVE_DATE);
            header.createCell(4).setCellValue(Constants.SUBMIT_DATE);

            for (TimeSheetDateStatus timeSheetDateStatus : timesheets.get(0).getTimeSheetDateStatusList()) {
                header.createCell(j).setCellValue(timeSheetDateStatus.getDate().format(DateTimeFormatter.ofPattern(Constants.dd_MM_yyyy)));
                j++;
            }

            for (TimeSheet timeSheet : timesheets) {
                Row dataRow = sheet.createRow(cols);
                dataRow.createCell(0).setCellValue(timeSheet.getEmployeeId());
                dataRow.createCell(1).setCellValue(timeSheet.getUserName());
                dataRow.createCell(2).setCellValue(timeSheet.getEmailId());
                dataRow.createCell(3).setCellValue(timeSheet.getSaveDate().format(DateTimeFormatter.ofPattern(Constants.dd_MM_yyyy)));
                if (timeSheet.getSubmitDate() == null) {
                    dataRow.createCell(4).setCellValue(timeSheet.getSaveDate().format(DateTimeFormatter.ofPattern(Constants.dd_MM_yyyy)));
                } else {
                    dataRow.createCell(4).setCellValue(timeSheet.getSubmitDate().format(DateTimeFormatter.ofPattern(Constants.dd_MM_yyyy)));

                }

                j = 5;

                for (TimeSheetDateStatus timeSheetDateStatus : timeSheet.getTimeSheetDateStatusList()) {
                    System.out.println("timesheet status" + timeSheetDateStatus.getStatus());
                    if (timeSheetDateStatus.getStatus().equals(Constants.PRESENT) || timeSheetDateStatus.getStatus().equals(Constants.ABSENT)) {
                        dataRow.createCell(j).setCellValue(timeSheetDateStatus.getStatus().equals(Constants.PRESENT) ? 1 : 0);
                    } else if (timeSheetDateStatus.getStatus().equals(Constants.HOLIDAY)) {
                        dataRow.createCell(j).setCellValue(Constants.ZERO);
                    } else if (timeSheetDateStatus.getStatus().equals(Constants.CL)) {
                        dataRow.createCell(j).setCellValue(Constants.CL);
                    } else if (timeSheetDateStatus.getStatus().equals(Constants.SL)) {
                        dataRow.createCell(j).setCellValue(Constants.SL);
                    } else if (timeSheetDateStatus.getStatus().equals(Constants.WFH)) {
                        dataRow.createCell(j).setCellValue(Constants.WFH);
                    } else if (timeSheetDateStatus.getStatus().equals(Constants.WCL)) {
                        dataRow.createCell(j).setCellValue(Constants.WCL);
                    } else if (timeSheetDateStatus.getStatus().equals(Constants.CO)) {
                        dataRow.createCell(j).setCellValue(Constants.CO);
                    } else {
                        dataRow.createCell(j).setCellValue(timeSheetDateStatus.getStatus() != null ? timeSheetDateStatus.getStatus() : Constants.ZERO);
                    }
                    j++;
                }
                cols++;
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

}

