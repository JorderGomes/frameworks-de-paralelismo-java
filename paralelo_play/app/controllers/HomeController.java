package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.mvc.*;
import scala.concurrent.ExecutionContextExecutor;
import services.HomeServiceImpl;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final WSClient ws;
    private final ExecutionContextExecutor exec;
    private HomeServiceImpl homeService = new HomeServiceImpl();

    @Inject
    public HomeController(WSClient ws, ExecutionContextExecutor exec) {
        this.ws = ws;
        this.exec = exec;
    }

    public Result index() {
        return ok("String");
    }

    public CompletionStage<Result>  paralelos()  {
        homeService.setAtributes(this.ws, exec);
        return homeService.chamadasParalelas().thenApply(
                s -> {
                    return ok(Json.toJson(s));
                }
        );
    }

}
