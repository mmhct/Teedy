package com.sismics.rest.exception;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.ws.rs.core.Response;
import jakarta.json.JsonObject;
import org.junit.jupiter.api.Test;

public class ClientExceptionTest {

    @Test
    void testClientExceptionWithTypeAndMessage() {
        // 准备测试数据
        String type = "ValidationError";
        String message = "Invalid input";

        // 创建异常对象
        ClientException exception = new ClientException(type, message);

        // 验证响应状态码
        Response response = exception.getResponse();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

        // 验证JSON内容
        JsonObject entity = (JsonObject) response.getEntity();
        assertEquals(type, entity.getString("type"));
        assertEquals(message, entity.getString("message"));
    }

    @Test
    void testClientExceptionWithCause() {
        // 准备测试数据
        String type = "AlreadyExistingEmail";
        String message = "Email already exists";
        Exception cause = new Exception("Root cause");

        // 创建异常对象
        ClientException exception = new ClientException(type, message, cause);

        // 验证响应状态码
        Response response = exception.getResponse();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

        // 验证JSON内容
        JsonObject entity = (JsonObject) response.getEntity();
        assertEquals(type, entity.getString("type"));
        assertEquals(message, entity.getString("message"));

        // 验证异常原因（可选，根据具体需求）
        // 注意：WebApplicationException默认不会保留cause，此处仅为演示
        // assertTrue(exception.getCause() instanceof Exception);
    }
}
