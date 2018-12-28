package ar.com.psychic_chainsaw.app.router;

import ar.com.psychic_chainsaw.app.handler.GlobalHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class GlobalRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GlobalHandler globalHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello")
                        .and(RequestPredicates
                                .accept(MediaType.TEXT_PLAIN)), globalHandler::hello);
    }

}
