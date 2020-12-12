package lol.kent.practice.spring.mongo.audit;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;
import org.springframework.data.auditing.DateTimeProvider;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年12月12日 17:33
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
public class DateTimeAuditImpl implements DateTimeProvider {


    @Override
    public Optional<TemporalAccessor> getNow() {
        return Optional.ofNullable(LocalDateTime.now());
    }
}
