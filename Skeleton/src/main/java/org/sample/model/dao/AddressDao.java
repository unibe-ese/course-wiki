package org.sample.model.dao;

import org.sample.model.Address;
import org.springframework.data.repository.CrudRepository;


public interface AddressDao  extends CrudRepository<Address,Long>{

}
