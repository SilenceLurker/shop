package xyz.silencelurker.project.shop.easyshop;

import lombok.extern.log4j.Log4j2;

import static xyz.silencelurker.project.shop.easyshop.utils.TokenUtil.*;

import org.junit.jupiter.api.Test;

@Log4j2
public class UtilApplicationTest {

    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJuYW1lIjoidGVzdDEiLCJpZCI6IjgiLCJ0aW1lIjoiMTcwNTMwNzMzNTQ1MCIsImVtYWlsIjoidGVzdDFAdGVzdC5jb20ifQ.s595rMTwyyB-YRRkAOUp3puKXg5JxgS-3cqqOgXMo8qMmso0T42tc8L4vbAPk5O51p3Bet3nd6CNjC-qgivPmA";

    @Test
    void test() {
        log.info(decodeToken(token));
    }

}
