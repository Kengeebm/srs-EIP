package com.eip.serviceHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.eip.domain.UserDetailsLeave;

import utils.Constants;

@Component
public class UserDetailsLeaveServiceHelper {

    public ByteArrayInputStream userDetailLeaveDataToExcel(List<UserDetailsLeave> userdetailleaves)
        throws IOException, ParseException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet("UserDetailLeave");
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            CellStyle style = workbook.createCellStyle();
            CellStyle style1 = workbook.createCellStyle();
            style.setFillBackgroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            style1.setFont(headerFont);
            int cols = 1;
            
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue(Constants.EMPLOYEE_ID);
            header.createCell(1).setCellValue(Constants.Emp_Name);
            header.createCell(2).setCellValue(Constants.LEAVE_TYPE);
            header.createCell(3).setCellValue(Constants.FROM_DATE);
            header.createCell(4).setCellValue(Constants.TO_DATE);
            header.createCell(5).setCellValue(Constants.TOTAL_DAYS);
            header.createCell(6).setCellValue(Constants.REASON_FOR_LEAVE);
            header.createCell(7).setCellValue(Constants.STATUS);

           
            for (UserDetailsLeave userDetailLeave : userdetailleaves) {
                Row dataRow = sheet.createRow(cols);
                dataRow.createCell(0).setCellValue(userDetailLeave.getEmployeeId());
                dataRow.createCell(1).setCellValue(userDetailLeave.getEmpName());
                dataRow.createCell(2).setCellValue(userDetailLeave.getLeaveType());
                dataRow.createCell(3).setCellValue(userDetailLeave.getFromDate());
                dataRow.createCell(4).setCellValue(userDetailLeave.getToDate());
                dataRow.createCell(5).setCellValue(userDetailLeave.getTotalDays());
                dataRow.createCell(6).setCellValue(userDetailLeave.getReasonForLeave());
                dataRow.createCell(7).setCellValue(userDetailLeave.getStatus());		

                
            }cols++;
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

}

