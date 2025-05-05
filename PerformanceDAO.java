
package techquizapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import techquizapp.dbutil.DBConnection;
import techquizapp.pojo.Performance;
import techquizapp.pojo.StudentScore;
/**
 *
 * @author Ankit
 */
public class PerformanceDAO {
    public static void addPerformance(Performance p)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into performance values(?,?,?,?,?,?,?)");
        ps.setString(1,p.getUserId());
        ps.setString(2,p.getExamId());
        ps.setInt(3,p.getRight());
        ps.setInt(4,p.getWrong());
        ps.setInt(5,p.getUnattempted());
        ps.setDouble(6,p.getPer());
        ps.setString(7,p.getLanguage());
        ps.executeUpdate();
    }
    public static ArrayList<Performance> getAllData()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ArrayList<Performance> performanceList=new ArrayList<>();
        ResultSet rs=st.executeQuery("select * from performance order by userid,examid");
        while(rs.next()){
            String userid=rs.getString(1);
            String examid=rs.getString(2);
            int right=rs.getInt(3);
            int wrong=rs.getInt(4);
            int unattempted=rs.getInt(5);
            double per=rs.getDouble(6);
            String lang=rs.getString(7);
            Performance p=new Performance(examid,lang,userid,right,wrong,unattempted,per);
            performanceList.add(p);
        }
        return performanceList;
    }
    public static ArrayList<String> getAllStudentId()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ArrayList<String> studentIdList=new ArrayList<>();
        ResultSet rs=st.executeQuery("Select distinct userid from performance");
        while(rs.next()){
            String id=rs.getString(1);
            studentIdList.add(id);
        }
        return studentIdList;
    } 
    public static ArrayList<String> getAllExamID(String studentId)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select examid from performance where userid=?");
        ps.setString(1,studentId);
        ArrayList<String> examIdList=new ArrayList<>();
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            String id=rs.getString(1);
            examIdList.add(id);
        }
        return examIdList;
    }
    public static StudentScore getScore(String studentId,String examId)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select language,per from performance where userid=? and examid=?");
        ps.setString(1,studentId);
        ps.setString(2,examId);
        ResultSet rs=ps.executeQuery();
        rs.next();
        StudentScore obj=new StudentScore();
        obj.setLanguage(rs.getString(1));
        obj.setPer(rs.getDouble(2));
        return obj;
    }
    
}   