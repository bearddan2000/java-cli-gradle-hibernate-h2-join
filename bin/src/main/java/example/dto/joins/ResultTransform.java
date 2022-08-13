package example.dto.joins;

import org.hibernate.transform.ResultTransformer;
import org.hibernate.*;

import java.util.List;

import example.entity.DogExpandedEntity;

public class ResultTransform {
  Session session = null;

  public ResultTransform(Session s){
    session = s;
    init();
  }

  private void init() {
    StringBuilder hql = new StringBuilder();
    Utils.appendString(hql, "SELECT d.id, b.breed, c.color");
    Utils.appendString(hql, "FROM dog d");
    Utils.appendString(hql, "JOIN breedLookup b ON d.breedId = b.id");
    Utils.appendString(hql, "JOIN colorLookup c ON d.colorId = c.id");
    Query query = session.createNativeQuery(hql.toString())
    .setResultTransformer(new ResultTransformer(){
            @Override
            public Object transformTuple(Object[] tuples, String[] aliases) {
                DogExpandedEntity personDTO = new DogExpandedEntity();
                personDTO.setId((Integer)tuples[0]);
                personDTO.setBreed((String)tuples[1]);
                personDTO.setColor((String)tuples[2]);
                return personDTO;
            }

            @Override
            public List transformList(List list) {
                return list;
            }
        });
    List<DogExpandedEntity> lst = query.list();
    for (DogExpandedEntity entity : lst) {
      session.beginTransaction();
      session.save(entity);
      session.getTransaction().commit();
    }
  }
}
