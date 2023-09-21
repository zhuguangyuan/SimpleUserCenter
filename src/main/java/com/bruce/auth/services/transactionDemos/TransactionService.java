package com.bruce.auth.services.transactionDemos;

import com.bruce.auth.models.Role;
import com.bruce.auth.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Slf4j
public class TransactionService {
    @Autowired
    private UserService4Transaction userService4Transaction;
    @Autowired
    private RoleService4Transaction roleService4Transaction;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void method0(TestCase testCase, boolean innerExp, boolean outerExp) {
        log.info("testCase:{}, innerExp:{}, outerExp:{}", testCase, innerExp, outerExp);

        String name = testCase.name();
        try {
            switch (testCase) {
                case REQUIRED:
                    roleService4Transaction.persistWithRequired(Role.builder().name(name).build(), innerExp);
                    break;
                case REQUIRES_NEW:
                    roleService4Transaction.persistWithRequiresNew(Role.builder().name(name).build(), innerExp);
                    break;
                case NESTED:
                    roleService4Transaction.persistWithNested(Role.builder().name(name).build(), innerExp);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            log.info("exp in inner, case:{}, expMsg:{}", testCase, e.getMessage());
        }

        userService4Transaction.persist(User.build(name, "myPass"), outerExp);
    }

    public void assertData(TestCase testCase, boolean innerFailed, boolean outerFailed) {
        List<User> outerResult = userService4Transaction.query(testCase.name());
        List<Role> innerResult = roleService4Transaction.query(testCase.name());
        try {
            if (innerFailed) {
                Assert.isTrue(innerResult.isEmpty());
            } else {
                Assert.notEmpty(innerResult);
            }
            if (outerFailed) {
                Assert.isTrue(outerResult.isEmpty());
            } else {
                Assert.notEmpty(outerResult);
            }
        } catch (Exception e) {
            log.error("assert failed.", e);
        }
    }

    public void clearOldData() {
        userService4Transaction.clearAll(200);
        roleService4Transaction.clearAll(200);
    }

    public enum TestCase {
        REQUIRED,
        REQUIRES_NEW,
        NESTED,
    }
}
