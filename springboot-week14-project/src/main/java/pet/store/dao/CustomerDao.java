package pet.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.store.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

	List<Customer> findPetStoreCustomersByPetStores_PetStoreId(Long petStoreId);
}
