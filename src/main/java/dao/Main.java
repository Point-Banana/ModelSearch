package dao;

import service.JudgeFeaturePoints;
import service.JudgeType;
import util.FindPoints;

/**
 *
 */
public class Main {

  public static void main(String[] args) throws Exception {

    PointDao pointDao = new PointDao();
    pointDao.getConnection();
    PrimitiveDao primitiveDao = new PrimitiveDao();
    primitiveDao.getConnection();

    GetPoint getPoint=new GetPoint();
    GetPrim getPrim=new GetPrim();

/*
    System.out.println(pointDao.pointList.toString());
    System.out.println("=============1==============");
    System.out.println(primitiveDao.primitiveList.toString());
    System.out.println("=============2==============");
    System.out.println(getPoint.getPointByPointID(1));
    System.out.println("=============3==============");
    System.out.println(getPrim.getPrimByPrimID(1));
    System.out.println("=============4==============");
        System.out.println(new JudgeType().outJudgePrimState(2));
    System.out.println("=============5==============");*/
    System.out.println(new JudgeFeaturePoints().getJudgeFeaturePoints(new JudgeType().outJudgePrimState(2),new JudgeType().outJudgePrimState(4)));
    System.out.println("=============5==============");
    System.out.println(FindPoints.uniformityCut(new JudgeType().outJudgePrimState(2)));



  }
}
