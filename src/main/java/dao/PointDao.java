package dao;

/**
 * 连接数据库提取基本体转换模型的数据
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Point;


public class PointDao {

  ResultSet rs = null;
  Statement statement = null;
  Connection connection = null;
  List<Point> pointList = new ArrayList<>();
  /**
   *  连接数据库的四大参数是：驱动类、url、用户名，以及密码。
   */
  private static final String DRIVER = "com.mysql.jdbc.Driver";

/*  useUnicode参数指定这个连接数据库的过程中，使用的字节集是Unicode字节集；
  characterEncoding参数指定穿上连接数据库的过程中，使用的字节集编码为UTF-8编码。
  请注意，mysql中指定UTF-8编码是给出的是UTF8，而不是UTF-8
  请问一下gmt%2b8就是代表北京这个时区：serverTimezone=GMT%2B8*/

  private static final String URL = "jdbc:mysql://localhost:3306/model?"
      + "useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
  private static final String USER = "root";
  private static final String PASSWORD = "123456";

  public void getConnection() {


    try {
//      注册驱动： Class.forName(“com.mysql.jdbc.Driver”)
      Class.forName(DRIVER);
//      获取连接： DriverManager.getConnection(url,username,password)
      connection = DriverManager.getConnection(URL, USER, PASSWORD);
//      获取对象： 在得到Connectoin之后，说明已经与数据库连接上了，下面是通过Connection获取Statement对象
      statement = connection.createStatement();
//      增删改查：
//        executeQuery—查询，查询得到的是一个ResultSet（结果集）
//        executeUpdate(String sql)：执行更新操作（insert、update、delete等）
//        如：int m = stmt.executeUpdate(“insert into user value(’zhangSan’, ’123’)”)
//        其中int类型的返回值表示执行这条SQL语句所影响的行数。
      rs = statement.executeQuery("SELECT * FROM model.primpoint");
/**
 * 使用该PreparedStatement对象可以防止sql攻击
 */
/*      String sql = "select * from tab_student where s_number=?";
      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, "S_1001");
      rs = pstmt.executeQuery();*/

      while (rs.next()) {

//        pointList = new ArrayList<>();
//      String username = rs.getString("username");
        int PointID=rs.getInt("PointID");
        int EndX=rs.getInt("EndX");
        int EndY=rs.getInt("EndY");
        int EndZ=rs.getInt("EndZ");


        Point point = new Point(PointID, EndX,EndY,EndZ);
        pointList.add(point);
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
          rs = null;
        }
        if (statement != null) {
          statement.close();
          statement = null;
        }
        if (connection != null) {
          connection.close();
          connection = null;
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

}


