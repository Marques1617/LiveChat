package tech.buildrun.livechatms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    
    @Override // Configure the message broker to use a simple in-memory STOMP broker with a destination prefix for topics and set the application destination prefix for messages sent from clients
    public void configureMessageBroker(MessageBrokerRegistry registry) {
       registry.enableSimpleBroker("/topics"); // The broker will handle messages sent to destinations prefixed with /topics
       registry.setApplicationDestinationPrefixes("/app"); // Messages sent from clients with a destination starting with /app will be routed to message-handling methods (like @MessageMapping) in the controller
    }

    @Override // Define the endpoint for WebSocket connections (WebSocketAPI)
    public void registerStompEndpoints(StompEndpointRegistry registry) {  //The frontend will connect to this endpoint to establish a WebSocket connection
        registry.addEndpoint("/buildrun-livechat-websocket");
    }
    
}

// /topics -> messages that will be distributed to all subscribers of the topic (broadcast, managed by the stomp broker)

// /app -> messages that will be sent from the client to the server and handled by @MessageMapping methods in the controller
// so that the spring knows that when a message as a prefix of /app is sent, it should be routed to the controller