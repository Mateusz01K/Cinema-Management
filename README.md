# Cinema-Management

#CRUD
<br>
Aplikacja do logowania, kupowania biletów, zarządzania seansami.
<br><br>
Dodawanie, Aktualizacja, Usuwanie danych seance.
<br><br>
Aplikacja jest w trakcie tworzenia, brakuje jeszcze sporo podstawowych funkcji, wyjątków, testów oraz wygląd strony jest podstawowy tylko do testowania aplikcacji.
<br><br>
1. Cinema:
<br> Wyświetla statystyki kina.
3. Seance:
<br> Tylko 'ADMIN' może dodawać nowe seanse.
4. Tikcet:
<br> Użytkownicy moga kupować bilety. Widzą bilety tylko kupione przez siebie. Mogą usuwać tylko swoje bilety.
5. User:
<br> Każdy użytkownik który się rejestruje ma odrazu przypisaną rolę 'USER'. Jest tylko jeden 'ADMIN'.
6. Security:
<br> Użytkownicy nie mają dostępu do dodawania/aktualizowania/usuwania seansów. Może robić to tylko 'ADMIN'.
7. MySQL:
<br> Wszystkie dane są zawarte w bazie danych.
<br><br>

## Jak uruchomić projekt

### MySQL WorkBench
Dodajemy nowe połączenie<br>
nazwa połącznia: MySQL Cinema<br>
metoda połączenia: Standard(TCP/IP)<br>
ip: 127.0.0.1<br>
port: 3306<br>
username: root<br>
password: root<br>

Dodajemy bazę która jest w projekcie<br>
z folderu "db" dodajemy "cinemalibrary"

### Tworzymy obraz Docker
W terminalu wpisujemy polecenia:<br>
docker pull mysql<br>
docker run --name cinemalibrary-mysql -e MYSQL_ROOT_PASSWROD=root -d -p 3306:3306 mysql

### Uruchamiamy projekt przez termianal w kompilatorze
mvn clean install<br>
mvn spring-boot:run

### W przeglądarce wpisujemy adres URL
http://localhost:8080/cinema/home
