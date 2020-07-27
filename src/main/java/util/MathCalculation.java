package util;

import dao.GetPoint;
import java.util.ArrayList;
import java.util.Iterator;
import model.JudgePrimState;
import model.Point;

public class MathCalculation {

  /**
   * | 用来计算两点之间的欧氏距离
   */
  public static float euclideanDistance(Point x, Point y) {
    float x1 = x.getEndX() - y.getEndX();
    float y1 = x.getEndY() - y.getEndY();
    float z1 = x.getEndZ() - y.getEndZ();
    return (float) Math.sqrt(x1 * x1 + y1 * y1 + z1 * z1);
  }

  /**
   * 给定两点，找寻中点坐标
   */
  public static Point findCenterOfTwoPoints(Point x, Point y) {
    float x1 = (x.getEndX() + y.getEndX()) / 2;
    float y1 = (x.getEndY() + y.getEndY()) / 2;
    float z1 = (x.getEndZ() + y.getEndZ()) / 2;
    return new Point(x1, y1, z1);
  }


  /**
   * 找出所给的list中最小值并返回
   */
  public static float findMinValue(ArrayList<Float> arrayList) {
    Iterator<Float> iterator = arrayList.iterator();
    float min = 0;
    while (iterator.hasNext()) {
      Float next = iterator.next();
      if (min > next) {
        min = next;
      }
    }
    return min;
  }


  public static Point getAPointOfCircularByParameter(JudgePrimState a, float t) {
    Point centerOfCircle = FindPoints.findCenterOfCircle(a);
    int pointID1 = a.getPrimitiveList().iterator().next().getEnd1();
    Point point1 = new GetPoint().getPointByPointID(pointID1);
    int pointID2 = a.getPrimitiveList().iterator().next().getEnd2();
    Point point2 = new GetPoint().getPointByPointID(pointID2);
    int pointID3 = a.getPrimitiveList().iterator().next().getEnd3();
    Point point3 = new GetPoint().getPointByPointID(pointID3);
    float r = MathCalculation.euclideanDistance(centerOfCircle, point1);
    Point aVector = new Point(point3.getEndX() - point1.getEndX(),
        point3.getEndY() - point1.getEndY(), point3.getEndZ() - point1.getEndZ());
    Point bVector = new Point(point3.getEndX() - point2.getEndX(),
        point3.getEndY() - point2.getEndY(), point3.getEndZ() - point2.getEndZ());
    float xt = (float) (centerOfCircle.getEndX() + r * Math.cos(t) * aVector.getEndX() + r * Math
        .sin(t) * bVector.getEndX());
    float yt = (float) (centerOfCircle.getEndY() + r * Math.cos(t) * aVector.getEndY() + r * Math
        .sin(t) * bVector.getEndY());
    float zt = (float) (centerOfCircle.getEndZ() + r * Math.cos(t) * aVector.getEndZ() + r * Math
        .sin(t) * bVector.getEndZ());
    return new Point(xt, yt, zt);
  }

  /**
   * 求出Tanimoto系数（比较切向向量）
   * @param endA1 A的起点
   * @param endA2 A的终点
   * @param endB1 B的起点
   * @param endB2 B的终点
   * @return
   */
  public  float Tanimoto(Point endA1,Point endA2,Point endB1,Point endB2)  {
    Point vsi = subtract(endA1,endA2);
    Point vti =subtract(endB1,endB2);
    float ti = multiplication(vsi, vti) / (multiplication(vsi, vsi) + multiplication(vti, vti)
        - multiplication(vsi, vti));
    return ti;
  }

  /**
   * 返回两法向向量的余弦相似度（比较法向向量）
   * @param endA1
   * @param endA2
   * @param endB1
   * @param endB2
   * @return
   */
  public float similarityOfCos(Point endA1,Point endA2,Point endB1,Point endB2) {
    Point vsi = subtract(endA1,endA2);
    Point vti =subtract(endB1,endB2);
    float sci = Math.abs(multiplication(vsi, vti) / (multiplication(vsi, vsi) * multiplication(vti, vti)));
    return sci;
  }



  /**
   * Point类相减
   * @param point 减数
   * @param point1 被减数
   * @return
   */
  private Point subtract(Point point, Point point1) {
    return new Point(point1.getEndX() - point.getEndX(), point1.getEndY() - point.getEndY(),
        point1.getEndZ() - point.getEndZ());
  }

  /**
   * 求出两个向量的数量积（点乘）
   * @param point
   * @param point1
   * @return
   */
  private float multiplication(Point point, Point point1) {
    return (point1.getEndX()*point.getEndX()+point1.getEndY()*point.getEndY()+
        point1.getEndZ()*point.getEndZ());
  }
}
