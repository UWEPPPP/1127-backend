package com.topview.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import www.topview.constant.WebSecurityConstant;
import www.topview.result.CommonResult;
import www.topview.result.ResultCode;
import www.topview.result.ResultUtil;
import www.topview.util.JwtUtil;

import javax.annotation.Resource;
import java.util.List;

@Component
public class GlobalGatewayFilter implements GlobalFilter, Ordered {

    @Value("${white-path}")
    private List<String> whitePath;

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (whitePath.contains(request.getPath().toString())) {
            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst(WebSecurityConstant.TOKEN_HEADER);
        if (token != null && jwtUtil.validateToken(token)) {
            // TODO 判断是否处于允许刷新token时间内

            return chain.filter(exchange);
        }

        return ResultUtil.printCode(exchange.getResponse(), CommonResult.operateFailDueToToken(ResultCode.TOKEN_INVALID_CODE, "无效的token"), ResultCode.TOKEN_INVALID_CODE);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
