<h1>System Rezerwacji Hotelowej</h1>
<h3>📝 Opis</h3>
System Rezerwacji Hotelowej to aplikacja konsolowa napisana w Java, umożliwiająca zarządzanie pokojami hotelowymi i rezerwacjami. System oferuje kompleksowe rozwiązanie do obsługi hotelu, włączając w to różne typy pokoi, sezonowe ceny oraz system zniżek.
<h3>🔑 Główne funkcje</h3>
<ul>
<li>Zarządzanie pokojami hotelowymi</li>
<li>System rezerwacji z walidacją dat</li>
<li>Dynamiczne ceny w zależności od sezonu</li>
<li>Zniżki dla dłuższych pobytów</li>
<li>Różne typy pokoi (jednoosobowe, dwuosobowe, apartamenty, rodzinne)</li>
<li>Różne widoki z pokoi (miasto, ogród, basen, parking)</li>
<li>System pięter</li>
<li>Kompleksowa obsługa błędów</li>
</ul>

<h3>📋 Wymagania systemowe</h3>
<ul>
<li>Java SDK 21 lub nowszy</li>
<li>System operacyjny: Windows/Linux/MacOS</li>
</ul>
<h3>🚀 Instalacja i uruchomienie </h3>
<ol>

<li><h4>Sklonuj repozytorium:</h4></li>

git clone https://github.com/twoje-repo/system-rezerwacji-hotelowej.git

<li><h4>Przejdź do katalogu projektu:</h4></li>

cd system-rezerwacji-hotelowej

<li><h4>Skompiluj projekt:</h4></li>

javac kosior/sebastian/*.java

<li><h4>Uruchom aplikację:</h4></li>
java kosior.sebastian.Main
</ol>
<h3>💡 Jak używać</h3>
<ol>
<li><h4>Przeglądanie pokoi</h4></li>
<ul>
<li>Wyświetlanie wszystkich pokoi</li>
<li>Filtrowanie po typie pokoju</li>
<li>Sprawdzanie dostępności</li>
</ul>

<li><h4>Zarządzanie rezerwacjami</h4></li>
<ul>
<li>Tworzenie nowej rezerwacji</li>
<li>Anulowanie rezerwacji</li>
<li>Przeglądanie aktywnych rezerwacji</li>
</ul>

<li><h4>System cenowy</h4></li>
<ul>
<li>Sprawdzanie cen bazowych</li>
<li>Informacje o mnożnikach sezonowych</li>
<li>Kalkulacja zniżek</li>
</ul>
</ol>


<h3>📊 Struktura projektu</h3><br>
src/<br>
├── kosior/sebastian/<br>
│   ├── Main.java            # Punkt wejścia aplikacji<br>
│   ├── Hotel.java           # Główna logika hotelu<br>
│   ├── Room.java            # Klasa pokoju<br>
│   ├── Reservation.java     # Zarządzanie rezerwacjami<br>
│   ├── PaymentSystem.java   # System płatności i cen<br>
│   └── Type.java            # Typy pokoi<br>
<h3>🔄 Obsługa błędów</h3>
<h4>System zawiera kompleksową obsługę błędów dla:</h4>
<ul>
<li>Nieprawidłowych dat rezerwacji</li>
<li>Niedostępnych pokoi</li>
<li>Nieprawidłowych danych wejściowych</li>
<li>Konfliktów rezerwacji</li>
<li>Błędów systemowych</li>
</ul>
<h3>🏷️ Wersjonowanie</h3>
<ul>
<li>Wersja: 1.0.3</li>
<li>Status: Stabilny</li>
<li>Ostatnia aktualizacja: 09.01.2025</li>
</ul>
<h3>👥 Autor</h3>
<ul>
<li>Sebastian Kosior</li>
</ul>