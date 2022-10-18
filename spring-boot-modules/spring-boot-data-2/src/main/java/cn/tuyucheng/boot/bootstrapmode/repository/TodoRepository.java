package cn.tuyucheng.boot.bootstrapmode.repository;

import cn.tuyucheng.boot.bootstrapmode.domain.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    
}