package com.liu.admin.handler;

import com.liu.admin.utils.DingDingMessageUtil;
import com.liu.admin.utils.JsonUtil;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;
@Component
public class DingDingNotifierHandler extends AbstractStatusChangeNotifier {
    public DingDingNotifierHandler(InstanceRepository repository) {
        super(repository);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        String serviceName = instance.getRegistration().getName();
        String serviceUrl = instance.getRegistration().getServiceUrl();
        String status = instance.getStatusInfo().getStatus();
        Map<String,Object> details = instance.getStatusInfo().getDetails();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[admin]  ");
        stringBuilder.append("[ "+serviceName+" ]");
        stringBuilder.append(" [ 服务地址 ]"+serviceUrl);
        stringBuilder.append(" [ 状态 ] "+status);
        stringBuilder.append("[ 详情]"+ JsonUtil.toJson(details));

        return Mono.fromRunnable(() -> {
            DingDingMessageUtil.sendTextMessage(stringBuilder.toString());
        });
    }
}
