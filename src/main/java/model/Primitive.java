package model;

/**
 * 基本体属性
 */
public class Primitive {

  public Primitive() {
  }

  public Primitive(int number, int primID, int FE, String primElem, int edgeNum, int edgeType, int end1, int end2, int end3) {
    this.Number = number;
    this.PrimID = primID;
    this.FE = FE;
    this.PrimElem = primElem;
    this.EdgeNum = edgeNum;
    this.EdgeType = edgeType;
    this.End1 = end1;
    this.End2 = end2;
    this.End3 = end3;
  }

  private int Number;
  private int PrimID;
  private int FE;
  private String PrimElem;
  private int EdgeNum;
  private int EdgeType;
  private int End1;
  private int End2;
  private int End3;

  public int getNumber() {
    return Number;
  }

  public void setNumber(int number) {
    Number = number;
  }

  public int getPrimID() {
    return PrimID;
  }

  public void setPrimID(int primID) {
    PrimID = primID;
  }

  public int getFE() {
    return FE;
  }

  public void setFE(int FE) {
    this.FE = FE;
  }

  public String getPrimElem() {
    return PrimElem;
  }

  public void setPrimElem(String primElem) {
    PrimElem = primElem;
  }

  public int getEdgeNum() {
    return EdgeNum;
  }

  public void setEdgeNum(int edgeNum) {
    EdgeNum = edgeNum;
  }

  public int getEdgeType() {
    return EdgeType;
  }

  public void setEdgeType(int edgeType) {
    EdgeType = edgeType;
  }

  public int getEnd1() {
    return End1;
  }

  public void setEnd1(int end1) {
    End1 = end1;
  }

  public int getEnd2() {
    return End2;
  }

  public void setEnd2(int end2) {
    End2 = end2;
  }

  public int getEnd3() {
    return End3;
  }

  public void setEnd3(int end3) {
    End3 = end3;
  }

  @Override
  public String toString() {
    return "Primitive{" +
        "Number=" + Number +
        ", PrimID=" + PrimID +
        ", FE=" + FE +
        ", PrimElem='" + PrimElem + '\'' +
        ", EdgeNum=" + EdgeNum +
        ", EdgeType=" + EdgeType +
        ", End1=" + End1 +
        ", End2=" + End2 +
        ", End3=" + End3 +
        '}';
  }
}




