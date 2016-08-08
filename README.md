# Liikenneviraston LIKE-järjestelmä #

Tämä projekti on LIKE-hankkeen paikkatietoja keräävän sovelluksen backend. Tämä Java SpringBoot-sovellus
vastaanottaa paikka- ja liikkumismuototietoja Android-sovellukselta ja huolehtii niiden tallennuksesta tietokantaan.

## Tietokanta ##
Tietokantana käytetään MariaDb-tietokantamoottoria.
Tietokanta luodaan tiedostosta README_DB.txt löytyvin scriptein. Tämän jälkeen kannasta huolehtii Flyway.
Tietokannan ORM-työkaluna käytetään MyBatis-kirjastoa.

## Käyttäjähallinta ##
Tämä projekti sisältää käyttäjien hallinnan, muttei sinänsä käyttäjien autentikointia tai autorisointia. 
Autentikointi ja autorisointi-tarpeet ovat reittien tarkastelu- ja hallinta-sovelluksilla. Kun uudelta 
käyttäjältä saadaan sijaintitietoa, käyttäjä lisätään sekä järjestelmän tietokantaan, että Apachelle. 
Komento, joka ajetaan löytyy application.properties-tiedostosta. 
