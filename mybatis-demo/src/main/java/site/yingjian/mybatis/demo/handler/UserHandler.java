package site.yingjian.mybatis.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import site.yingjian.mybatis.demo.pojo.primary.User;

@Slf4j
public class UserHandler implements ResultHandler<User> {

    @Override
    public void handleResult(ResultContext<? extends User> resultContext) {
        int resultCount = resultContext.getResultCount();
        log.info("ResultCount --> {}", resultCount);
        User resultObject = resultContext.getResultObject();
        log.info("ResultObject --> {}", resultObject);
    }
}
