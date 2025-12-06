import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenConsole {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%&*()-_=+[]{};:,.<>?";

    public static String generate(int length, boolean useUpper, boolean useLower, boolean useDigits, boolean useSymbols, boolean avoidAmbiguous) {
        StringBuilder pool = new StringBuilder();
        if (useUpper) pool.append(UPPER);
        if (useLower) pool.append(LOWER);
        if (useDigits) pool.append(DIGITS);
        if (useSymbols) pool.append(SYMBOLS);

        String poolStr = pool.toString();
        if (avoidAmbiguous) {
            poolStr = poolStr.replaceAll("[Il1O0]", ""); // remove ambiguous chars
        }

        if (poolStr.isEmpty()) throw new IllegalArgumentException("Character pool is empty");

        SecureRandom rnd = new SecureRandom();
        StringBuilder pw = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int idx = rnd.nextInt(poolStr.length());
            pw.append(poolStr.charAt(idx));
        }
        return pw.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Password length (e.g. 16): ");
        int len = sc.nextInt();
        sc.nextLine();
        System.out.print("Include uppercase? (y/n): ");
        boolean u = sc.nextLine().trim().equalsIgnoreCase("y");
        System.out.print("Include lowercase? (y/n): ");
        boolean l = sc.nextLine().trim().equalsIgnoreCase("y");
        System.out.print("Include digits? (y/n): ");
        boolean d = sc.nextLine().trim().equalsIgnoreCase("y");
        System.out.print("Include symbols? (y/n): ");
        boolean s = sc.nextLine().trim().equalsIgnoreCase("y");
        System.out.print("Avoid ambiguous chars (Il1O0)? (y/n): ");
        boolean a = sc.nextLine().trim().equalsIgnoreCase("y");

        String pw = generate(len, u, l, d, s, a);
        System.out.println("\nGenerated password:\n" + pw);
        sc.close();
    }
}
