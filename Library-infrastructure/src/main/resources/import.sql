INSERT INTO LIBRARY(ID_LIBRARY,address_Postal_Code,address_Number,address_Street, address_City, library_Director_Nom,library_Director_Prenom, LIBRARY_TYPE) values ('LA75011', 75011,88, 'rue de Picpus', 'Paris', 'Jo', 'Barr', 'Publique');
INSERT INTO LIBRARY(ID_LIBRARY,address_Postal_Code,address_Number,address_Street, address_City, library_Director_Nom,library_Director_Prenom, LIBRARY_TYPE) values ('LA75012', 75012,88, 'rue de Paris', 'Paris', 'Helene', 'Barr', 'Publique');
INSERT INTO LIBRARY(ID_LIBRARY,address_Postal_Code,address_Number,address_Street, address_City, library_Director_Nom,library_Director_Prenom, LIBRARY_TYPE) values ('LA75013', 75013,88, 'rue de Lille', 'Paris', 'François', 'Barr', 'Nationale');
INSERT INTO LIBRARY(ID_LIBRARY,address_Postal_Code,address_Number,address_Street, address_City, library_Director_Nom,library_Director_Prenom, LIBRARY_TYPE) values ('LA75014', 75014,88, 'rue de Lyon', 'Paris', 'Helene', 'Barr', 'Scolaire');
INSERT INTO LIBRARY(ID_LIBRARY,address_Postal_Code,address_Number,address_Street, address_City, library_Director_Nom,library_Director_Prenom, LIBRARY_TYPE) values ('LA75015', 75015,88, 'rue de Toto', 'Paris', 'Toto', 'to', 'Universitaire');
INSERT INTO LIBRARY(ID_LIBRARY,address_Postal_Code,address_Number,address_Street, address_City, library_Director_Nom,library_Director_Prenom, LIBRARY_TYPE) values ('LA75016', 75016,88, 'rue de Titi', 'Paris', 'Titi', 'ti', 'Association');

INSERT INTO BOOK(ID_BOOK,AUTHOR,GENRE,ISBN,NOMBER_PAGE,TITLE) values(1,'EMILE ZOLA','Literraire','ISNFR254ZZ',150,'Germinal');
INSERT INTO BOOK(ID_BOOK,AUTHOR,GENRE,ISBN,NOMBER_PAGE,TITLE) values(2,'VICTOR HUGO','Literraire','ISNFR888ZZ',250,'Les misérales');

INSERT INTO LIBRARY_BOOKS values ('LA75012',1);
INSERT INTO LIBRARY_BOOKS values ('LA75012',2);

