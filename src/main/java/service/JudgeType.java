package service;

import dao.GetPrim;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import model.JudgePrimState;
import model.Primitive;

/**
 * 判断转换模型的类型,先判断是否为多段，在判断是否为开环
 */

public class JudgeType {

  private List<JudgePrimState> judgePrimStateList;
  GetPrim getPrim = new GetPrim();

  /**
   * 判断基本体是否为一条直线
   */
  public boolean isALine(int primID) throws Exception {
    int size = getPrim.getPrimByPrimID(primID).size();
    Iterator<Primitive> primitiveIterator = getPrim.getPrimByPrimID(primID).iterator();

    if (size == 1) {
      Primitive primitive = primitiveIterator.next();
      if (primitive.getEdgeType() == 1) {
        return true;
      }
      return false;
    }
    return false;
  }

  /**
   * 判断基本体是否为一个圆
   */
  public boolean isACircular(int primID) {
    int size = getPrim.getPrimByPrimID(primID).size();
    Iterator<Primitive> primitiveIterator = getPrim.getPrimByPrimID(primID).iterator();
    if (size == 2) {
      Primitive primitive1 = primitiveIterator.next();
      Primitive primitive2 = primitiveIterator.next();
      if (primitive1.getEdgeType() == primitive2.getEdgeType() && primitive2.getEdgeType() == 2&&isMatching(primID)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 判断基本体是否为多段形态
   */
  public boolean isMultistage(int primID) {
    int size = getPrim.getPrimByPrimID(primID).size();
    Iterator<Primitive> primitiveIterator = getPrim.getPrimByPrimID(primID).iterator();
    //基本体Number为1或者基本体是一个圆
    if (size == 0) {
      throw new IllegalArgumentException("没有被标识的基本体，primID为：" + primID + "。");
    } else if (size == 1 || isACircular(primID)) {
      return false;
    }
    return true;
  }

  /**
   * 判断基本体是否为闭合形态
   */
  public boolean isClosed(int primID) {
    int size = getPrim.getPrimByPrimID(primID).size();
    Iterator<Primitive> primitiveIterator = getPrim.getPrimByPrimID(primID).iterator();

// 该段配对成功则输出true。
    if (size == 0) {
      throw new IllegalArgumentException("没有被标识的基本体，primID为：" + primID + "。");
    } else if (isMatching(primID)) {
      return true;
    } else {
      return false;
    }
  }
  /**
   * 输入一个list，将他遍历到set中如果set大小为list的一半，则说明两两成对,则输出true
   * 使用了两种方法获得list和set
   */
  private boolean isMatching(int primID) {
/*  ArrayList pointArrayList = new ArrayList();
  HashSet pointHashSet = new HashSet();*/
    Iterator<Primitive> iterator1 = getPrim.getPrimByPrimID(primID).iterator();
    Iterator<Primitive> iterator2 = getPrim.getPrimByPrimID(primID).iterator();
/*    while (iterator.hasNext()) {
      Primitive primitive = iterator.next();
      pointArrayList.add(primitive.getEnd1());
      pointArrayList.add(primitive.getEnd2());
      pointHashSet.add(primitive.getEnd1());
      pointHashSet.add(primitive.getEnd2());
    }*/
    ArrayList pointArrayList = iteratorIToAyyay(iterator1);
    HashSet pointHashSet = iteratorIToHashSet(iterator2);
    return (pointArrayList.size() == pointHashSet.size() * 2);
  }

  /**
   * 输出封装对象JudgePrimitive,List封装的有该基本体所有的成分。
   */
  public JudgePrimState outJudgePrimState(int primID) throws Exception {

    return new JudgePrimState(getPrim.getPrimByPrimID(primID), isALine(primID), isACircular(primID),
        isMultistage(primID), isClosed(primID));
  }

  private ArrayList iteratorIToAyyay(Iterator<Primitive> iterator) {
    ArrayList arrayList = new ArrayList();
    while (iterator.hasNext()) {
      Primitive primitive = iterator.next();
      arrayList.add(primitive.getEnd1());
      arrayList.add(primitive.getEnd2());

    }
    return arrayList;
  }
  private HashSet iteratorIToHashSet(Iterator<Primitive> iterator) {
    HashSet hashSet = new HashSet();
    while (iterator.hasNext()) {
      Primitive primitive = iterator.next();
      hashSet.add(primitive.getEnd1());
      hashSet.add(primitive.getEnd2());

    }
    return hashSet;
  }
}


