package sample.GeneratePDF;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import sample.Admin.DetailExams;
import sample.SceneManager;
import sample.Utilities.ConnectionUser;


import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;


public class GeneratePDF {
    private final String directory = System.getProperty("user.dir") + "/";
    private String outputFileLocation = directory;

    public static Font normal = FontFactory.getFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, 18, Font.NORMAL);


    public void setOutputFileLocation(String outputFileLocation) {
        this.outputFileLocation = outputFileLocation;
    }


    public void pdfMaker(int studentID){
        String query = "select * from student_general_info where Student_Id = " + studentID;
        ConnectionUser.MakeConnection();
        Connection connection = ConnectionUser.getConnection();
        try {
            String outputFileName = outputFileLocation + "\\معلومات الطالب - " + studentID + ".pdf";
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(outputFileName));
            document.open();

            PdfPTable table = new PdfPTable(1);
            table.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            document.add(table);

            Statement stmt = connection.createStatement();
            stmt.execute(query);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()){
                Paragraph paragraph1 = new Paragraph("اسم الطالب : " + rs.getString("Name"), normal);
                table.addCell(paragraph1);

                Paragraph paragraph2 = new Paragraph("رقم الطالب : " + rs.getString("Student_Id"), normal);
                table.addCell(paragraph2);

                Paragraph paragraph3 = new Paragraph("مدرسة : " + rs.getString("School"), normal);
                table.addCell(paragraph3);

                Paragraph paragraph4 = new Paragraph("تاريخ الانضمام معنا : " + rs.getString("Joined_Date"), normal);
                table.addCell(paragraph4);

                Paragraph paragraph5 = new Paragraph("رقم الصف : " + rs.getString("Grade_Id"), normal);
                table.addCell(paragraph5);

                Paragraph paragraph6 = new Paragraph("جروب الطالب : " + rs.getString("Group_Student"), normal);
                table.addCell(paragraph6);
            }
            document.add(tableName("معلومات الطالب"));
            document.add(table);
            Paragraph space = new Paragraph("\n");
            document.add(space);

            document.add(tableName("جدول حضور الطالب"));
            document.add(StudentAttendanceTable(studentID));
            document.add(space);

            document.add(tableName("جدول امتحانات الطالب"));
            document.add(StudentExams(studentID));
            document.add(space);

            document.add(tableName("جدول امتحانات الحصص"));
            document.add(StudentQuizs(studentID));
            document.close();
            stmt.close();
            System.out.println("Generated PDF Successfully");
            Desktop.getDesktop().open(new File(outputFileLocation));
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "طباعة بيانات الطالب", "تم نزول PDF بمعلومات الطالب", false, false);
        } catch (Exception e){
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "طباعة بيانات الطالب", "لم ينجح عملية الطباعة", false, true);
            System.err.println(e);
        }
    }

    PdfPTable StudentAttendanceTable(int studentID){
        String query = "select * from attendance as a join lectures as l on a.lec_Id = l.lec_Id where Student_Id = " + studentID;
        ConnectionUser.MakeConnection();
        Connection connection = ConnectionUser.getConnection();

        PdfPTable table = new PdfPTable(3);
        table.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);


        PdfPCell heading1 = new PdfPCell(new Phrase("حصة", normal));
        table.addCell(heading1);

        PdfPCell heading2 = new PdfPCell(new Phrase("مبلغ المدفوع", normal));
        table.addCell(heading2);

        PdfPCell heading3 = new PdfPCell(new Phrase("تاريخ الحضور", normal));
        table.addCell(heading3);

        table.setHeaderRows(1);
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(query);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()){
                PdfPCell cell = new PdfPCell(new Phrase(rs.getString("Lec_Name"), normal));
                table.addCell(cell);
                PdfPCell cell1 = new PdfPCell(new Phrase(rs.getString("Paied"), normal));
                table.addCell(cell1);
                PdfPCell cell2 = new PdfPCell(new Phrase(rs.getString("Attencdance_Date"), normal));
                table.addCell(cell2);
            }
            stmt.close();
        } catch (Exception e){
            System.err.println(e);
        }
        return table;
    }

    PdfPTable StudentExams(int studentID){
        String query = "select * from students_exams as se join exams as e on e.Exam_ID = se.Exam_ID where Student_Id = " + studentID;
        ConnectionUser.MakeConnection();
        Connection connection = ConnectionUser.getConnection();
        PdfPTable table = new PdfPTable(4);
        table.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

        PdfPCell heading1 = new PdfPCell(new Phrase("امتحان", normal));
        table.addCell(heading1);

        PdfPCell heading2 = new PdfPCell(new Phrase("درجة الطالب", normal));
        table.addCell(heading2);

        PdfPCell heading3 = new PdfPCell(new Phrase("درجة الامتحان", normal));
        table.addCell(heading3);

        PdfPCell heading4 = new PdfPCell(new Phrase("تاريخ", normal));
        table.addCell(heading4);

        table.setHeaderRows(1);
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(query);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()){
                PdfPCell cell = new PdfPCell(new Phrase(rs.getString("Exam_Name"), normal));
                table.addCell(cell);
                PdfPCell cell2 = new PdfPCell(new Phrase(rs.getString("Student_Degree"), normal));
                table.addCell(cell2);
                PdfPCell cell3 = new PdfPCell(new Phrase(rs.getString("Exam_Degree"), normal));
                table.addCell(cell3);
                PdfPCell cell1 = new PdfPCell(new Phrase(rs.getString("Exam_Date"), normal));
                table.addCell(cell1);

            }
            stmt.close();
        } catch (Exception e){
            System.err.println(e);
        }
        return table;
    }

    PdfPTable StudentQuizs(int studentID){
        String query = "select * from lecture_quiz as lq join lectures as l on l.lec_Id = lq.Lec_Id where lq.Student_Id = " + studentID;
        ConnectionUser.MakeConnection();
        Connection connection = ConnectionUser.getConnection();
        PdfPTable table = new PdfPTable(4);
        table.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

        PdfPCell heading1 = new PdfPCell(new Phrase("حصة", normal));
        table.addCell(heading1);

        PdfPCell heading2 = new PdfPCell(new Phrase("درجة الطالب", normal));
        table.addCell(heading2);

        PdfPCell heading3 = new PdfPCell(new Phrase("درجة الامتحان", normal));
        table.addCell(heading3);

        PdfPCell heading4 = new PdfPCell(new Phrase("تاريخ", normal));
        table.addCell(heading4);

        table.setHeaderRows(1);
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(query);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()){
                PdfPCell cell = new PdfPCell(new Phrase(rs.getString("Lec_Name"), normal));
                table.addCell(cell);
                PdfPCell cell2 = new PdfPCell(new Phrase(rs.getString("Degree"), normal));
                table.addCell(cell2);
                PdfPCell cell3 = new PdfPCell(new Phrase(rs.getString("Max_Degree"), normal));
                table.addCell(cell3);
                PdfPCell cell4 = new PdfPCell(new Phrase(rs.getString("Date"), normal));
                table.addCell(cell4);
            }
            stmt.close();
        } catch (Exception e){
            System.err.println(e);
        }
        return table;
    }

    PdfPTable tableName(String tableName){
        PdfPTable table = new PdfPTable(1);
        table.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

        Paragraph name = new Paragraph(tableName, normal);
        table.addCell(name);

        return table;
    }

    public void dailyPaidPDF(){
        String dateOfDay = '\"' + Date.valueOf(LocalDate.now()).toString() + '\"';
        String query = "select * from money where dateMoney = " + dateOfDay;
        ConnectionUser.MakeConnection();
        Connection connection = ConnectionUser.getConnection();
        try {
            String outputFileName = outputFileLocation + "السجل المالي اليومي" + ".pdf";
            Document document = new Document();


            PdfWriter.getInstance(document, new FileOutputStream(outputFileName));
            document.open();


            PdfPTable table = new PdfPTable(3);
            table.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            document.add(table);


            PdfPCell heading1 = new PdfPCell(new Phrase("الوصف", normal));
            table.addCell(heading1);

            PdfPCell heading2 = new PdfPCell(new Phrase("المبلغ", normal));
            table.addCell(heading2);

            PdfPCell heading3 = new PdfPCell(new Phrase("التاريخ", normal));
            table.addCell(heading3);

            table.setHeaderRows(1);

            document.add(table);

            Statement stmt = connection.createStatement();
            stmt.execute(query);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()){
                PdfPCell cell = new PdfPCell(new Phrase(rs.getString("descriptionMoney"), normal));
                table.addCell(cell);
                PdfPCell cell2 = new PdfPCell(new Phrase(rs.getString("money"), normal));
                table.addCell(cell2);
                PdfPCell cell3 = new PdfPCell(new Phrase(rs.getString("dateMoney"), normal));
                table.addCell(cell3);
            }
            document.add(table);
            stmt.close();
            document.close();
            System.out.println("Generated PDF Successfully");
            Desktop.getDesktop().open(new File(outputFileLocation));
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "طباعة السجل اليومي", "تم نزول PDF بالسجل اليومي", false, false);

        }catch (Exception e){
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "طباعة السجل اليومي", "لم ينجح عملية الطباعة", false, true);
            System.out.println(e.toString());
        }
    }

    public void totalPaidPDF(){
        String query = "select * from money ";
        ConnectionUser.MakeConnection();
        Connection connection = ConnectionUser.getConnection();
        try {
            String outputFileName = outputFileLocation + "السجل المالي الكلي" + ".pdf";
            Document document = new Document();


            PdfWriter.getInstance(document, new FileOutputStream(outputFileName));
            document.open();


            PdfPTable table = new PdfPTable(3);
            table.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            document.add(table);


            PdfPCell heading1 = new PdfPCell(new Phrase("الوصف", normal));
            table.addCell(heading1);

            PdfPCell heading2 = new PdfPCell(new Phrase("المبلغ", normal));
            table.addCell(heading2);

            PdfPCell heading3 = new PdfPCell(new Phrase("التاريخ", normal));
            table.addCell(heading3);

            table.setHeaderRows(1);

            document.add(table);

            Statement stmt = connection.createStatement();
            stmt.execute(query);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()){
                PdfPCell cell = new PdfPCell(new Phrase(rs.getString("descriptionMoney"), normal));
                table.addCell(cell);
                PdfPCell cell2 = new PdfPCell(new Phrase(rs.getString("money"), normal));
                table.addCell(cell2);
                PdfPCell cell3 = new PdfPCell(new Phrase(rs.getString("dateMoney"), normal));
                table.addCell(cell3);
            }
            document.add(table);
            stmt.close();
            document.close();
            System.out.println("Generated PDF Successfully");
            Desktop.getDesktop().open(new File(outputFileLocation));
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "طباعة السجل الكلي", "تم نزول PDF بالسجل الكلي", false, false);

        }catch (Exception e){
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "طباعة السجل الكلي", "لم ينجح عملية الطباعة", false, true);
            System.out.println(e.toString());
        }
    }

}