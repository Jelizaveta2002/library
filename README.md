# Setting up Frontend

For front-end I chose Vue.js. To run frontend you need to open 
file "library_frontend" in Terminal and run "npm run serve" command.

## Back-end

On back-end I made some additional services and controllers,
that allow finding books by specific features (status, title).

In my project I also added pagination (2 books/checkouts) per page.

Both books and checkouts are returned sorted.

It is possible to save book (id is automatically generated, dueDate = localDate + 3months)

Checkouts could be returned in 3 ways (opened (those that are not returned yet), closed(already returned), 
late (those that are over dated))

For getting all late checkouts I in CheckoutRepository I used @Query using native SQL statements.

From front-end I do not use checkout deletion, but I use setDate method to set up returned date for checkout.

## Front-end

On frontend I have 4 main pages.

1. Books: using axios I return all the existed books in alphabetical order.
From this page there is possible to delete particular book, click "more info" to watch
book in a separate page or mark this book as a favourite (it appears in FAVOURITE section).(Used localStorage)

2. Books Options (contains 4 options to work with)
   1. Search by status - leads to separate page where choosing status are returned all the books 
   with chosen status.
   2. Search by title - leads to separate page where it is possible to find book by title (ignored case).
   3. Add book - possible to add a new book to the database.
   4. Books - leads to the page where all books are displayed.

3. Checkouts: contains of 3 options. There you can choose which checkouts you want to see (opened, closed or late)
For each checkout there is a button "more info". When you push this button you can see details about particular
checkout (due date, checkout date ...). Also you can push "return book". If you push it checkout automatically 
gets returnedDate and book status changes to AVAILABLE. It is possible to get info about reserved book from
checkoutView.
4. Favourite books: display all books that are marked as favourite. It is possible to delete book from
favourites and also get more info about this book by pushing "more info".


## Additional Information
Working on frontend I also added modal confirmation dialogues.
For my project I used some materials from one of my university projects: https://github.com/Jelizaveta2002/iti0302-frontend
I also used some tips from https://stackoverflow.com/ and https://chat.openai.com/chat for particular information
(mostly related to HTML and CSS)




