package io.seata.sample.service;

import io.seata.sample.dao.AccountDao;
import java.math.BigDecimal;

import io.seata.spring.annotation.GlobalLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author IT云清
 */
@Service("accountServiceImpl")
public class AccountServiceImpl implements AccountService{

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final BigDecimal ERROR_MONEY = new BigDecimal("200");
    @Autowired
    private AccountDao accountDao;

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    @Override
    @GlobalLock // 获取全局锁，保证全局事务在执行的时候，本地业务不可以操作全局事务中的记录。
    @Transactional // @GlobalLock + @Transactional只会开启本地事务和获取全局锁。
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("------->扣减账户开始account中");
        //模拟超时异常，全局事务回滚 条件：money=200
        if(ERROR_MONEY.compareTo(money) == 0){
            throw new RuntimeException("非法参数,money为："+money);
        }
        accountDao.decrease(userId,money);
        LOGGER.info("------->扣减账户结束account中");
    }
}
