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
import model.Primitive;
import util.MathCalculation;

public class PrimitiveDao {

  ResultSet rs = null;
  Statement statement = null;
  Connection connection = null;
  List<Primitive> primitiveList = new ArrayList<
          >();

  /**
   * 连接数据库的四大参数是：驱动类、url、用户名，以及密码。
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
      rs = statement.executeQuery("SELECT * FROM model.primitive");
/**
 * 使用该PreparedStatement对象可以防止sql攻击
 */
/*      String sql = "select * from tab_student where s_number=?";
      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, "S_1001");
      rs = pstmt.executeQuery();*/

      while (rs.next()) {

//        primitiveList = new ArrayList<>();
//        String username = rs.getString("username");
        int Number = rs.getInt("Number");
        int PrimID = rs.getInt("PrimID");
        int FE = rs.getInt("FE");
        String PrimElem = rs.getString("PrimElem");
        int EdgeNum = rs.getInt("EdgeNum");
        int EdgeType = rs.getInt("EdgeType");
        int End1 = rs.getInt("End1");
        int End2 = rs.getInt("End2");
        int End3 = rs.getInt("End3");
        if (EdgeType == 2) {
          judgePoint(End1, End2, End3);
        }
        Primitive primitive = new Primitive(Number, PrimID, FE, PrimElem, EdgeNum, EdgeType, End1,
            End2, End3);
        primitiveList.add(primitive);
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

  /**
   * 判断起止点
   */
  private void judgePoint(int x, int y, int z) {
    Point X = new GetPoint().getPointByPointID(x);
    Point Y = new GetPoint().getPointByPointID(y);
    Point Z = new GetPoint().getPointByPointID(z);
    float xy = MathCalculation.euclideanDistance(X, Y);
    float xz = MathCalculation.euclideanDistance(X, Z);
    float yz = MathCalculation.euclideanDistance(Y, Z);
    if (xy > xz && xy > yz) {
      return;
    } else if (xz > xy && xz > yz) {
      y = z;
      return;
    } else {
      x = z;
    }
  }
}


