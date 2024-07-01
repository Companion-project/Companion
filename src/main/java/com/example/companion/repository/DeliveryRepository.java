package com.example.companion.repository;

import com.example.companion.domain.DeliveryDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeliveryRepository {
    @Autowired
    SqlSession sqlSession;
    String namespace = "com.example.companion.repository.DeliveryRepository";
    String statement;

    public Integer deliveryInsert(DeliveryDTO deliveryDTO) {
        statement = namespace + ".deliveryInsert";
        return sqlSession.insert(statement, deliveryDTO);
    }
    public Integer deliveryDelete(String purchaseNum) {
        statement = namespace + ".deliveryDelete";
        return sqlSession.delete(statement,purchaseNum);
    }
    public Integer deliveryStateUpdate(String purchaseNum) {
        statement = namespace + ".deliveryStateUpdate";
        return sqlSession.update(statement,purchaseNum);
    }
}
