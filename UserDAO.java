
package techquizapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import techquizapp.dbutil.DBConnection;
import techquizapp.pojo.User;
/**
 *
 * @author Ankit
 */
public class UserDAO {
    public static boolean validateUser(User user)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select * from users where userid=? and password=? and usertype=?");
        ps.setString(1,user.getUserId());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getUserType());
        ResultSet rs=ps.executeQuery();
        return rs.next();
    }
    public static ArrayList<String> getUserId()throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select userid from users where usertype=?");
        ps.setString(1,"Student");
        ResultSet rs=ps.executeQuery();
        ArrayList<String> userIdList=new ArrayList<>();
        while(rs.next()){
            userIdList.add(rs.getString(1));
        }
        return userIdList;
    }
    public static boolean addUser(String userId,String password)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into users values(?,?,?)");
        ps.setString(1, userId);
        ps.setString(2,password);
        ps.setString(3,"Student");
        int ans=ps.executeUpdate();
        return ans==1;
    }
    public static boolean updatePassword(String userId,String password)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update users set password=? where userid=?");
        ps.setString(1,password);
        ps.setString(2,userId);
        int ans=ps.executeUpdate();
        return ans==1;
    }
}
