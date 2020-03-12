# For A Fistful Of Dollars
Nicolas Béreux
Auguste Jerlström
Nathan Schoech


## Parties du sujet traitées :

Nous avons traité les principales parties du sujet qui permettent de réaliser un projet fonctionnant correctement,
avec les fonctionnalités nécessaires au bon fonctionnement d'une partie entière de Colt Express®, 
à savoir les parties I à V.
Concernant la partie VI, nous avons décidé d'implémenter la récupération des paramètres de jeu dans une fenêtre de dialogue au début de la partie.
Cette fenêtre de dialogue est la sortie standard sur laquelle s'affiche aussi les conséquences des déplacements et des actions des bandits.
Ainsi, au début de la partie, on peut demander aux joueurs combien ils seront, sur un train de quelle taille est-ce qu'ils veulent jouer, etc.


## Choix d'architectures

Tout d'abord, pour représenter notre plateau de jeu, à savoir le train, nous avons décidé de le représenter sous la forme d'un tableau de tableaux de Case.
La classe Case est une classe que nous avons créée, elle représente à la fois les Wagons et les Roof, qui en héritent tout deux.
La classe Character est utilisée pour représenter les différents protagonistes: les Bandits et les Marshall qui en sont des classes filles.

Les premières versions ont été écrites en trinôme lors du TP consacré au projet, afin de partir sur de bonnes bases communes d'organisation de classes.

Ensuite, nous nous sommes séparé le travail de la manière suivante :
    Nicolas : display et core
    Auguste : tests, corrections des bugs et core
    Nathan : core
    
Les différentes parties réalisées par chacun correspondent grosse modo aux différentes branches que nous avons créées depuis la branche master.

## Difficultés rencontrées

La première difficulté a été la prise en main de GitLab, étant donné que nous comptions nous répartir le travail nous souhaitions utiliser
toutes les ressources à notre disposition. En effet, les branches et les commandes pull, push, merge etc s'agissaient d'un environnement complètement nouveau.
De plus, à plusieurs reprises, GitLab nous a posé des problèmes lors de son utilisation : impossibilité de se connecter, suppression de merge...
Ensuite, comme précisé précédemment, dès lors que nous avions des difficultés concernant le code ou autre, nous nous entraidions dans la mesure du possible.

Prise en main de l'interface graphique et des JFrames pour le display, utilisation massive de méthodes inconnues jusque là.

Enfin, concernant les difficultés purement de code, nous avons eu du mal à expliquer et corriger certaines erreurs de tests concernant certaines méthodes,
en particulier la méthode shoot, ou encore sur l'utilisation correcte du bouton Action, qui devrait permettre de lancer chaque nouvelle Action de chaque Bandit.
De plus, nous avons des problèmes avec les déplacements du Marshall pendant la partie.

## Code emprunté ailleurs

Aucun ! Toutes les lignes de commande ont été écrites par nous 3.