package services;

import play.libs.ws.*;
import scala.concurrent.ExecutionContextExecutor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;


public class HomeServiceImpl implements HomeService {

    private WSClient ws;
    private ExecutionContextExecutor exec;

    private List<String> urls = List.of(
            "https://api.github.com/events",
            "https://api.github.com/repos/torvalds/linux/comments",
            "https://api.github.com/repos/torvalds/linux/commits",
            "https://api.github.com/gitignore/templates",
            "https://api.github.com/repos/levxyca/pandadomalbot/issues",
            "https://api.github.com/events",
            "https://api.github.com/repos/torvalds/linux/comments",
            "https://api.github.com/repos/torvalds/linux/commits",
            "https://api.github.com/gitignore/templates",
            "https://api.github.com/repos/levxyca/pandadomalbot/issues"
    );

    @Override
    public CompletableFuture<List<String>> chamadasParalelas() {
        List<CompletableFuture<String>> executionPromises = new ArrayList<>();
        for (String u: urls){
            CompletionStage<String> resposta = ws
                    .url(u)
                    .get()
                    .thenApply(
                            r -> {
                                return r.getBody();
                            }
                    );
            executionPromises.add(resposta.toCompletableFuture());
        }
        CompletableFuture<Void> joinedPromise = CompletableFuture.allOf(executionPromises.toArray(CompletableFuture[]::new));
        return joinedPromise.thenApply(voit -> executionPromises.stream().map(CompletableFuture::join).collect(Collectors.toList()));

    }

    public void setAtributes(WSClient ws, ExecutionContextExecutor exec){
        this.ws = ws;
        this.exec = exec;
    }

}
