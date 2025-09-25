# CV Generator - Backend API

Bu proje, CV oluşturma uygulamasının backend servisidir. Kullanıcı yönetimi, CV verilerinin işlenmesi, PDF oluşturma ve diğer sunucu taraflı işlemleri yönetmek için RESTful API sağlar. Spring Boot kullanılarak geliştirilmiştir.

## ✨ Özellikler

- **RESTful API**: Frontend uygulamasıyla iletişim kurmak için standartlaşmış endpoint'ler.
- **Güvenlik**: JSON Web Tokens (JWT) ile kullanıcı kimlik doğrulama ve yetkilendirme.
- **Veri Kalıcılığı**: Spring Data JPA ve PostgreSQL ile verilerin yönetimi.
- **Dosya Yükleme**: Kullanıcı fotoğrafları gibi dosyaları yönetmek için [Cloudinary](https://cloudinary.com/) entegrasyonu.
- **E-posta Servisi**: Kullanıcı kaydı veya bildirimler için e-posta gönderme yeteneği.

## 🚀 Kullanılan Teknolojiler

- **Framework**: [Spring Boot](https://spring.io/projects/spring-boot) 3.3.4
- **Dil**: [Java](https://www.java.com/) 17
- **Veritabanı**: [PostgreSQL](https://www.postgresql.org/)
- **Güvenlik**: [Spring Security](https://spring.io/projects/spring-security) & [JWT](https://jwt.io/)
- **Veri Erişimi**: [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- **Derleme ve Bağımlılık Yönetimi**: [Maven](https://maven.apache.org/)
- **Yardımcı Kütüphaneler**: [Lombok](https://projectlombok.org/), [JJwt](https://github.com/jwtk/jjwt)

## 🔧 Yapılandırma

Projeyi çalıştırmadan önce bazı yapılandırma adımlarını tamamlamanız gerekmektedir.

1.  **Veritabanı Kurulumu**:
    -   Yerel makinenizde bir PostgreSQL veritabanı oluşturun.
    -   Veritabanı adını `cv_generator_db` olarak ayarlayın veya `application.properties` dosyasında `spring.datasource.url` özelliğini kendi veritabanı URL'nizle güncelleyin.

2.  **Ortam Değişkenleri (Environment Variables)**:
    -   Projenin `application.properties` dosyasında hassas bilgiler (veritabanı şifresi, JWT anahtarı, e-posta kimlik bilgileri) bulunmaktadır. Güvenlik nedeniyle bu bilgileri doğrudan kodda tutmak yerine ortam değişkenleri olarak ayarlamanız **önemle tavsiye edilir**.

    -   Aşağıdaki değişkenleri sisteminizde veya IDE'nizin çalıştırma yapılandırmasında tanımlayın:

        ```properties
        DB_USERNAME=postgres
        DB_PASSWORD=sizin_veritabani_sifreniz
        JWT_SECRET=guclu_ve_rastgele_bir_jwt_anahtari
        MAIL_USERNAME=mail_adresiniz@gmail.com
        MAIL_PASSWORD=gmail_uygulama_sifreniz
        ```

    -   Ardından `application.properties` dosyasını bu değişkenleri kullanacak şekilde güncelleyin:

        ```properties
        spring.datasource.username=${DB_USERNAME}
        spring.datasource.password=${DB_PASSWORD}
        jwt.key=${JWT_SECRET}
        spring.mail.username=${MAIL_USERNAME}
        spring.mail.password=${MAIL_PASSWORD}
        ```

## 📦 Kurulum ve Başlatma

1.  **Depoyu klonlayın:**
    ```bash
    git clone https://github.com/kullanici-adiniz/proje-adiniz.git
    cd proje-adiniz/case-backend
    ```

2.  **Projeyi Derleyin:**
    Maven projesi olduğu için, bir IDE (IntelliJ IDEA, Eclipse, vb.) üzerinden açtığınızda bağımlılıklar otomatik olarak yüklenecektir. Alternatif olarak komut satırından derleyebilirsiniz:
    ```bash
    ./mvnw clean install
    ```

3.  **Uygulamayı Başlatın:**
    Uygulamayı IDE'niz üzerinden çalıştırabilir veya aşağıdaki komutu kullanabilirsiniz:
    ```bash
    ./mvnw spring-boot:run
    ```
    API, varsayılan olarak `http://localhost:6767` adresinde çalışacaktır.

## 🤝 Katkıda Bulunma

Katkılarınız projeyi daha iyi hale getirmemize yardımcı olur! Lütfen bir "pull request" açmaktan veya "issue" bildirmekten çekinmeyin.
