package com.effsim.main.service;


import io.grpc.examples.helloworld.HelloWorldGrpc;
import io.grpc.examples.helloworld.HelloWorldProto;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class HelloWorldServerImpl extends HelloWorldGrpc.HelloWorldImplBase {

    private static final Logger logger = Logger.getLogger(HelloWorldServerImpl.class.getName());

    @Override
    public void sayHello(HelloWorldProto.HelloRequest request, StreamObserver<HelloWorldProto.HelloReply> responseObserver) {
        logger.info("sayHello request:" + request.getName());
        responseObserver.onNext(HelloWorldProto.HelloReply.newBuilder().setMessage("留取丹心照汗青").build());
        responseObserver.onCompleted();
    }
}
