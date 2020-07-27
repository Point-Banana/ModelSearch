package util;

import dao.GetPoint;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import model.JudgePrimState;
import model.Point;
import model.Primitive;

public class FindPoints {


  /**
   * 找到基本体成分a的两个端点，并返回list，list中是端点的PointID
   * 使用set可以去重的思路
   */
  public static ArrayList<Integer> findEndpoints(JudgePrimState a) {
    ArrayList<Integer> arrayList = new ArrayList();
    HashSet<Integer> hashSet = new HashSet();
    Iterator<Primitive> iterator = a.getPrimitiveList().iterator();
    while (iterator.hasNext()) {
      Primitive prim = iterator.next();
      arrayList.add(prim.getEnd1());
      arrayList.add(prim.getEnd2());
      hashSet.add(prim.getEnd1());
      hashSet.add(prim.getEnd2());
    }
    for (int i = 0; i < hashSet.size(); i++) {
      Iterator<Integer> iterator1 = hashSet.iterator();
      while (iterator1.hasNext()) {
        arrayList.remove(iterator1.next());
      }
    }
    return arrayList;
  }

  /**
   * 找到基本体成分a的所有顶点，并返回list，list中是顶点的PointID
   * 使用set可以去重的思路
   */
  public static ArrayList<Integer> findVertex(JudgePrimState a) {
    ArrayList<Integer> arrayList = new ArrayList();
    HashSet<Integer> hashSet = new HashSet();
    Iterator<Primitive> iterator = a.getPrimitiveList().iterator();
    while (iterator.hasNext()) {
      Primitive prim = iterator.next();
      hashSet.add(prim.getEnd1());
      hashSet.add(prim.getEnd2());
    }
    while (hashSet.iterator().hasNext()) {
      Integer next = hashSet.iterator().next();
      arrayList.add(next);
    }
    return arrayList;
  }

  /**
   * 返回基本体集圆的圆心
   */
  public static Point findCenterOfCircle(JudgePrimState a) {

    GetPoint point = new GetPoint();
    Primitive primitive = a.getPrimitiveList().iterator().next();
    Point point1 = point.getPointByPointID(primitive.getEnd1());
    Point point2 = point.getPointByPointID(primitive.getEnd2());
    return MathCalculation.findCenterOfTwoPoints(point1, point2);
  }

  /**
   * 给定基本体成分集（圆），返回按照参数方程切分好的圆的点坐标 (圆的参数方程)http://blog.sina.com.cn/s/blog_6496e38e0102vi7e.html
   */
  public static ArrayList<Point> uniformityCut(JudgePrimState a) {
    int num = 16;
    float pi = (float) 3.1416;
    ArrayList<Point> pointArrayList = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      pointArrayList.add(MathCalculation.getAPointOfCircularByParameter(a, i));
      i += 2*pi / num;
    }
    return pointArrayList;
  }
}
