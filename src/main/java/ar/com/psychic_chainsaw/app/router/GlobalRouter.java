package ar.com.psychic_chainsaw.app.router;

import ar.com.psychic_chainsaw.app.handler.RecordHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class GlobalRouter {

    @Bean
    public RouterFunction<ServerResponse> router(
           @Qualifier("recordRouter") RouterFunction<ServerResponse> recordRouter
    ) {
        return RouterFunctions
                .nest(RequestPredicates.path("/record")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), recordRouter);
    }

    @Bean
    @Qualifier("recordRouter")
    public RouterFunction<ServerResponse> recordRouter(
            RecordHandler recordHandler
    ) {
        return RouterFunctions
                .route(RequestPredicates.method(HttpMethod.POST), recordHandler::save)
                .andRoute(RequestPredicates.method(HttpMethod.GET), recordHandler::getAll);
    }

}
