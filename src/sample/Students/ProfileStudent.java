package sample.Students;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.Classes.StudentProfile;
import sample.GeneratePDF.GeneratePDF;
import sample.SceneManager;

public class  ProfileStudent {
    StudentProfile studentProfile;
    //region Labels
    @FXML private Label ProfileIdLabel;
    @FXML private Label ProfileNameLabel;
    @FXML private Label ProfileSchoolLabel;
    @FXML private Label ProfileNumberLabel;
    @FXML private Label ProfileParentNumberLabel;
    @FXML private Label ProfileGroupLabel;
    @FXML private Label ProfileGradeLabel;
    @FXML private Label ProfileDateLabel;
    //endregion

    //region Set Profile
    public void SetProfileStudent(StudentProfile _studentProfile) {
        this.studentProfile = _studentProfile;
        GetStudentData(this.studentProfile.getStudentID());
    }
    //endregion

    public void GoToGradeStudent() {
        GradeStudents gs = SceneManager.openNewWindow("Students/GradeStudents.fxml","admin");
        gs.SendData(studentProfile);
    }

    public void BackToGradeStudent() {
        SceneManager sceneManager = new SceneManager();
        sceneManager.changeScene("Students/student.fxml","admin");
    }

    public void GoToDailyStudent() {
        DetailStudent detailStudent = SceneManager.openNewWindow("Students/DeatailStudent.fxml","admin");
        detailStudent.SendData(studentProfile);
    }

    private void GetStudentData(int id) {
        try {
            int StudentId = id;

            ProfileIdLabel.setText(String.valueOf(studentProfile.getStudentID()));
            ProfileNameLabel.setText(studentProfile.getStudentName());
            ProfileGradeLabel.setText(studentProfile.getStudentGrade());
            ProfileGroupLabel.setText(studentProfile.getStudentGroup());
            ProfileSchoolLabel.setText(studentProfile.getStudentSchool());
            ProfileNumberLabel.setText(studentProfile.getStudentNumber());
            ProfileParentNumberLabel.setText(studentProfile.getStudentParentNumber());
            ProfileDateLabel.setText(studentProfile.getStudentJoinedDate().toString());

        } catch (NumberFormatException e) {
            e.printStackTrace();
            ProfileIdLabel.setText("-");
            ProfileNameLabel.setText("-");
            ProfileGradeLabel.setText("-");
            ProfileGroupLabel.setText("-");
            ProfileSchoolLabel.setText("-");
            ProfileNumberLabel.setText("-");
            ProfileParentNumberLabel.setText("-");
            ProfileDateLabel.setText("-");
        }
    }

    public void makePDF(){
        int id = studentProfile.getStudentID();

        GeneratePDF generatePDF = new GeneratePDF();

        generatePDF.pdfMaker(id);

    }
}
