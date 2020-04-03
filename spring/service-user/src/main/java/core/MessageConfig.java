package core;

import core.messaging.Receiver;
import core.messaging.Sender;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Bean(name = "userExchange")
    public DirectExchange userExchange() {
        return new DirectExchange("user.createUser");
    }

    @Bean
    public Sender sender() {
        return new Sender();
    }

    ///////////////////////////////////////////////////////

    @Bean(name = "authExchange")
    public DirectExchange authExchange() {
        return new DirectExchange("user.createAuth");
    }

    @Bean
    public Queue queue() {
        return new Queue("user.createAuth.requests");
    }

    @Bean
    public Binding binding(DirectExchange authExchange,
                           Queue queue) {
        return BindingBuilder.bind(queue)
                .to(authExchange)
                .with("createAuth");
    }

    @Bean
    public Receiver receiver() {
        return new Receiver();
    }

}
