src/
├─ main/
│  └─ java/warehouse/
│     ├─ Main.java
│     ├─ Product.java
│     ├─ ProductFilter.java
│     ├─ ProductRepository.java
│     ├─ InMemoryProductRepository.java
│     ├─ ProductService.java
│     └─ ProductServiceImpl.java
└─ test/
   └─ java/warehouse/
      └─ ProductServiceTest.java


Як запустити

    Клонуй репозиторій:

git clone https://github.com/Lat06/Homework_data.git
cd Homework_data

Скомпілюй та запусти:

mvn compile exec:java -Dexec.mainClass=warehouse.Main

Запусти тести:

    mvn test

📌 Вимоги

    Java 17+
    Maven 3.x

