package example.dto.joins;

import org.hibernate.transform.ResultTransformer;
import org.hibernate.*;

import java.util.List;

import example.entity.DogExpandedEntity;

public class NonResultTransform {
  Session session = null;

  public NonResultTransform(Session s){
    session = s;
    init();
  }

  private void init() {

    StringBuilder hql = new StringBuilder();
    Utils.appendString(hql, "SELECT b.breed, c.color");
    Utils.appendString(hql, "FROM DogEntity d");
    Utils.appendString(hql, "JOIN BreedLookupEntity b ON d.breedId = b.id");
    Utils.appendString(hql, "JOIN ColorLookupEntity c ON d.colorId = c.id");
    Query query = session.createQuery(hql.toString());

    List<Object[]> lst = query.list();
    for (Object[] dog : lst) {
      String breed = (String)dog[0];
      String color = (String)dog[1];
      try {
        insert(breed, color);
      } catch(Exception e) {}
    }
  }

  private void insert(String breed, String color) throws Exception {
    if (color == null)
      throw new Exception("must provide color");
    if (breed == null)
      throw new Exception("must provide breed");

    session.beginTransaction();

    DogExpandedEntity dog = new DogExpandedEntity();
    dog.setBreed(breed);
    dog.setColor(color);

    session.save(dog);

    session.getTransaction().commit();
  }
}
