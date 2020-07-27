package service;

import java.util.ArrayList;
import model.JudgePrimState;
import model.Point;
import model.Primitive;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import util.MathCalculation;


/**
 * 三种比较方法
 */

public class
ComparativeMethod {

  /**
   * 单一比较
   */
  public float singleComparison(JudgePrimState a, JudgePrimState b) {

    Point endA1 = new Point(a.getPrimitiveList().iterator().next().getEnd1());
    Point endA2 = new Point(a.getPrimitiveList().iterator().next().getEnd2());
    Point endB1 = new Point(b.getPrimitiveList().iterator().next().getEnd1());
    Point endB2 = new Point(b.getPrimitiveList().iterator().next().getEnd2());

    return new MathCalculation().Tanimoto(endA1, endA2, endB1, endB2);
  }

  /**
   * 逐段比较（当ab都是多段的情况下）
   */
  public float segmentalComparison(JudgePrimState a, JudgePrimState b) {
    ArrayList<Point> featurePoints = new JudgeFeaturePoints().getJudgeFeaturePoints(a, b);
    Point aFeaturePoint = featurePoints.get(0);
    Point bFeaturePoint = featurePoints.get(1);

    return 0;
  }

  public float segmentBeforeComparison() {
    return 0;
  }


  /**
   * 求弧长
   */
  private float lengthOfCurve(Primitive a) {
    int end1 = a.getEnd1();
    int end2 = a.getEnd2();
    int end3 = a.getEnd3();
    Point point1 = new Point(end1);
    Point point2 = new Point(end2);
    Point point3 = new Point(end3);
// 12之间的向量
    //  RealVector vector12=new ArrayRealVector(new double[]{point2.getEndX()-point1.getEndX(),point2.getEndY()-point1.getEndY(),point2.getEndZ()-point1.getEndZ()});
// 13之间的向量
    //  RealVector vector13=new ArrayRealVector(new double[]{point3.getEndX()-point1.getEndX(),point3.getEndY()-point1.getEndY(),point3.getEndZ()-point1.getEndZ()});
// 23之间的向量
    //   RealVector vector23=new ArrayRealVector(new double[]{point3.getEndX()-point2.getEndX(),point3.getEndY()-point2.getEndZ(),point3.getEndZ()-point2.getEndZ()});
    float x1 = point1.getEndX();
    float y1 = point1.getEndY();
    float z1 = point1.getEndZ();
    float x2 = point2.getEndX();
    float y2 = point2.getEndY();
    float z2 = point2.getEndZ();
    float x3 = point3.getEndX();
    float y3 = point3.getEndY();
    float z3 = point3.getEndZ();

    float A = x1 * (y2 - y3) - y1 * (x2 - x3) + x2 * x3 - x3 * y2;
    float B =
        (x1 * x1 + y1 * y1) * (y3 - y2) + (x2 * x2 + y2 * y2) * (y1 - y3) + (x3 * x3 + y3 * y3) * (
            y2 - y1);
    float C =
        (x1 * x1 + y1 * y1) * (x2 - x3) + (x2 * x2 + y2 * y2) * (x3 - x1) + (x3 * x3 + y3 * y3) * (
            x1 - x2);
    float D = (x1 * x1 + y1 * y1) * (x3 * y2 - x2 * y3) + (x2 * x2 + y2 * y2) * (x1 * y3 - x3 * y1)
        + (x3 * x3 + y3 * y3) * (
        x2 * y1 - x1 * y2);




   int edgeType = a.getEdgeType();

    float l = MathCalculation.euclideanDistance(point1, point2);
    float r = MathCalculation.euclideanDistance(MathCalculation., point2);

    if (edgeType == 1) {
      return l;
    } else if (edgeType == 2) {
// TODO
      Math.toDegrees()

    } else {
      throw new IllegalArgumentException("prim曲线类型异常，未定义edgeType == 3的情况。");
    }

return 0.0f;
  }

}
