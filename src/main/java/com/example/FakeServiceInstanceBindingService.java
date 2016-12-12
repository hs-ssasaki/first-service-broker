package com.example;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceAppBindingResponse;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingResponse;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.stereotype.Component;

@Component
public class FakeServiceInstanceBindingService implements ServiceInstanceBindingService {
	private final Logger log = LoggerFactory.getLogger(FakeServiceInstanceBindingService.class);
	
	@Override
	public CreateServiceInstanceBindingResponse createServiceInstanceBinding(CreateServiceInstanceBindingRequest req) {
		// cf bind-serviceで呼ばれる
		// (PUT /v2/service_instances/:instance_id/service_bindings/:binding_id)
		// ※binding_idはCCで生成されて渡される。instance_idはこれ以前に生成しているサービスインスタンスのID。
		String serviceInstanceId = req.getServiceInstanceId();
		String bindingId = req.getBindingId();
		log.info("Create Service Binding({})", bindingId, serviceInstanceId);
		Map<String, Object> credentials = new LinkedHashMap<>();
		credentials.put("url", "http://example.com" + UUID.randomUUID());
		credentials.put("username", UUID.randomUUID());
		credentials.put("password",  UUID.randomUUID());
		// TODO: CreateServiceInstance[App]BindingReponseって何？
		return new CreateServiceInstanceAppBindingResponse().withCredentials(credentials);
	}

	@Override
	public void deleteServiceInstanceBinding(DeleteServiceInstanceBindingRequest req) {
		// cf unbind-serviceで呼ばれる
		// (DELETE /v2/service_instances/:instance_id/service_bindings/:binding_id)
		String serviceInstanceId = req.getServiceInstanceId();
		String bindingId = req.getBindingId();
		log.info("Delete Service Binding({}) for Service({})", bindingId, serviceInstanceId);
	}
}
