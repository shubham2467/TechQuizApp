package techquizapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import techquizapp.dbutil.DBConnection;
import techquizapp.pojo.Question;
import techquizapp.pojo.QuestionStore;

/**
 *
 * @author Ankit
 */
public class QuestionDAO {
    public static void addQuestions(QuestionStore qstore)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into questions values(?,?,?,?,?,?,?,?,?)");
        ArrayList<Question> questionsList=qstore.getAllQuestions();
        for(Question q:questionsList){
            ps.setString(1,q.getExamId());
            ps.setInt(2,q.getQno());
            ps.setString(3,q.getQuestion());
            ps.setString(4,q.getAnswer1());
            ps.setString(5,q.getAnswer2());
            ps.setString(6,q.getAnswer3());
            ps.setString(7,q.getAnswer4());
            ps.setString(8,q.getCorrectAnswer());
            ps.setString(9,q.getLanguage());
            ps.executeUpdate();
        }
    }
    
    public static ArrayList<Question> getQuestionsByExamId(String examId)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select * from questions where examid=?");
        ps.setString(1,examId);
        ResultSet rs=ps.executeQuery();
        ArrayList<Question> questionList=new ArrayList<>();
        while(rs.next()){
            int qno=rs.getInt(2);
            String question=rs.getString(3);
            String option1=rs.getString(4);
            String option2=rs.getString(5);
            String option3=rs.getString(6);
            String option4=rs.getString(7);
            String correctOption=rs.getString(8);
            String language=rs.getString(9);
            Question obj=new Question(examId, qno,language,question,option1, option2, option3, option4,correctOption);
            questionList.add(obj);
        }
        return questionList;
    }
    public static void updateQuestion(QuestionStore qstore)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update questions set question=?,answer1=?,answer2=?,answer3=?,answer4=?,correct_answer=? where examid=? and qno=?");
        ArrayList<Question> questionsList=qstore.getAllQuestions();
        for(Question q:questionsList){
            ps.setString(1,q.getQuestion());
            ps.setString(2,q.getAnswer1());
            ps.setString(3,q.getAnswer2());
            ps.setString(4,q.getAnswer3());
            ps.setString(5, q.getAnswer4());
            ps.setString(6, q.getCorrectAnswer());
            ps.setString(7,q.getExamId());
            ps.setInt(8, q.getQno());
            ps.executeUpdate();
        }
    }
}
