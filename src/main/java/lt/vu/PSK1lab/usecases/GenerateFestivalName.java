package lt.vu.PSK1lab.usecases;

import lt.vu.PSK1lab.interceptors.LoggedInvocation;
import lt.vu.PSK1lab.services.FestivalNameGenerator;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateFestivalName implements Serializable {

    @Inject
    FestivalNameGenerator festivalNameGenerator;

    private CompletableFuture<String> festivalNameGenerationTask = null;

    @LoggedInvocation
    public String generateNewFestivalName() {
        festivalNameGenerationTask = CompletableFuture.supplyAsync(() -> festivalNameGenerator.getName());

        return  "/index.xhtml?faces-redirect=true";
    }

    public boolean isFestivalNameGenerationRunning() {
        return festivalNameGenerationTask != null && !festivalNameGenerationTask.isDone();
    }

    public String getFestivalNameGenerationStatus() throws ExecutionException, InterruptedException {
        if (festivalNameGenerationTask == null) {
            return null;
        } else if (isFestivalNameGenerationRunning()) {
            return "Festival name generation in progress";
        }
        return "Generated festival name: " + festivalNameGenerationTask.get();
    }
}
