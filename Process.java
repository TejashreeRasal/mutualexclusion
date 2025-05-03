class Process implements Runnable {
    private int id;
    private TokenRing tokenRing;

    public Process(int id, TokenRing tokenRing) {
        this.id = id;
        this.tokenRing = tokenRing; // Assign the tokenRing instance
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Wait for the token
                if (id == tokenRing.getCurrentProcessWithToken().getId()) {
                    enterCriticalSection();
                    passTokenToNextProcess();
                }
                Thread.sleep(500); // Simulate waiting time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void enterCriticalSection() {
        System.out.println("Process " + id + " entered critical section.");
        try {
            Thread.sleep(2000); // Simulate work in critical section
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Process " + id + " left critical section.");
    }

    public void passTokenToNextProcess() {
        int nextIndex = tokenRing.getNextProcessIndex(id);
        tokenRing.passToken(id, nextIndex);
    }
}
