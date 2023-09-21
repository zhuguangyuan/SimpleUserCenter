package com.bruce.auth.controllers;

import com.bruce.auth.services.transactionDemos.RoleService4Transaction;
import com.bruce.auth.services.transactionDemos.TransactionService;
import com.bruce.auth.services.transactionDemos.UserService4Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * api for spring propagation test
 * <p>
 * outer-required
 * inner-required/requires_new/nested
 * <p>
 * inner exp will cause outer rollback in inner-required, but not inner-requires_new or inner-nested
 * outer exp will cause inner rollback in inner-required/inner-nested, but not inner-requires_new.
 * that is ,
 * inner-requires_new will create a new standalone transaction.
 * inner-nested will use the outer transaction with new savepoint, but will rollback if outer rollback.
 */
@RestController
@Slf4j
public class TrxPropagationController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService4Transaction userService4Transaction;
    @Autowired
    private RoleService4Transaction roleService4Transaction;

    @GetMapping("/testPropagation")
    public void test() {
        for (TransactionService.TestCase testCase : TransactionService.TestCase.values()) {
            log.warn("==== test {} \n\n", testCase);
            boolean innerFailed;
            boolean outerFailed;

            // 内部异常，外部无异常。测试内部异常对外部的影响
            try {
                transactionService.method0(testCase, true, false);
            } catch (Exception e) {
                log.info("exp in controller with testCase {}, msg:{}", testCase, e.getMessage());
            }
            innerFailed = true;
            outerFailed = true;
            /* new 和 nested 的内部异常 不应该影响外部 */
            if (testCase == TransactionService.TestCase.REQUIRES_NEW
                    || testCase == TransactionService.TestCase.NESTED) {
                outerFailed = false;
            }
            transactionService.assertData(testCase, innerFailed, outerFailed);
            transactionService.clearOldData();

            // 内部无异常，外部异常。测试外部异常对内部的影响
            try {
                transactionService.method0(testCase, false, true);
            } catch (Exception e) {
                log.info("exp in controller with testCase {}, msg:{}", testCase, e.getMessage());
            }
            innerFailed = true;
            outerFailed = true;
            /* new 类型时，外部异常不应该影响内部
             注意 nested 类型，外部会影响内部 */
            if (testCase == TransactionService.TestCase.REQUIRES_NEW) {
                innerFailed = false;
            }
            transactionService.assertData(testCase, innerFailed, outerFailed);
            transactionService.clearOldData();
        }
    }
}
