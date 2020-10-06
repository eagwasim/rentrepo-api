package com.noubug.rentrepo.infrastructure.serviceimpl;

import com.google.cloud.tasks.v2.*;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import com.noubug.rentrepo.domain.model.TaskQueueModel;
import com.noubug.rentrepo.domain.services.TaskQueueService;
import com.noubug.rentrepo.infrastructure.exceptions.TaskQueueException;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import javax.inject.Named;
import java.nio.charset.Charset;

@Named
@Log4j2
public class GCPCouldTaskServiceImpl implements TaskQueueService {
    private static final String BASE_LOCATION = "projects/rentrepo/locations/europe-west1/queues/";
    private static final String BASE_URL_KEY = "spring.cloud.gcp.base-url";

    private static final Gson GSON = new Gson();

    private final Environment environment;

    @Inject
    public GCPCouldTaskServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public <T> void queueTask(TaskQueues taskQueues, TaskQueueModel<T> taskQueueModel) {
        QueueName queueName = QueueName.parse(String.format("%s%s", BASE_LOCATION, taskQueues.getQueueName()));

        try (CloudTasksClient cloudTasksClient = CloudTasksClient.create()) {
            Task.Builder taskBuilder = Task.newBuilder()
                    .setHttpRequest(
                            HttpRequest.newBuilder()
                                    .setBody(ByteString.copyFrom(GSON.toJson(taskQueueModel.getData()), Charset.defaultCharset()))
                                    .setUrl(String.format("%s%s", environment.getRequiredProperty(BASE_URL_KEY), taskQueues.getQueueEndPoint()))
                                    .setHttpMethod(HttpMethod.POST)
                                    .putHeaders("Content-Type", "application/json")
                                    .build()
                    );

            // Send create task request.
            Task task = cloudTasksClient.createTask(queueName, taskBuilder.build());

            log.info("Task created: " + task.getName());
        } catch (Exception e) {
            log.error("Task Queue Error: ", e);
            throw new TaskQueueException(e.getMessage());
        }
    }
}
