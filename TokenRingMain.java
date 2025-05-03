public class TokenRingMain {
    public static void main(String[] args) {
        // Create a TokenRing with 5 processes
        TokenRing tokenRing = new TokenRing(5);
        tokenRing.startRing();
    }
}

