package sample.Utilities;

import sample.Classes.*;
import sample.Students.Update.QuizUpdateStudent;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static sample.Utilities.SQLQueries.GetResultsLike;

public class GetBigData {
    public static StudentProfile GetStudentByID(int StudentId) {
        StudentProfile studentProfile = new StudentProfile();
        try {
            String name = SQLQueries.GetString("Name", DatabaseTables.STUDENT_GENERAL_INFO, "Student_Id", StudentId);

            int GroupId = SQLQueries.GetInt("Grade_Id", DatabaseTables.STUDENT_GENERAL_INFO, "Student_Id", StudentId);
            String GradeName = SQLQueries.GetString("Garade_Name", DatabaseTables.GRADES, "Grade_Id", GroupId);

            String group = SQLQueries.GetString("Group_Student", DatabaseTables.STUDENT_GENERAL_INFO, "Student_Id", StudentId);

            String school = SQLQueries.GetString("School", DatabaseTables.STUDENT_GENERAL_INFO, "Student_Id", StudentId);

            String studentNumber = SQLQueries.GetString("Student_Phone", DatabaseTables.PHONES_NUMBER, "Student_Id", StudentId);
            String studentParentNumber = SQLQueries.GetString("Parent_Phone", DatabaseTables.PHONES_NUMBER, "Student_Id", StudentId);

            //int groupId = SQLQueries.GetInt("Grade_Id", DatabaseTables.STUDENT_GENERAL_INFO, "Student_Id", StudentId);
            int gradeId = SQLQueries.GetInt("Grade_Id", DatabaseTables.STUDENT_GENERAL_INFO, "Student_Id", StudentId);

            Date date = SQLQueries.GetDate("Joined_Date", DatabaseTables.STUDENT_GENERAL_INFO, "Student_Id", StudentId);

            studentProfile.setStudentName(name);
            studentProfile.setStudentGrade(GradeName);
            studentProfile.setStudentGradeID(gradeId);
            //studentProfile.setStudentGroupID(groupId);
            studentProfile.setStudentGroup(group);
            studentProfile.setStudentSchool(school);
            studentProfile.setStudentNumber(studentNumber);
            studentProfile.setStudentParentNumber(studentParentNumber);
            studentProfile.setStudentJoinedDate(date);
            studentProfile.setStudentID(StudentId);

            return studentProfile;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList GetStudentByName(String StudentName) {

        ArrayList<StudentProfile> studentProfiles =  new ArrayList<>();
        ResultSet rs = GetResultsLike("student_general_info", "Name", StudentName);
        try {
            while (rs.next()){
                System.out.println("WORK FROM GetStudentByID");

                int studentId = rs.getInt("Student_Id");

                String name = rs.getString("Name");

                int GroupId = rs.getInt("Grade_Id");

                String group = rs.getString("Group_Student");

                String school = rs.getString("School");


                int gradeId = rs.getInt("Grade_Id");

                Date date = rs.getDate("Joined_Date");

                StudentProfile studentProfile = new StudentProfile();

                studentProfile.setStudentName(name);
                studentProfile.setStudentGradeID(gradeId);
                studentProfile.setStudentGroup(group);
                studentProfile.setStudentSchool(school);
                studentProfile.setStudentJoinedDate(date);
                studentProfile.setStudentID(studentId);

                studentProfiles.add(studentProfile);
            }


            return studentProfiles;
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }




    public static ArrayList<Book> GetBooks(int gradeID) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            ResultSet rs = SQLQueries.GetResults(DatabaseTables.BOOKS, "Grade", gradeID);
            while (rs.next())
            {
                Book book = new Book (
                        rs.getInt("Book_Id"),
                        rs.getString("Name"),
                        rs.getDouble("Book_Price"),
                        rs.getInt("Grade")
                );
                books.add(book);
            }

            return books;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<OwnedBook> GetOwnedBooks(int studentID, int gradeID) {
        ArrayList<Book> books = GetBooks(gradeID);
        ArrayList<OwnedBook> ownedBooks = new ArrayList<>();
        try {
            ResultSet rs = SQLQueries.GetResults(DatabaseTables.BOOKS_SALES, "Student_Id", studentID);
            while (rs.next())
            {
                OwnedBook ownedBook = new OwnedBook (
                        rs.getInt("Book_Id"),
                        rs.getInt("Paid"),
                        rs.getDate("Sell_Date")
                );

                for (Book book : books)
                {
                    if (book.getBookID() == ownedBook.getBookId())
                    {
                        ownedBook.setBookName(book.getBookName());
                        ownedBook.setFullPrice(book.getFullPrice());
                        break;
                    }
                }
                ownedBooks.add(ownedBook);
            }

            return ownedBooks;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<QuizUpdateStudent.QuizTable> GetOwnedQuizs(int studentID) {
        ArrayList<QuizUpdateStudent.QuizTable> degreeTables = new ArrayList<>();
        try {
            ResultSet rs = SQLQueries.GetResults(DatabaseTables.LECTURE_QUIZ, "Student_Id", studentID);

            while (rs.next())
            {
                ResultSet rsLec = SQLQueries.GetResults(DatabaseTables.LECTURES, "Lec_Id", rs.getInt("Lec_Id"));

                if (rsLec.next())
                {
                    QuizUpdateStudent.QuizTable ownedExam = new QuizUpdateStudent.QuizTable(
                            rsLec.getString("Lec_Name"),
                            rs.getInt("Degree"),
                            rsLec.getInt("Max_Degree"),
                            rsLec.getDate("Date").toString()
                    );

                    degreeTables.add(ownedExam);
                }
            }

            return degreeTables;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<DegreeTable> GetOwnedExams(int studentID) {
        ArrayList<DegreeTable> degreeTables = new ArrayList<>();
        try {
            ResultSet rs = SQLQueries.GetResults(DatabaseTables.STUDENTS_EXAMS, "Student_Id", studentID);

            while (rs.next())
            {
                ResultSet rsExam = SQLQueries.GetResults(DatabaseTables.EXAMS, "Exam_ID", rs.getInt("Exam_ID"));
                rsExam.next();
                DegreeTable ownedExam = new DegreeTable (
                    rsExam.getString("Exam_Name"),
                    rs.getDate("Exam_Date"),
                    rs.getInt("Student_Degree"),
                    rsExam.getInt("Exam_Degree"),
                    rsExam.getInt("IsFinal")
                );

                degreeTables.add(ownedExam);
            }

            return degreeTables;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Attendance> GetOwnedAttendance(int studentID) {
        ArrayList<Attendance> attendances = new ArrayList<>();
        try {
            ResultSet rs = SQLQueries.GetResults(DatabaseTables.ATTENDANCE, "Student_Id", studentID);

            while (rs.next()) {
                ResultSet rsLec = SQLQueries.GetResults(DatabaseTables.LECTURES, "Lec_Id", rs.getInt("Lec_Id"));
                Map<String, Object> hashMap = new HashMap<>();
                hashMap.put("Student_Id", rs.getInt("Student_Id"));
                hashMap.put("Lec_Id", rs.getInt("Lec_Id"));
                ResultSet rsQuiz = SQLQueries.GetResults(DatabaseTables.LECTURE_QUIZ, hashMap);
                rsLec.next();

                int examValue = 0;

                if (rsQuiz.next())
                {
                    examValue = rsQuiz.getInt("Degree");
                }

                Attendance ownedAttendance = new Attendance (
                        rsLec.getInt("Lec_Id"),
                        rs.getInt("Paied"),
                        rs.getDate("Attencdance_Date"),
                        examValue,
                        rsLec.getInt("Max_Degree")
                );

                attendances.add(ownedAttendance);
            }

            return attendances;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public  static ArrayList<Assistance> GetAsssistanceRecords() {
        ArrayList<Assistance> assistances = new ArrayList<>();
        try {
            ResultSet rs = SQLQueries.GetResults(DatabaseTables.SECRETARY_DATA);

            while (rs.next())
            {
                int id = rs.getInt("Secretary_Id");
                String name = rs.getString("Secretary_Name");
                String address = rs.getString("Address");
                String ssn = rs.getString("SSN");
                Date date = rs.getDate("Joined_Date");
                int salary = rs.getInt("salary");

                assistances.add(new Assistance(
                        id,
                        name,
                        address,
                        date.toString(),
                        ssn,
                        salary
                ));
            }
            return assistances;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static ArrayList<DailyLog> GetDailyRecords() {

        ArrayList<DailyLog> dailyLogs = new ArrayList<>();
        try {
            String dailyDate = Date.valueOf(LocalDate.now()).toString();
            Map<String, Object> mapCondition = new HashMap<>();
            mapCondition.put("dateMoney", dailyDate);
            ResultSet rs = SQLQueries.GetResults(DatabaseTables.MONEY, mapCondition);

            while (rs.next())
            {
                String description = rs.getString("descriptionMoney");
                int paid = rs.getInt("money");
                Date date = rs.getDate("dateMoney");

                dailyLogs.add(new DailyLog(
                        description,
                        paid,
                        date.toString()
                ));
            }
            return dailyLogs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
