package com.conectdoe.api.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@DynamoDBTable(tableName = "conectdoe.api.ong")
public class Ong {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String ongId;

    @DynamoDBAttribute
    @NotBlank(message = "O nome da ONG deve ser informado")
    private String nome;

    @DynamoDBAttribute
    @NotEmpty(message = "O email da ONG deve ser informado")
    private String email;

    @DynamoDBAttribute
    @NotEmpty(message = "O telefone da ONG deve ser informado")
    private String telefone;

    @DynamoDBAttribute
    @NotEmpty(message = "A cidade da ONG deve ser informado")
    private String cidade;

    @DynamoDBAttribute
    @NotEmpty(message = "O UF da ONG deve ser informado")
    private String uf;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ong ong = (Ong) o;
        return Objects.equals(ongId, ong.ongId) && Objects.equals(nome, ong.nome) && Objects.equals(email, ong.email) && Objects.equals(telefone, ong.telefone) && Objects.equals(cidade, ong.cidade) && Objects.equals(uf, ong.uf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ongId, nome, email, telefone, cidade, uf);
    }

    public String getOngId() {
        return ongId;
    }

    public void setOngId(String ongId) {
        this.ongId = ongId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
