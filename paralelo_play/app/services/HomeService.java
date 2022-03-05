package services;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public interface HomeService {


    CompletableFuture<List<String>> chamadasParalelas();
}
