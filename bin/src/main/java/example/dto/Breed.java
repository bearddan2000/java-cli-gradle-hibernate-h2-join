package example.dto;

import org.hibernate.Query;
import org.hibernate.Session;
import java.util.List;

import example.dto.interfaces.ILookup;
import example.entity.BreedLookupEntity;

public class Breed implements ILookup{
  Session session = null;
  static int idx = 1;

  public Breed(Session s){session = s;}

  @Override
  public void insert(String breed) throws Exception {
    if (breed == null)
      throw new Exception("must provide breed");

		session.beginTransaction();

		BreedLookupEntity dog = new BreedLookupEntity();
    dog.setId(++idx);
    dog.setBreed(breed);

		session.save(dog);

		session.getTransaction().commit();
  }

  @Override
  public void selectAll(){
    String hql = "FROM BreedLookupEntity";
    Query query = session.createQuery(hql);
    List<BreedLookupEntity> lst = query.list();

    System.out.println(hql);
    for (BreedLookupEntity entity : lst)
      System.out.println(entity.toString());
  }
}
