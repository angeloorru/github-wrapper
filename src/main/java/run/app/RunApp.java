package run.app;

import controller.github.MakeGitRequest;

public class RunApp {
    public static void main(String[] args) {
        (new Thread(new MakeGitRequest())).start();
    }
}
