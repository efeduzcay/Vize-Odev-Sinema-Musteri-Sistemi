import java.util.Scanner;

public class SinemaSistemi {

    static final int MAX_FILM = 10;
    static final int MAX_MUSTERI = 20;

    static String[] filmler = new String[MAX_FILM];
    static int[] filmSureleri = new int[MAX_FILM];
    static String[] filmTurleri = new String[MAX_FILM];
    static int filmIndex = 0;

    static String[] musteriler = new String[MAX_MUSTERI];
    static String[] musteriMailleri = new String[MAX_MUSTERI];
    static int musteriIndex = 0;

    // Biletler: [musteriIndex] -> filmIndex
    static int[] biletler = new int[MAX_MUSTERI];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Başlangıçta tüm biletleri -1 olarak ayarlıyoruz
        for (int i = 0; i < biletler.length; i++) {
            biletler[i] = -1;
        }

        while (true) {
            menuGoster();
            System.out.print("Seçim: ");
            int secim = input.nextInt();
            input.nextLine(); // satır atla

            switch (secim) {
                case 1 -> filmEkle(input);
                case 2 -> musteriEkle(input);
                case 3 -> biletKaydet(input);
                case 4 -> biletListele();
                case 5 -> {
                    System.out.println("Çıkılıyor...");
                    return;
                }
                default -> System.out.println("Geçersiz seçim!");
            }
        }
    }

    static void menuGoster() {
        System.out.println("\n--- MENÜ ---");
        System.out.println("1. Film Ekle");
        System.out.println("2. Müşteri Ekle");
        System.out.println("3. Müşterinin İzleyeceği Film");
        System.out.println("4. Biletleri Listele");
        System.out.println("5. Çıkış");
    }

    static void filmEkle(Scanner input) {
        if (filmIndex >= MAX_FILM) {
            System.out.println("Film kapasitesi doldu!");
            return;
        }

        System.out.print("Film Adı: ");
        filmler[filmIndex] = input.nextLine();
        System.out.print("Film Süresi (dk): ");
        filmSureleri[filmIndex] = input.nextInt();
        input.nextLine(); // satır atla
        System.out.print("Film Türü: ");
        filmTurleri[filmIndex] = input.nextLine();

        System.out.println("Film eklendi.");
        filmIndex++;
    }

    static void musteriEkle(Scanner input) {
        if (musteriIndex >= MAX_MUSTERI) {
            System.out.println("Müşteri kapasitesi doldu!");
            return;
        }

        System.out.print("Müşteri Adı: ");
        musteriler[musteriIndex] = input.nextLine();
        System.out.print("Müşteri Email: ");
        musteriMailleri[musteriIndex] = input.nextLine();

        System.out.println("Müşteri eklendi.");
        musteriIndex++;
    }

    static void biletKaydet(Scanner input) {
        if (filmIndex == 0 || musteriIndex == 0) {
            System.out.println("Lütfen önce film ve müşteri ekleyin.");
            return;
        }

        System.out.println("\n--- Müşteriler ---");
        for (int i = 0; i < musteriIndex; i++) {
            System.out.println(i + ". " + musteriler[i]);
        }
        System.out.print("Müşteri No: ");
        int mNo = input.nextInt();

        if (mNo < 0 || mNo >= musteriIndex) {
            System.out.println("Geçersiz müşteri!");
            return;
        }

        System.out.println("\n--- Filmler ---");
        for (int i = 0; i < filmIndex; i++) {
            System.out.println(i + ". " + filmler[i] + " - " + filmTurleri[i] + " (" + filmSureleri[i] + " dk)");
        }
        System.out.print("Film No: ");
        int fNo = input.nextInt();

        if (fNo < 0 || fNo >= filmIndex) {
            System.out.println("Geçersiz film!");
            return;
        }

        biletler[mNo] = fNo;
        System.out.println("Bilet kaydedildi.");
    }

    static void biletListele() {
        System.out.println("\n--- Bilet Listesi ---");
        for (int i = 0; i < musteriIndex; i++) {
            System.out.print(musteriler[i] + " (" + musteriMailleri[i] + ") -> ");
            int fNo = biletler[i];
            if (fNo != -1) {
                System.out.println(filmler[fNo] + " - " + filmTurleri[fNo]);
            } else {
                System.out.println("Henüz film seçilmemiş.");
            }
        }
    }
}
