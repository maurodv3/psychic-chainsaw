package ar.com.psychic_chainsaw.warehouse.router;

import ar.com.psychic_chainsaw.warehouse.handler.RecordHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class GlobalRouter {

    @Bean
    public RouterFunction<ServerResponse> router(
           @Qualifier("recordRouter") RouterFunction<ServerResponse> recordRouter
    ) {
        return RouterFunctions
                .nest(path("/record")
                        .and(accept(APPLICATION_JSON)), recordRouter);
    }

    @Bean
    @Qualifier("recordRouter")
    public RouterFunction<ServerResponse> recordRouter(
            RecordHandler recordHandler
    ) {
        return RouterFunctions
                .route(method(POST), recordHandler::save)
                .andRoute(GET("/{id}"), recordHandler::get)
                .andRoute(method(GET), recordHandler::getAll)
                .andRoute(DELETE("/{id}"), recordHandler::delete);
    }

}
