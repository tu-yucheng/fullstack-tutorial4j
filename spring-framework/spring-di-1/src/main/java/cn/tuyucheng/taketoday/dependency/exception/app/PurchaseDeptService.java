package cn.tuyucheng.taketoday.dependency.exception.app;

import cn.tuyucheng.taketoday.dependency.exception.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PurchaseDeptService {

    private InventoryRepository repository;

    public PurchaseDeptService(@Qualifier("dresses") InventoryRepository repository) {
        this.repository = repository;
    }
}