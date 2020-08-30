package com.effsim.main.service;

import com.effsim.main.comment.Constant;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 *
 *@description:  GRPC启动类
 *@author: Shiqinghu
 *@time: 2020/8/30 13:53
 */
@Service
public class GrpcStart {

    private static final Logger logger = Logger.getLogger(GrpcStart.class.getName());

    private Server server;


    @Autowired
    HelloWorldServerImpl helloWorldServer;

    /**
     * @description: GRPC服务启动入口
     * @return: com.effsim.main.service.GrpcStart
     * @author: Shiqinghu
     * @time: 2020/8/30 15:10
     *
     */
    public  void start(){

        try {
            server = ServerBuilder.forPort(Constant.RUNNING_PORT)
                    .addService(helloWorldServer)
                    .build()
                    .start();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        logger.info("Server started, listening on " + Constant.RUNNING_PORT);
        /**
         * 这个方法的意思就是在jvm中增加一个关闭的钩子
         * 当jvm关闭的时候会执行系统中已经设置的所有通过方法addShutdownHook添加的钩子
         * 当系统执行完这些钩子后，jvm才会关闭
         * 所以这些钩子可以在jvm关闭的时候进行内存清理、对象销毁等操作
         */
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                GrpcStart.this.stop();
                System.err.println("*** server shut down");
            }
        });
        GrpcStart.this.blockUntilShutdown();
    }
    /**
     * @description: [GRPC服务停止]
     * @return: com.effsim.main.service.GrpcStart
     * @author: qinghuShi
     * @time: 2020/8/30 15:10
     *
     */
    private void stop() {
        if (server != null) {
            try {
                server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    private void blockUntilShutdown() {
        if (server != null) {
            try {
                server.awaitTermination();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }
    }


}
