package com.cristi.web.emarket.infra.persistence.order;

import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersSdj extends JpaRepository<Order, UniqueId> {
}
