#  Port and Adapter (Hexagonal Architecture) – Dummy Data ile Basit Java Örneği

Bu örnekte **Port ve Adapter (Hexagonal Architecture)** mimarisini kullanarak **basit bir "Kitap Yönetim Sistemi" (Book Management System)** oluşturacağız.

---

##  Ne Yapacağız?

-  Kitapları listeleyebileceğiz.
-  Dummy verilerle başlangıç yapacağız.
-  Veritabanı yerine **InMemoryBookRepository** (Bellek içi adapter) kullanacağız.
-  API üzerinden kitap ekleyebileceğiz.

---


##  Hexagonal Architecture Yapısı

Aşağıdaki yapı, **Hexagonal Architecture**'ın temel bileşenlerini göstermektedir:

| **Bileşen**         | **Açıklama** |
|---------------------|-------------|
|  **Inbound Adapter**  | `BookRestAdapter (REST API)` → Dış dünyadan gelen istekleri alır. |
|  **Inbound Port**     | `BookServicePort` → İş mantığını yönetir. |
|  **Outbound Port**    | `BookRepositoryPort` → Veri erişimini soyutlar. |
|  **Outbound Adapter** | `InMemoryBookAdapter (Dummy DB)` → Veriyi yönetir. |
|  **Domain Entity**    | `Book` → İş mantığının merkezinde yer alır. |

---

##  Proje Yapısı

```bash
book-management/
├── src/
│   ├── main/
│   │   ├── java/com/example/bookmanagement/
│   │   │   ├── domain/          #  Çekirdek iş mantığı
│   │   │   │   ├── Book.java
│   │   │   │   ├── BookRepositoryPort.java
│   │   │   ├── application/      #  İş Mantığı Servisleri (Inbound Port)
│   │   │   │   ├── BookServicePort.java
│   │   │   ├── infrastructure/   #  Outbound Adaptörler
│   │   │   │   ├── InMemoryBookAdapter.java
│   │   │   ├── api/              #  Inbound Adaptörler
│   │   │   │   ├── BookRestAdapter.java
│   │   │   ├── BookManagementApplication.java  #  Spring Boot Starter
│── pom.xml  #  Maven Build Config
