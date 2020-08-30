package com.effsim.main.client;

import com.effsim.main.comment.Constant;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.examples.helloworld.HelloWorldGrpc;
import io.grpc.examples.helloworld.HelloWorldProto;
import io.grpc.protobuf.ProtoUtils;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloWorldClient {
    private static final Logger logger = Logger.getLogger(HelloWorldClient.class.getName());

    public static void main(String[] args) throws Exception {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", Constant.RUNNING_PORT)
                .usePlaintext()
                .build();

        HelloWorldGrpc.HelloWorldBlockingStub helloWorldBlockingStub = HelloWorldGrpc.newBlockingStub(channel);
        HelloWorldProto.HelloRequest helloRequest = HelloWorldProto.HelloRequest.newBuilder().setName("人生自古谁无死").build();
        logger.info("Will try to query age = " + helloRequest + " ...");
        try {
            HelloWorldProto.HelloReply helloReply = helloWorldBlockingStub.sayHello(helloRequest);
            logger.info("Response: " + helloReply.getMessage());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        } finally {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
    }


}
