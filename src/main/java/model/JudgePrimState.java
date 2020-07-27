package model;

import java.util.List;

/**
 * 保存比较时的基本体状态，实体，是否是直线，是否为多段，是否封闭
 */
public class JudgePrimState {

  List<Primitive> primitiveList;
  boolean isALine;
  boolean isACircular;
  boolean isMultistage;
  boolean isClosed;

  public JudgePrimState(List<Primitive> primitiveList, boolean isALine, boolean isACircular,
      boolean isMultistage, boolean isClosed) {
    this.primitiveList = primitiveList;
    this.isALine = isALine;
    this.isACircular = isACircular;
    this.isMultistage = isMultistage;
    this.isClosed = isClosed;
  }

  public List<Primitive> getPrimitiveList() {
    return primitiveList;
  }

  public void setPrimitiveList(List<Primitive> primitiveList) {
    this.primitiveList = primitiveList;
  }

  public boolean isALine() {
    return isALine;
  }

  public void setALine(boolean ALine) {
    isALine = ALine;
  }

  public boolean isACircular() {
    return isACircular;
  }

  public void setACircular(boolean ACircular) {
    isACircular = ACircular;
  }

  public boolean isMultistage() {
    return isMultistage;
  }

  public void setMultistage(boolean multistage) {
    isMultistage = multistage;
  }

  public boolean isClosed() {
    return isClosed;
  }

  public void setClosed(boolean closed) {
    isClosed = closed;
  }

  @Override
  public String toString() {
    return "JudgePrimState{" +
        "primitiveList=" + primitiveList +
        ", isALine=" + isALine +
        ", isACircular=" + isACircular +
        ", isMultistage=" + isMultistage +
        ", isClosed=" + isClosed +
        '}';
  }
}
