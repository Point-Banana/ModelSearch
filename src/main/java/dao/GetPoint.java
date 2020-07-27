package dao;

import java.util.Iterator;
import model.Point;

/**
 * 获得点对象
 */
public class GetPoint{

  public Point getPointByPointID(int givenPointID) {
    PointDao pointDao = new PointDao();
    pointDao.getConnection();
    Iterator<Point> pointIterator = pointDao.pointList.iterator();
    while (pointIterator.hasNext()) {
      Point point = pointIterator.next();
      if (point.getPointID() == givenPointID) {
        return point;
      }
    }
    throw new IllegalArgumentException("未找到对应PointID的点");
  }
}
