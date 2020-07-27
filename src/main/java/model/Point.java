package model;

import dao.GetPoint;

public class Point {
  private int PointID;
  private float EndX;
  private float EndY;
  private float EndZ;


  public Point() {
  }

  public Point(int pointID) {
    PointID = pointID;
    EndX = new GetPoint().getPointByPointID(pointID).getEndX();
    EndY = new GetPoint().getPointByPointID(pointID).getEndY();
    EndZ = new GetPoint().getPointByPointID(pointID).getEndZ();
  }

  public Point(float endX, float endY, float endZ) {
    PointID=0;
    EndX = endX;
    EndY = endY;
    EndZ = endZ;
  }

  public Point(int pointID, float endX, float endY, float endZ) {
    PointID = pointID;
    EndX = endX;
    EndY = endY;
    EndZ = endZ;
  }

  public int getPointID() {
    return PointID;
  }

  public void setPointID(int pointID) {
    PointID = pointID;
  }

  public float getEndX() {
    return EndX;
  }

  public void setEndX(float endX) {
    EndX = endX;
  }

  public float getEndY() {
    return EndY;
  }

  public void setEndY(float endY) {
    EndY = endY;
  }

  public float getEndZ() {
    return EndZ;
  }

  public void setEndZ(float endZ) {
    EndZ = endZ;
  }

  @Override
  public String toString() {
    return "Point{" +
        "PointID=" + PointID +
        ", EndX=" + EndX +
        ", EndY=" + EndY +
        ", EndZ=" + EndZ +
        '}';
  }
}
