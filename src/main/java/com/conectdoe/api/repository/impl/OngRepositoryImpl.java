package com.conectdoe.api.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.conectdoe.api.domain.Ong;
import com.conectdoe.api.exception.ResourceNotFoundException;
import com.conectdoe.api.repository.OngRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OngRepositoryImpl implements OngRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public OngRepositoryImpl(DynamoDBMapper dynamoDBMapper){
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public Ong salvar(Ong ong) {
        dynamoDBMapper.save(ong);
        return ong;
    }

    @Override
    public List<Ong> listarTodos() {
        return dynamoDBMapper.scan(Ong.class, new DynamoDBScanExpression());
    }

    @Override
    public Ong buscarPorId(String ongId) {
        return dynamoDBMapper.load(Ong.class, ongId);
    }

    @Override
    public Ong buscarPorNome(String nome) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":nomeVal", new AttributeValue().withS(nome));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("nome = :nomeVal").withExpressionAttributeValues(eav);

        List<Ong> scanResult = dynamoDBMapper.scan(Ong.class, scanExpression);
        return scanResult.stream().findFirst().orElse(null);
    }

    @Override
    public void deletar(String ongId) {
        var ong = dynamoDBMapper.load(Ong.class, ongId);
        if(ong != null) {
            dynamoDBMapper.delete(ong);
        }  else {
            throw new ResourceNotFoundException("A ONG de id - " + ongId + " - não foi encontrada!");
        }
    }
}
