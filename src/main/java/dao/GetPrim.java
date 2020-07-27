package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Primitive;
/**
 * 获得基本体对象表
 */
public class GetPrim {

  public List<Primitive> getPrimByPrimID(int givenPrimID) {
    PrimitiveDao primitiveDao = new PrimitiveDao();
    primitiveDao.getConnection();
    List<Primitive> targetPrimitiveList = new ArrayList<>();
    Iterator<Primitive> primitiveIterator = primitiveDao.primitiveList.iterator();
    while (primitiveIterator.hasNext()) {
      Primitive primitive = primitiveIterator.next();
      if (primitive.getPrimID() == givenPrimID) {
        targetPrimitiveList.add(primitive);
      }
    }
    return targetPrimitiveList;
  }
}
