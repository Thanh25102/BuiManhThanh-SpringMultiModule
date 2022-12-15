package com.buimanhthanh.admin.user;

import com.buimanhthanh.common.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RoleRepositoryTest {
    @Autowired private RoleRepository repo;

    @Test
    @Rollback(value = false)
    public void testCreateFirstRole(){
        Role admin = new Role(null,"admin","manage everything");
        Role savedRole = repo.save(admin);
        assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @Test
    @Rollback(value = false)
    public void testCreateListRole(){
        repo.saveAll(List.of(
                new Role(null,"SalesPerson","manage product price, customers, shipping, orders and sales report"),
                new Role(null,"Editor","manage categories, brands, products, articles and menus"),
                new Role(null,"Shipper","view products, view orders, update order status"),
                new Role(null,"Assitant","manage question and reviews")
        ));
    }
}
