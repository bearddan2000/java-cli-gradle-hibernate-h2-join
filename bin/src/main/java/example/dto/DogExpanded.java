package example.dto;

import org.hibernate.*;
import java.util.List;

import example.entity.*;
import example.dto.joins.*;
import example.dto.interfaces.IJoined;

public class DogExpanded implements IJoined {
  Session session = null;
  static int idx = 1;

  public DogExpanded(Session s){
    session = s;
    new ResultTransform(s);
    new NonResultTransform(s);
  }

  private String lookup(String entity, String return_col, Integer id)
  {

    StringBuilder hql = new StringBuilder();
    Utils.appendString(hql, "SELECT " + return_col);
    Utils.appendString(hql, "FROM " + entity);
    Utils.appendString(hql, "WHERE id=" + id);
    Query query = session.createQuery(hql.toString());
    query.setMaxResults(1);
    return (String)query.uniqueResult();
  }

  @Override
  public void insert(Integer breedId, Integer colorId) throws Exception {
    if (colorId < 1)
      throw new Exception("must provide color");
    if (breedId < 1)
      throw new Exception("must provide breed");

    String breed = lookup("BreedLookupEntity", "breed", breedId);
    String color = lookup("ColorLookupEntity", "color", colorId);

    session.beginTransaction();

    DogExpandedEntity dog = new DogExpandedEntity();
    dog.setId(++idx);
    dog.setBreed(breed);
    dog.setColor(color);

    session.save(dog);

    session.getTransaction().commit();
  }

  @Override
  public void selectAll(){

    String hql = "FROM DogExpandedEntity";
    Query query = session.createQuery(hql);
    List<DogExpandedEntity> lst = query.list();
    System.out.println(hql);
    for (DogExpandedEntity entity : lst)
      System.out.println(entity.toString());
  }
}
