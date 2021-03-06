package com.amazonaws.greengrass.cdddocker.handlers;

import com.amazonaws.greengrass.cdddocker.data.DockerListRequest;
import com.amazonaws.greengrass.cdddocker.data.Topics;
import com.amazonaws.greengrass.cdddocker.docker.DockerHelper;
import com.awslabs.aws.iot.greengrass.cdd.events.ImmutablePublishMessageEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import javax.inject.Inject;

public class DockerListRequestHandler {
    @Inject
    EventBus eventBus;
    @Inject
    Topics topics;
    @Inject
    DockerHelper dockerHelper;

    @Inject
    public DockerListRequestHandler() {
    }

    @Subscribe
    public void dockerRequestType(DockerListRequest dockerListRequest) {
        try {
            dockerHelper.dumpImagesInfo();
            dockerHelper.dumpContainersInfo();
        } catch (Exception e) {
            if (e.getMessage() != null) {
                eventBus.post(ImmutablePublishMessageEvent.builder().topic(topics.getResponseTopic()).message(e.getMessage()).build());

                return;
            }

            // NULL exception message can happen in dump images/dump containers if a NULL reference occurs, catch that here so the user gets some indication that something went wrong
            eventBus.post(ImmutablePublishMessageEvent.builder().topic(topics.getResponseTopic()).message("NULL exception message").build());
            e.printStackTrace();
        }
    }
}
