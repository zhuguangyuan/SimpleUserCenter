package com.bruce.auth.services.transactionDemos;

import com.bruce.auth.daos.batis.RoleMapper;
import com.bruce.auth.models.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class RoleService4Transaction {
    @Autowired
    private RoleMapper mapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void persistWithRequired(Role role, boolean exp) {
        this.persist(role, exp);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void persistWithRequiresNew(Role role, boolean exp) {
        this.persist(role, exp);
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void persistWithNested(Role role, boolean exp) {
        this.persist(role, exp);
    }

    public void persist(Role role, boolean exp) {
        log.info("to persist: {}", role);
        this.mapper.save(role);
        List<Role> result = this.mapper.findByName(role.getName());
        log.info("persist result: {}", result);
        if (exp) {
            throw new RuntimeException("exp in inner, when persist Role.");
        }
    }

    public List<Role> query(String name) {
        List<Role> result = this.mapper.findByName(name);
        log.info("query role result:{}", result);
        return result;
    }

    public void clearAll(int id) {
        this.mapper.clearAll(id);
    }
}
