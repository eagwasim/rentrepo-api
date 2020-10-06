package com.noubug.rentrepo.domain.services;

import com.noubug.rentrepo.domain.model.TaskQueueModel;
import lombok.Getter;

public interface TaskQueueService {
    @Getter
    enum TaskQueues {
        DISTRESS_CALL_BROADCAST_QUEUE("queue-distress-call-broadcast", "/api/v1/tasks/distress/call/broadcast"),
        REPORT_USER_PROCESSING_QUEUE("report-user-processing-queue", "/api/v1/tasks/user/report/process"),
        SAFE_WALK_PROCESSING_QUEUE("safe-walk-processing-queue", "/api/v1/tasks/user/safe/walk/process"),
        CHANNEL_POST_BROADCAST_QUEUE("channel-post-broadcast-queue", "/api/v1/tasks/channel/post/broadcast"),
        CHANNEL_POST_DELETE_PROCESS_QUEUE("channel-post-delete-processing-queue", "/api/v1/tasks/channel/delete/process"),
        ;

        private String queueName;
        private String queueEndPoint;

        TaskQueues(String queueName, String queueEndPoint) {
            this.queueName = queueName;
            this.queueEndPoint = queueEndPoint;
        }
    }

    <T> void queueTask(TaskQueues taskQueues, TaskQueueModel<T> tTaskQueueDomain);
}
