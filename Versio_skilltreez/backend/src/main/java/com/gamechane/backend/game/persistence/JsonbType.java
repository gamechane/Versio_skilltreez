package com.gamechane.backend.game.persistence;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class JsonbType implements UserType {

  @Override
  public int[] sqlTypes() {
    return new int[] { Types.JAVA_OBJECT };
  }

  @Override
  public Class<String> returnedClass() {
    return String.class;
  }

  @Override
  public boolean equals(Object x, Object y) throws HibernateException {
    if (x == null && y == null) {
      return true;
    }
    if (x == null || y == null) {
      return false;
    }
    return x.equals(y);
  }

  @Override
  public int hashCode(Object x) throws HibernateException {
    return (x != null) ? x.hashCode() : 0;
  }

  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
    String json = rs.getString(names[0]);
    if (json == null) {
      return null;
    }
    return json;
  }

  @Override
  public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
    if (value == null) {
      st.setNull(index, Types.OTHER);
      return;
    }
    st.setObject(index, value, Types.OTHER);
  }

  @Override
  public Object deepCopy(Object original) throws HibernateException {
    if (original == null) {
      return null;
    }
    return new String((String) original);
  }

  @Override
  public boolean isMutable() {
    return true;
  }

  @Override
  public Serializable disassemble(Object o) throws HibernateException {
    return null;
  }

  @Override
  public Object assemble(Serializable serializable, Object o) throws HibernateException {
    return null;
  }

  @Override
  public Object replace(Object o, Object o1, Object o2) throws HibernateException {
    return o;
  }

}
