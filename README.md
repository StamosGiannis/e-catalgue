Περιγραφή Εφαρμογής

Η εφαρμογή μου είναι ένα ολοκληρωμένο σύστημα ηλεκτρονικού καταλόγου που περιλαμβάνει λειτουργίες authentication και authorization. Με τη χρήση της εφαρμογής, ο διαχειριστής (admin) έχει τη δυνατότητα να διαχειρίζεται έναν ηλεκτρονικό κατάλογο προϊόντων. Συγκεκριμένα, ο admin μπορεί να καταχωρεί, να επεξεργάζεται και να διαγράφει προϊόντα, καθώς και να εμφανίζει έναν κατάλογο όλων των χρηστών και των προϊόντων.Η εφαρμογή περιέχει επίσης μπάρες αναζήτησης που επιτρέπουν την αναζήτηση χρηστών με βάση το επώνυμό τους ή προϊόντων με βάση το όνομά τους.

Οι πελάτες έχουν τη δυνατότητα να πλοηγούνται στον κατάλογο προϊόντων και να βλέπουν τα διαθέσιμα προϊόντα. Η εφαρμογή διασφαλίζει την ασφάλεια των δεδομένων μέσω των μηχανισμών authentication και authorization, επιτρέποντας την πρόσβαση μόνο σε εξουσιοδοτημένους χρήστες.

Επιπλέον, έχω υλοποιήσει κρυπτογράφηση κωδικών για την προστασία των κωδικών πρόσβασης. Παρόλα αυτά, η κρυπτογράφηση έχει προσωρινά απενεργοποιηθεί ώστε να επιτρέπεται η σύνδεση κάποιου ως admin μετά την προσθήκη των στοιχείων του χειροκίνητα στη βάση δεδομένων.

Application Description

My application is a comprehensive e-catalogue system that includes authentication and authorization functionalities. Using the application, the administrator (admin) has the ability to manage an electronic product catalog. Specifically, the admin can add, edit, and delete products, as well as view a list of all users and products.The application also includes search bars that allow searching for users by their last name or products by their name.

Customers can navigate through the product catalog and view the available products. The application ensures data security through authentication and authorization mechanisms, allowing access only to authorized users.

Additionally, I have implemented password encryption to protect user passwords. However, encryption has been temporarily disabled to allow an admin to log in after manually adding their details to the database.

Tech Stacks & Tools Used

Tech Stacks:

    1.Java

    2.MySQL

    3.Spring

    4.SpringBoot

    5.Hibernate

    6.SL4J

    7.Thymeleaf

Some Endpoints to access the functionalities

login user
http://localhost:8080/login

register user
http://localhost:8080/register

admins dashboard
http://localhost:8080/admins/dashboard

get all products
http://localhost:8080/admins/products/get

edit product
http://localhost:8080/admins/products/edit/1

delete product
http://localhost:8080/admins/products/remove/1

search product by product name
http://localhost:8080/admins/products/search?productName=

search customer by lastname
http://localhost:8080/admins/customers/search?lastname=

customers dashboard catalogue
http://localhost:8080/customers/dashboard

search product by product name
http://localhost:8080/customers/search?productName=
