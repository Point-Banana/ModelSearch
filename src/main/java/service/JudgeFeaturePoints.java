package service;

import java.util.ArrayList;
import model.JudgePrimState;
import model.Point;
import util.FindPoints;
import util.MathCalculation;

/**
 * 判断特征点(6种情况,给定的基本体成分集为a和b)
 */

public class JudgeFeaturePoints {

  /**
   * 给定两个基本体成分集，返回特征点, 1为多闭；2为多开；3为单闭；4为单开 D为多闭；A为多开；C为单闭；B为单开
   */
  public ArrayList<Point> getJudgeFeaturePoints(JudgePrimState a, JudgePrimState b) {
    String aState = judgeState(a);
    String bState = judgeState(b);

    String input = aState + bState;
    if (input.equals("AA") || input.equals("AB") || input.equals("BB") || input.equals("BA")) {
      return notCloseAndNotClose(a, b);
    } else if (input.equals("AC") || input.equals("BC") || input.equals("CA") || input
        .equals("CB")) {
      return notCloseAndNotMultistageClosed(a, b);
    } else if (input.equals("BD") || input.equals("AD") || input.equals("DB") || input
        .equals("DA")) {
      return notCloseAndMultistageClosed(a, b);
    } else if (input.equals("CC")) {
      return notMultistageClosedAndNotMultistageClosed(a, b);
    } else if (input.equals("CD") || input.equals("DC")) {
      return notMultistageClosedAndMultistageClosed(a, b);
    } else /*(input == "DD")*/ {
      return multistageClosedAndMultistageClosed(a, b);
    }
  }

  /**
   * 开环与开环之间(22,24,44,42)
   */
  private ArrayList<Point> notCloseAndNotClose(JudgePrimState a, JudgePrimState b) {

    ArrayList<Integer> pointIDArrayList = FindPoints.findEndpoints(a);
    ArrayList<Integer> pointIDArrayList1 = FindPoints.findEndpoints(b);

    ArrayList<Point> pointArrayList = pointIDListToPointList(pointIDArrayList);
    ArrayList<Point> pointArrayList1 = pointIDListToPointList(pointIDArrayList1);

    return closedPoint(pointArrayList, pointArrayList1);
  }

  /**
   * 开环与单段闭环之间(23,43,32,34),为了保证输出的list前者为a的点，后者为b的点，采用if else判断。
   */
  private ArrayList<Point> notCloseAndNotMultistageClosed(JudgePrimState a, JudgePrimState b) {
    JudgePrimState kaiHuan = a;
    JudgePrimState danDuanBiHuan = b;
    // C表示单段闭环
    if (judgeState(a).equals("C")) {
      kaiHuan = b;
      danDuanBiHuan = a;
      ArrayList<Integer> pointIDArrayList = FindPoints.findEndpoints(kaiHuan);

      ArrayList<Point> pointArrayList = pointIDListToPointList(pointIDArrayList);
      ArrayList<Point> pointArrayList1 = FindPoints.uniformityCut(danDuanBiHuan);

      return closedPoint(pointArrayList1, pointArrayList);
    } else {
      ArrayList<Integer> pointIDArrayList = FindPoints.findEndpoints(kaiHuan);

      ArrayList<Point> pointArrayList = pointIDListToPointList(pointIDArrayList);
      ArrayList<Point> pointArrayList1 = FindPoints.uniformityCut(danDuanBiHuan);

      return closedPoint(pointArrayList, pointArrayList1);
    }
  }

  /**
   * 开环与多段闭环之间（41,21,14,12）
   */
  private ArrayList<Point> notCloseAndMultistageClosed(JudgePrimState a, JudgePrimState b) {
    JudgePrimState kaiHuan = a;
    JudgePrimState duoDuanBiHuan = b;
    // D表示单段闭环
    if (judgeState(a).equals("D")) {
      kaiHuan = b;
      duoDuanBiHuan = a;
      ArrayList<Integer> pointIDArrayList = FindPoints.findEndpoints(kaiHuan);
      ArrayList<Integer> pointIDArrayList1 = FindPoints.findVertex(duoDuanBiHuan);

      ArrayList<Point> pointArrayList = pointIDListToPointList(pointIDArrayList);
      ArrayList<Point> pointArrayList1 = pointIDListToPointList(pointIDArrayList1);

      return closedPoint(pointArrayList1, pointArrayList);
    } else {
      ArrayList<Integer> pointIDArrayList = FindPoints.findEndpoints(kaiHuan);
      ArrayList<Integer> pointIDArrayList1 = FindPoints.findVertex(duoDuanBiHuan);

      ArrayList<Point> pointArrayList = pointIDListToPointList(pointIDArrayList);
      ArrayList<Point> pointArrayList1 = pointIDListToPointList(pointIDArrayList1);

      return closedPoint(pointArrayList, pointArrayList1);
    }

  }


  /**
   * 单段闭环与单段闭环之间（33）
   */
  private ArrayList<Point> notMultistageClosedAndNotMultistageClosed(JudgePrimState a,
      JudgePrimState b) {
    ArrayList<Point> pointArrayList = new ArrayList<>();
    Point center=FindPoints.findCenterOfCircle(a);
    Point center1=FindPoints.findCenterOfCircle(b);
    pointArrayList.add(center);
    pointArrayList.add(center1);
    return pointArrayList;
  }

  /**
   * 单段闭环与多段闭环之间（31,13）
   */
  private ArrayList<Point> notMultistageClosedAndMultistageClosed(JudgePrimState a,
      JudgePrimState b) {
    JudgePrimState danDuanBiHuan = a;
    JudgePrimState duoDuanBiHuan = b;
    // D表示单段闭环
    if (judgeState(a).equals("D")) {
      danDuanBiHuan = b;
      duoDuanBiHuan = a;
      ArrayList<Integer> pointIDArrayList1 = FindPoints.findVertex(duoDuanBiHuan);

      ArrayList<Point> pointArrayList = FindPoints.uniformityCut(danDuanBiHuan);
      ArrayList<Point> pointArrayList1 = pointIDListToPointList(pointIDArrayList1);

      return closedPoint(pointArrayList1, pointArrayList);
    } else {
      ArrayList<Integer> pointIDArrayList1 = FindPoints.findVertex(duoDuanBiHuan);

      ArrayList<Point> pointArrayList =  FindPoints.uniformityCut(danDuanBiHuan);
      ArrayList<Point> pointArrayList1 = pointIDListToPointList(pointIDArrayList1);

      return closedPoint(pointArrayList, pointArrayList1);
    }

  }

  /**
   * 多段闭环与多段闭环之间（11）
   */
  private ArrayList<Point> multistageClosedAndMultistageClosed(JudgePrimState a, JudgePrimState b) {

    ArrayList<Integer> pointIDArrayList = FindPoints.findVertex(a);
    ArrayList<Integer> pointIDArrayList1 = FindPoints.findVertex(b);

    ArrayList<Point> pointArrayList = pointIDListToPointList(pointIDArrayList);
    ArrayList<Point> pointArrayList1 = pointIDListToPointList(pointIDArrayList1);

    return closedPoint(pointArrayList, pointArrayList1);

  }

  /**
   * 判断单多开闭环 D为多闭；A为多开；C为单闭；B为单开
   */
  private String judgeState(JudgePrimState a) {
    String aState;
    if (a.isMultistage()) {
      if (a.isClosed()) {
        aState = "D";
      }
      aState = "A";
    } else {
      if (a.isClosed()) {
        aState = "C";
      } else {
        aState = "B";
      }
    }
    return aState;
  }


  /**
   * 给定两个点的坐标的list，返回距离最近的两个点的坐标的list
   */
  private ArrayList<Point> closedPoint(ArrayList<Point> pointArrayList,
      ArrayList<Point> pointArrayList1) {
    ArrayList<Point> nearestDistanceIterationPoint = null;
    float minDistance = 0;
    while (pointArrayList.iterator().hasNext()) {
      Point point = pointArrayList.iterator().next();
      while (pointArrayList1.iterator().hasNext()) {
        Point point1 = pointArrayList1.iterator().next();
        float thisDistance = MathCalculation.euclideanDistance(point, point1);
        if (minDistance == 0 || thisDistance < minDistance) {
          minDistance = thisDistance;
          ArrayList<Point> pointArrayList2 = new ArrayList<>();
          pointArrayList2.add(point);
          pointArrayList2.add(point1);
          nearestDistanceIterationPoint = pointArrayList2;
        }
      }
    }
    return nearestDistanceIterationPoint;
  }

  /**
   * 将给定的pointIDArrayList转化成pointArrayList（坐标List）
   */
  private ArrayList<Point> pointIDListToPointList(ArrayList<Integer> pointIDArrayList) {
    ArrayList<Point> pointArrayList = new ArrayList<>();
    while (pointIDArrayList.iterator().hasNext()) {
      pointArrayList.add(new Point(pointIDArrayList.iterator().next()));
    }
    return pointArrayList;
  }
}
