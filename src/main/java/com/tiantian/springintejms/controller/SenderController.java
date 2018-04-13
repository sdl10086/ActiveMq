package com.tiantian.springintejms.controller;

import com.tiantian.springintejms.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jms.Destination;

/**
 * Created by sdl on 18-4-13
 *
 * @project activeMQ
 * @package com.tiantian.springintejms.controller
 * @Description
 * @github https://github.com/sdl10086
 */

@Controller
@RequestMapping("mq")
public class SenderController {

    @Autowired
    private ProducerService producerService;
    @Autowired
    @Qualifier("queueDestination")
    private Destination destination;

    @Autowired
    @Qualifier("topicDestination")
    private Destination topicDestination ;

    @RequestMapping("/send")
    public void send(){
        for (int i=0; i<2; i++) {
            producerService.sendMessage(destination, "你好，生产者！这是消息：" + (i+1));
        }
    }
    
    @RequestMapping("/sendTopic")
    public void sendTopic(){
        for (int i=0; i<2; i++) {
            producerService.sendMessage(topicDestination, "你好，生产者！这是消息Topic：" + (i+1));
        }
    }
}
