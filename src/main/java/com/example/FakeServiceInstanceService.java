package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.GetLastServiceOperationRequest;
import org.springframework.cloud.servicebroker.model.GetLastServiceOperationResponse;
import org.springframework.cloud.servicebroker.model.UpdateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.UpdateServiceInstanceResponse;
import org.springframework.cloud.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Component;

@Component
public class FakeServiceInstanceService implements ServiceInstanceService {
	private final Logger log = LoggerFactory.getLogger(FakeServiceInstanceService.class);

	@Override
	public CreateServiceInstanceResponse createServiceInstance(CreateServiceInstanceRequest req) {
		// cf create-service で呼ばれる (PUT /v2/service_instances/:instance_id)
		// ※このinstance_idはCCから与えられるので、以降の処理用に、ブローカでリソースと紐付けて覚えておく。
		String serviceInstanceId = req.getServiceInstanceId();
		log.info("Create Service Instance({})", serviceInstanceId);
		return new CreateServiceInstanceResponse();
	}

	@Override
	public GetLastServiceOperationResponse getLastOperation(GetLastServiceOperationRequest req) {
        // サービスインスタンスが非同期で作成される場合に、cf servicesまたはcf serviceで呼ばれる
		// (GET /v2/service_instances/:service_instance_id/last_operation)
        // 状態チェック
		String serviceInstanceId = req.getServiceInstanceId();
		log.info("Get Last ServiceOperation({})", serviceInstanceId);
		return null;
	}
	
	@Override
	public DeleteServiceInstanceResponse deleteServiceInstance(DeleteServiceInstanceRequest req) {
		// cf deleteで呼ばれる (DELETE /v2/service_instances/:instance_id)
		String serviceInstanceId = req.getServiceInstanceId();
		log.info("Delete Service Instance({})", serviceInstanceId);
		return null;
	}

	@Override
	public UpdateServiceInstanceResponse updateServiceInstance(UpdateServiceInstanceRequest req) {
		// cf update-serviceで呼ばれる (PATCH /v2/service_instances/:instance_id)
		String serviceInstanceId = req.getServiceInstanceId();
		log.info("Update Service Instance({})", serviceInstanceId);
		return null;
	}
}
