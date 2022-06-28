package com.micropos.delivery.channel;


import com.micropos.api.dto.OrderDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 接收者
 * @author pan_junbiao
 **/
@Component
public class DeliveryGenerator
{
    @Autowired
    private Jackson2JsonMessageConverter jackson2JsonMessageConverter;

    @RabbitHandler
    @RabbitListener(queues = "generateDelivery", containerFactory = "rabbitListenerContainerFactory")
    public void process(Message orderDtoMsg)
    {
        OrderDto orderDto = (OrderDto) jackson2JsonMessageConverter.fromMessage(orderDtoMsg);
        System.out.println("接收者收到JSON格式消息：");
        System.out.println("订单编号：" + orderDto.orderId());
        System.out.println("订单账户：" + orderDto.accountId());
        System.out.println("订单内容：" + orderDto.items());
    }
}
