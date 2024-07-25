# dorikell

API de gestion des entrées aux événements

## Multi-événement

Permet de garder les informations relatives aux entrées de tous les événements, et d'ainsi garder un historique sur les événements précédents

## Interface avec d'autres modules

Appelle [Kalon](https://github.com/breizhcamp/kalon) pour la synchronisation avec les événements existants

Appelle [Bilhed](https://github.com/breizhcamp/bilhed) pour l'import des billets relatifs à un événement

## Modèle de données 

### Relationnelle - PostgreSQL

Une base de données PostgreSQL est utilisée pour stocker les informations relatives aux bureaux de délivrance des badges (les objets `Desk`) et sur les événements (les objets `Event`)

Le modèle de la base suit le schéma suivant :

![
La base est composée de trois tables.
La première est l'entité "event", qui contient un champ "id" clé primaire, non nullable et numérique & un champ "event_start" nullable et de type date.
La deuxième entité "desk" possède un champ "id" clé primaire, non nullable de type UUID généré par le SGBD; & un champ "name" textuel non nullable.
La troisième table représente les codes des billets pour un bureau à un événement.
Elle possède donc un clé primaire composite, composée de deux clés étrangères.
La première "desk_id" fait référence à la colonne "id" de "desk", la deuxième est de type numérique et fait référence à la colonne "id" de "event".
Elle possède aussi deux champs, non nullables de type numérique, appelés "first_code" et "last_code"
](./docs/images/db_model_v1.png)

### Documentaire - Elasticsearch

La base de données documentaire Elasticsearch permet de faire des recherches sur les champs de documents que l'on définit

Elle sert à contenir les billets des personnes participant aux événements, et de les rechercher rapidement en fonction de leur nom, prénom et entreprise, ou de leur numéro ou code-barre de billet

Le modèle est composé d'un document "person", qui contient les champs relatifs à une personne, c'est-à-dire : 

1. l'id
2. le code-barre 
3. le nom
4. le prénom
5. l'entreprise (champ nullable)
6. le type de badge (Exposant, Staff, Sponsor, ...)
7. le numéro de billet
8. la date de validation du billet (Moment où la personne retire son badge à l'entrée de l'événement, champ mis à null si l'enregistrement n'est pas effectué)
9. l'événement -> sous-document contenant :
   - l'id de l'événement pour lequel le billet est utilisable
   - la date de début de cet événement

## Interface WEB

### HTTP

La description des endpoints HTTP est trouvable dans un [swagger](./docs/open_api/swagger_v1.yml)

### WebSocket

Cette API est aussi un message broker pour des topics WebsSocket : 

- `/topic/checkin` : Le message contient l'id d'une personne à marquer comme validée à l'entrée, et les souscripteurs sont ensuite notifiés du changement
- `/topic/remove` : Le message contient l'id de la personne à retirer, aucun traitement n'est effectué et les souscripteurs sont directement notifiés

## Sécurité

Cette API est sécurisée grâce à [Keycloak]()

### Couche HTTP

L'exécution de requêtes HTTP demande à tout utilisateur d'être authentifié, et nécessite :
- le rôle `dorikell` pour effectuer des requêtes **`GET`** ou des requêtes **`POST`** sur l'endpoint de recherche
- le rôle `admin` pour effectuer des requêtes **`POST`** ou **`DELETE`** sur tous les autres endpoints

### Couche WebSocket

Pour l'instant, l'interface WebSocket n'est pas encore sécurisée
